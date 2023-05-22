package client.tools;

import java.io.Serializable;
import java.util.LinkedList;

/* Mantem armazenado os pedidos feitos naquela mesa */
public class Table implements Serializable {
    private final int ID;
    private LinkedList<Product> requests;

    public Table(int ID) {
        this.ID = ID;
        requests = new LinkedList<>();
    }

    /* Insere um novo pedido a mesa */
    public void insert(Product product) {
        requests.add(product);
    }

    /* Remove um pedido da mesa */
    public void remove(Product product) {
        //NÃO IMPLEMENTADO!!!!!!!!
    }

    public int getID() {
        return ID;
    }

    /* Calcula a montante acumulada dos pedidos */
    public float getTotal() {
        float total = 0;
        for (Product r : requests) {
            float price = r.getPrice();
            total += price;
        }
        return total;
    }
}
