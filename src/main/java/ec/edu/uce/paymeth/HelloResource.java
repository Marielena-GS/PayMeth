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

    @Inject
    @QualifierPayment("PaymentsService")
    PaymentsService paymentsService;

    @GET
    @Produces("text/plain")
    @Path("/createuser")
    public String saveUser(@QueryParam("name") String name,
                           @QueryParam("lastname") String lastname,
                           @QueryParam("type_account") String type,
                           @QueryParam("email") String email,
                           @QueryParam("phone_user") String phone,
                           @QueryParam("state_account") String state_account)
    {
        User nuser = new User();
        List<Account> accounts = new ArrayList<>();
        Account account = new Account();
        State state = new State();

        if (name != null && lastname != null)
        {
            state.setState_account(state_account);
            nuser.setName(name);
            nuser.setLastName(lastname);
            account.setType(type);
            account.setEmail(email);
            account.setPhone(phone);
            account.setState(state);
            accounts.add(account);
            nuser.setAccount(accounts);
        }
        else
        {
            state.setState_account("Cancel");
            nuser.setName("John");
            nuser.setLastName("Fuentes");
            account.setType("Ahorros");
            account.setEmail("jfuente@uce.edu.ec");
            account.setPhone("0985402174");
            account.setState(state);
            accounts.add(account);
            nuser.setAccount(accounts);

            state.setState_account("Pagada");
            nuser.setName("John");
            nuser.setLastName("Freire");
            account.setType("Corriente");
            account.setEmail("jfreire@uce.edu.ec");
            account.setPhone("0995412154");
            account.setState(state);
            accounts.add(account);
            nuser.setAccount(accounts);
        }
        stateService.createState(state);
        accountService.createAccount(account);
        usersService.createUser(nuser);

        List<User> users =  usersService.findByJoin();
        StringBuilder sb = new StringBuilder();

        for (User user : users) {
             sb.append("- - - - - - - - - - - - -")
                    .append("\n    * New User *")
                    .append("\n- - - - - - - - - - - - -\n")
                    .append(" - Id User: ")
                    .append(user.getId())
                    .append("\n - Name: ")
                    .append(user.getName())
                    .append("\n - Last Name: ")
                    .append(user.getLastName())
                    .append("\n");

            for(Account a : user.getAccount())
            {
                sb.append("- - - - - - - - - - - - -")
                        .append("\n    * User Account * ")
                        .append("\n- - - - - - - - - - - - -")
                        .append("\n - Id Account: ")
                        .append(a.getId())
                        .append("\n - Type Account: ")
                        .append(a.getType())
                        .append("\n - Email User: ")
                        .append(a.getEmail())
                        .append("\n - Phone: ")
                        .append(a.getPhone())
                        .append("\n")
                        .append(" - State: ")
                        .append(a.getState().getState_account())
                        .append("\n");
            }
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
            product.setCode(code);
            product.setName(name);
            product.setPrice(price);
        }
        else
        {
           product.setCode(154752);
           product.setName("Italian Ham");
           product.setPrice(3.85);

            product.setCode(137752);
            product.setName("Sliced Mortadella");
            product.setPrice(1.75);
        }
        productService.createProduct(product);

        List<Product> products =  productService.findAll();
        StringBuilder sb = new StringBuilder();

        for (Product product1 : products) {
            sb.append("- - - - - - - - - - - - - - -")
                    .append("\n    * Products * ")
                    .append("\n- - - - - - - - - - - - - - -\n")
                    .append("       ")
                    .append(product1.getId())
                    .append("  |  ")
                    .append(product1.getCode())
                    .append("\n - Name Product: ")
                    .append(product1.getName())
                    .append("\n - Price Product: ")
                    .append(product1.getPrice())
                    .append("\n");
        }
        return sb.toString();
    }

    @GET
    @Produces("text/plain")
    @Path("/creditcard")
    public String CreditCard(@QueryParam("id_user") int id,
                             @QueryParam("list_products") List<Integer> id_product)
    {
        User user;
        List<Product> products = new ArrayList<>();
        Payments payments = new Payments();

        if(id != 0 && id_product != null)
        {
            user = usersService.findByID(id);
            payments.setId_user(user);

            for(Integer i : id_product)
            {
                products.add(productService.findByID(i));
            }
        }
        else
        {
            user = usersService.findByID(1);
            products.add(productService.findByID(1));
        }

        record.setProduct(products);
        record.setUser(user);
        payments.setPayment_method(" * Billing Payment * ");
        paymentsService.createPay(payments);
        record.setReturnToMethod("\n  * Credit Card Payment * ");
        String data = cardPay.sendPayNotify(record);
        return data;
    }

    @GET
    @Produces("text/plain")
    @Path("/paypal")
    public String PayPal(@QueryParam("id_user") int id,
                         @QueryParam("list_products") List<Integer> id_product)
    {


        User user;
        List<Product> products = new ArrayList<>();
        Payments payments = new Payments();

        if(id != 0 && id_product != null)
        {
            user = usersService.findByID(id);
            payments.setId_user(user);

            for(Integer i : id_product)
            {
                products.add(productService.findByID(i));
            }
        }
        else
        {
            user = usersService.findByID(1);
            products.add(productService.findByID(2));
        }

        record.setProduct(products);
        record.setUser(user);
        payments.setPayment_method(" * Billing Payment * ");
        paymentsService.createPay(payments);
        record.setReturnToMethod("\n  * PayPal Payment * ");
        String data = paypalPay.sendPayNotify(record);

        return data;
    }

    @GET
    @Produces("text/plain")
    @Path("/transfer")
    public String Transfer(@QueryParam("id_user") int id,
                           @QueryParam("list_products") List<Integer> id_product)
    {
        User user;
        List<Product> products = new ArrayList<>();
        Payments payments = new Payments();

        if(id != 0 && id_product != null)
        {
            user = usersService.findByID(id);
            payments.setId_user(user);

            for(Integer i : id_product)
            {
                products.add(productService.findByID(i));
            }
        }
        else
        {
            user = usersService.findByID(1);
            products.add(productService.findByID(1));
        }

        record.setProduct(products);
        record.setUser(user);
        payments.setPayment_method(" * Billing Payment * ");
        paymentsService.createPay(payments);
        record.setReturnToMethod("\n  * Transfer Payment * ");
        String data = transferPay.sendPayNotify(record);

        return data;
    }
}

