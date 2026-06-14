package com.zym.fastplatform.portal.framework.aspect;

import com.zym.fastplatform.common.common.framework.entity.BaseEntity;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.data.jpa.domain.Specification;

/**
 * Portal模块专用的状态过滤切面
 * 自动为所有基于BaseEntity的实体类查询添加 status=1 的条件
 */
//@Aspect
//@Component
public class StatusFilterAspect {

    /**
     * 定义切点：拦截BaseDao及其子类的findAll方法（带Specification参数的版本）
     */
    @Pointcut("execution(* com.zym.fastplatform.common.common.framework.dao.BaseDao+.findAll(org.springframework.data.jpa.domain.Specification, ..))")
    public void baseDaoFindAllWithSpecification() {
    }

    /**
     * 定义切点：拦截BaseDao及其子类的findAll方法（带Specification和Sort参数的版本）
     */
    @Pointcut("execution(* com.zym.fastplatform.common.common.framework.dao.BaseDao+.findAll(org.springframework.data.jpa.domain.Specification, org.springframework.data.domain.Sort))")
    public void baseDaoFindAllWithSpecificationAndSort() {
    }

    /**
     * 定义切点：拦截BaseDao及其子类的findAll方法（带Specification和Pageable参数的版本）
     */
    @Pointcut("execution(* com.zym.fastplatform.common.common.framework.dao.BaseDao+.findAll(org.springframework.data.jpa.domain.Specification, org.springframework.data.domain.Pageable))")
    public void baseDaoFindAllWithSpecificationAndPageable() {
    }

    /**
     * 环绕通知：为查询添加 status=1 的条件
     */
    @Around("baseDaoFindAllWithSpecification() || baseDaoFindAllWithSpecificationAndSort() || baseDaoFindAllWithSpecificationAndPageable()")
    public Object addStatusFilter(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();

        // 获取原始的Specification参数（第一个参数）
        @SuppressWarnings("unchecked")
        Specification<BaseEntity> originalSpec = (args.length > 0 && args[0] instanceof Specification)
                ? (Specification<BaseEntity>) args[0] : null;

        // 创建新的Specification，组合原始条件和status=1条件
        Specification<BaseEntity> newSpec = (Root<BaseEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            Predicate statusPredicate = cb.equal(root.get("status"), (byte) 1);

            if (originalSpec != null) {
                Predicate originalPredicate = originalSpec.toPredicate(root, query, cb);
                if (originalPredicate != null) {
                    return cb.and(originalPredicate, statusPredicate);
                }
            }
            return statusPredicate;
        };

        // 将新的Specification设置为第一个参数
        args[0] = newSpec;

        // 继续执行原始方法
        return joinPoint.proceed(args);
    }
}