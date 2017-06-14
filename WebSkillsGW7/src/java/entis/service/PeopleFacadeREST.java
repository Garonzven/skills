/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entis.service;

import entis.Credentials;
import entis.Log;
import entis.People;
import entis.Roltipo;
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
import java.util.Iterator;
import javax.ws.rs.core.Context;
import javax.servlet.http.HttpServletRequest;
import com.nimbusds.jose.JOSEException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.logging.Logger;
import utils.AuthUtils;
import utils.Token;

/**
 *
 * @author Usuario
 */
@Stateless
@Path("entis.people")
public class PeopleFacadeREST extends AbstractFacade<People> {

    public static final String CLIENT_ID_KEY = "client_id", REDIRECT_URI_KEY = "redirect_uri",
            CLIENT_SECRET = "client_secret", CODE_KEY = "code", GRANT_TYPE_KEY = "grant_type",
            AUTH_CODE = "authorization_code";

    public static final String NOT_FOUND_MSG = "User not found", LOGING_ERROR_MSG = "Wrong email and/or password";
    
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
    public Response login(Credentials user , @Context final HttpServletRequest request) throws JOSEException {
        Response respuesta = null;
        People foundUser;
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        String rol;
        Roltipo roltipo;
        
        Logger logger = Logger.getLogger(getClass().getName());
        try  {            
            
            logger.severe("severe");
            logger.severe("--------------1.......");
            foundUser = findByEmail(user);
            logger.severe("--------------2.......");
           // roltipo = findByRolTipo(foundUser);
            rol = foundUser.getIdrol().getName();
            logger.severe("--------------3.......");
        }
        catch (Exception e) {
            logger.severe("--------------4.......");
            return Response.status(Response.Status.UNAUTHORIZED).entity(gson.toJson(LOGING_ERROR_MSG)).build();
        }
        if (foundUser == null) {
           logger.severe("--------------5.......");
           respuesta= Response.status(Response.Status.UNAUTHORIZED).entity(gson.toJson(NOT_FOUND_MSG)).build();

        } else {
           logger.severe("--------------6.......");
           final Token token = AuthUtils.createToken(request.getRemoteHost(), foundUser, rol);          
           respuesta =  Response.ok().entity(gson.toJson(token)).build();
        }
        logger.severe("--------------7.......");
        return respuesta;
    }
   /* private Roltipo findByRolTipo(People p) throws Exception {
        
       Query query = em.createQuery(
            "SELECT r FROM Roltipo r WHERE  r.idrol  = " + p.getIdrol().getIdrol());
       
       List<Roltipo> lista=query.getResultList();
  
       if (lista.isEmpty()) {
           throw new Exception();
       }
       else return lista.get(0);
    }   */ 
    private People findByEmail(Credentials user) throws Exception {
        // Authenticate against a database, LDAP, file or whatever
        // Throw an Exception if the credentials are invalid
        Logger logger = Logger.getLogger(getClass().getName());
       logger.severe("--------------8.......");
       Query query = em.createQuery(
            "SELECT p FROM People p WHERE  p.email  = '"+user.getEmail()+"' AND p.pasword='"+ user.getPassword() +"'");
       
       List<People> lista=query.getResultList();
       logger.severe("--------------9.......");
       if (lista.isEmpty()) {
           throw new Exception();
       }
       else {
           logger.severe("--------------10.......");
           return lista.get(0); 
       }
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