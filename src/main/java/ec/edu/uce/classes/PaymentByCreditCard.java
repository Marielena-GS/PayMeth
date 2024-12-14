package ec.edu.uce.classes;

import ec.edu.uce.interfaces.IPay;
import ec.edu.uce.interfaces.QualifierPayment;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
@QualifierPayment("card")
public class PaymentByCreditCard implements IPay {

    @PostConstruct
    public void init() {
        System.out.println("Credit Card pay notification init");
    }
    @PreDestroy
    public void destroy() {
        System.out.println("Credit Card pay notification destroy");
    }

    @Override
    public String sendPayNotify(Record record, String message) {
        record.setMessage(message);
        return record.getdata();
    }
}
