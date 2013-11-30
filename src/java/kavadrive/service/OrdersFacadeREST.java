/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kavadrive.service;

import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import kavadrive.classes.Response;
import kavadrive.dao.UsersDAO;
import kavadrive.entity.Users;
import static kavadrive.dao.OrdersDAO.Parameters.*;
import kavadrive.dao.OrdersDAO;
import kavadrive.entity.Orders;

/**
 *
 * @author  Aleksey Dziuniak
 */
@Path("orders")
public class OrdersFacadeREST extends AbstractFacade<Orders> {
    
    public OrdersFacadeREST() {
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public  Response create(Orders entity) {
        try {
            OrdersDAO.create(entity);
            return super.createMessage(entity);
        } catch (Exception e) {
            //Logger.getLogger(OrdersFacadeREST.class.getName()).log(Level.SEVERE, null, e);        
            return super.createMessage(e.getMessage());
        }
    }

    @POST
    @Override
    @Path("update")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response edit(Orders entity) {
        try {
            OrdersDAO.edit(entity);
            return super.createMessage(entity);
        } catch (Exception e) {
            //Logger.getLogger(OrdersFacadeREST.class.getName()).log(Level.SEVERE, null, e);        
            return super.createMessage(e.getMessage());
        }
    }

    @GET
    @Override
    @Path("{id}/delete")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response remove(@PathParam("id") Integer id) {
        try {
            Orders entity = OrdersDAO.find(id);
            OrdersDAO.remove(entity);
            return super.createMessage();
        } catch (Exception e) {
            //Logger.getLogger(OrdersFacadeREST.class.getName()).log(Level.SEVERE, null, e);        
            return super.createMessage(e.getMessage());
        }
    }

    @GET
    @Override
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response find(@PathParam("id") Integer id) {
        try {
            Orders entity = OrdersDAO.find(id);
            return super.createMessage(entity);
        } catch (Exception e) {
            //Logger.getLogger(OrdersFacadeREST.class.getName()).log(Level.SEVERE, null, e);        
            return super.createMessage(e.getMessage());
        }
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response findAll() {
        try {
            List<Orders> entityList = OrdersDAO.findAll();
            return super.createMessage(entityList);
        } catch (Exception e) {
            //Logger.getLogger(OrdersFacadeREST.class.getName()).log(Level.SEVERE, null, e);        
            return super.createMessage(e.getMessage());
        }
    }

    @GET
    @Override
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        try {
            List<Orders> entityList = OrdersDAO.findRange(new int[]{from, to});
            return super.createMessage(entityList);
        } catch (Exception e) {
            //Logger.getLogger(OrdersFacadeREST.class.getName()).log(Level.SEVERE, null, e);        
            return super.createMessage(e.getMessage());
        }
    }

    @GET
    @Override
    @Path("count")
    @Produces(MediaType.APPLICATION_XML)
    public Response count() {
        try {
            int count = OrdersDAO.count();
            return super.createMessage(count);
        } catch (Exception e) {
            //Logger.getLogger(OrdersFacadeREST.class.getName()).log(Level.SEVERE, null, e);        
            return super.createMessage(e.getMessage());
        }
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
            //Logger.getLogger(OrdersFacadeREST.class.getName()).log(Level.SEVERE, null, e);        
            return super.createMessage(e.getMessage());
        }
    }
}
