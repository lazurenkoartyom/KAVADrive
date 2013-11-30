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
import kavadrive.dao.ProductItemDAO;
import kavadrive.entity.ProductItem;

/**
 *
 * @author  Aleksey Dziuniak
 */

@Path("productitem")
public class ProductItemFacadeREST extends AbstractFacade<ProductItem>  {
    
    public ProductItemFacadeREST() {
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response create(ProductItem entity) {
        try {
            ProductItemDAO.create(entity);
            return super.createMessage(entity);
        } catch (Exception e) {
            //Logger.getLogger(ProductItemFacadeREST.class.getName()).log(Level.SEVERE, null, e);        
            return super.createMessage(e.getMessage());
        }
    }

    @POST
    @Override
    @Path("update")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response edit(ProductItem entity) {
        try {
            ProductItemDAO.edit(entity);
            return super.createMessage(entity);
        } catch (Exception e) {
            //Logger.getLogger(ProductItemFacadeREST.class.getName()).log(Level.SEVERE, null, e);        
            return super.createMessage(e.getMessage());
        }
    }

    @GET
    @Override
    @Path("{id}/delete")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response remove(@PathParam("id") Integer id) {
        try {
            ProductItem entity = ProductItemDAO.find(id);
            ProductItemDAO.remove(entity);
            return super.createMessage();
        } catch (Exception e) {
            //Logger.getLogger(ProductItemFacadeREST.class.getName()).log(Level.SEVERE, null, e);        
            return super.createMessage(e.getMessage());
        }
    }

    @GET
    @Override
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response find(@PathParam("id") Integer id) {
        try {
            ProductItem entity = ProductItemDAO.find(id);
            return super.createMessage(entity);
        } catch (Exception e) {
            //Logger.getLogger(ProductItemFacadeREST.class.getName()).log(Level.SEVERE, null, e);        
            return super.createMessage(e.getMessage());
        }
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response findAll() {
        try {
            List<ProductItem> entityList = ProductItemDAO.findAll();
            return super.createMessage(entityList);
        } catch (Exception e) {
            //Logger.getLogger(ProductItemFacadeREST.class.getName()).log(Level.SEVERE, null, e);        
            return super.createMessage(e.getMessage());
        }
    }

    @GET
    @Override
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        try {
            List<ProductItem> entityList = ProductItemDAO.findRange(new int[]{from, to});
            return super.createMessage(entityList);
        } catch (Exception e) {
            //Logger.getLogger(ProductItemFacadeREST.class.getName()).log(Level.SEVERE, null, e);        
            return super.createMessage(e.getMessage());
        }
    }

    @GET
    @Override
    @Path("count")
    @Produces(MediaType.APPLICATION_XML)
    public Response count() {
        try {
            int count = ProductItemDAO.count();
            return super.createMessage(count);
        } catch (Exception e) {
            //Logger.getLogger(ProductItemFacadeREST.class.getName()).log(Level.SEVERE, null, e);        
            return super.createMessage(e.getMessage());
        }
    }
}
