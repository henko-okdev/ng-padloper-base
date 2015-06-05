package ngdemo.dao.mock;

import ngdemo.dao.GenericDao;
import ngdemo.domain.Contact;
import ngdemo.domain.Donation;
import ngdemo.domain.Operator;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MockDonationDao implements GenericDao<Donation>{

    private final static List<Donation> mockData = new ArrayList<Donation>();

    public Donation fetch(int id) {
        return mockData.get(id);
    }

    public List<Donation> find() {
        return mockData;
    }

    public Donation create(Donation entity) {
        mockData.add(entity);
        return null;
    }

    public Donation update(Donation entity) {
        System.out.println(entity);
        Donation toUpdate = mockData.get( entity.getId() );

        toUpdate.setContact( entity.getContact() );
        toUpdate.setOperator( entity.getOperator() );
        toUpdate.setAmount( entity.getAmount() );
        toUpdate.setDate( entity.getDate() );

        return toUpdate;
    }

    public Donation delete(int id) {
        return mockData.remove(id);
    }

    static {
        GenericDao<Contact> contactDao = new MockContactDao();
        GenericDao<Operator> operatorDao = new MockOperatorDao();

        mockData.add( new Donation(0, operatorDao.fetch(0), contactDao.fetch(0), 1000, new Date()));
        mockData.add( new Donation(1, operatorDao.fetch(1), contactDao.fetch(1), 2000, new Date()));
        mockData.add( new Donation(2, operatorDao.fetch(2), contactDao.fetch(2), 3000, new Date()));
        mockData.add( new Donation(3, operatorDao.fetch(3), contactDao.fetch(3), 4000, new Date()));
        mockData.add( new Donation(4, operatorDao.fetch(4), contactDao.fetch(0), 5000, new Date()));
        mockData.add( new Donation(5, operatorDao.fetch(5), contactDao.fetch(1), 6000, new Date()));
        mockData.add( new Donation(6, operatorDao.fetch(6), contactDao.fetch(2), 7000, new Date()));
        mockData.add( new Donation(7, operatorDao.fetch(7), contactDao.fetch(3), 8000, new Date()));
        mockData.add( new Donation(8, operatorDao.fetch(8), contactDao.fetch(0), 9000, new Date()));
    }
}
