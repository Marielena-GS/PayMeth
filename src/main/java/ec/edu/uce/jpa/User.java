package ec.edu.uce.jpa;

import jakarta.persistence.*;

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
