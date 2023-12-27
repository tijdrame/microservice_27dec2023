package com.emard.employeeservice.service;

import com.emard.employeeservice.dto.APIResponseDto;
import com.emard.employeeservice.dto.EmployeeDto;

public interface EmployeeService {
    EmployeeDto saveEmployee(EmployeeDto employeeDto);
    APIResponseDto getEmployeeById(Long id);
}
