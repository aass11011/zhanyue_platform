package com.zym.fastplatform.framework.dao;

import com.zym.fastplatform.framework.entity.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

@NoRepositoryBean
public interface BaseDao <T extends BaseEntity> extends JpaRepository<T,Long>, JpaSpecificationExecutor<T> {

    Optional<T> findByIdAndStatus(Long id, Integer status);
    @Modifying
    @Query("update #{#entityName} e set e.status= :status, e.updatedBy = :updatedBy WHERE e.id = :id")
    void updateStatusById(@Param("id") Long id, @Param("status") Integer status,@Param("updatedBy") String updatedBy);
}