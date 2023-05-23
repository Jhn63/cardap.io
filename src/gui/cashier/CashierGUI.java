package gui.cashier;

import client.cashier.Cashier;
import gui.cashier.mainframe.MainFrame;

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

    public static void main(String[] args) {
        new CashierGUI();
    }
}
