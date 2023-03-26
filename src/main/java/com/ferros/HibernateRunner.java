package com.ferros;

import com.ferros.entity.User;
import com.ferros.util.HibernateUtil;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;

@Slf4j
public class HibernateRunner {
//    private static final Logger log = LoggerFactory.getLogger(HibernateRunner.class);
    public static void main(String[] args) throws SQLException {
        User user = User.builder()
                .username("ivan@gmail.com")
                .lastname("Ivanov")
                .firstname("Ivan")
                .build();

        log.info("User entity is in transient state? object: {}", user);


        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory()){
            Session session1 = sessionFactory.openSession();
             try(session1) {
                 Transaction transaction = session1.beginTransaction();

                 log.trace("Transaction is crated? {}", transaction);
                session1.saveOrUpdate(user);
                 log.warn("User is in persistent state: {}, session {}", user, session1);

                session1.getTransaction().commit();
            }catch (Exception exception){
                 log.error("Exception occurred", exception);
                 throw exception;
             }

        }


    }
}
