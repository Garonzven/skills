/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entis.service;

import entis.People;
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

    @POST
    @Override
    @Consumes(MediaType.APPLICATION_JSON)
    public void create(People entity) {
        super.create(entity);
    }

    @POST
    @Path("login")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public People login(People p) {
       // return "{\"email\":\"Pedro\",\"pasword\":\"sddsds\"}";
       Query query = em.createQuery(
            "SELECT p FROM People p WHERE  p.email  = '"+p.getEmail()+"' AND p.pasword='"+p.getPasword()+"'");
       List<People> results = query.getResultList();
       for (People c : results) {
            p=c;
            break; 
        }
       // p=super.find(new Integer(id));
        return p;
    }
    
    @PUT
   // @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void edit(People entity) {
        super.edit(entity);
        
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
