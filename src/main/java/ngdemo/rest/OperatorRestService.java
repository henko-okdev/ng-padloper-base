package ngdemo.rest;

import ngdemo.dao.GenericDao;
import ngdemo.dao.impl.H2OperatorDao;
import ngdemo.dao.mock.MockOperatorDao;
import ngdemo.domain.Operator;
import ngdemo.service.GenericService;

import javax.ws.rs.*;
import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/operators")
public class OperatorRestService implements GenericService<Operator> {

    private final GenericDao<Operator> dao;

    public OperatorRestService(){
        this.dao = new H2OperatorDao();
    }

    @GET
    @Path("/fetch/{id}")
    @Produces(APPLICATION_JSON)
    public Operator fetch(@PathParam("id") int id) {
        return dao.fetch(id);
    }

    @GET
    @Path("/find")
    @Produces(APPLICATION_JSON)
    public List<Operator> find() {
        return dao.find();
    }

    @POST
    @Path("/create")
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    public Operator create(Operator entity) {
        return dao.create(entity);
    }

    @PUT
    @Path("/update")
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    public Operator update(Operator entity) {
        return dao.update(entity);
    }

    @DELETE
    @Path("/delete/{id}")
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    public Operator delete(@PathParam("id") int id) {
        return dao.delete(id);
    }
}
