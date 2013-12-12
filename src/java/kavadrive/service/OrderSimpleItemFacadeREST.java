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
import kavadrive.dao.OrderSimpleItemDAO;
import kavadrive.entity.OrderSimpleItem;

/**
 *
 * @author  Aleksey Dziuniak
 */
@Path("ordersimpleitem")
public class OrderSimpleItemFacadeREST extends AbstractFacade<OrderSimpleItem> {
    
    public OrderSimpleItemFacadeREST() {
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response create(OrderSimpleItem entity) {
        try {
            OrderSimpleItemDAO.create(entity);
            return Response.create(entity);
        } catch (ServiceException e) {
            //Logger.getLogger(OrdersFacadeREST.class.getName()).log(Level.SEVERE, null, e);        
            return Response.create(Message.catchException(e));
        }
    }

    @POST
    @Override
    @Path("update")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response edit(OrderSimpleItem entity) {
        try {
            OrderSimpleItemDAO.edit(entity);
            return Response.create(entity);
        } catch (ServiceException e) {
            //Logger.getLogger(OrdersFacadeREST.class.getName()).log(Level.SEVERE, null, e);        
            return Response.create(Message.catchException(e));
        }
    }

    @GET
    @Override
    @Path("{id}/delete")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response remove(@PathParam("id") Integer id) {
        try {
            OrderSimpleItem entity = OrderSimpleItemDAO.find(id);
            OrderSimpleItemDAO.remove(entity);
            return Response.create();
        } catch (ServiceException e) {
            //Logger.getLogger(OrdersFacadeREST.class.getName()).log(Level.SEVERE, null, e);        
            return Response.create(Message.catchException(e));
        }
    }

    @GET
    @Override
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response find(@PathParam("id") Integer id) {
        try {
            OrderSimpleItem entity = OrderSimpleItemDAO.find(id);
            return Response.create(entity);
        } catch (ServiceException e) {
            //Logger.getLogger(OrdersFacadeREST.class.getName()).log(Level.SEVERE, null, e);        
            return Response.create(Message.catchException(e));
        }
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response findAll() {
        try {
            List<OrderSimpleItem> entityList = OrderSimpleItemDAO.findAll();
            return Response.create(entityList);
        } catch (ServiceException e) {
            //Logger.getLogger(OrdersFacadeREST.class.getName()).log(Level.SEVERE, null, e);        
            return Response.create(Message.catchException(e));
        }
    }

    @GET
    @Override
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        try {
            List<OrderSimpleItem> entityList = OrderSimpleItemDAO.findRange(new int[]{from, to});
            return Response.create(entityList);
        } catch (ServiceException e) {
            //Logger.getLogger(OrdersFacadeREST.class.getName()).log(Level.SEVERE, null, e);        
            return Response.create(Message.catchException(e));
        }
    }

    @GET
    @Override
    @Path("count")
    @Produces(MediaType.APPLICATION_XML)
    public Response count() {
        try {
            int count = OrderSimpleItemDAO.count();
            return Response.create(count);
        } catch (ServiceException e) {
            //Logger.getLogger(OrdersFacadeREST.class.getName()).log(Level.SEVERE, null, e);        
            return Response.create(Message.catchException(e));
        }
    }
}
