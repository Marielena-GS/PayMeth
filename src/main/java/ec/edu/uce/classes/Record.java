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

         sb.append(("- - - - - - - - - - - - -"))
            .append("\n    * Payment Invoice *")
            .append(("\n- - - - - - - - - - - - -\n"));

        sb.append("User: ")
                .append("\n - Id: ")
                .append(user.getId())
                .append("\n - Name: ")
                .append(user.getName())
                .append("\n - Last Name: ")
                .append(user.getLastName())
                .append("\n");

        for(Account a : getUser().getAccount())
        {
            sb.append("Account User : \n")
                    .append(" - Id: ")
                    .append(a.getId())
                    .append("\n - Type: ")
                    .append(a.getType())
                    .append("\n - Email: ")
                    .append(a.getEmail())
                    .append("\n - Phone: ")
                    .append(a.getPhone())
                    .append("\n- - - - - - - - - - - - -")
                    .append("\n Account State: ")
                    .append(a.getState().getState_account())
                    .append("\n- - - - - - - - - - - - -")
                    .append("\n");

            if(a.getState().getState_account().equals("Cancel"))
            {
                return sb.toString();
            }

        }

        for(Product p : getProduct())
        {
            sb.append("Product User : \n")
                    .append(" - Id: ")
                    .append(p.getId())
                    .append("\n - Code: ")
                    .append(p.getCode())
                    .append("\n - Name: ")
                    .append(p.getName())
                    .append("\n - Price: ")
                    .append(p.getPrice());
            value += p.getPrice();
        }
        sb.append("\n- - - - - - - - - - - - -")
        .append("\n     Total Value: \n")
                .append("         " + value)
                .append(("\n- - - - - - - - - - - - -"))
                .append(getReturnToMethod())
                .append(("\n- - - - - - - - - - - - -"))
                .append("\n----- End of billing -----")
                .append(("\n- - - - - - - - - - - - -\n"));
        return sb.toString();
    }
}

