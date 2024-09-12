package com.programmers.mycoffee.service.admin.impl;

import com.programmers.mycoffee.model.admin.AdminEntity;
import com.programmers.mycoffee.model.admin.JoinDto;
import com.programmers.mycoffee.model.admin.Role;
import com.programmers.mycoffee.repository.admin.AdminRepository;
import com.programmers.mycoffee.service.admin.JoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class JoinServiceImpl implements JoinService {
    private final AdminRepository adminRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    public JoinServiceImpl(AdminRepository adminRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.adminRepository = adminRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public JoinDto joinProcess(JoinDto joinDto) {
        String username = joinDto.username();
        String password = joinDto.password();
        Role role = joinDto.role();

        // 입력값 검증
        if (username == null || username.isEmpty() ||
                password == null || password.isEmpty() ||
                role == null) {
            throw new IllegalArgumentException("모든 필드는 반드시 입력되어야 합니다.");
        }

        Boolean isExit = adminRepository.existsByUsername(username);

        // 이미 존재하는 사용자인 경우
        if (isExit) {
            throw new IllegalStateException("이미 존재하는 사용자명입니다.");
        }

        AdminEntity admin = AdminEntity.builder()
                .username(username)
                .password(bCryptPasswordEncoder.encode(password))
                .role(role)
                .build();

        adminRepository.save(admin);

        return joinDto;
    }
}
