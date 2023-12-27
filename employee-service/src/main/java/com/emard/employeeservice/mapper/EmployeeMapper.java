package com.emard.employeeservice.mapper;

import com.emard.employeeservice.dto.EmployeeDto;
import com.emard.employeeservice.entity.Employee;

public class EmployeeMapper {
    public static EmployeeDto mapToEmployeeDto(Employee emp) {
        return new EmployeeDto(emp.getId(), emp.getFirstName(), emp.getLastName(), emp.getEmail(), emp.getDepartmentCode(),
                emp.getOrganizationCode());
    }

    public static Employee mapToEmployee(EmployeeDto dto) {
        return new Employee(dto.getId(), dto.getFirstName(), dto.getLastName(), dto.getEmail(), dto.getDepartmentCode(),
                dto.getOrganizationCode());
    }
}
