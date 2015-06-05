package ngdemo.service;

import ngdemo.dao.GenericDao;
import ngdemo.dao.mock.MockContactDao;
import ngdemo.domain.Contact;

import java.util.List;

public class ContactService implements GenericService<Contact>{

    private final GenericDao<Contact> dao;

    public ContactService(){
        this.dao = new MockContactDao();
    }

    public Contact fetch(int id) {
        return dao.fetch(id);
    }

    public List<Contact> find() {
        return dao.find();
    }

    public Contact create(Contact entity) {
        return dao.create(entity);
    }

    public Contact update(Contact entity) {
        return dao.update(entity);
    }

    public Contact delete(int id) {
        return dao.delete(id);
    }
}