package com.zym.fastplatform.common.common.framework.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface CommonDao<T> extends JpaRepository<T,String>, JpaSpecificationExecutor<T> {
}
