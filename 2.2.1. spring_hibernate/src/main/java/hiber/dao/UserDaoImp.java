package hiber.dao;

import hiber.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

    @Override
    public List<User> findUsersByCarModelAndSeries(String model, int series) {
        Session session = sessionFactory.getCurrentSession();

        Query query = session.createQuery(" FROM User u where u.car.model = :modelCar and u.car.series = :seriesCar");
        query.setParameter("modelCar", model);
        query.setParameter("seriesCar", series);

        return query.getResultList();
    }

}
