package com.zym.fastplatform.common.common.framework.service.impl;

import com.zym.fastplatform.common.common.util.JpaUtils;
import com.zym.fastplatform.common.common.framework.annotation.FuzzyQuery;
import com.zym.fastplatform.common.common.framework.convert.BaseConvertMapper;
import com.zym.fastplatform.common.common.framework.dao.CommonDao;
import com.zym.fastplatform.common.common.framework.entity.BaseDTO;
import com.zym.fastplatform.common.common.framework.entity.BaseEntity;
import com.zym.fastplatform.common.common.framework.service.CommonService;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.lang.reflect.Field;
import java.util.List;

public class CommonServiceImpl<DAO extends CommonDao<T>,T extends BaseEntity,CVT extends BaseConvertMapper<T,VO,DTO>,DTO extends BaseDTO,VO>  implements CommonService<VO,DTO> {
    @Autowired
    protected DAO dao;
    @Autowired
    protected CVT convertMapper;


    @Override
    public VO findById(String id) {
        return convertMapper.toVO(dao.findById(id).orElse(null));
    }

    @Override
    public void save(DTO entity) {
        T t = convertMapper.toEntity(entity);
        dao.save(t);
    }

    @Override
    public void delBatch(String[] ids) {
        dao.deleteAllById(List.of(ids));
    }

    @Override
    public Page<VO> findAll(Integer page, Integer size, String sort, DTO condition) {
        T entity = convertMapper.toEntity(condition);
        Specification<T> spec = buildSpecification(entity);
        Sort sortObj = JpaUtils.parseSort( sort);
        PageRequest pageRequest = PageRequest.of(page, size,sortObj);
        return convertMapper.toVOPage(dao.findAll(spec,pageRequest));
    }

    protected Specification<T> buildSpecification(T entity) {
        return (Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            Predicate predicate = cb.conjunction();

            // 遍历实体类的所有字段
            Class<?> clazz = entity.getClass();
            while (clazz != null && clazz != Object.class) {
                Field[] fields = clazz.getDeclaredFields();
                for (Field field : fields) {
                    field.setAccessible(true);
                    try {
                        Object value = field.get(entity);
                        if (value != null) {
                            // 检查是否为集合类型，跳过集合字段以避免Hibernate 6错误
                            Class<?> fieldType = field.getType();
                            if (!isCollectionType(fieldType)) {
                                // 检查字段是否有FuzzyQuery注解
                                if (field.isAnnotationPresent(FuzzyQuery.class)) {
                                    // 对于有FuzzyQuery注解的字段，使用like进行模糊查询
                                    if(field.getAnnotation(FuzzyQuery.class).ignore()){
                                        continue;
                                    }
                                    predicate = cb.and(predicate, cb.like(root.get(field.getName()), "%" + value + "%"));
                                } else {
                                    // 对于没有FuzzyQuery注解的字段，使用精确匹配
                                    predicate = cb.and(predicate, cb.equal(root.get(field.getName()), value));
                                }
                            }
                        }
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
                clazz = clazz.getSuperclass();
            }

            return predicate;
        };
    }

    /**
     * 检查是否为集合类型
     */
    private boolean isCollectionType(Class<?> clazz) {
        return java.util.Collection.class.isAssignableFrom(clazz) ||
                java.util.Map.class.isAssignableFrom(clazz) ||
                java.util.Set.class.isAssignableFrom(clazz) ||
                List.class.isAssignableFrom(clazz);
    }
}
