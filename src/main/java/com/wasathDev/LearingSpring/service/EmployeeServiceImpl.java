package com.wasathDev.LearingSpring.service;

import com.wasathDev.LearingSpring.dto.CustomPageDto;
import com.wasathDev.LearingSpring.dto.EmployeeDto;
import com.wasathDev.LearingSpring.exception.DataNotFoundException;
import com.wasathDev.LearingSpring.exception.DuplicateDataFoundException;
import com.wasathDev.LearingSpring.model.EmployeeEntity;
import com.wasathDev.LearingSpring.repository.EmployeeRepository;
import com.wasathDev.LearingSpring.repository.OrganizationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class EmployeeServiceImpl implements EmployeeService {

    //extranl configuration
    @Value("${error.message.not.found}")
    private String messageNotFound;

    private final EmployeeRepository employeeRepository;
    private final OrganizationRepository organizationRepository;

    //complie time eke objext hadenwa
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, OrganizationRepository organizationRepository) {
        this.employeeRepository = employeeRepository;
        this.organizationRepository = organizationRepository;
    }


    @Override
    public ResponseEntity<EmployeeDto> save(int organizationId, EmployeeDto dto) {
//       employee dto eki entity eki athre data maru wenn thmi meka hdnne
        if (!organizationRepository.existsById(organizationId)) {
            log.error(messageNotFound);
            throw new DataNotFoundException("Organization not found", String.valueOf(organizationId));
        }
//            organizationRepository.findById(organizationId).orElseThrow(()->
//                    new DataNotFoundException("Organization not found", String.valueOf(organizationId)));

        try {

            EmployeeEntity entity = new EmployeeEntity();
            BeanUtils.copyProperties(dto, entity);
            entity.setOrganizationId(organizationId);

            employeeRepository.save(entity);

        } catch (DataIntegrityViolationException e) {
            log.error(e.getLocalizedMessage());
            throw new DuplicateDataFoundException("Duplicate data found", dto.getEmail());
        }
//        catch (Exception e) {
//            log.error(e.getLocalizedMessage());
//            throw new GeneralException("General error", e.getLocalizedMessage());
//        }

        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @Override
    public ResponseEntity<EmployeeDto> findByName(String name) {

        EmployeeEntity byName = employeeRepository.findByName(name).orElseThrow(() ->
                new DataNotFoundException("Organization not found", String.valueOf(name)));


        return ResponseEntity.status(HttpStatus.OK).body(byName.toDto());
    }

    @Override
    public ResponseEntity<EmployeeDto> findByIdAndOrganizationId(int id, int organizationsId) {
        EmployeeEntity byName = employeeRepository.findByIdAndOrganizationId(id, organizationsId)
                .orElseThrow(() -> new DataNotFoundException("Organization not found",
                        String.format("id: %s organizationsId: %s", id, organizationsId)));

        return ResponseEntity.status(HttpStatus.OK).body(byName.toDto());

    }

    @Override
    public ResponseEntity<List<EmployeeDto>> findByCountry(String name) {
        List<EmployeeDto> employeeDtos = employeeRepository.findByCountry(name).stream().map(EmployeeEntity::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(employeeDtos);
    }

    @Override
    public ResponseEntity<EmployeeDto> update(int organizationId, EmployeeDto dto) {

        employeeRepository.findByIdAndOrganizationId(dto.getId(), organizationId)
                .orElseThrow(() -> new DataNotFoundException("Organization not found",
                        String.format("id: %s organizationsId: %s", dto.getId(), organizationId)));

        log.info("Setting New Data To existing employee");
        EmployeeEntity entity = dto.toEntity();
        entity.setOrganizationId(organizationId);
              entity = employeeRepository.save(entity);
        return ResponseEntity.status(HttpStatus.OK).body(entity.toDto());
    }


    @Override
    public ResponseEntity<CustomPageDto<EmployeeDto>> findAllWithPagination(int page, int size,int organizationsId) {

        Pageable pageable = PageRequest.of(page,size, Sort.by("name").ascending());

        CustomPageDto<EmployeeDto> customPageDto = new CustomPageDto<>();

        Page<EmployeeEntity> employeeEntityPage = employeeRepository.findAll(pageable );

        BeanUtils.copyProperties(employeeEntityPage,customPageDto);
      customPageDto.setData(employeeEntityPage.stream().map(EmployeeEntity::toDto).collect(Collectors.toList()));

        return ResponseEntity.status(HttpStatus.OK).body(customPageDto);
    }
}
