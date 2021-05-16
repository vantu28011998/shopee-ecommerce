package com.nh7.ecommerce.model.jwt;

import com.nh7.ecommerce.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class JwtResponse implements Serializable {

    private static final long serialVersionUID = -8091879091924046844L;
    private final UserDto userDto;
    private final String jwttoken;
}
