/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package kavadrive.logic;

import kavadrive.service.entity.OrderIdList;
import kavadrive.service.entity.StoreServiceOrdersList;
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
import kavadrive.dao.OrdersDAO;
import kavadrive.dao.OrdersDAO.Parameters.*;
import kavadrive.dao.OrdersDAO.Status.*;
import kavadrive.classes.Criteria;
import kavadrive.dao.StoreDAO;
import kavadrive.entity.Orders;
import kavadrive.entity.Store;

/**
 *
 * @author Aleksey Dziuniak
 */
@Path("store_service")
public class StoreService {
    
    public StoreService(){
        
    }
    
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response common(){
        return Response.EMPTY;
    }
    
    @GET
    @Path("{store_id}/accepted")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response getListAcceptedOrders(@PathParam("store_id") Integer storeId) {
       
        try {
            Store store = StoreDAO.find(storeId);
            
            Criteria[] crits = new Criteria[2];
            crits[0] = new Criteria(OrdersDAO.Parameters.STATUS.getName(),OrdersDAO.Status.ACCEPTED);           
            crits[1] = new Criteria(OrdersDAO.Parameters.STORE.getName(),store);
            
            List<Orders> orders = OrdersDAO.findByCriterias(crits);
            
            StoreServiceOrdersList shopOrders = new StoreServiceOrdersList(store,orders);
            return Response.create(shopOrders);
        } catch (ServiceException e) {
            //Logger.getLogger(OrdersFacadeREST.class.getName()).log(Level.SEVERE, null, e);        
            return Response.create(Message.catchException(e));
        } 
    }
    
    @POST
    @Path("status/set_cancelled")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response setStatusCancelled(OrderIdList ids) {
        try {
            List<Orders> orders = OrdersDAO.getOrdersByListIds(ids.getOrders());
            OrdersDAO.setParameterInEntityList(orders, OrdersDAO.Parameters.STATUS, OrdersDAO.Status.CANCELLED);
            return Response.create();
        } catch (ServiceException e) {
            //Logger.getLogger(OrdersFacadeREST.class.getName()).log(Level.SEVERE, null, e);        
            return Response.create(Message.catchException(e));
        }
    }
    
    @POST
    @Path("status/set_completed")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response setStatusCompleted(OrderIdList ids) {
        try {
            List<Orders> orders = OrdersDAO.getOrdersByListIds(ids.getOrders());
            OrdersDAO.setParameterInEntityList(orders, OrdersDAO.Parameters.STATUS, OrdersDAO.Status.COMPLETED);
            return Response.create();
        } catch (ServiceException e) {
            //Logger.getLogger(OrdersFacadeREST.class.getName()).log(Level.SEVERE, null, e);        
            return Response.create(Message.catchException(e));
        }
    }
}
