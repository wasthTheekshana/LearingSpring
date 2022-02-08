package com.wasathDev.LearingSpring.service;

import com.wasathDev.LearingSpring.dto.CustomPageDto;
import com.wasathDev.LearingSpring.dto.EmployeeDto;
import com.wasathDev.LearingSpring.model.EmployeeEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;


public interface EmployeeService {
    ResponseEntity<EmployeeDto> save(int organizationId, EmployeeDto dto);

    ResponseEntity<EmployeeDto> update(int organizationId, EmployeeDto dto);

    ResponseEntity<EmployeeDto> findByName(String name);

    ResponseEntity<EmployeeDto> findByIdAndOrganizationId(int id, int organizationsId);

    ResponseEntity<List<EmployeeDto>> findByCountry(String name);

    ResponseEntity<CustomPageDto<EmployeeDto>> findAllWithPagination(int page, int size,int organizationsId);


}
