package com.emard.departmentservice.mapper;

import com.emard.departmentservice.dto.DepartmentDto;
import com.emard.departmentservice.entity.Department;

public class DepartmentMapper {
    public static DepartmentDto mapToDepartmentDto(Department dpt) {
        return new DepartmentDto(dpt.getId(), dpt.getDepartmentName(), dpt.getDepartmentDescription(), dpt.getDepartmentCode());
    }

    public static Department mapToDepartment(DepartmentDto departmentDto) {
        return new Department(departmentDto.getId(), departmentDto.getDepartmentName(),
                departmentDto.getDepartmentDescription(), departmentDto.getDepartmentCode());
    }
}
