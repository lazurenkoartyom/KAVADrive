/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kavadrive.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import kavadrive.classes.Response;
import kavadrive.classes.Response_List;
import kavadrive.entity.ProductSetList;

/**
 *
 * @author Artyom
 */
//@javax.ejb.Stateless
@Path("kavadrive.entity.productsetlist")
public class ProductSetListFacadeREST extends AbstractFacade<ProductSetList> {
//    @PersistenceContext(unitName = "KAVADrivePU")
//    private EntityManager em;

    public ProductSetListFacadeREST() {
        super(ProductSetList.class);
    }

    @POST
    @Override
    @Consumes({"application/xml", "application/json"})
    public Response create(ProductSetList entity) {
        return super.create(entity);
    }

    @POST
    @Path("update")
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response edit(ProductSetList entity) {
        return super.edit(entity);
    }

    @GET
    @Path("{id}/delete")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
     public Response remove(@PathParam("id") Integer id) {
        return super.remove(super.find(id).getEntity());
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response_List<ProductSetList> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response_List findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.APPLICATION_XML)
    public String countREST() {
        return String.valueOf(super.count());
    }
    
}
