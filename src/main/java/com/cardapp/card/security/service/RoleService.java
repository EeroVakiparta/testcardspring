package com.cardapp.card.security.service;


import com.cardapp.card.security.repository.entity.Role;

public interface RoleService {

    Role save(final String name);
}
