/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kavadrive.service;

import java.util.List;
import kavadrive.logic.Security;
import java.util.Random;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import kavadrive.classes.Message;
import kavadrive.classes.Response;
import kavadrive.classes.ServiceException;
import kavadrive.dao.UsersDAO;
import static kavadrive.dao.UsersDAO.Parameters.*;
import kavadrive.entity.Role;
import kavadrive.entity.Users;

/**
 *
 * @author  Aleksey Dziuniak
 */

@Path("users")
public class UsersFacadeREST extends AbstractFacade<Users> {
    static Role ADMINISTRATOR = new Role(1);
    static Role MANAGER = new Role(2);
    static Role USER = new Role(3);

    @Context
    HttpServletRequest request;
    
    public UsersFacadeREST() {
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response create(Users entity) {
        try {
            if (!isCorrectEntity(entity)) {
                return super.createResponse(Message.INVALID_DATA);
            }
            entity.setRoleId(USER);
            entity.setSecretCod(generateSecurityCode());
            
            UsersDAO.create(entity);
            return super.createResponse(entity);
        } catch (ServiceException e) {
            //Logger.getLogger(OrdersFacadeREST.class.getName()).log(Level.SEVERE, null, e);        
            return super.createResponse(Message.catchException(e));
        }
    }

    private boolean isCorrectEntity(Users entity) throws ServiceException {
        return (entity.getUserName() != null)
                &&  (entity.getUserPassword() != null)
                &&  ((entity.getUserPhone()!= null) || (entity.getEmail() != null))
                &&  (UsersDAO.findByParameter(PHONE, entity.getUserPhone()).isEmpty())
                &&  (UsersDAO.findByParameter(EMAIL, entity.getEmail()).isEmpty());
    }
    
    private int generateSecurityCode() {
        Random rnd = new Random();
        int range=9999;
        int secretCod = 1000 + rnd.nextInt(range - 1000);
        return secretCod;
    }  
    

    @POST
    @Override
    @Path("update")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response edit(Users entity) {
        try {
            
            Users oldUser = UsersDAO.find(entity.getUserId());
            if ((entity.getEmail() == null) && (entity.getUserPhone() == null)){
                return super.createResponse(Message.EMPTY_PHONE_NUMBER_AND_EMAIL);
            }
            if ((entity.getUserPhone() != null) 
                    && !entity.getUserPhone().equals(oldUser.getUserPhone())
                    && (!UsersDAO.findByParameter(PHONE, entity.getUserPhone()).isEmpty())) {
                return super.createResponse(Message.EXISTING_PHONE_NUMBER);
            }
            if ((entity.getEmail()!= null) 
                    && !entity.getEmail().equals(oldUser.getEmail())
                    && (!UsersDAO.findByParameter(EMAIL,entity.getEmail()).isEmpty())) {
                return super.createResponse(Message.EXISTING_EMAIL);
            }
            
            if (oldUser.getRoleId() != entity.getRoleId()) {
               if(!Security.checkClientRole(request,ADMINISTRATOR)){
                    entity.setRoleId(oldUser.getRoleId());
                    return super.createResponse(Message.INVALID_ROLE);
                }
            }

            if (entity.getUserName() == null) {
                entity.setUserName(oldUser.getUserName());
            }
            if (entity.getUserPassword()== null) {
                entity.setUserPassword(oldUser.getUserPassword());
            }
            
            UsersDAO.edit(entity);
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
            if(!Security.checkClientRole(request,ADMINISTRATOR)){
                 return new Response(Message.INVALID_ROLE);
            }

            Users entity = UsersDAO.find(id);
            UsersDAO.remove(entity);
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
            if(!Security.checkClientRole(request,ADMINISTRATOR)){
                 return new Response(Message.INVALID_ROLE);
            }

            Users entity = UsersDAO.find(id);
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
            if(!Security.checkClientRole(request,ADMINISTRATOR)){
                 return new Response(Message.INVALID_ROLE);
            }

            List<Users> entityList = UsersDAO.findAll();
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
            if(!Security.checkClientRole(request,ADMINISTRATOR)){
                 return new Response(Message.INVALID_ROLE);
            }

            List<Users> entityList = UsersDAO.findRange(new int[]{from, to});
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
            if(!Security.checkClientRole(request,ADMINISTRATOR)){
                 return new Response(Message.INVALID_ROLE);
            }

            int count = UsersDAO.count();
            return super.createResponse(count);
        } catch (ServiceException e) {
            //Logger.getLogger(OrdersFacadeREST.class.getName()).log(Level.SEVERE, null, e);        
            return super.createResponse(Message.catchException(e));
        }
    }    
}
