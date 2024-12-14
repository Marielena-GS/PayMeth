package ec.edu.uce.jpa;

import jakarta.persistence.*;

@Entity
@Table(name = "State")
public class State {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "State_Product")
    private String state_product;

    public State() { }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getState_product() {
        return state_product;
    }

    public void setState_product(String state_product) {
        this.state_product = state_product;
    }
}
