package com.cardapp.card.security.controller;


import com.cardapp.card.security.dto.RoleRequest;
import com.cardapp.card.security.dto.RoleResponse;
import com.cardapp.card.security.repository.entity.Role;
import com.cardapp.card.security.service.RoleService;
import io.swagger.annotations.Api;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
@Api(tags = "Roles Api", value = "/roles")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleController {

    RoleService roleService;


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RoleResponse> createRole(@Valid @RequestBody final RoleRequest request) {
        final Role role = roleService.save(request.getName());
        RoleResponse response = new RoleResponse();
        response.setId(role.getId());
        response.setName(role.getName());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

}
