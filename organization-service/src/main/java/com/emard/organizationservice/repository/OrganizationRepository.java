package com.emard.organizationservice.repository;

import com.emard.organizationservice.entity.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrganizationRepository extends JpaRepository<Organization, Long> {
    Optional <Organization> findByOrganizationCode(String code);
}
