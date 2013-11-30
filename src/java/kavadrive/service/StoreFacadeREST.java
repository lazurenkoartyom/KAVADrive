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
import kavadrive.dao.StoreDAO;
import kavadrive.entity.Store;

/**
 *
 * @author  Aleksey Dziuniak
 */

@Path("store")
public class StoreFacadeREST extends AbstractFacade<Store> {
    
    public StoreFacadeREST() {
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public  Response create(Store entity) {
        try {
            StoreDAO.create(entity);
            return super.createMessage(entity);
        } catch (Exception e) {
            //Logger.getLogger(StoreFacadeREST.class.getName()).log(Level.SEVERE, null, e);        
            return super.createMessage(e.getMessage());
        }
    }

    @POST
    @Override
    @Path("update")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response edit(Store entity) {
        try {
            StoreDAO.edit(entity);
            return super.createMessage(entity);
        } catch (Exception e) {
            //Logger.getLogger(StoreFacadeREST.class.getName()).log(Level.SEVERE, null, e);        
            return super.createMessage(e.getMessage());
        }
    }

    @GET
    @Override
    @Path("{id}/delete")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response remove(@PathParam("id") Integer id) {
        try {
            Store entity = StoreDAO.find(id);
            StoreDAO.remove(entity);
            return super.createMessage();
        } catch (Exception e) {
            //Logger.getLogger(StoreFacadeREST.class.getName()).log(Level.SEVERE, null, e);        
            return super.createMessage(e.getMessage());
        }
    }

    @GET
    @Override
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response find(@PathParam("id") Integer id) {
        try {
            Store entity = StoreDAO.find(id);
            return super.createMessage(entity);
        } catch (Exception e) {
            //Logger.getLogger(StoreFacadeREST.class.getName()).log(Level.SEVERE, null, e);        
            return super.createMessage(e.getMessage());
        }
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response findAll() {
        try {
            List<Store> entityList = StoreDAO.findAll();
            return super.createMessage(entityList);
        } catch (Exception e) {
            //Logger.getLogger(StoreFacadeREST.class.getName()).log(Level.SEVERE, null, e);        
            return super.createMessage(e.getMessage());
        }
    }

    @GET
    @Override
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        try {
            List<Store> entityList = StoreDAO.findRange(new int[]{from, to});
            return super.createMessage(entityList);
        } catch (Exception e) {
            //Logger.getLogger(StoreFacadeREST.class.getName()).log(Level.SEVERE, null, e);        
            return super.createMessage(e.getMessage());
        }
    }

    @GET
    @Override
    @Path("count")
    @Produces(MediaType.APPLICATION_XML)
    public Response count() {
        try {
            int count = StoreDAO.count();
            return super.createMessage(count);
        } catch (Exception e) {
            //Logger.getLogger(StoreFacadeREST.class.getName()).log(Level.SEVERE, null, e);        
            return super.createMessage(e.getMessage());
        }
    }
}
