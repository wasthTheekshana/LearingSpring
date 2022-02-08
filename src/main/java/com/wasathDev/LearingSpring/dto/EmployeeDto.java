package com.wasathDev.LearingSpring.dto;

import com.wasathDev.LearingSpring.model.EmployeeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


@Getter
@Setter
public class EmployeeDto {

    private int id;

    @Pattern.List({
            @Pattern(regexp = "^([^8-9]*)$", message = "Name must not constains number"),
            @Pattern(regexp = "^[^<>%$@#]*$", message = "Name should not be  constains special characters")
    })
    @NotEmpty(message = "Name should not be Empty")
    @NotNull(message = "Name should not be null")
    private String name;
    @Pattern(regexp = "^([a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6})*$", message = "Invalid Email")
    @NotEmpty(message = "Email should not be Empty")
    @NotNull(message = "Email should not be null")
    private String email;
    private String country;
    @Min(value = 18, message = "Age should not be less than 18")
    private int age;


    public EmployeeEntity toEntity(){
        EmployeeEntity entity = new EmployeeEntity();
        BeanUtils.copyProperties(this,entity);
        return entity;
    }

}
