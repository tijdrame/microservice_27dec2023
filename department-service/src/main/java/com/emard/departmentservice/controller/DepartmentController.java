package com.emard.departmentservice.controller;

import com.emard.departmentservice.dto.DepartmentDto;
import com.emard.departmentservice.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/departments")
@RequiredArgsConstructor
public class DepartmentController {
    private final DepartmentService service;

    @PostMapping
    public ResponseEntity<DepartmentDto> saveDepartment(@RequestBody DepartmentDto departmentDto) {
        DepartmentDto savedDepartmentDto = service.saveDepartment(departmentDto);
        return new ResponseEntity<>(savedDepartmentDto, HttpStatus.CREATED);
    }

    @GetMapping("{department-code}")
    public  ResponseEntity<DepartmentDto> getDepartment(@PathVariable("department-code") String departmentCode) {
        return new ResponseEntity<>(service.getDepartmentByCode(departmentCode), HttpStatus.OK);
    }
}
