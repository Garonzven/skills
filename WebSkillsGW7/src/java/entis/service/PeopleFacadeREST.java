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
import javax.ws.rs.core.Context;
import javax.servlet.http.HttpServletRequest;
import com.nimbusds.jose.JOSEException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.logging.Logger;
import utils.AuthUtils;
import utils.Token;
import utils.Utils;

/**
 *
 * @author Lorena Portillo
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
        entity.setName(Utils.capitalize(entity.getName()));
        entity.setLastname(Utils.capitalize(entity.getLastname()));
        entity.setEmail(entity.getEmail().toLowerCase());
        entity.setLocation(Utils.capitalize(entity.getLocation()));
        entity.setSkillandlevel(entity.getSkillandlevel().toLowerCase());
        entity.setJobtitle(Utils.capitalize(entity.getJobtitle()));
        entity.setIschangepassword('T');
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
        Response respuesta;
        People foundUser = null;
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        String rol = "";
        Roltipo roltipo;
        String info="" ;
       
        Logger logger = Logger.getLogger(getClass().getName());
        try{            
            if (user!=null) {
                user.setEmail(user.getEmail().toLowerCase());
                foundUser = findByEmail(user);
                rol = foundUser.getIdrol().getName();
            }
            else info = "not user input";
        }
        catch (Exception e) {
            logger.severe("not user input");
            return Response.status(Response.Status.UNAUTHORIZED).entity(gson.toJson(LOGING_ERROR_MSG)).build();
        }
        if (foundUser == null) {
           logger.severe("not user found");
           respuesta= Response.status(Response.Status.UNAUTHORIZED).entity(gson.toJson(NOT_FOUND_MSG)).build();

        } else {
           final Token token = AuthUtils.createToken(request.getRemoteHost(), foundUser, rol);          
           respuesta =  Response.ok().entity(gson.toJson(token)).build();
        }
        return respuesta;
    }

    private People findByEmail(Credentials user) throws Exception {
        Logger logger = Logger.getLogger(getClass().getName());
       Query query = em.createQuery(
            "SELECT p FROM People p WHERE  p.email  = '"+user.getEmail().toLowerCase()+"' AND p.password='"+ user.getPassword() +"'");
       
       List<People> lista=query.getResultList();
       if (lista.isEmpty()) {
           throw new Exception();
       }
       else {
           return lista.get(0); 
       }
    }
    
    private People findByEmail(String email) {
       Logger logger = Logger.getLogger(getClass().getName());
       Query query = em.createQuery(
            "SELECT p FROM People p WHERE  p.email  = '"+email.toLowerCase()+"'");
       
       List<People> lista=query.getResultList();
       if (!lista.isEmpty()) {
           return lista.get(0); 
       }
       else return null;
    }
    
    @POST
    @Path("getuser")
    @Secured
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public People login(People p) {
        People user = findByEmail(p.getEmail().toLowerCase());
        return user;
    }
    
    
    @PUT
    @Secured
    @Consumes(MediaType.APPLICATION_JSON)
    public void edit(People entity) {
        super.edit(entity);
       
    }
    
    @PUT
    @Secured
    @Path("recovery")
    @Consumes(MediaType.APPLICATION_JSON)
    public void recovery(People entity) {
        People user = findByEmail(entity.getEmail());
        if (user != null){
              user.setIschangepassword('T');
              user.setPassword(Utils.getCadenaAlfanumAleatoria(6));
              edit(user);
              Utils util = new Utils();
              util.enviarCorreo(entity, 2);
        }
    }
    
    @PUT
    @Secured
    @Path("reset")
    @Consumes(MediaType.APPLICATION_JSON)
    public void reset(Credentials user) throws Exception { 
        People foundUser;
        try{            
            if (user!=null) {
                user.setEmail(user.getEmail().toLowerCase());
                foundUser = findByEmail(user);
                foundUser.setPassword(user.getPassword());
                foundUser.setIschangepassword('F');
                edit(foundUser);
            }
        }
        catch (Exception e) {
            throw new Exception();
        }
        
    }
    
    @Secured
    @DELETE
    public void remove(People entity) {
       entity.setIschangepassword('D');
       edit(entity);
    }

    @POST
    @Secured
    @Path("find")
    @Produces(MediaType.APPLICATION_JSON)
    public People find(People entity) {
        return super.find(entity.getIdpeople());
    }

    @GET
    @Override
    @Secured
    @Produces(MediaType.APPLICATION_JSON)
    public List<People> findAll() {
        List<People> lista;
        lista = super.findAll();
        for (People p : lista){
            if ((p.getIschangepassword() != null) && (p.getIschangepassword().compareTo('D')==0)){
                lista.remove(p);
            }
        }
        int count = lista.size();
        return lista;
    }

    @GET
    @Secured
    @Path("{from}/{to}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<People> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Secured
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(findAll().size());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}