package ngdemo.rest;


import ngdemo.dao.GenericDao;
import ngdemo.dao.impl.H2DonationDao;
import ngdemo.dao.mock.MockDonationDao;
import ngdemo.domain.Donation;
import ngdemo.service.GenericService;

import javax.ws.rs.*;
import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/donations")
public class DonationRestService implements GenericService<Donation> {

    private final GenericDao<Donation> dao;

    public DonationRestService() {
        this.dao = new H2DonationDao();
    }

    @GET
    @Path("/fetch/{id}")
    @Produces(APPLICATION_JSON)
    public Donation fetch(@PathParam("id") int id) {
        return dao.fetch(id);
    }

    @GET
    @Path("/find")
    @Produces(APPLICATION_JSON)
    public List<Donation> find() {
        return dao.find();
    }

    @POST
    @Path("/create")
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    public Donation create(Donation entity) {
        return dao.create(entity);
    }

    @PUT
    @Path("/update")
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    public Donation update(Donation entity) {
        return dao.update(entity);
    }

    @DELETE
    @Path("/delete/{id}")
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    public Donation delete(@PathParam("id") int id) {
        return dao.delete(id);
    }
}
