package com.example.backend;

import com.example.backend.exceptions.InvalidPrincipal;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.keycloak.representations.AccessToken;

import java.security.Principal;

public final class Utilities {

    private Utilities() {}

    public static Long getUserDbIdFromPrincipal(Principal principal) throws InvalidPrincipal {
        if (!(principal instanceof KeycloakAuthenticationToken)) {
            throw new InvalidPrincipal("Invalid principal (not a keycloak auth token)");
        }
        KeycloakPrincipal<KeycloakSecurityContext> kc = (KeycloakPrincipal<KeycloakSecurityContext>)
                ((KeycloakAuthenticationToken) principal).getPrincipal();
        AccessToken token = kc.getKeycloakSecurityContext().getToken();
        if (token == null) {
            throw new InvalidPrincipal("Invalid token");
        }
        if (token.getOtherClaims().containsKey("db_id"))
            return Long.valueOf((Integer) token.getOtherClaims().get("db_id"));
        throw new InvalidPrincipal("Cannot retrieve id from the token");
    }
}
