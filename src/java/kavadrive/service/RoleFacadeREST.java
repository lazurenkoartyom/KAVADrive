/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kavadrive.service;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import kavadrive.classes.Response;
import kavadrive.entity.Role;

/**
 *
 * @author Artyom
 */
//@javax.ejb.Stateless
@Path("role")
public class RoleFacadeREST extends AbstractFacade<Role> {
//    @PersistenceContext(unitName = "KAVADrivePU")
//    private EntityManager em;

    public RoleFacadeREST() {
        super(Role.class);
    }

    @POST
    @Override
    @Consumes({"application/xml", "application/json"})
    public Response create(Role entity) {
        return super.create(entity);
    }

    @POST
    @Path("update")
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response edit(Role entity) {
        return super.edit(entity);
    }

    @GET
    @Path("{id}/delete")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response remove(@PathParam("id") Integer id) {
        return super.remove(super.find(id).getEntity());
    }

    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public Response find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({"application/xml", "application/json"})
    public Response findAll() {
        Response resp = new Response(super.findAll().getEntity(),"OK",0);
        return resp;
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public Response<Role> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.APPLICATION_XML)
    public Response countREST() {
        return super.count();
    }

//    @Override
//    protected EntityManager getEntityManager() {
//        return em;
//    }
    
}
