package com.example.demo.models;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class EntrepreneurSignupForm {
        @NotBlank
        @Size(max = 20)
        private String email;
        @NotBlank
        private String organizationName;
        @NotBlank
        private String type;
}
