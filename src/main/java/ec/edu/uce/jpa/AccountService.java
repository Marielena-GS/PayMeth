package ec.edu.uce.jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class AccountService {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("AccountPU");
    private EntityManager em;

    public AccountService(){
        em = emf.createEntityManager();
    }

    public void save(Account account){
        em.getTransaction().begin();
        em.persist(account);
        em.getTransaction().commit();
    }

    public Account getAccount(int id) {
        return em.find(Account.class, id);
    }
}
