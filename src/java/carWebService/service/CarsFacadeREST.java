/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carWebService.service;

import carWebService.Cars;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Kev
 */
@Stateless
@Path("carwebservice.cars")
public class CarsFacadeREST extends AbstractFacade<Cars> {

    @PersistenceContext(unitName = "CarWebServicePU")
    private EntityManager em;

    public CarsFacadeREST() {
        super(Cars.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Cars entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Cars entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Cars find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Cars> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Cars> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }
    
    // Get Reference
    @GET
    // Path Reference
    @Path("findByCModelAndYear/{cModel}/{cYear}")
    // Produces Reference in relation to how the client consumes the data
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    // Method to allow the brouser to access the parameters
    public List<Cars> findByCModelAndYear(@PathParam("cModel") String cModel, @PathParam("cYear") int cYear) {
        return super.findModelAndYear(cModel, cYear);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
