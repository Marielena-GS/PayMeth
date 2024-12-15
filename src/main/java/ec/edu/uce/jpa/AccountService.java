package ec.edu.uce.jpa;

import ec.edu.uce.interfaces.QualifierPayment;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.util.List;
@ApplicationScoped
@QualifierPayment("AccountService")
public class AccountService {

    private EntityManager em;
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("Persistence");

    public AccountService()
    {
        this.em = emf.createEntityManager();
    }

    public Account createAccount(Account account) {
        em.getTransaction().begin();
        em.persist(account);
        em.getTransaction().commit();
        return account;
    }

    public Account findByID(int id) {
        return em.find(Account.class, id);
    }

    public List<Account> findAll(){
        String query = "SELECT a FROM Account a";
        return em.createQuery(query,Account.class).getResultList();
    }

    public void updateAccount(Account account) {
        em.getTransaction().begin();
        em.merge(account);
        em.getTransaction().commit();
    }

    public void deleteById(int id) {
        Account account = findByID(id);
        em.getTransaction().begin();
        em.remove(account);
        em.getTransaction().commit();
    }

    public List<Account> findByJoin(){
        TypedQuery<Account> query = em.createQuery("SELECT a FROM Account a JOIN a.state", Account.class);
        return query.getResultList();
    }
}
