package com.cardapp.card.security.service.impl;


import com.cardapp.card.security.dto.SignUpRequest;
import com.cardapp.card.security.repository.RoleRepository;
import com.cardapp.card.security.repository.UserRepository;
import com.cardapp.card.security.repository.entity.QRole;
import com.cardapp.card.security.repository.entity.User;
import com.cardapp.card.security.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.IterableUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserServiceImpl implements UserService {


    PasswordEncoder encoder;

    RoleRepository roleRepository;

    UserRepository userRepository;


    @Override
    @Transactional(rollbackFor = Throwable.class)
    public void save(final SignUpRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(encoder.encode(request.getPassword()));
        if (CollectionUtils.isNotEmpty(request.getRoles())) {
            user.setRoles(IterableUtils.toList(roleRepository.findAll(QRole.role.id.in(request.getRoles()))));
        }
        userRepository.save(user);
    }
}
