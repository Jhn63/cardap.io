package client.tools;

import java.io.Serializable;

/* BIBLIOTECAS PARA CRIAR O XML
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
*/

public class Product implements Serializable {
    private int ID;
    private float price;
    private String name;
    private int quantity;
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
    public int getQuantity() { return quantity; }
    public float getPrice() {
        return price;
    }
    public int getID() {
        return ID;
    }

}
