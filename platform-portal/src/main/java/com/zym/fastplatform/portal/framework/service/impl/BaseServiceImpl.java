package com.zym.fastplatform.portal.framework.service.impl;

import com.zym.fastplatform.framework.annotation.FuzzyQuery;
import com.zym.fastplatform.framework.annotation.Url;
import com.zym.fastplatform.framework.convert.BaseConvertMapper;

import com.zym.fastplatform.framework.dao.BaseDao;
import com.zym.fastplatform.framework.entity.BaseDTO;
import com.zym.fastplatform.framework.entity.BaseEntity;
import com.zym.fastplatform.framework.utils.StringUtils;
import com.zym.fastplatform.portal.framework.service.BaseService;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;

import static com.zym.fastplatform.common.util.JpaUtils.parseSort;
@Service
public abstract class BaseServiceImpl<D extends BaseDao<T>,T extends BaseEntity,CVT extends BaseConvertMapper<T,VO,DTO>,DTO extends BaseDTO,VO> implements BaseService<T,VO,DTO> {
    @Autowired
    protected D dao;
    @Autowired
    protected CVT convertMapper;
    @Value("${environment.address}")
    private String address;
    @Override
    public T findById(Long id) {
        T t = dao.findByIdAndStatus(id,1).orElse(null);
        handleUrl(t);
        return t;
    }

    @Override
    public Page<VO> findAll(Integer page, Integer size, String sort, DTO condition) {
        T entity = convertMapper.toEntity(condition);
        Specification<T> spec = buildSpecification(entity);
        Sort sortObj = parseSort(sort);
        PageRequest pageRequest = PageRequest.of(page, size,sortObj);
        Page<T> res = dao.findAll(spec,pageRequest);
        res.getContent().forEach(this::handleUrl);
        return convertMapper.toVOPage(res);
    }

    @Override
    public List<VO> findAll(String sort, DTO condition) {
        T entity = convertMapper.toEntity(condition);
        Specification<T> spec = buildSpecification(entity);
        List<T> list = null;
        if(StringUtils.isNotEmpty(sort)){
            Sort sortObj = parseSort(sort);
            list = dao.findAll(spec, sortObj);
        }else{
            list = dao.findAll(spec);
        }
        list.forEach(this::handleUrl);
        return convertMapper.toVOList(list);
    }

    private void handleUrl(Object item) {
        try {
            for (Field field : item.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                Object value = field.get(item);
                if(field.isAnnotationPresent(Url.class)){
                    if(field.getAnnotation(Url.class).collection()){
                        List<?> list = (List<?>) value;
                        list.forEach(this::handleUrl);
                    }else if(field.getAnnotation(Url.class).complete()){
                        field.set(item,address+value);
                    }else {
                        String str = value.toString();
                        str = str.replaceAll("src=\"", "src=\"" + address);
                        field.set(item,str);
                    }
                } else if (value != null) {

                }
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public VO findVOById(Long id) {
        T t = dao.findByIdAndStatus(id,1).orElse(null);
        handleUrl(t);
        return convertMapper.toVO(t);
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
            predicate = cb.and(predicate, cb.equal(root.get("status"), 1));
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
