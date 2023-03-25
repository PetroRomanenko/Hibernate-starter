package com.ferros;

import com.ferros.converter.BirthdayConverter;
import com.ferros.entity.Birthday;
import com.ferros.entity.Role;
import com.ferros.entity.User;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.SQLException;
import java.time.LocalDate;

public class HibernateRunner {
    public static void main(String[] args) throws SQLException {
//        BlockingQueue<Connection> pool = null;
//        Connection connection=  pull.take
//        SessionFactory
//        Connection connection = DriverManager
//                .getConnection("jdbc:postgresql://localhost:5433/postgres", "postgres", "dr005764");
        Configuration configuration = new Configuration();
//        configuration.setPhysicalNamingStrategy(new CamelCaseToUnderscoresNamingStrategy());
//        configuration.addAnnotatedClass(User.class);
        configuration.addAttributeConverter(new BirthdayConverter());
        configuration.registerTypeOverride(new JsonBinaryType());
        configuration.configure();

        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.openSession()) {

            session.beginTransaction();
//            User user = User.builder()
//                    .username("ivan9@gmail.com")
//                    .firstname("Ivan")
//                    .lastname("Ivanov")
//                    .info("""
//                            {
//                                "name": "Ivan",
//                                "id": 25
//                            }
//                            """)
//                    .birthDate(new Birthday(LocalDate.of(2000, 1, 19)))
//                    .role(Role.ADMIN)
//                    .build();

            User user1 = session.get(User.class, "ivan2@gmail.com");
            user1.setLastname("Petrov");
            session.flush();


//            session.evict(user1);
//            session.clear();
//            session.close();

            session.getTransaction().commit();
        }


    }
}
