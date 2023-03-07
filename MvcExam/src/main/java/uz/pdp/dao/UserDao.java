package uz.pdp.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import uz.pdp.dto.UserDto;
import uz.pdp.entity.UserEntity;

import java.util.List;

@Repository
public class UserDao implements BaseDao<UserEntity, UserDto> {
    private final SessionFactory sessionFactory;

    public UserDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public UserEntity getById(int id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        UserEntity user = session.get(UserEntity.class, id);
        closeSession(session);
        return user;
    }

    @Override
    public UserEntity add(UserEntity user) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(user);
        closeSession(session);
        return user;
    }

    @Override
    public List<UserEntity> getList() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<UserEntity> list = session.createNativeQuery("Select * from users", UserEntity.class).getResultList();
        closeSession(session);
        return list;
    }

    @Override
    public void delete(UserDto user) {
        Session session = sessionFactory.openSession();
        session.delete(user);
        closeSession(session);
    }
}
