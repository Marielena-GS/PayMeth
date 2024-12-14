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

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("Persistence");
    private EntityManager em = emf.createEntityManager();

    @GET
    @Produces("text/plain")
    @Path("/creditcard")
    public String saveUser(@QueryParam("id") int id,
                           @QueryParam("name") String name,
                           @QueryParam("lastname") String lastname)
    {
        UsersService usersService = new UsersService(em);
        List<User> users =  usersService.findAll();

        User nuser = new User();
        nuser.setName(name);
        nuser.setLastName(lastname);

        if (id != 0)
        {
            usersService.createUser(nuser);
        }

        StringBuilder sb = new StringBuilder();

        for (User user : users) {
            sb.append(user.getId())
                    .append(" ")
                    .append(user.getName())
                    .append(" ")
                    .append(user.getLastName())
                    .append("\n");
        }
        usersService.createUser(nuser);
        return sb.toString();
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

