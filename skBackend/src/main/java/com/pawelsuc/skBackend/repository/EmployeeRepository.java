package com.pawelsuc.skBackend.repository;

import com.pawelsuc.skBackend.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository <Employee, Long> {
}
