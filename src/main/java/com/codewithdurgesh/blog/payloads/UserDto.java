package com.codewithdurgesh.blog.payloads;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {
    private int id;


    @NotEmpty(message = "give  a proper name")
    private  String name;


    @Email(message = "give a proper email address")
    private  String email;


    @NotEmpty
   @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
            message = "Password must be at least 8 characters long, and include at least one uppercase letter, one lowercase letter, one digit, and one special character."
    )
    private String password;
    @NotEmpty(message = "give an about message")
    private  String  about;






}
