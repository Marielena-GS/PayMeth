package ec.edu.uce.jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.lang.reflect.Type;
import java.util.List;

public class UsersService {

    private EntityManager em;

    public UsersService(EntityManager em)
    {
        this.em = em;
    }

    public User createUser(User user) {
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
        return user;
    }

    public User readUser(int id)
    {
        return em.find(User.class, id);
    }

    public User findByID(int id) {
        return em.find(User.class, id);
    }

    public List<User> findAll(){
        String query = "SELECT u FROM User u";
        return em.createQuery(query, User.class).getResultList();
    }

    public void updateUser(User user) {
        em.getTransaction().begin();
        em.merge(user);
        em.getTransaction().commit();
    }

    public void deleteById(int id) {
        User user = findByID(id);
        em.getTransaction().begin();
        em.remove(user);
        em.getTransaction().commit();
    }
}
