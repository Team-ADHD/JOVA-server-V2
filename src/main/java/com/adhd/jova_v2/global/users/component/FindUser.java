package com.adhd.jova_v2.global.users.component;

import com.adhd.jova_v2.global.users.dto.UserDto;
import com.adhd.jova_v2.global.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FindUser {

    private final UserRepository userRepository;

    public boolean isExistUser(String email) {
        return userRepository.existsByEmail(email);
    }

    public UserDto findUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(UserDto::fromEntity)
                .orElse(null);
    }

    public UserDto findUserByUuid(String uuid) {
        return userRepository.findByUUID(uuid)
                .map(UserDto::fromEntity)
                .orElse(null);
    }
}
