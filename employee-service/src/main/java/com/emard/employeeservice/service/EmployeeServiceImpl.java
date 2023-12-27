package com.emard.employeeservice.service;

import com.emard.employeeservice.dto.APIResponseDto;
import com.emard.employeeservice.dto.DepartmentDto;
import com.emard.employeeservice.dto.EmployeeDto;
import com.emard.employeeservice.dto.OrganizationDto;
import com.emard.employeeservice.entity.Employee;
import com.emard.employeeservice.mapper.EmployeeMapper;
import com.emard.employeeservice.repository.EmployeeRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository repository;
    //private final RestTemplate restTemplate;
    private final WebClient webClient;
    //private final APIClient apiClient;

    @Override
    public EmployeeDto saveEmployee(EmployeeDto dto) {
        Employee emp = EmployeeMapper.mapToEmployee(dto);
        emp = repository.save(emp);
        return EmployeeMapper.mapToEmployeeDto(emp);
    }

    //@CircuitBreaker(name = "${spring.application.name}", fallbackMethod = "getDefaultDepartment")
    //@Retry(name = "${spring.application.name}", fallbackMethod = "getDefaultDepartment")
    @Override
    public APIResponseDto getEmployeeById(Long id) {
        log.info("inside getEmployeeById() method");
        Employee emp = repository.findById(id).get();

        //restTemplate
        /*ResponseEntity<DepartmentDto> responseEntity = restTemplate.getForEntity(
                "http://localhost:8080/api/departments/"+emp.getDepartmentCode(), DepartmentDto.class);
        DepartmentDto departmentDto = responseEntity.getBody();*/

        //WebClient
        DepartmentDto departmentDto = webClient.get()
                .uri("http://localhost:8080/api/departments/"+emp.getDepartmentCode())
                .retrieve()
                .bodyToMono(DepartmentDto.class)
                .block();
        OrganizationDto organizationDto = webClient.get()
                .uri("http://localhost:8083/api/organizations/"+emp.getOrganizationCode())
                .retrieve()
                .bodyToMono(OrganizationDto.class)
                .block();
        //FeignClient
        //DepartmentDto departmentDto = apiClient.getDepartment(emp.getDepartmentCode());

                EmployeeDto employeeDto = EmployeeMapper.mapToEmployeeDto(emp);
        APIResponseDto apiResponseDto = new APIResponseDto(employeeDto, departmentDto, organizationDto);

        return apiResponseDto;
    }

    public APIResponseDto getDefaultDepartment(Long id, Exception e){
        log.info("inside getDefaultDepartment() method");
        Employee emp = repository.findById(id).get();

        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setDepartmentName("R&D Department");
        departmentDto.setDepartmentCode("RD001");
        departmentDto.setDepartmentDescription("Research and developpement Department");

        EmployeeDto employeeDto = EmployeeMapper.mapToEmployeeDto(emp);
        APIResponseDto apiResponseDto = new APIResponseDto(employeeDto, departmentDto, null);

        return apiResponseDto;
    }
}
