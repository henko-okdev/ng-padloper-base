package ngdemo.service;


import java.util.List;

public interface GenericService <T>{

    T fetch(int id);
    List<T> find();
    T create(T entity);
    T update(T entity);
    T delete(int id);

}
