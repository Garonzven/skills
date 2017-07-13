/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entis.service;

import com.google.gson.Gson;
import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;
import entis.Listado;
import entis.Log;
import entis.People;
import entis.Skill;
import entis.Skillpeople;
import entis.SkillpeoplePK;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
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
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import net.minidev.json.JSONObject;

/**
 *
 * @author Lorena Portillo
 */
@Stateless
@Path("entis.skillpeople")
public class SkillpeopleFacadeREST extends AbstractFacade<Skillpeople> {

    @PersistenceContext(unitName = "WebSkillsGW7PU")
    private EntityManager em;   
        
    @EJB
    private SkillFacadeREST ejbSkill;
    
    @EJB
    private PeopleFacadeREST ejbFacadePeople;

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
    public void create(Skillpeople entity) {
        Skill skill = ejbSkill.findByName(entity.getSkill());
        if (skill==null){
            skill = new Skill();
            skill.setName(entity.getSkill().getName().toLowerCase());
            skill.setUpdatedate(entity.getSkill().getUpdatedate());
            skill.setCreatedate(entity.getSkill().getCreatedate());
            skill.setLevel(entity.getSkill().getLevel());
            People p = ejbFacadePeople.find(entity.getSkillpeoplePK().getIdpeople());
            skill.setIdpeople(p);
            em.persist(skill); 
        }
        entity.getSkillpeoplePK().setIdskill(skill.getIdskill());
        entity.setSkill(skill);
        em.persist(entity); 
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces("application/json")
    @Path("create")
    public Response create(Skills skills){
        String results = "";
        JSONObject json = new JSONObject();
        try {
            results = String.valueOf(skills.getIdpeople());           
            for (int k = 0; k < skills.getSkills().length; k++) {
                People p;
                p = ejbFacadePeople.find(skills.getIdpeople());
                results+="\n\r Person Found -->"+p.toString();
                Skill skill = ejbSkill.findByName(skills.getSkills()[k]);//buscar skill por nombre
                if (skill == null) {// si el skill no existe agregarlo.
                    results+="\n\r Skill Not Found ";
                    skill = new Skill();
                    skill.setName(skills.getSkills()[k].getName().toLowerCase());
                    skill.setUpdatedate(new Date());
                    skill.setCreatedate(new Date());
                    skill.setLevel(skills.getSkills()[k].getLevel());
                    skill.setIdpeople(p);
                    em.persist(skill);   
                    skill = ejbSkill.findByName(skills.getSkills()[k]);
                }else results+="\n\r Skill Found -->"+skill.toString();
                
                results+="\n\r idSkill -->"+skill.getIdskill();
                
                Skillpeople entity = new Skillpeople();
                entity.setLevel(Integer.valueOf(skills.getSkills()[k].getLevel()));
                entity.setPeople(p);
                entity.setSkill(skill);
                SkillpeoplePK pk = new SkillpeoplePK();
                pk.setIdpeople(p.getIdpeople());
                pk.setIdskill(skill.getIdskill());
                entity.setSkillpeoplePK(pk);
                entity.setUpdatedate(new Date());
                em.persist(entity);
                results += "persists --> "+skills.getSkills()[k].getName() + "\n";
            }
        } catch (Exception e) {
            results +="\n\rException = "+e.getMessage();
            json.put("status", "error");
            json.put("code", Response.Status.BAD_REQUEST.getStatusCode());
            json.put("message", e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(json.toString()).build();
        }
        json.put("status", "success");
        return Response.ok(json.toString()).build();
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("update")
    public Response update(Skills skills){
        JSONObject json = new JSONObject();
        try {          
            for (int k = 0; k < skills.getSkills().length; k++) {
                People p;
                p = ejbFacadePeople.find(skills.getIdpeople());
                Skill skill = skills.getSkills()[k];
                Skillpeople entity = new Skillpeople();
                entity.setLevel(Integer.valueOf(skills.getSkills()[k].getLevel()));
                entity.setPeople(p);
                entity.setSkill(skill);
                SkillpeoplePK pk = new SkillpeoplePK();
                pk.setIdpeople(p.getIdpeople());
                pk.setIdskill(skill.getIdskill());
                entity.setSkillpeoplePK(pk);
                entity.setUpdatedate(new Date());
                em.merge(entity);
            }
        } catch (Exception e) {
            json.put("status", "error");
            json.put("code", Response.Status.BAD_REQUEST.getStatusCode());
            json.put("message", e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(json.toString()).build();
        }
        json.put("status", "success");
        return Response.ok(json.toString()).build();
    }
    
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Override
    public void edit(Skillpeople entity) {
        entity.getSkill().setUpdatedate(new Date(System.currentTimeMillis()));
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
    @Path("findallbypeople/{idp}")
    @Produces({ MediaType.APPLICATION_JSON})
    public List<Skillpeople> findAllByPeople(@PathParam("idp") PathSegment idp) {
        Skillpeople p=null;
        List<Skillpeople> results = null;
        
        Query query =null;
        if (!idp.toString().equals(String.valueOf(0))) {       
            query   = em.createQuery(
            "SELECT p FROM Skillpeople p WHERE  p.skillpeoplePK.idpeople  = "+idp+"");
            results= query.getResultList();
        }
       return results;
    }
   
    @GET
    @Path("findallbyskill/{ids}")
    @Produces({ MediaType.APPLICATION_JSON})
    public List<Skillpeople> findAllBySkill(@PathParam("ids") PathSegment ids) {
        Skillpeople p=null;
        List<Skillpeople> results = null;
        Query query;
        
        if (!ids.toString().equals(String.valueOf(0))) {       
            query   = em.createQuery(
            "SELECT p FROM Skillpeople p WHERE p.skillpeoplePK.idskill  = "+ids+"");
            results= query.getResultList();
            
        }
       return results;
    }
    @POST
    @Path("findallbyskillname")
    @Secured
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public List<People> findAllBySkillName(Skill s) {
        Skillpeople p=null;
        List<People> results = null;
        Query query;
        
       query   = em.createQuery(
            "SELECT p.people FROM Skillpeople p WHERE p.skill.name  = '"+s.getName().toString().toLowerCase()+"'");
       results= query.getResultList();
       return results;
    }
    
    @GET
    @Path("list")
    @Produces({ MediaType.APPLICATION_JSON})
    public List<Listado> list() {
        Skillpeople p=null;
        List rs = null;
        Object[] ale;
        List<Listado> result = new ArrayList();
        String consulta = "SELECT p.people.name, p.skill.name,p.updatedate FROM Skillpeople p ";
        Query query = em.createQuery(consulta , Listado.class);
        rs = query.getResultList(); 
        for (Object obj : rs) {
            ale = (Object[])obj; 
            result.add(new Listado((String)ale[0], (String)ale[1], (Date)ale[2]));
        }
        return result;
     }
    
    @GET
    @Path("findtotal")
    @Produces({ MediaType.APPLICATION_JSON})
    public List<Skillpeople> findtotal() {
         return super.findAll();
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