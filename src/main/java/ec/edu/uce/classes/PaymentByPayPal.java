package ec.edu.uce.classes;

import ec.edu.uce.interfaces.IPay;
import ec.edu.uce.interfaces.QualifierPayment;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
@QualifierPayment("paypal")
public class PaymentByPayPal implements IPay {

    @PostConstruct
    public void init() {
        System.out.println("PayPal pay notification init");
    }
    @PreDestroy
    public void destroy() {
        System.out.println("PayPal pay notification destroy");
    }


    @Override
    public String sendPayNotify(Record record, String menssage) {
        return record.getdata();
    }
}
