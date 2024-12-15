package ec.edu.uce.classes;

import ec.edu.uce.jpa.Account;
import ec.edu.uce.jpa.Product;
import ec.edu.uce.jpa.User;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.Singleton;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
@Singleton
public class Record
{
    private User user;
    private List<Product> product;
    private double value;
    private String returnToMethod;

    @PostConstruct
    public void init() { }

    public Record(){ }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getReturnToMethod() {
        return returnToMethod;
    }

    public void setReturnToMethod(String returnToMethod) {
        this.returnToMethod = returnToMethod;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Product> getProduct() {
        return product;
    }

    public void setProduct(List<Product> product) {
        this.product = product;
    }

    public String getdata()
    {
        StringBuilder sb = new StringBuilder();
        sb.append(user.getId())
                .append(user.getId())
                .append(user.getName())
                .append(user.getLastName());

        for(Account a : getUser().getAccount())
        {
            sb.append(a.getId())
                    .append(a.getType())
                    .append(a.getEmail())
                    .append(a.getPhone())
                    .append(a.getState().getState_account());
        }

        for(Product p : getProduct())
        {
            sb.append(p.getId())
                    .append(p.getCode())
                    .append(p.getName())
                    .append(p.getPrice());

            value += p.getPrice();
        }
        sb.append(value);
        sb.append(getReturnToMethod());
        return sb.toString();
    }
}

