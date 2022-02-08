package com.wasathDev.LearingSpring.repository;


import com.wasathDev.LearingSpring.model.EmployeeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Integer> {

    //JPA Query
    Optional<EmployeeEntity> findByName(String name);
    Optional<EmployeeEntity> findByIdAndOrganizationId(int id,int organizationsId);

    Optional<EmployeeEntity> findByNameAndCountry(String name, String country);

    List<EmployeeEntity> findByCountry(String country);
//    Page<EmployeeEntity> findByCountry(String country);

    //JPQL Query
    @Query(value = "SELECT e FROM EmployeeEntity e WHERE e.name =:name And e.country =:country")
    Optional<EmployeeEntity> selectByNameAndCountry(String name, String country);

    //Native Query
    @Query(value = "SELECT * FROM employee e WHERE e.name =:name", nativeQuery = true)
    Optional<EmployeeEntity> searchByName(String name);

    @Query(value = "SELECT * FROM employee e WHERE e.name =:name And e.country =:country", nativeQuery = true)
    Optional<EmployeeEntity> searchByNameAndCountry(String name, String country);

    @Query(value = "SELECT e.name As employeeName, o.name As organizationName FROM employee e INNER  JOIN organization o ON e.Organization_id = o.id", nativeQuery = true)
    List<EmployeeAndOrganization> searchByEmployeeAndOrganization(String name);

    interface EmployeeAndOrganization {

        String getEmployeeName();

        String getOrganizationName();

    }

    //Pagination


}
