package com.oze.kelechi_oze.models.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StaffRequestModel {

    @NotBlank(message = "First name is required")
    @Pattern(regexp = "([a-zA-Z]{3,30}\\s*)+", message = "Enter valid first name")
    private String firstName;
    @Pattern(regexp = "([a-zA-Z]{3,30}\\s*)+", message = "Enter valid last name")
    @NotBlank(message = "Last name is required")
    private String lastName;

}
