package com.emard.organizationservice.service;

import com.emard.organizationservice.dto.OrganizationDto;

import java.util.Optional;

public interface OrganizationService {
    OrganizationDto saveOrganization(OrganizationDto organizationDto);
    OrganizationDto getOrganizationByCode(String code);
}
