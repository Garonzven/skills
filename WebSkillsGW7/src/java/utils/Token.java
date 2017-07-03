/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

/**
 *
 * @author Lorena Portillo
 */
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;

public class Token {

    String token;
    Date expiration;

    public Token(@JsonProperty("token") String token) {
        this.token = token;  
    }

    public Token(@JsonProperty("token") String token, @JsonProperty("expiration") Date expiration) {
        this.token = token;
        this.expiration = expiration;
    }
    public void setExpiration(Date expiration) {
        this.expiration = expiration;
    }
    public String getToken() {
        return token;
    }
    public Date getExpiration() {
        return expiration;
    }
    
}