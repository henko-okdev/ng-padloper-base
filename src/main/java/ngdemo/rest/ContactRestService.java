package ngdemo.rest;

import ngdemo.dao.GenericDao;
import ngdemo.dao.impl.H2ContactDao;
import ngdemo.domain.Contact;
import ngdemo.service.ContactService;
import ngdemo.service.GenericService;


import javax.ws.rs.*;
import java.util.List;

import static javax.ws.rs.core.MediaType.*;

@Path("/contacts")
public class ContactRestService {

    private final GenericDao<Contact> dao;

    public ContactRestService() {
        this.dao = new H2ContactDao();
    }

    @GET
    @Path("/fetch/{id}")
    @Produces(APPLICATION_JSON)
    public Contact fetch(@PathParam("id") int id){
        return dao.fetch(id);
    }

    @GET
    @Path("/find")
    @Produces(APPLICATION_JSON)
    public List<Contact> find() {
        return dao.find();
    }

    @POST
    @Path("/create")
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    public Contact create(Contact contact) {
        System.out.println(contact);
        return dao.create(contact);
    }

    @PUT
    @Path("/update")
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    public Contact update(Contact contact) {
        return dao.update(contact);
    }

    @DELETE
    @Path("/delete/{id}")
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    public Contact delete(@PathParam("id")Integer id){
        return dao.delete(id);
    }
}
