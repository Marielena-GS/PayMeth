package ec.edu.uce.jpa;

import ec.edu.uce.interfaces.QualifierPayment;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;
@ApplicationScoped
@QualifierPayment("ProductService")
public class ProductService {

    private EntityManager em;
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("Persistence");

    public ProductService()
    {

        this.em = emf.createEntityManager();
    }

    public Product createProduct(Product product) {
        em.getTransaction().begin();
        em.persist(product);
        em.getTransaction().commit();
        return product;
    }

    public Product findByID(int id) {
        return em.find(Product.class, id);
    }

    public List<Product> findAll(){
        String query = "SELECT p FROM Product p";
        return em.createQuery(query,Product.class).getResultList();
    }

    public void updateUser(Product product) {
        em.getTransaction().begin();
        em.merge(product);
        em.getTransaction().commit();
    }

    public void deleteById(int id) {
        Product product = findByID(id);
        em.getTransaction().begin();
        em.remove(product);
        em.getTransaction().commit();
    }
}
