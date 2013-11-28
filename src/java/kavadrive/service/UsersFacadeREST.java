/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kavadrive.service;

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
import kavadrive.classes.Response;
import kavadrive.entity.Role;
import kavadrive.entity.Users;
import kavadrive.logic.UserDAO;

/**
 *
 * @author Artyom
 */
//@javax.ejb.Stateless
@Path("users")
public class UsersFacadeREST extends AbstractFacade {
    static Role ADMINISTRATOR = new Role(1);
    static Role MANAGER = new Role(2);
    static Role USER = new Role(3);

    @Context
    HttpServletRequest request;

    public UsersFacadeREST() {
        super(Users.class);
    }

    @POST
    //@Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response create(Users entity) {
        try {
            Role role = USER;
            entity.setRoleId(role);
            boolean correctEntity = (entity.getUserName() != null) 
                    &&  (entity.getUserPassword() != null)
                    &&  ((entity.getUserPhone()!= null) || (entity.getEmail() != null))
                    &&  (UserDAO.findByPhone(entity.getUserPhone()) == null)
                    &&  (UserDAO.findByEMail(entity.getEmail()) == null);
            if (!correctEntity) {
                return new Response("Data for user is not correct.",-1);
            }
            entity.setSecretCod(generateSecurityCode());
            Response<Users> resp = super.create(entity);
            resp.getEntity().setUserPassword(null);
            return resp;
        } catch (Exception ex) {
            entity.setUserPassword(null);
            return new Response(entity, ex.getMessage(), -1);
        }
    }

    @POST
    @Path("update")
    //@Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response edit(Users entity) {
        try {
            Users oldUser = UserDAO.findById(entity.getUserId());
            if ((entity.getEmail() == null) && (entity.getUserPhone() == null)){
                return new Response("You have to provide your phone or e-mail.",-1);
            }
            if ((entity.getUserPhone() != null) 
                    && !entity.getUserPhone().equals(oldUser.getUserPhone())
                    && (UserDAO.findByPhone(entity.getUserPhone()) != null)) {
                return new Response("User with phone number " + entity.getUserPhone() + " already existing.",-1);
            }
            if ((entity.getEmail()!= null) 
                    && !entity.getEmail().equals(oldUser.getEmail())
                    && (UserDAO.findByEMail(entity.getEmail()) != null)) {
                return new Response("User with email " + entity.getEmail() + " already existing.",-1);
            }
            
            if (oldUser.getRoleId() != entity.getRoleId()) {
               if(!Security.checkClientRole(request,ADMINISTRATOR)){
                    entity.setRoleId(oldUser.getRoleId());
                    return new Response("U do not have enough permissions to change role", -1);
                }
            }

            if (entity.getUserName() == null) {
                entity.setUserName(oldUser.getUserName());
            }
            if (entity.getUserPassword()== null) {
                entity.setUserPassword(oldUser.getUserPassword());
            }
            return super.edit(entity);
        } catch (Exception ex) {
            //Writing to log
            return new Response(ex.getMessage(), -1);
        }
    }

    @GET
    @Path("{id}/delete")
    public Response remove(@PathParam("id") Integer id) {
        try {
            if(!Security.checkClientRole(request,ADMINISTRATOR)){
                 return new Response("U do not have enough permissions", -1);
             }
            return super.remove(super.find(id).getEntity());
        } catch (Exception ex) {
            return new Response(ex.getMessage(), -1);
        }
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response find(@PathParam("id") Integer id) {
        try {
            if(!Security.checkClientRole(request,ADMINISTRATOR)){
                 return new Response("U do not have enough permissions", -1);
             }
            return super.find(id);
        } catch (Exception ex) {
            return new Response(ex.getMessage(), -1);
        }
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response findAll() {
        try {
            if(!Security.checkClientRole(request,ADMINISTRATOR)){
                 return new Response("U do not have enough permissions", -1);
             }
            return super.findAll();
        } catch (Exception ex) {
            return new Response(ex.getMessage(), -1);
        }
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        try {
            if(!Security.checkClientRole(request,ADMINISTRATOR)){
                 return new Response("U do not have enough permissions", -1);
             }
            return super.findRange(new int[]{from, to});
        } catch (Exception ex) {
            return new Response(ex.getMessage(), -1);
        }
    }

    @GET
    @Path("count")
    @Produces(MediaType.APPLICATION_XML)
    public Response countREST() {
        return super.count();
    }

    private int generateSecurityCode() {
        Random rnd = new Random();
        int range=9999;
        int secretCod = 1000 + rnd.nextInt(range - 1000);
        return secretCod;
    }    
}
