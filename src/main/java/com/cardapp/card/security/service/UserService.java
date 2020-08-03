package com.cardapp.card.security.service;


import com.cardapp.card.security.dto.SignUpRequest;

public interface UserService {

    void save(final SignUpRequest request);
}
