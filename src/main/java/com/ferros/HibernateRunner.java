package com.ferros;

import com.ferros.converter.BirthdayConverter;
import com.ferros.entity.Birthday;
import com.ferros.entity.Role;
import com.ferros.entity.User;
import com.ferros.util.HibernateUtil;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.SQLException;
import java.time.LocalDate;

public class HibernateRunner {
    public static void main(String[] args) throws SQLException {
        User user = User.builder()
                .username("ivan@gmail.com")
                .lastname("Ivanov")
                .firstname("Ivan")
                .build();


        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory()){
             try(Session session1 = sessionFactory.openSession()) {
                session1.beginTransaction();
                session1.save(user);

                session1.getTransaction().commit();
            } try(Session session2 = sessionFactory.openSession()) {
                session2.beginTransaction();
                user.setFirstname("Sveta");
//                session2.delete(user);
                session2.refresh(user);
                session2.getTransaction().commit();
            }
        }


    }
}
