package ec.edu.uce.jpa;

import ec.edu.uce.interfaces.QualifierPayment;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

@ApplicationScoped
@QualifierPayment("StateService")
public class StateService {
    private EntityManager em;
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("Persistence");

    public StateService()
    {
        this.em = emf.createEntityManager();
    }

    public State createState(State state) {
        em.getTransaction().begin();
        em.persist(state);
        em.getTransaction().commit();
        return state;
    }

    public State findByID(int id) {
        return em.find(State.class, id);
    }

    public List<State> findAll(){
        String query = "SELECT s FROM State s";
        return em.createQuery(query,State.class).getResultList();
    }

    public void updateState(State state) {
        em.getTransaction().begin();
        em.merge(state);
        em.getTransaction().commit();
    }

    public void deleteById(int id) {
        State state = findByID(id);
        em.getTransaction().begin();
        em.remove(state);
        em.getTransaction().commit();
    }
}
