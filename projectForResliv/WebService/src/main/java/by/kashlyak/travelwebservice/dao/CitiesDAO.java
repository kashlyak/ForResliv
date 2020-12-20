package by.kashlyak.travelwebservice.dao;


import by.kashlyak.travelwebservice.models.City;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;


@Service
public class CitiesDAO {

    final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();

    public void saveNewCity(City city) {
        try {
            SessionFactory sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();

            try (Session session = sessionFactory.openSession()) {

                session.beginTransaction();
                session.save(city);
                session.getTransaction().commit();
                session.close();

            }
        } catch (Exception e) {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

    public List<City> allCitiesFromDB() {
        try {
            SessionFactory sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();

            try (Session session = sessionFactory.openSession()) {
                String sql = "From " + City.class.getSimpleName();

                session.beginTransaction();

                List city = session.createQuery("FROM City").list();

                session.getTransaction().commit();
                session.close();
                return city;
            }
        } catch (Exception e) {
            StandardServiceRegistryBuilder.destroy(registry);
            return null;
        }
    }

    public City findByName(String name) {
        try {
            SessionFactory sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();

            try (Session session = sessionFactory.openSession()) {
                session.beginTransaction();

                Query query = session.createQuery("FROM City where name =:name").setParameter("name", name);
                City singleCity = (City) query.getSingleResult();


                session.getTransaction().commit();
                session.close();
                return singleCity;
            }
        } catch (Exception e) {
            StandardServiceRegistryBuilder.destroy(registry);
            return null;
        }
    }

    public void update(String name, City city) {

        try {
            SessionFactory sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            City cityForUpdate = findByName(name);

            try (Session session = sessionFactory.openSession()) {

                int id = city.getId();


                session.beginTransaction();


                Query query = session.createQuery("update City  set name =:newName,description=:newDescription where name=:name");
                query.setParameter("name", cityForUpdate.getName());
                query.setParameter("newName", city.getName());
                query.setParameter("newDescription", city.getDescription());
                query.executeUpdate();


                session.getTransaction().commit();


            }
        } catch (Exception e) {
            StandardServiceRegistryBuilder.destroy(registry);

        }
    }

    public void delete(String name) {
        try {
            SessionFactory sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();

            City cityForDelete = findByName(name);

            try (Session session = sessionFactory.openSession()) {

                session.beginTransaction();
                session.delete(cityForDelete);
                session.getTransaction().commit();


            }
        } catch (Exception e) {
            StandardServiceRegistryBuilder.destroy(registry);

        }
    }


}







