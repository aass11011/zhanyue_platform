package com.zym.fastplatform.framework.dao;

import com.zym.fastplatform.framework.entity.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

@NoRepositoryBean
public interface BaseDao <T extends BaseEntity> extends JpaRepository<T,Long>, JpaSpecificationExecutor<T> {

    Optional<T> findByIdAndStatus(Long id, Integer status);
}