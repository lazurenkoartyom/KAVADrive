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
import kavadrive.classes.Message;
import kavadrive.classes.Response;
import kavadrive.classes.ServiceException;
import kavadrive.dao.OrderItemDAO;
import kavadrive.dao.OrdersDAO;
import static kavadrive.dao.OrderItemDAO.Parameters.*;
import kavadrive.dao.UsersDAO;
import kavadrive.entity.OrderItem;
import kavadrive.entity.Orders;
import kavadrive.entity.Users;

/**
 *
 * @author  Aleksey Dziuniak
 */
@Path("orderitem")
public class OrderItemFacadeREST extends AbstractFacade<OrderItem>  {
    
    public OrderItemFacadeREST() {
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response create(OrderItem entity) {
        try {
            OrderItemDAO.create(entity);
            return super.createResponse(entity);
        } catch (ServiceException e) {
            //Logger.getLogger(OrdersFacadeREST.class.getName()).log(Level.SEVERE, null, e);        
            return super.createResponse(Message.catchException(e));
        }
    }

    @POST
    @Override
    @Path("update")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response edit(OrderItem entity) {
        try {
            OrderItemDAO.edit(entity);
            return super.createResponse(entity);
        } catch (ServiceException e) {
            //Logger.getLogger(OrdersFacadeREST.class.getName()).log(Level.SEVERE, null, e);        
            return super.createResponse(Message.catchException(e));
        }
    }

    @GET
    @Override
    @Path("{id}/delete")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response remove(@PathParam("id") Integer id) {
        try {
            OrderItem entity = OrderItemDAO.find(id);
            OrderItemDAO.remove(entity);
            return super.createResponse();
        } catch (ServiceException e) {
            //Logger.getLogger(OrdersFacadeREST.class.getName()).log(Level.SEVERE, null, e);        
            return super.createResponse(Message.catchException(e));
        }
    }

    @GET
    @Override
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response find(@PathParam("id") Integer id) {
        try {
            OrderItem entity = OrderItemDAO.find(id);
            return super.createResponse(entity);
        } catch (ServiceException e) {
            //Logger.getLogger(OrdersFacadeREST.class.getName()).log(Level.SEVERE, null, e);        
            return super.createResponse(Message.catchException(e));
        }
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response findAll() {
        try {
            List<OrderItem> entityList = OrderItemDAO.findAll();
            return super.createResponse(entityList);
        } catch (ServiceException e) {
            //Logger.getLogger(OrdersFacadeREST.class.getName()).log(Level.SEVERE, null, e);        
            return super.createResponse(Message.catchException(e));
        }
    }

    @GET
    @Override
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        try {
            List<OrderItem> entityList = OrderItemDAO.findRange(new int[]{from, to});
            return super.createResponse(entityList);
        } catch (ServiceException e) {
            //Logger.getLogger(OrdersFacadeREST.class.getName()).log(Level.SEVERE, null, e);        
            return super.createResponse(Message.catchException(e));
        }
    }

    @GET
    @Override
    @Path("count")
    @Produces(MediaType.APPLICATION_XML)
    public Response count() {
        try {
            int count = OrderItemDAO.count();
            return super.createResponse(count);
        } catch (ServiceException e) {
            //Logger.getLogger(OrdersFacadeREST.class.getName()).log(Level.SEVERE, null, e);        
            return super.createResponse(Message.catchException(e));
        }
    }
    
    @GET
    @Path("order/{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response findByOrder(@PathParam("id") Integer id) {
        try {
            Orders order = OrdersDAO.find(id);
            List<OrderItem> found = OrderItemDAO.findByParameter(ORDER, order);
            OrderItemDAO.clearParameters(found,ORDER);
            return super.createResponse(order, found);
        } catch (ServiceException e) {
            //Logger.getLogger(OrdersFacadeREST.class.getName()).log(Level.SEVERE, null, e);        
            return super.createResponse(Message.catchException(e));
        }
    }
}
