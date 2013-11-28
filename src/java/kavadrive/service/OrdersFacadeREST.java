/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kavadrive.service;

import java.util.List;
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
import kavadrive.dao.UsersDAO;
import kavadrive.entity.Orders;
import kavadrive.entity.Users;
import static kavadrive.dao.OrdersDAO.Parameters.*;
import kavadrive.logic.ServiceException;

/**
 *
 * @author Artyom
 */
//@javax.ejb.Stateless
@Path("orders")
public class OrdersFacadeREST extends AbstractFacade {

    public OrdersFacadeREST() {
        super(Orders.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public <Orders> Response create(Orders entity) {
        return super.create(entity);
    }

    @POST
    @Path("update")
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public <Orders> Response edit(Orders entity) {
        return super.edit(entity);
    }

    @GET
    @Path("{id}/delete")
    public Response remove(@PathParam("id") Integer id) {
        return super.remove(super.find(id).getEntity());
    }

    @GET
    @Path("user/{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response findByUser(@PathParam("id") Integer id) {
        try {
            Users user = UsersDAO.find(id);
            List<Orders> found = OrdersDAO.findByParameter(USER, user); 
            return super.createMessage(user, found);
        } catch (Exception e) {
            Logger.getLogger(OrdersFacadeREST.class.getName()).log(Level.SEVERE, null, e);        
            return super.createMessage(e.getMessage());
        }
    }
    
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response findAll() {
        try {
            List<Orders> found; 
            found = OrdersDAO.findAll();
            return super.createMessage(found);
        } catch (Exception e) {
            Logger.getLogger(OrdersFacadeREST.class.getName()).log(Level.SEVERE, null, e);        
            return super.createMessage(e.getMessage());
        }
//        return super.findAll();
     }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.APPLICATION_XML)
    public Response countREST() {
        return super.count();
    }
}
