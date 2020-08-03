package com.cardapp.card.security.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SignUpRequest {

    @NotEmpty
    String username;

    String email;

    @NotEmpty
    String password;

    @Valid
    List<Long> roles = new ArrayList<>();


}
