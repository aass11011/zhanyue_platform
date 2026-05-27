package com.zym.fastplatform.framework.dao;

import com.zym.fastplatform.framework.entity.NoStatusBaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface NoStatusBaseDao <T extends NoStatusBaseEntity> extends JpaRepository<T,Long>, JpaSpecificationExecutor<T> {
}
