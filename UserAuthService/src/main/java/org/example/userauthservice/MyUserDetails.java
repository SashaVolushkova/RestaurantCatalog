//package org.example.userauthservice;
//
//import org.example.userauthservice.model.RolesEntity;
//import org.example.userauthservice.model.UsersEntity;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.List;
//
//public class MyUserDetails implements UserDetails {
//    private final UsersEntity entity;
//
//    public MyUserDetails(UsersEntity entity) {
//        this.entity = entity;
//    }
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        List<GrantedAuthority> list = new ArrayList<>();
//        for (RolesEntity role : entity.getRoles()) {
//            list.add(new SimpleGrantedAuthority(role.getName()));
//        }
//        return list;
//    }
//
//    @Override
//    public String getPassword() {
//        return entity.getPassword();
//    }
//
//    @Override
//    public String getUsername() {
//        return entity.getEmail();
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        //TODO: add expiration date in DB
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        //TODO: add lock flag in DB
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        //TODO: add password expirition date
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        //TODO: add lock flag in DB
//        return true;
//    }
//}
