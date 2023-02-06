package com.mclebtec.demo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {
    private String id;
    @JsonProperty(value = "first_name", required = true)
    private String firstName;
    @JsonProperty(value = "last_name", required = true)
    private String lastName;
    @JsonProperty(value = "email_id", required = true)
    private String emailId;
}
