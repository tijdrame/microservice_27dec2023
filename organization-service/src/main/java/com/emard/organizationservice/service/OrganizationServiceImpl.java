package com.emard.organizationservice.service;

import com.emard.organizationservice.dto.OrganizationDto;
import com.emard.organizationservice.entity.Organization;
import com.emard.organizationservice.mapper.OrganizationMapper;
import com.emard.organizationservice.repository.OrganizationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class OrganizationServiceImpl implements OrganizationService {
    private final OrganizationRepository repository;
    @Override
    public OrganizationDto saveOrganization(OrganizationDto organizationDto) {
        return OrganizationMapper.mapToOrganizationDto(repository.save(OrganizationMapper.mapToOrganization(organizationDto)));
    }

    @Override
    public OrganizationDto getOrganizationByCode(String code) {
        Optional<Organization> byOrganizationCode = repository.findByOrganizationCode(code);
        if(byOrganizationCode.isEmpty()) {
            throw new IllegalArgumentException(String.format("Ce code  %s d'Organization n'existe pas.",code));
        }
        return OrganizationMapper.mapToOrganizationDto(byOrganizationCode.get());
    }
}
