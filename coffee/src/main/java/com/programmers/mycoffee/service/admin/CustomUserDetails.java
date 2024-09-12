package com.programmers.mycoffee.service.admin;

import com.programmers.mycoffee.model.admin.AdminEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
@Slf4j
public class CustomUserDetails implements UserDetails {
    private final AdminEntity admin;

    public CustomUserDetails(AdminEntity admin) {
        this.admin = admin;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection = new ArrayList<>();
        collection.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return admin.getRole().toString();
            }

        });
        return collection;
    }

    @Override
    public String getPassword() {
        log.info("password {} ", admin.getPassword());
        return admin.getPassword();
    }

    @Override
    public String getUsername() {
        return admin.getUsername();
    }
    @Override
    public boolean isAccountNonExpired() {

        return true;
    }

    @Override
    public boolean isAccountNonLocked() {

        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {

        return true;
    }

    @Override
    public boolean isEnabled() {

        return true;
    }
}
