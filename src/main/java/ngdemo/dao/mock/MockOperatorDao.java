package ngdemo.dao.mock;

import ngdemo.dao.GenericDao;
import ngdemo.domain.Operator;

import java.util.ArrayList;
import java.util.List;

public class MockOperatorDao implements GenericDao<Operator> {

    private final static List<Operator> mockData = new ArrayList<Operator>();

    public Operator fetch(int id) {
        return mockData.get(id);
    }

    public List<Operator> find() {
        return mockData;
    }

    public Operator create(Operator entity) {
        mockData.add(entity);
        return entity;
    }

    public Operator update(Operator entity) {
        Operator toUpdate = mockData.get( entity.getId() );

        toUpdate.setFirstName(entity.getFirstName());
        toUpdate.setLastName(entity.getLastName());
        toUpdate.setPhone(entity.getPhone());
        toUpdate.setEmail(entity.getEmail());

        return toUpdate;
    }

    public Operator delete(int id) {
        return mockData.remove(id);
    }

    static {
        mockData.add( new Operator(0, "George", "Fridle", "+43212234", "fridle.george@mail.com"));
        mockData.add( new Operator(1, "Kate", "Garbgae", "+412312334", "kate.george@mail.com"));
        mockData.add( new Operator(2, "Mery", "Hoffer", "+534534", "mery.george@mail.com"));
        mockData.add( new Operator(3, "Andru", "Amuna", "+4324564534", "adnru.george@mail.com"));
        mockData.add( new Operator(4, "George", "Keymi", "+4989734", "heorh.george@mail.com"));
        mockData.add( new Operator(5, "Finigan", "Vasde", "+4562234", "fnigah.george@mail.com"));
        mockData.add( new Operator(6, "Vasiyi", "Uruka", "+1234252234", "vasili.george@mail.com"));
        mockData.add( new Operator(7, "Amika", "Vasil", "+6453534", "amika.george@mail.com"));
        mockData.add( new Operator(8, "Damy", "Hanl", "+56734234", "damy.george@mail.com"));
    }
}
