package ec.edu.uce.jpa;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table( name = "Payments")
public class Payments
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_bill;

    @OneToOne
    @JoinColumn(name = "id_user")

    private User id_user;
    private String payment_method;
    private Date date_done = new Date();

    public Payments() { }

    public int getId_bill() {
        return id_bill;
    }

    public void setId_bill(int id_bill) {
        this.id_bill = id_bill;
    }

    public User getId_user() {
        return id_user;
    }

    public void setId_user(User id_user) {
        this.id_user = id_user;
    }

    public String getPayment_method() {
        return payment_method;
    }

    public void setPayment_method(String payment_method) {
        this.payment_method = payment_method;
    }

    public Date getDate_done() {
        return date_done;
    }

    public void setDate_done(Date date_done) {
        this.date_done = date_done;
    }
}
