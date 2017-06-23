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
 * @author Lorena Portillo
 */
public class AuthUtils {
    private static final JWSHeader JWT_HEADER = new JWSHeader(JWSAlgorithm.HS256);
    private static final String TOKEN_SECRET = "garonzkey**";
    public static final String AUTH_HEADER_KEY = "Authorization";
    
    public static Token createToken(String host, People people, String rol) throws JOSEException {
        

        JWTClaimsSet claim = new JWTClaimsSet();
        claim.setSubject(Integer.toString(people.getIdpeople()));
        claim.setIssueTime(DateTime.now().toDate());
        claim.setExpirationTime(DateTime.now().plusDays(1).toDate());
        claim.setCustomClaim("user", people.getName() + " " + people.getLastname());
        claim.setCustomClaim("rol", rol);

        JWSSigner signer = new MACSigner(TOKEN_SECRET);
        SignedJWT jwt = new SignedJWT(JWT_HEADER, claim);
        jwt.sign(signer);
        return new Token(jwt.serialize());
    }

    public static String getSerializedToken(String authHeader) {
        return authHeader.split(" ")[0];
    }
    
    public static ReadOnlyJWTClaimsSet decodeToken(String authHeader) throws ParseException, JOSEException {
        SignedJWT signedJWT = SignedJWT.parse(getSerializedToken(authHeader));
        if (signedJWT.verify(new MACVerifier(TOKEN_SECRET))) {

            return signedJWT.getJWTClaimsSet();
        } else {
            throw new JOSEException("Signature verification failed");
        }
    }
}
