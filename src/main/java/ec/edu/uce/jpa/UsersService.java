package ec.edu.uce.jpa;

import ec.edu.uce.interfaces.QualifierPayment;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.util.List;
@ApplicationScoped
@QualifierPayment("UsersService")
public class UsersService {

    private EntityManager em;
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("Persistence");

    public UsersService()
    {
        this.em = emf.createEntityManager();
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

    public List<User> findByJoin(){
        TypedQuery<User> query = em.createQuery("SELECT u FROM User u JOIN u.account", User.class);
        return query.getResultList();
    }

}
