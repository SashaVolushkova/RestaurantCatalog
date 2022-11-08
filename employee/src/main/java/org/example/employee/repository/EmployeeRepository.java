package org.example.employee.repository;

import org.example.employee.model.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {
    @Query("SELECT e FROM EmployeeEntity e JOIN FETCH e.department WHERE e.id = :id")
    Optional<EmployeeEntity> getEmployeeById(@Param("id") Long id);

    @Query("SELECT e FROM EmployeeEntity e JOIN FETCH e.department order by e.name")
    List<EmployeeEntity> getEmployees();
}