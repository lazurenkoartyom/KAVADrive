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
    public Response edit(Category entity) {
        try {
            CategoryDAO.edit(entity);
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
            Category entity = CategoryDAO.find(id);
            CategoryDAO.remove(entity);
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
            Category entity = CategoryDAO.find(id);
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
            List<Category> entityList = CategoryDAO.findAll();
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
            List<Category> entityList = CategoryDAO.findRange(new int[]{from, to});
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
            int count = CategoryDAO.count();
            return Response.create(count);
        } catch (ServiceException e) {
            //Logger.getLogger(OrdersFacadeREST.class.getName()).log(Level.SEVERE, null, e);        
            return Response.create(Message.catchException(e));
        }
    }
}
