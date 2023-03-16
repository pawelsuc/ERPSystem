package com.pawelsuc.skBackend.controller;

import com.pawelsuc.skBackend.dto.EmployeeDto;
import com.pawelsuc.skBackend.entity.Employee;
import com.pawelsuc.skBackend.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeRepository employeeRepository;


    @PostMapping("/employees")
    EmployeeDto newEmployee(@RequestBody EmployeeDto newEmployee) {
        return EmployeeDto.of(employeeRepository.save(Employee.of(newEmployee)));
    }

    @GetMapping("/employees")
    List<EmployeeDto> listEmployees() {
        return employeeRepository.findAll()
                .stream()
                .map(EmployeeDto::of)
                .collect(Collectors.toList());
    }
    @DeleteMapping("/employees")
    ResponseEntity deleteEmployee(@RequestBody Long idEmployee) {
        employeeRepository.deleteById(idEmployee);
        return ResponseEntity.ok().build();
    }

}
