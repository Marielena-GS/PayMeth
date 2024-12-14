package ec.edu.uce.jpa;

import jakarta.persistence.*;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "Product")
public class Product {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private int id;
    @Column(name = "Code")
    @GeneratedValue(strategy = IDENTITY)
    private int code;
    @Column(name= "Product_Name")
    private String name;
    @Column(name = "Product_Price")
    private double price;

    public Product() { }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
