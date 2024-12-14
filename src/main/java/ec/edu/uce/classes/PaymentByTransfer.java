package ec.edu.uce.classes;

import ec.edu.uce.interfaces.IPay;
import ec.edu.uce.interfaces.QualifierPayment;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped

@QualifierPayment("transfer")
public class PaymentByTransfer implements IPay {

    @PostConstruct
    public void init() {
        System.out.println("Transfer pay notification init");
    }
    @PreDestroy
    public void destroy() {
        System.out.println("Transfer pay notification destroy");
    }

    @Override
    public String sendPayNotify(Record record, String message) {
        record.setMessage(message);
        return record.getdata();
    }

}
