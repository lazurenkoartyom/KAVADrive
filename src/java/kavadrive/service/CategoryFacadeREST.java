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
import kavadrive.dao.CategoryDAO;
import kavadrive.entity.Category;

/**
 *
 * @author Aleksey Dziuniak
 */

@Path("category")
public class CategoryFacadeREST extends AbstractFacade<Category> {

    public CategoryFacadeREST() {
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response create(Category entity) {
        try {
            CategoryDAO.create(entity);
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
    public Response edit(Category entity) {
        try {
            CategoryDAO.edit(entity);
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
            Category entity = CategoryDAO.find(id);
            CategoryDAO.remove(entity);
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
            Category entity = CategoryDAO.find(id);
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
            List<Category> entityList = CategoryDAO.findAll();
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
            List<Category> entityList = CategoryDAO.findRange(new int[]{from, to});
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
            int count = CategoryDAO.count();
            return super.createMessage(count);
        } catch (Exception e) {
            //Logger.getLogger(OrdersFacadeREST.class.getName()).log(Level.SEVERE, null, e);        
            return super.createMessage(e.getMessage());
        }
    }
}
