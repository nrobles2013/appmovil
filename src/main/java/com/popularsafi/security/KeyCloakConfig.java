package com.popularsafi.security;

import jakarta.ws.rs.client.ClientBuilder;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;


public class KeyCloakConfig {
    public static Keycloak keycloak = null;
    public final static String serverUrl= "https://segurfce.popular-safi.com";
    public final static String realm="popularsafi";
    public final static String client_id="appsafimovil";
   //public final static String clientSecret="rlh2O3zU2a9EwQ8UxhKTmZlyaOzEiEgZ" ;  //test

   public final static String clientSecret="Zg6jElxU3lPRdeRGN8QlKmJw3B4eSGdg"; //dev
        public final static String correo = "10348298";
    public final static String password = "12345678";

    public KeyCloakConfig() {
    }

    public static Keycloak getInstance(){
        if (keycloak == null){

            keycloak= KeycloakBuilder.builder()
                    .serverUrl(serverUrl)
                    .realm(realm)
                    .grantType(OAuth2Constants.PASSWORD)
                    .username(correo)
                    .password(password)
                    .clientId(client_id)
                    .clientSecret(clientSecret)
                    .resteasyClient(ClientBuilder.newBuilder().build())
                    .build();
        }
        return keycloak;
    }
}
