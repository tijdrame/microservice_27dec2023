package com.emard.organizationservice.controller;

import com.emard.organizationservice.dto.OrganizationDto;
import com.emard.organizationservice.service.OrganizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/organizations")
public class OrganizationController {
    private final OrganizationService service;
    @PostMapping
    public ResponseEntity<OrganizationDto> saveOrganization(@RequestBody OrganizationDto organizationDto) {
        return new ResponseEntity<>(service.saveOrganization(organizationDto), HttpStatus.CREATED);
    }

    @GetMapping("{organizationCode}")
    public ResponseEntity<OrganizationDto> getOrganizationByCode(@PathVariable String organizationCode) {
        return new ResponseEntity<>(service.getOrganizationByCode(organizationCode), HttpStatus.OK);
    }
}
