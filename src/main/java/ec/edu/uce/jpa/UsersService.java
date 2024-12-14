package ec.edu.uce.jpa;

import jakarta.persistence.EntityManager;

public class UsersService {

    private EntityManager em;

    public UsersService(EntityManager em)
    {
        this.em = em;
    }

    public User createUser(int id, String name, String lastName) {
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setLastName(lastName);
        em.persist(user);//actualiza todos los datos
        return user;
    }

    public User findByID(int id) {
        return em.find(User.class, id);
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
