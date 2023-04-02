package com.ferros;

import com.ferros.entity.Birthday;
import com.ferros.entity.PersonalInfo;
import com.ferros.entity.User;
import com.ferros.util.HibernateUtil;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.time.LocalDate;

@Slf4j
public class HibernateRunner {
//    private static final Logger log = LoggerFactory.getLogger(HibernateRunner.class);
    public static void main(String[] args) throws SQLException {
        User user = User.builder()
                .username("petr@gmail.com")
                .personalInfo(PersonalInfo.builder()
                        .lastname("Petrov")
                        .firstname("Petr")
                        .birthDate(new Birthday(LocalDate.of(200,1,19)))
                        .build())
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
                try(Session session = sessionFactory.openSession()){
                    PersonalInfo key = PersonalInfo.builder()
                            .lastname("Petrov")
                            .firstname("Petr")
                            .birthDate(new Birthday(LocalDate.of(200, 1, 19)))
                            .build();
                    User user2 = session.get(User.class, key);
                    System.out.println();
                }
            }catch (Exception exception){
                 log.error("Exception occurred", exception);
                 throw exception;
             }

        }


    }
}
