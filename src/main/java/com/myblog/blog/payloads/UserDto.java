package com.myblog.blog.payloads;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDto {

    private int id;
    @NotEmpty
    @Size(min = 4, message = "Username must be min of 4 characters")

    private String name;

    @Email(message = "Email address is not valid!")
    @NotEmpty(message = "Email is required!")
    private String email;

    @NotEmpty
    @Size(min = 3, max = 10, message = "Password must be min of 3 chars and m ax of 10 chars !! ")
    private String password;

    @NotEmpty
    private String about;

}
