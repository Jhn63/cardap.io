package tools;

import java.io.Serializable;

public class Product implements Serializable {
    private int ID;
    private float price;
    private String name;
    private String details;


    public Product(String name, int ID, float p, String d) {
        this.name = name;
        this.ID = ID;
        details = d;
        price = p;
    }

    public String getName() {
        return name;
    }
}
