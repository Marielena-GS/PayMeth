package ec.edu.uce.jpa;

import jakarta.persistence.*;

@Entity
@Table(name = "User")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "User_name")
    private String name;
    @Column(name= "User_Last_Name")
    private String lastName;
}
