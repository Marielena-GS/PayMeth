package ec.edu.uce.jpa;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "\"User\"")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "User_name")
    private String name;
    @Column(name= "User_Last_Name")
    private String lastName;

    @OneToMany
    @JoinColumn(name = "id_user")
    List<Account> account;

    public List<Account> getAccount()
    {
        return account;
    }

    public void setAccount(List<Account> account) {
        this.account = account;
    }

    public User() { }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
