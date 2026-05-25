package com.seek_with_sight.infrastructure.adapter.out.security;

import com.seek_with_sight.infrastructure.adapter.out.persistence.user.repository.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserJpaRepository userJpaRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        var user = userJpaRepository.findByEmailIgnoreCase(email)
                .orElseThrow(() -> new UsernameNotFoundException(email));

        var authorities = new HashSet<GrantedAuthority>();

        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName().name()));

            role.getPermissions().forEach(permission -> new SimpleGrantedAuthority(permission.getName()));
        });

        return new User(
                user.getEmail(),
                user.getPassHash(),
                authorities
        );
    }
}
