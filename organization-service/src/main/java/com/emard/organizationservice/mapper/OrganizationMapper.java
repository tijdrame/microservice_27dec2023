package com.emard.organizationservice.mapper;

import com.emard.organizationservice.dto.OrganizationDto;
import com.emard.organizationservice.entity.Organization;

public class OrganizationMapper {

    public static OrganizationDto mapToOrganizationDto(Organization organization) {
        return new OrganizationDto(organization.getId(), organization.getOrganizationName(), organization.getOrganizationDescription(),
                organization.getOrganizationCode(), organization.getCreatedDate());
    }

    public static Organization mapToOrganization(OrganizationDto dto) {
        return new Organization(dto.getId(), dto.getOrganizationName(), dto.getOrganizationDescription(),
                dto.getOrganizationCode(), dto.getCreatedDate());
    }

}
