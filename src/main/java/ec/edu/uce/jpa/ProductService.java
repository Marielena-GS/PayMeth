package ec.edu.uce.jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class ProductService {

    private EntityManager em;

    public ProductService()
    {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Persistence");
        em = emf.createEntityManager();
    }

    public Product createProduct(int id, int code, String name, double price )
    {
        Product product = new Product();
        product.setId(id);
        product.setCode(code);
        product.setName(name);
        product.setPrice(price);
        em.persist(product);//actualiza todos los datos
        return product;
    }

    public Product readProduct(int id)
    {
        return em.find(Product.class, id);
    }

    public Product findByID(int id) {
        return em.find(Product.class, id);
    }

    public void updateProduct(Product product) {
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
