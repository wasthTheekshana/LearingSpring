package com.wasathDev.LearingSpring.repository;


import com.wasathDev.LearingSpring.model.OrganizationEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OrganizationRepository extends JpaRepository<OrganizationEntity,Integer> {

}
