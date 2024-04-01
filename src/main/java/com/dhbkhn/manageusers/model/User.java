package com.dhbkhn.manageusers.model;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.dhbkhn.manageusers.enums.Role;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "user")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Enumerated(value = EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "user")
    private List<Token> tokens;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<Token> getTokens() {
        return tokens;
    }

    public void setTokens(List<Token> tokens) {
        this.tokens = tokens;
    }

    public static Object withDefaultPasswordEncoder() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'withDefaultPasswordEncoder'");
    }
}

// import java.util.Collection;
// import java.util.List;

// import org.springframework.security.core.GrantedAuthority;
// import org.springframework.security.core.authority.SimpleGrantedAuthority;
// import org.springframework.security.core.userdetails.UserDetails;

// import com.dhbkhn.manageusers.enums.Role;

// import jakarta.persistence.Entity;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
// import jakarta.persistence.Id;
// import jakarta.persistence.Table;

// @Entity
// @Table(name = "user")
// public class User implements UserDetails {
// @Id
// @GeneratedValue(strategy = GenerationType.IDENTITY)
// private int id;
// private String name;
// private String address;
// private String email;
// private String password;
// private Role role;

// public User() {
// }

// public User(int id, String name, String address, String email, String
// password, Role role) {
// super();
// this.id = id;
// this.name = name;
// this.address = address;
// this.email = email;
// this.password = password;
// this.role = role;

// }

// @Override
// public Collection<? extends GrantedAuthority> getAuthorities() {
// // TODO Auto-generated method stub

// if (role == Role.USER) {
// return List.of(new SimpleGrantedAuthority(Role.USER.name()));
// } else if (role == Role.MERCHANT) {
// return List.of(new SimpleGrantedAuthority(Role.MERCHANT.name()));
// }
// return List.of(new SimpleGrantedAuthority(Role.ADMIN.name()));
// }

// @Override
// public String getUsername() {
// // TODO Auto-generated method stub
// return email;
// }

// @Override
// public boolean isAccountNonExpired() {
// // TODO Auto-generated method stub
// return true;
// }

// @Override
// public boolean isAccountNonLocked() {
// // TODO Auto-generated method stub
// return true;
// }

// @Override
// public boolean isCredentialsNonExpired() {
// // TODO Auto-generated method stub
// return true;
// }

// @Override
// public boolean isEnabled() {
// // TODO Auto-generated method stub
// return true;
// }

// public int getId() {
// return id;
// }

// public void setId(int id) {
// this.id = id;
// }

// public String getName() {
// return name;
// }

// public void setName(String name) {
// this.name = name;
// }

// public String getAddress() {
// return address;
// }

// public void setAddress(String address) {
// this.address = address;
// }

// public String getEmail() {
// return email;
// }

// public void setEmail(String email) {
// this.email = email;
// }

// public String getPassword() {
// return password;
// }

// public void setPassword(String password) {
// this.password = password;
// }

// public Role getRole() {
// return role;
// }

// public void setRole(Role role) {
// this.role = role;
// }

// @Override
// public String toString() {
// return "{" +
// " id='" + getId() + "'" +
// ", name='" + getName() + "'" +
// ", address='" + getAddress() + "'" +
// ", email='" + getEmail() + "'" +
// ", password='" + getPassword() + "'" +
// "}";
// }

// }
