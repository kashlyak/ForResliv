package by.kashlyak.restwebservice.rest_web_service.dao;


import by.kashlyak.restwebservice.rest_web_service.models.City;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.util.List;

@PropertySource("application.properties")
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
            e.printStackTrace();
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
            e.printStackTrace();
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
            e.printStackTrace();
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
            e.printStackTrace();

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
            e.printStackTrace();

        }
    }


}







