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
import kavadrive.classes.Response_List;
import kavadrive.entity.Users;
import kavadrive.logic.Roles;
import static kavadrive.logic.Roles.*;
import kavadrive.logic.UserDAO;

/**
 *
 * @author Artyom
 */
//@javax.ejb.Stateless
@Path("users")
public class UsersFacadeREST extends AbstractFacade<Users> {

    @Context
    HttpServletRequest request;

    public UsersFacadeREST() {
        super(Users.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response create(Users entity) {
        try {
            Integer role = Roles.USER.getId();
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
            return super.create(entity);
        } catch (Exception ex) {
            entity.setUserPassword(null);
            return new Response(entity, ex.getMessage(), -1);
        }
    }

    @POST
    @Path("update")
    @Override
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
                 return new Response("U are not have enough permissions", -1);
             }
            return super.remove(super.find(id).getEntity());
        } catch (Exception ex) {
            return new Response(ex.getMessage(), -1);
        }
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response<Users> find(@PathParam("id") Integer id) {
        try {
            if(!Security.checkClientRole(request,ADMINISTRATOR)){
                 return new Response("U are not have enough permissions", -1);
             }
            return super.find(id);
        } catch (Exception ex) {
            return new Response(ex.getMessage(), -1);
        }
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response_List<Users> findAll() {
        try {
            if(!Security.checkClientRole(request,ADMINISTRATOR)){
                 return new Response_List("U are not have enough permissions", -1);
             }
            return super.findAll();
        } catch (Exception ex) {
            return new Response_List(ex.getMessage(), -1);
        }
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response_List<Users> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        try {
            if(!Security.checkClientRole(request,ADMINISTRATOR)){
                 return new Response_List("U are not have enough permissions", -1);
             }
            return super.findRange(new int[]{from, to});
        } catch (Exception ex) {
            return new Response_List(ex.getMessage(), -1);
        }
    }

    @GET
    @Path("count")
    @Produces(MediaType.APPLICATION_XML)
    public String countREST() {
        return String.valueOf(super.count());
    }

    private int generateSecurityCode() {
        Random rnd = new Random();
        int range=9999;
        int secretCod = 1000 + rnd.nextInt(range - 1000);
        return secretCod;
    }    
}
