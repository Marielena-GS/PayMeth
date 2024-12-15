package ec.edu.uce.jpa;

import ec.edu.uce.interfaces.QualifierPayment;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.util.List;
@ApplicationScoped
@QualifierPayment("PaymentsService")
public class PaymentsService
{

    private EntityManager em;
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("Persistence");

    public PaymentsService()
    {
        this.em = emf.createEntityManager();
    }

    public Payments createPay(Payments payments) {
        em.getTransaction().begin();
        em.persist(payments);
        em.getTransaction().commit();
        return payments;
    }

    public Payments findByID(int id) {
        return em.find(Payments.class, id);
    }

    public List<Payments> findAll(){
        String query = "SELECT pay FROM Payments pay";
        return em.createQuery(query, Payments.class).getResultList();
    }

    public void updatePay(Payments payments) {
        em.getTransaction().begin();
        em.merge(payments);
        em.getTransaction().commit();
    }

    public void deleteById(int id) {
        Payments payments = findByID(id);
        em.getTransaction().begin();
        em.remove(payments);
        em.getTransaction().commit();
    }

    public List<Payments> findByJoin(){
        TypedQuery<Payments> query = em.createQuery("SELECT pay FROM Payments pay JOIN pay.id_user", Payments.class);
        return query.getResultList();
    }
}
