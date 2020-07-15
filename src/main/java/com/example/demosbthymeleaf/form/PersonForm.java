package com.example.demosbthymeleaf.form;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PersonForm {
    private String firstName;
    private String lastName;
    private Integer age;
}
