package com.emard.departmentservice.service;

import com.emard.departmentservice.dto.DepartmentDto;
import com.emard.departmentservice.entity.Department;
import com.emard.departmentservice.mapper.DepartmentMapper;
import com.emard.departmentservice.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DepartmentServiceImpl implements DepartmentService{
    private final DepartmentRepository repository;
    @Override
    public DepartmentDto saveDepartment(DepartmentDto departmentDto) {
        Department dpt = DepartmentMapper.mapToDepartment(departmentDto);
        dpt = repository.save(dpt);
        return DepartmentMapper.mapToDepartmentDto(dpt);
    }

    @Override
    public DepartmentDto getDepartmentByCode(String code) {
        Department dpt = repository.findByDepartmentCode(code);
        return DepartmentMapper.mapToDepartmentDto(dpt);
    }
}
