package org.example.dao;

import org.hibernate.Session;

import java.util.List;

public interface Base<T> {
    List<T>listAll();
    boolean addObject(T t);
    boolean delete(int id);
    boolean update(T t);
    default void closeSession(Session session){
        session.getTransaction().commit();
        session.close();
    }
}
