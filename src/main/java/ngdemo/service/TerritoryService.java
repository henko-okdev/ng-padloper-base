package ngdemo.service;

import ngdemo.dao.GenericDao;
import ngdemo.dao.mock.MockTerritoryDao;
import ngdemo.domain.Territory;

import java.util.List;

public class TerritoryService implements GenericService<Territory>{

    private final GenericDao<Territory> dao;

    public TerritoryService() {
        this.dao = new MockTerritoryDao();
    }

    public Territory fetch(int id) {
        return dao.fetch(id);
    }

    public List<Territory> find() {
        return dao.find();
    }

    public Territory create(Territory entity){
        return dao.create(entity);
    }

    public Territory update(Territory entity) {
        return dao.update(entity);
    }

    public Territory delete(int id) {
        return dao.delete(id);
    }
}
