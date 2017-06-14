/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.ReadOnlyJWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import entis.Credentials;
import entis.People;
import java.text.ParseException; 
import java.util.Arrays;
import org.joda.time.DateTime;
/**
 *
 * @author Usuario
 */
public class AuthUtils {
    private static final JWSHeader JWT_HEADER = new JWSHeader(JWSAlgorithm.HS256);
    private static final String TOKEN_SECRET = "garonzkey**";
    public static final String AUTH_HEADER_KEY = "Authorization";
    
    public static Token createToken(String host, People people, String rol) throws JOSEException {
        
        System.err.println("--------------11.......");
        JWTClaimsSet claim = new JWTClaimsSet();
        System.err.println("--------------12.......");
        claim.setSubject(Integer.toString(people.getIdpeople()));
        System.err.println("--------------13.......");
        claim.setIssueTime(DateTime.now().toDate());
        System.err.println("--------------14.......");
        claim.setExpirationTime(DateTime.now().plusDays(1).toDate());
        System.err.println("--------------15.......");
        claim.setCustomClaim("user", people.getName() + " " + people.getLastname());
        System.err.println("--------------16......");
        claim.setCustomClaim("rol", rol);
        System.err.println("--------------17.......");
        JWSSigner signer = new MACSigner(TOKEN_SECRET);
        System.err.println("--------------18.......");
        SignedJWT jwt = new SignedJWT(JWT_HEADER, claim);
        System.err.println("--------------19.......");
        jwt.sign(signer);
        System.err.println("--------------20.......");
        return new Token(jwt.serialize());
    }

    public static String getSerializedToken(String authHeader) {
        return authHeader.split(" ")[1];
    }
    
    public static ReadOnlyJWTClaimsSet decodeToken(String authHeader) throws ParseException, JOSEException {
        SignedJWT signedJWT = SignedJWT.parse(getSerializedToken(authHeader));
        System.err.println("--------------21.......");
        if (signedJWT.verify(new MACVerifier(TOKEN_SECRET))) {
            System.err.println("--------------22.......");
            return signedJWT.getJWTClaimsSet();
        } else {
            System.err.println("--------------23.......");
            throw new JOSEException("Signature verification failed");
        }
    }
}
