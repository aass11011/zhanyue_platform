package com.zym.fastplatform.common.common.framework.dao;

import com.zym.fastplatform.common.common.framework.entity.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseDao <T extends BaseEntity> extends JpaRepository<T,Long>, JpaSpecificationExecutor<T> {
}