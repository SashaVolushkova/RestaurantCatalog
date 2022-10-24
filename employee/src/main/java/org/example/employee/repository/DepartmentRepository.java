package org.example.employee.repository;

import org.example.employee.model.DepartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<DepartmentEntity, Long> {

    @Query("SELECT d FROM DepartmentEntity d LEFT JOIN FETCH d.parentDepartment LEFT JOIN FETCH d.chief " +
            "WHERE d.id = :id")
    Optional<DepartmentEntity> getDepartmentById(@Param("id") Long id);

    @Query("SELECT d FROM DepartmentEntity d LEFT JOIN FETCH d.parentDepartment LEFT JOIN FETCH d.chief")
    List<DepartmentEntity> getDepartments();
}