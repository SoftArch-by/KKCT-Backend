package com.example.demo.models;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class SignupForm {
    @NotBlank
    @Size(max = 20)
    private String name;
    @NotBlank
    @Size(min = 3, max = 20)
    private String username;
//    @NotBlank
//    @Size(max = 60)
//    private String email;
    @NotBlank
    @Size(min = 6, max = 20)
    private String password;
}
