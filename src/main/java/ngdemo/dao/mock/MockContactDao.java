package ngdemo.dao.mock;

import ngdemo.dao.GenericDao;
import ngdemo.domain.Contact;
import ngdemo.domain.Territory;
import ngdemo.service.TerritoryService;

import java.util.ArrayList;
import java.util.List;

public class MockContactDao implements GenericDao<Contact> {

    private static List<Contact> mockData = new ArrayList<Contact>();

    public Contact fetch(int id) {
        return mockData.get(id);
    }

    public List<Contact> find() {
        return mockData;
    }

    public Contact create(Contact entity) {
        int id = mockData.size();
        entity.setId(id);
        mockData.add(entity);
        return entity;
    }

    public Contact update(Contact entity) {
        Contact toUpdate = mockData.get( entity.getId() );

        toUpdate.setFirstName( entity.getFirstName() );
        toUpdate.setLastName( entity.getLastName() );
        toUpdate.setEmail( entity.getEmail() );
        toUpdate.setCity(entity.getCity());
        toUpdate.setCountry(entity.getCountry());

        return toUpdate;
    }

    public Contact delete(int id) {
        return mockData.remove(id);
    }

    static {
        TerritoryService territoryService = new TerritoryService();
        List<Territory> territories = territoryService.find();

        mockData.add( new Contact(0, "Ruslan", "Kurchenko", "+3805015657", "henko.okdev@gmail.com", territories.get(4)) );
        mockData.add( new Contact(1, "Irina", "Mikhalchenko", "+3805231257", "irina.mikhalchenko@gmail.com", territories.get(5)) );
        mockData.add( new Contact(2, "Vlad", "Kurchenko", "+384234312", "vlad.kurchenko@gmail.com", territories.get(6)) );
        mockData.add( new Contact(3, "Alex", "Ischenko", "+383498231", "alex.ishchenko@gmail.com", territories.get(7)) );
    }


}
