package ec.edu.uce.paymeth;
import ec.edu.uce.classes.PaymentByCreditCard;
import ec.edu.uce.interfaces.IPay;
import ec.edu.uce.interfaces.QualifierPayment;
import ec.edu.uce.classes.Record;
import ec.edu.uce.jpa.*;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.ws.rs.*;
import org.hibernate.internal.build.AllowSysOut;

import java.util.ArrayList;
import java.util.List;

@Path("/pay")
public class HelloResource {

    @Inject
    Record record;

    @Inject
    @QualifierPayment("card")
    IPay cardPay;

    @Inject
    @QualifierPayment("paypal")
    IPay paypalPay;

    @Inject
    @QualifierPayment("transfer")
    IPay transferPay;

    @Inject
    @QualifierPayment("ProductService")
    ProductService productService;

    @Inject
    @QualifierPayment("UsersService")
    UsersService usersService;

    @Inject
    @QualifierPayment("StateService")
    StateService stateService;

    @Inject
    @QualifierPayment("AccountService")
    AccountService accountService;

    @GET
    @Produces("text/plain")
    @Path("/createuser")
    public String saveUser(@QueryParam("name") String name,
                           @QueryParam("lastname") String lastname,
                           @QueryParam("type_account") String type,
                           @QueryParam("email") String email,
                           @QueryParam("phone_user") String phone)
    {
        User nuser = new User();
        List<Account> accounts = new ArrayList<>();
        Account account = new Account();
        nuser.setName(name);
        nuser.setLastName(lastname);
        account.setType(type);
        account.setEmail(email);
        account.setPhone(phone);
        accounts.add(account);
        nuser.setAccount(accounts);

        if (name != null && lastname != null)
        {
            usersService.createUser(nuser);
            accountService.createAccount(account);
        }
        List<User> users =  usersService.findByJoin();
        StringBuilder sb = new StringBuilder();

        for (User user : users) {
            sb.append(user.getId())
                    .append(" ")
                    .append(user.getName())
                    .append(" ")
                    .append(user.getLastName())
                    .append("\n");
        }
        return sb.toString();
    }

    @GET
    @Produces("text/plain")
    @Path("/createproduct")
    public String saveProduct(@QueryParam("code") int code,
                           @QueryParam("nameproduct") String name,
                           @QueryParam("price") double price)
    {
        Product product = new Product();
        product.setCode(code);
        product.setName(name);
        product.setPrice(price);

        if (code != 0 && name != null && price != 0)
        {
            productService.createProduct(product);
        }
        List<Product> products =  productService.findAll();
        StringBuilder sb = new StringBuilder();

        for (Product product1 : products) {
            sb.append(product1.getId())
                    .append(" ")
                    .append(product1.getCode())
                    .append(" ")
                    .append(product1.getName())
                    .append(" ")
                    .append(product1.getPrice())
                    .append("\n");
        }
        return sb.toString();
    }

    @GET
    @Produces("text/plain")
    @Path("/creditcard")
    public String emailNotification() {
        return paypalPay.sendPayNotify(record, "paypal pay");

    }

    @GET
    @Produces("text/plain")
    @Path("/paypal")
    public String SMSNotification() {
        return paypalPay.sendPayNotify(record, "paypal pay");

    }

    @GET
    @Produces("text/plain")
    @Path("/transfer")
    public String pushNotification() {
        return transferPay.sendPayNotify(record,"transfer pay");

    }

}

