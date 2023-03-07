package org.example.dao;

import lombok.RequiredArgsConstructor;
import org.example.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class UserDao implements Base<User> {
    @Qualifier("localSessionFactoryBean")
    private final SessionFactory sessionFactory;

    @Autowired
    public UserDao(@Qualifier(value = "localSessionFactoryBean") SessionFactory sessionFactory,JavaMailSender javaMailSender) {
        this.sessionFactory = sessionFactory;
        this.javaMailSender=javaMailSender;
    }
    @Autowired
    private final JavaMailSender javaMailSender;

    @Override
    public List<User> listAll() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<User> userList = session.createNativeQuery("select * from users", User.class).getResultList();
        closeSession(session);
        return userList;
    }

    @Override
    public boolean addObject(User user) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        try {
            user.setEmailCode(UUID.randomUUID().toString());
            session.save(user);
            sendEmail(user.getEmail(), user.getEmailCode());
        } catch (Exception e) {
            return false;
        }
        closeSession(session);
        return true;
    }

    public boolean sendEmail(String email, String emailCode){
        try {
            SimpleMailMessage simpleMailMessage=new SimpleMailMessage();
            simpleMailMessage.setFrom("bekzod@gaiml.com");
            simpleMailMessage.setTo(email);
            simpleMailMessage.setSubject("Keldi kod");
            simpleMailMessage.setText("https://online.pdp.uz/course-play/malumotlar-ombori-asosida-authentication-va-authorization-qilish-emailga-xabar-yuborish-auditing-qilish");
            javaMailSender.send(simpleMailMessage);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public User getUser(int id){
        for (User user:listAll()){
            if (user.getId()==id){
                return user;
            }
        }
        return null;
    }

    @Override
    public boolean delete(int id) {
        Session session=sessionFactory.openSession();
        session.beginTransaction();
        try {

            session.delete(getUser(id));
        }catch (Exception e){
            return false;
        }
        closeSession(session);
        return true;
    }


    @Override
    public boolean update(User user) {
        Session session=sessionFactory.openSession();
        session.beginTransaction();
        try {
            session.update(user);
        }catch (Exception e){
            return false;
        }
        closeSession(session);
        return true;
    }
}
