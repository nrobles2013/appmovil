package com.popularsafi.service.impl;

import com.popularsafi.dto.UsuarioDTO;
import com.popularsafi.model.NuevoUsuario;
import com.popularsafi.model.Role;
import com.popularsafi.model.Usuario;
import com.popularsafi.repo.IUserRepo;
import com.popularsafi.security.KeyCloakConfig;
import com.popularsafi.service.IKeyCloakService;
import jakarta.ws.rs.core.Response;
import org.apache.http.auth.Credentials;
import org.keycloak.admin.client.CreatedResponseUtil;
import org.keycloak.admin.client.resource.RealmResource;

import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.popularsafi.security.KeyCloakConfig.keycloak;

@Service
public class KeyCloakServiceImpl implements IKeyCloakService {

    @Override
    public boolean addUser(Usuario user) throws Exception {
        boolean rpta;
        RealmResource realmResource = KeyCloakConfig.getInstance().realm(KeyCloakConfig.realm);
        UsersResource usersResource = realmResource.users();
        System.out.println("paso por aacass 100"+user.getUsername());
        List<UserRepresentation> lista = usersResource.search(user.getUsername().trim(),true);
        System.out.println("paso por aacass 200"+user.getUsername());
        rpta = lista.isEmpty();
        System.out.println("paso por aacass 300"+lista.size());
        if (rpta){
            UserRepresentation ur = new UserRepresentation();
            System.out.println("paso USUERNAM"+user.getUsername());
            ur.setUsername(user.getUsername());
            ur.setCredentials(Collections.singletonList(generaPassword(user.getPassword())));
            ur.setFirstName(user.getNombre());
            ur.setLastName(user.getApellido());
            ur.setEmail(user.getEmail());
            ur.setEnabled(true);
            ur.setEmailVerified(true);
            Response response = usersResource.create(ur);
            String userId = CreatedResponseUtil.getCreatedId(response);
            System.out.println("paso paso0");
            RoleRepresentation rr= realmResource.roles().get("user").toRepresentation();
            System.out.println("paso paso1");
            usersResource.get(userId).roles().realmLevel().add(Arrays.asList(rr));


        }
        return rpta;
    }

    private CredentialRepresentation generaPassword(String password){
        CredentialRepresentation credential = new CredentialRepresentation();
        credential.setType(CredentialRepresentation.PASSWORD);
        credential.setValue(password);
        return credential;
    }


    public boolean updateUser22(Usuario user) throws Exception {
        boolean rpta;

        System.out.println("por aqui"+user.getUsername()+ " " + user.getPassword());
        Optional<UserRepresentation> user1 =  KeyCloakConfig.getInstance().realm(KeyCloakConfig.realm).users().search(user.getUsername().trim()).stream()
                .filter(u -> u.getUsername().equals(user.getUsername().trim())).findFirst();
        System.out.println("por aqui2");
        if (user1.isPresent()) {
            UserRepresentation userRepresentation = user1.get();
            UserResource userResource = KeyCloakConfig.getInstance().realm(KeyCloakConfig.realm).users().get(userRepresentation.getId());
            Map<String, List<String>> attributes = new HashMap<>();
            System.out.println("por aqui3");
            attributes.put("credentials", Arrays.asList(user.getPassword()));
            System.out.println("por aqui4");
            userRepresentation.setCredentials(Collections.singletonList(generaPassword(user.getPassword())));
            System.out.println("por aqui5");
            //userResource.update(userRepresentation);
            System.out.println("por aqui6");
            rpta= true;
        } else {
            rpta= false;
        }

        return rpta;
    }

        @Override
        public boolean updateUser(Usuario user) throws Exception {
        boolean rpta;

        System.out.println("por aqui"+user.getUsername()+ " " + user.getPassword());
        Optional<UserRepresentation> user1 =  KeyCloakConfig.getInstance().realm(KeyCloakConfig.realm).users().search(user.getUsername().trim()).stream()
                .filter(u -> u.getUsername().equals(user.getUsername().trim())).findFirst();
        System.out.println("por aqui2");
        if (user1.isPresent()) {
            UserRepresentation userRepresentation = user1.get();
            UserResource userResource = KeyCloakConfig.getInstance().realm(KeyCloakConfig.realm).users().get(userRepresentation.getId());
            Map<String, List<String>> attributes = new HashMap<>();
            System.out.println("por aqui3");
            attributes.put("credentials", Arrays.asList(user.getPassword()));
            System.out.println("por aqui4");
            userRepresentation.setCredentials(Collections.singletonList(generaPassword(user.getPassword())));
            System.out.println("por aqui5");
            userResource.update(userRepresentation);
            System.out.println("por aqui6");
            rpta= true;
        } else {
            rpta= false;
        }

        return rpta;
    }
}
