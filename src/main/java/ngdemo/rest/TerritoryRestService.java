package ngdemo.rest;

import ngdemo.dao.GenericDao;
import ngdemo.dao.impl.H2TerritoryDao;
import ngdemo.domain.Territory;
import ngdemo.service.GenericService;
import ngdemo.service.TerritoryService;

import javax.ws.rs.*;
import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/territories")
public class TerritoryRestService {

    private final GenericDao<Territory> dao;

    public TerritoryRestService() {
        this.dao = new H2TerritoryDao();
    }

    @GET
    @Path("/fetch/{id}")
    @Produces(APPLICATION_JSON)
    public Territory fetch(@PathParam("id") int id){
        return dao.fetch(id);
    }

    @GET
    @Path("/find")
    @Produces(APPLICATION_JSON)
    public List<Territory> find() {
        return dao.find();
    }

    @POST
    @Path("/create")
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    public Territory create(Territory territory) {
        return dao.create(territory);
    }

    @PUT
    @Path("/update")
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    public Territory update(Territory territory) {
        return dao.update(territory);
    }

    @DELETE
    @Path("/delete/{id}")
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    public Territory delete(@PathParam("id")Integer id){
        return dao.delete(id);
    }

}
