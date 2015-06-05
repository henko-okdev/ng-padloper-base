package ngdemo.dao;

import ngdemo.domain.Territory;

import java.util.List;

public interface GenericDao <T>{

    T fetch(int id);
    List<T> find();
    T create(T entity);
    T update(T entity);
    T delete(int id);

}
