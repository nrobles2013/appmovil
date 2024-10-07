package com.popularsafi.security;

import com.popularsafi.model.Usuario;
import com.popularsafi.repo.IUserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {

    private final IUserRepo repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("loadUserByUsername"+ username);
        Usuario usuario = repo.findByUsername(username.trim());
        System.out.println("loadUserByUsername"+ usuario.getUsername());
        if(usuario == null){
            throw new UsernameNotFoundException(String.format("Usuario not exists", username));
        }

        List<GrantedAuthority> roles = new ArrayList<>();
        usuario.getRoles().forEach(rol -> {
            roles.add(new SimpleGrantedAuthority(rol.getName()));
        });

        UserDetails ud = new org.springframework.security.core.userdetails.User(usuario.getUsername(), usuario.getPassword(), roles);
        return ud;
    }

    public UserDetails loadUserByTipoUsername(String cptipoparticipe, String username) throws UsernameNotFoundException {
        System.out.println("loadUserByTipoUsername"+ username);
        Usuario usuario = repo.findByUsernameLike(cptipoparticipe.trim(),username.trim());
        if(usuario == null){
            throw new UsernameNotFoundException(String.format("Usuario not exists", username));
        }

        List<GrantedAuthority> roles = new ArrayList<>();
        usuario.getRoles().forEach(rol -> {
            roles.add(new SimpleGrantedAuthority(rol.getName()));
        });

        UserDetails ud = new org.springframework.security.core.userdetails.User(usuario.getUsername(), usuario.getPassword(), roles);
        return ud;
    }

}
