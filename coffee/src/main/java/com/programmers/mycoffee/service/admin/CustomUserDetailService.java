package com.programmers.mycoffee.service.admin;

import com.programmers.mycoffee.model.admin.AdminEntity;
import com.programmers.mycoffee.repository.admin.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {
    private final AdminRepository adminRepository;

    @Autowired
    public CustomUserDetailService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AdminEntity adminData = adminRepository.findByUsername(username);

        if (adminData != null) {
            return new CustomUserDetails(adminData);
        }
        return null;
    }
}
