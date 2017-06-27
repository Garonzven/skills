/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entis.service;

import entis.Listado;
import entis.Skill;
import java.util.ArrayList;
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

/**
 *
 * @author Lorena Portillo
 */
@Stateless
@Path("entis.skill")
public class SkillFacadeREST extends AbstractFacade<Skill> {

    @PersistenceContext(unitName = "WebSkillsGW7PU")
    private EntityManager em;

    public SkillFacadeREST() {
        super(Skill.class);
    }

    @POST
    @Override
    @Consumes(MediaType.APPLICATION_JSON)
    public void create(Skill entity) {
        super.create(entity);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void edit(Skill entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Skill find(@PathParam("id") Integer id) {
        return super.find(id);
    }
    
    public Skill findByName(Skill sk) {
        Skill sk1 = null;
        
        Query query = em.createQuery(
                "SELECT s FROM Skill s WHERE s.name = '" + sk.getName().toLowerCase()+ "'");
        List<Skill> results = query.getResultList();
        if (results.size()>0) sk1 = (Skill)results.get(0);
        return sk1;
    }
    
    @GET
    @Override
    @Produces(MediaType.APPLICATION_JSON)
    public List<Skill> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Skill> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("list")
    @Produces({ MediaType.APPLICATION_JSON})
    public List<Listado> list() {
        Skill p=null;
        List rs = null;
        Object[] ale;
        List<Listado> result = new ArrayList();
        String consulta = "SELECT s.idpeople.name, s.name, s.updatedate FROM Skill s ";
        Query query = em.createQuery(consulta , Listado.class);
        rs = query.getResultList(); 
        for (Object obj : rs) {
            ale = (Object[])obj; 
            result.add(new Listado((String)ale[0], (String)ale[1], (Date)ale[2]));
        }
        return result;
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
