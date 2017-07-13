/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entis.service;

import java.io.IOException;
import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jwt.JWTClaimsSet;
//import com.sun.servicetag.UnauthorizedAccessException;
import entis.People;
import java.security.Principal;
import java.text.ParseException;
import java.util.ArrayList;
import javax.ejb.EJB;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.servlet.Filter;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.ext.Provider;
import net.minidev.json.JSONObject;
import utils.AuthUtils;
import org.joda.time.DateTime;
/**
 *
 * @author Lorena Portillo
 */
@Secured
@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthenticationFilter implements ContainerRequestFilter, ContainerResponseFilter{
 
    @EJB
    private PeopleFacadeREST ejbFacadePeople;
    @EJB
    private RoltipoFacadeREST ejbFacadeRoltipo;
    private static final String EXPIRE_ERROR_MSG = "Token has expired",
            JWT_ERROR_MSG = "Unable to parse JWT",
            JWT_INVALID_MSG = "Invalid JWT token",
            JWT_INVALID_USER = "Usuario no encontrado";
    
    
    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException{
        SecurityContext originalContext = requestContext.getSecurityContext();
        String authHeader = requestContext.getHeaderString("Authorization");
        ResponseBuilder responseBuilder = null;
        Response response = null;
        if (authHeader == null || authHeader.isEmpty()) {
           Authorizer authorizer = new Authorizer("", "",
                    originalContext.isSecure());
            requestContext.setSecurityContext(authorizer);
            throw new IOException(JWT_INVALID_MSG);
        } else { 
            JWTClaimsSet claimSet;
            try {
                claimSet = (JWTClaimsSet) AuthUtils.decodeToken(authHeader);
            } catch (ParseException e) {
                throw new IOException(JWT_ERROR_MSG);
            } catch (JOSEException e) {
                throw new IOException(JWT_INVALID_MSG);
            }

            // ensure that the token is not expired
            if (new DateTime(claimSet.getExpirationTime()).isBefore(DateTime.now())) {
                JSONObject json = new JSONObject();
                json.put("status", "user is not authorized");
                json.put("code", Response.Status.UNAUTHORIZED.getStatusCode());
                responseBuilder = Response.ok(this, MediaType.APPLICATION_JSON);
                response = responseBuilder.status(Response.Status.UNAUTHORIZED).build();
                requestContext.abortWith(response);
            } 
            else {
                try {
                    People user = ejbFacadePeople.find(Integer.parseInt(claimSet.getSubject()));
                   // String rol = ejbFacadeRoltipo.find(user.getIdrol()).getName();
                    Authorizer authorizer = new Authorizer(user.getIdrol().getName(), user.getEmail(),
                            originalContext.isSecure());
                    requestContext.setSecurityContext(authorizer);
                }catch (Exception e) {
                    throw new IOException(JWT_INVALID_USER);
                }
            }
        }


    }  

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext response) throws IOException {
        response.getHeaders().putSingle("Access-Control-Allow-Origin", "*");
        response.getHeaders().putSingle("Access-Control-Allow-Methods", "OPTIONS, GET, POST, PUT, DELETE");
        response.getHeaders().putSingle("Access-Control-Allow-Headers", "Content-Type, Access-Control-Allow-Headers, Authorization ");
    }
    
    public static class Authorizer implements SecurityContext {

        String roles;//Muchos a muchos Roles - Usuarios
        //Roles rol; //Uno a muchos Roles - Usuarios
        String username;
        boolean isSecure;

        public Authorizer(String roles, String username, boolean isSecure) {
            this.roles = roles;
            this.username = username;
            this.isSecure = isSecure;
        }

        @Override
        public Principal getUserPrincipal() {
            return new User(username);
        }

        @Override
        public boolean isUserInRole(String role) {
            //return rol.equals(role); Uno a muchos
            return roles.equals(role);//Muchos a muchos
        }

        @Override
        public boolean isSecure() {
            return isSecure;
        }

        @Override
        public String getAuthenticationScheme() {
            return "JWT Authentication";
        }
    }

    public static class User implements Principal {

        String name;

        public User(String name) {
            this.name = name;
        }

        @Override
        public String getName() {
            return name;
        }
    }

}