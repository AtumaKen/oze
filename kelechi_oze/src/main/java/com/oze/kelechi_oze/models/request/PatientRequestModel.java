package com.oze.kelechi_oze.models.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PatientRequestModel {

    @NotBlank(message = "First name is required")
    @Pattern(regexp = "^[a-zA-Z-'][ ]*$", message = "Enter valid first name")
    private String firstName;


    @Pattern(regexp = "^[a-zA-Z-'][ ]*$", message = "Enter valid last name")
    @NotBlank(message = "Last name is required")
    private String lastName;

    @NotNull(message = "Age is required")
    @Min(value = 0, message = "Age can not be a negative value")
    private Integer age;
}
