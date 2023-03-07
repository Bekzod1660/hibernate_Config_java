package uz.pdp.dao;

import org.hibernate.Session;

import java.util.List;

public interface BaseDao<T,R> {
    T getById(int id);

    T add(T item);

    List<T> getList();

    void delete(R item);

    default void closeSession(Session session){
        session.getTransaction().commit();
        session.close();
    };
}
