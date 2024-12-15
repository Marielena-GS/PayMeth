package ec.edu.uce.classes;

import ec.edu.uce.interfaces.IPay;
import ec.edu.uce.interfaces.QualifierPayment;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
@QualifierPayment("card")
public class PaymentByCreditCard implements IPay
{
    @Override
    public String sendPayNotify(Record record)
    {
        return record.getdata();
    }
}
