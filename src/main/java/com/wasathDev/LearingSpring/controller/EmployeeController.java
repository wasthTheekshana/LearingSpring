package com.wasathDev.LearingSpring.controller;

import com.wasathDev.LearingSpring.dto.CustomPageDto;
import com.wasathDev.LearingSpring.dto.EmployeeDto;
import com.wasathDev.LearingSpring.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/organizations")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    @PostMapping(value = "/{organizationsId}/employees")
    public ResponseEntity<EmployeeDto> save(@PathVariable(value = "organizationsId") int organizationsId,
                                             @Valid @RequestBody EmployeeDto dto) {
        return employeeService.save(organizationsId, dto);
    }

    @GetMapping(value = "/{organizationsId}/employees/{employeeId}")
    public ResponseEntity<EmployeeDto> get(
            @PathVariable(value = "organizationsId") int organizationsId,
            @PathVariable(value = "employeeId") int employeeId
    ) {
        return employeeService.findByIdAndOrganizationId(employeeId,organizationsId);
    }

    @PutMapping(value = "/{organizationsId}/employees")
    public ResponseEntity<EmployeeDto> update(
            @PathVariable(value = "organizationsId") int organizationsId,
            @RequestBody @Valid EmployeeDto dto
    ) {
        return employeeService.update(organizationsId,dto);
    }

    @GetMapping(value = "/{organizationsId}/employees")
    public ResponseEntity<EmployeeDto> findByName(
            @PathVariable(value = "organizationsId") int organizationsId,
            @RequestParam(value = "name") String employeeName
    ) {
        return employeeService.findByName(employeeName);
    }


    @GetMapping(value = "/{organizationsId}/employees/country ")
    public ResponseEntity<List<EmployeeDto>> findByCountry(
            @PathVariable(value = "organizationsId") int organizationsId,
            @RequestParam(value = "name") String CountryName
    ) {
        return employeeService.findByCountry(CountryName);
    }

//localhost:8080/organization/1/employess/pagination?page=0&size=5
    @GetMapping(value = "/{organizationsId}/employees/pagination")
    public ResponseEntity<CustomPageDto<EmployeeDto>> findAllWithPagination(
            @PathVariable(value = "organizationsId") int organizationsId,
            @RequestParam(value = "page") int page,
            @RequestParam(value = "size") int size
    ) {
        return employeeService.findAllWithPagination(page, size,organizationsId);
    }
}
