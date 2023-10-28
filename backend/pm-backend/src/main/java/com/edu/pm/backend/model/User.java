package com.edu.pm.backend.model;

import com.edu.pm.backend.model.enums.Role;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "users")
@Builder
@AllArgsConstructor
@Slf4j
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    private String username;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column
    private String password;

    @Column
    @Enumerated(EnumType.STRING)
    private List<Role> roles;

    @ManyToOne
    @Nullable
    private Team team;

    @ManyToOne
    @Nullable
    private Project project;

    public void addRole(@NotNull Role role) {
        this.roles.add(role);
    }

    public void removeRole(@NotNull Role role) {
        boolean isRemoved = this.roles.remove(role);
        if (!isRemoved) {
            log.warn("Role {} from user {} was not removed", role, username);
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream().map(item -> new SimpleGrantedAuthority(item.name())).toList();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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
