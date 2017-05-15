/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entis.service;

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
import utils.Utils;

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
   // @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void edit(People entity) {
        super.edit(entity);
       
    }
    
    @PUT
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

    @DELETE
    public void remove(People entity) {
        super.remove(find(entity));
    }

    @POST
    @Path("find")
    @Produces(MediaType.APPLICATION_JSON)
    public People find(People entity) {
        return super.find(entity.getIdpeople());
    }

    @GET
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
