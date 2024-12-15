package ec.edu.uce.jpa;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "State")
public class State {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "State_Account")
    private String state_account;

    public State() { }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getState_account() {
        return state_account;
    }

    public void setState_account(String state_account) {
        this.state_account = state_account;
    }
}
