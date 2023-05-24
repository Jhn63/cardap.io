package gui.cashier;

import client.cashier.Cashier;
import client.tools.Product;
import gui.cashier.mainframe.MainFrame;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/** Interface Grafica paro o caixa
 *
 */
public class CashierGUI {
    private Cashier cashier;
    private MainFrame mainFrame;

    public CashierGUI() {
        cashier = new Cashier();
        mainFrame = new MainFrame("Cardap.io", cashier);
    }

    public static void main(String[] args) throws IOException {
        //registrando produto
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("src/client/db/products/test2.bin"));
        Product product = new Product("yakisoba",12,70,null);
        out.writeObject(product);
        out.close();

        new CashierGUI();
    }
}
