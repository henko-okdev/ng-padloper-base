package ngdemo.dao.mock;

import ngdemo.dao.GenericDao;
import ngdemo.domain.Territory;

import java.util.ArrayList;
import java.util.List;

public class MockTerritoryDao implements GenericDao<Territory> {

    private static List<Territory> mockdata = new ArrayList<Territory>();

    public Territory fetch(int id) {
        return mockdata.get(id);
    }

    public List<Territory> find() {
        return mockdata;
    }

    public Territory create(Territory entity) {
        mockdata.add(entity);
        return entity;
    }

    public Territory update(Territory entity) {
        System.out.println(entity);

        Territory toUpdate = this.fetch(entity.getId());
        toUpdate.setName( entity.getName() );
        toUpdate.setType( entity.getType() );
        toUpdate.setZip( entity.getZip() );
        toUpdate.setParent( entity.getParent() );

        return toUpdate;
    }

    public Territory delete(int id) {
        return mockdata.remove(id);
    }

    static {
        Territory country1 = new Territory(0, "Ukraine", "country", null);
        Territory country2 = new Territory(1, "England", "country", null);
        Territory country3 = new Territory(2, "Russia", "country", null);
        Territory country4 = new Territory(3, "USA", "country", null);
        Territory town1 = new Territory(4, "Kiev", "city", country1);
        Territory town2 = new Territory(5, "London", "city", country2);
        Territory town3 = new Territory(6, "Moscow", "city", country3);
        Territory town4 = new Territory(7, "Washington", "city", country4);

        mockdata.add(country1);
        mockdata.add(country2);
        mockdata.add(country3);
        mockdata.add(country4);
        mockdata.add(town1);
        mockdata.add(town2);
        mockdata.add(town3);
        mockdata.add(town4);
    }
}
