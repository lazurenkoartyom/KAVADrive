/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kavadrive.service;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import kavadrive.classes.Response;
import kavadrive.dao.OrdersDAO;
import kavadrive.entity.OrderItem;
import kavadrive.entity.Users;
import kavadrive.logic.ServiceException;

/**
 *
 * @author Artyom
 */
//@javax.ejb.Stateless
@Path("orderitem")
public class OrderItemFacadeREST extends AbstractFacade {
//    @PersistenceContext(unitName = "KAVADrivePU")
//    private EntityManager em;

    public OrderItemFacadeREST() {
        super(OrderItem.class);
    }

    @POST
    @Override
    @Consumes({"application/xml", "application/json"})
    public <OrderItem> Response create(OrderItem entity) {
        return super.create(entity);
    }

    @POST
    @Path("update")
    @Override
    @Consumes({"application/xml", "application/json"})
    public <OrderItem> Response edit(OrderItem entity) {
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
        try {
            Users user = new Users();
            OrdersDAO.find(id);
            OrdersDAO.findByParameter(OrdersDAO.Parameters.USER, user);
        } catch (ServiceException ex) {
            Logger.getLogger(OrderItemFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        return super.find(id);
    }

    @GET
    @Override
    @Produces({"application/xml", "application/json"})
    public Response findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public Response findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
