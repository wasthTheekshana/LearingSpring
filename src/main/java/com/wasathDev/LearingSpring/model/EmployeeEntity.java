package com.wasathDev.LearingSpring.model;

import com.wasathDev.LearingSpring.dto.EmployeeDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;


@Getter
@Setter
@Table(name = "employee", uniqueConstraints = @UniqueConstraint(name = "uk_email", columnNames = "email"))
public class EmployeeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String email;
    private String country;
    private int age;

    @Column(name = "organization_id")
    private int organizationId;


    public EmployeeDto toDto(){
        EmployeeDto dto = new EmployeeDto();
        BeanUtils.copyProperties(this,dto);
        return dto;
    }
}
