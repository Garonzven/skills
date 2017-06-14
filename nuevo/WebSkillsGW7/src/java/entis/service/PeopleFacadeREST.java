/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entis.service;

import entis.Credencials;
import entis.Log;
import entis.People;
import entis.Skillpeople;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import utils.Utils;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Iterator;

/**
 *
 * @author Usuario
 */
@Stateless
@Path("entis.people")
public class PeopleFacadeREST extends AbstractFacade<People> {

    @PersistenceContext(unitName = "WebSkillsGW7PU")
    private EntityManager em;

    public PeopleFacadeREST() {
        super(People.class);
    }
    public void registrar(People entity, String accion){
        Log registro = new Log();
        registro.setIdpeople(entity);
        registro.setDatetimelog(new Date(System.currentTimeMillis()));
        registro.setActionlog(accion);
        em.persist(registro);
        
    }

    @POST
    @Secured
    @Override
    @Consumes(MediaType.APPLICATION_JSON)
    public void create(People entity) {
        super.create(entity);
        registrar(entity, "Create User "+ entity.getName()+ " " + entity.getLastname()+ " by SuperAdmin");
        Utils util = new Utils();
        util.enviarCorreo(entity, 1);   
      
    }
    @POST
    @Path("login")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response authenticateUser(Credencials credencials){

        String username = credencials.getUsername();
        String password = credencials.getPassword();

        try {

            // Authenticate the user using the credentials provided
            authenticate(username, password);

            // Issue a token for the user
            String token = createJWT(username);

            // Return the token on the response
            return Response.ok(token).build();

        } catch (Exception e) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }      
    }
    
    
    private void authenticate(String username, String password) throws Exception {
        // Authenticate against a database, LDAP, file or whatever
        // Throw an Exception if the credentials are invalid
       Query query = em.createQuery(
            "SELECT p FROM People p WHERE  p.email  = '"+username+"' AND p.pasword='"+ password +"'");
       
       List<People> lista=query.getResultList();
  
       if (lista.isEmpty()) {
           throw new Exception();
       }
    }
    private String createJWT(String id, String issuer, String subject, long ttlMillis) {
 
    //The JWT signature algorithm we will be using to sign the token
    SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
 
    long nowMillis = System.currentTimeMillis();
    Date now = new Date(nowMillis);
 
    //We will sign our JWT with our ApiKey secret
    byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(apiKey.getSecret());
    Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
 
    //Let's set the JWT Claims
    JwtBuilder builder = Jwts.builder().setId(id)
                                .setIssuedAt(now)
                                .setSubject(subject)
                                .setIssuer(issuer)
                                .signWith(signatureAlgorithm, signingKey);
 
    //if it has been specified, let's add the expiration
    if (ttlMillis >= 0) {
    long expMillis = nowMillis + ttlMillis;
        Date exp = new Date(expMillis);
        builder.setExpiration(exp);
    }
 
    //Builds the JWT and serializes it to a compact, URL-safe string
    return builder.compact();
}
    
    
    @POST
    @Path("login2")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public People login(People p) {
       Query query = em.createQuery(
            "SELECT p FROM People p JOIN FETCH p.skillpeopleCollection i WHERE  p.email  = '"+p.getEmail()+"' AND p.pasword='"+p.getPasword()+"'");
    
       List<People> lista=query.getResultList();
 
        for (People c: lista) {

       // System.out.println(e.getNombre());
            Collection<Skillpeople> skillpeopleCollection= c.getSkillpeopleCollection();
           /* for (Skillpeople i :skillpeopleCollection) {
                 System.out.println(i.getLevel());
            }*/
            p=c;
            break; 
        }
        return p;
    }
    
    
    @PUT
    @Secured
   // @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void edit(People entity) {
        super.edit(entity);
       
    }
    
    @PUT
    @Secured
    @Path("recovery")
    @Consumes(MediaType.APPLICATION_JSON)
    public void recovery(People entity) {
        Query query = em.createQuery(
                "SELECT s FROM People s WHERE s.email = '" + entity.getEmail()+ "'");
        List<People> results = query.getResultList();
        for (People c : results) {
              entity = c;
              entity.setPasword(Utils.getCadenaAlfanumAleatoria(6));
              //em.persist(entity);
              edit(entity);
              Utils util = new Utils();
              util.enviarCorreo(entity, 2);
              break; 
        }
        
    }
    @Secured
    @DELETE
    public void remove(People entity) {
        super.remove(find(entity));
    }

    @POST
    @Secured
    @Path("find")
    @Produces(MediaType.APPLICATION_JSON)
    public People find(People entity) {
        return super.find(entity.getIdpeople());
    }

    @GET
    @Secured
    @Override
    @Produces(MediaType.APPLICATION_JSON)
    public List<People> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<People> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
