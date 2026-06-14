package com.zym.fastplatform.common.system.dao;

import com.zym.fastplatform.common.common.framework.dao.BaseDao;
import com.zym.fastplatform.common.system.entity.SysDepartment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysDepartmentDao extends BaseDao<SysDepartment> {
    @Query(value = "WITH RECURSIVE cte AS (SELECT t1.* FROM sys_department t1 WHERE t1.id = :deptId \n" +
            "UNION ALL SELECT t2.* FROM sys_department t2 INNER JOIN cte ON t2.parent_id = cte.id)\n" +
            "SELECT * FROM cte",nativeQuery = true)
    List<SysDepartment> findAllChildDept(Long deptId);
}