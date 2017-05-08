/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entis.service;

import entis.Skillpeople;
import entis.SkillpeoplePK;
import java.util.ArrayList;
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
import javax.ws.rs.core.PathSegment;

/**
 *
 * @author Usuario
 */
@Stateless
@Path("entis.skillpeople")
public class SkillpeopleFacadeREST extends AbstractFacade<Skillpeople> {

    @PersistenceContext(unitName = "WebSkillsGW7PU")
    private EntityManager em;

    private SkillpeoplePK getPrimaryKey(PathSegment pathSegment) {
        /*
         * pathSemgent represents a URI path segment and any associated matrix parameters.
         * URI path part is supposed to be in form of 'somePath;idpeople=idpeopleValue;idskill=idskillValue'.
         * Here 'somePath' is a result of getPath() method invocation and
         * it is ignored in the following code.
         * Matrix parameters are used as field names to build a primary key instance.
         */
        entis.SkillpeoplePK key = new entis.SkillpeoplePK();
        javax.ws.rs.core.MultivaluedMap<String, String> map = pathSegment.getMatrixParameters();
        java.util.List<String> idpeople = map.get("idpeople");
        if (idpeople != null && !idpeople.isEmpty()) {
            key.setIdpeople(new java.lang.Integer(idpeople.get(0)));
        }
        java.util.List<String> idskill = map.get("idskill");
        if (idskill != null && !idskill.isEmpty()) {
            key.setIdskill(new java.lang.Integer(idskill.get(0)));
        }
        return key;
    }

    public SkillpeopleFacadeREST() {
        super(Skillpeople.class);
    }

    @POST
    @Override
    @Consumes(MediaType.APPLICATION_JSON)
    public void create(Skillpeople entity) {
        super.create(entity);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void edit(Skillpeople entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{idp}/{ids}")
    @Consumes({ MediaType.APPLICATION_JSON})
    public void remove(@PathParam("idp") PathSegment idp,@PathParam("ids") PathSegment ids, Skillpeople p) {
        super.remove(find(idp,ids));
    }

    @GET
    @Path("find/{idp}/{ids}")
    @Produces({ MediaType.APPLICATION_JSON})
    public Skillpeople find(@PathParam("idp") PathSegment idp,@PathParam("ids") PathSegment ids) {
        Skillpeople p=null;
        Query query = em.createQuery(
            "SELECT p FROM Skillpeople p WHERE  p.skillpeoplePK.idpeople  = "+idp+" AND p.skillpeoplePK.idskill="+ids+"");
       List<Skillpeople> results = query.getResultList();
       for (Skillpeople c : results) {
            p = c;
            break; 
        }
       return p;
    }

    @GET
    @Path("findall/{idp}/{ids}")
    @Produces({ MediaType.APPLICATION_JSON})
    public List<Skillpeople> findAll(@PathParam("idp") PathSegment idp,@PathParam("ids") PathSegment ids) {
        Skillpeople p=null;
        List<Skillpeople> results = null;
        
        Query query =null;
        if (!idp.toString().equals(String.valueOf(0))) {       
            query   = em.createQuery(
            "SELECT p FROM Skillpeople p WHERE  p.skillpeoplePK.idpeople  = "+idp+"");
            results= query.getResultList();
        }
        if (!ids.toString().equals(String.valueOf(0))) {       
            query   = em.createQuery(
            "SELECT p FROM Skillpeople p WHERE  p.skillpeoplePK.idskill  = "+ids+"");
            results= query.getResultList();
            
        }
       return results;
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
