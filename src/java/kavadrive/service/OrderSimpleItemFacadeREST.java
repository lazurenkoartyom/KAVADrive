/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kavadrive.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import kavadrive.classes.Response;
import kavadrive.classes.Response_List;
import kavadrive.entity.OrderSimpleItem;

/**
 *
 * @author Artyom
 */
//@javax.ejb.Stateless
@Path("ordersimpleitem")
public class OrderSimpleItemFacadeREST extends AbstractFacade<OrderSimpleItem> {
//    @PersistenceContext(unitName = "KAVADrivePU")
//    private EntityManager em;

    public OrderSimpleItemFacadeREST() {
        super(OrderSimpleItem.class);
    }

    @POST
    @Override
    @Consumes({"application/xml", "application/json"})
    public Response create(OrderSimpleItem entity) {
        return super.create(entity);
    }

    @POST
    @Path("update")
    @Override
    @Consumes({"application/xml", "application/json"})
    public Response edit(OrderSimpleItem entity) {
        return super.edit(entity);
    }

    @GET
    @Path("{id}/delete")
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
    public Response_List<OrderSimpleItem> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public Response_List<OrderSimpleItem> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces("application/xml")
    public String countREST() {
        return String.valueOf(super.count());
    }

//    @Override
//    protected EntityManager getEntityManager() {
//        return em;
//    }
    
}
