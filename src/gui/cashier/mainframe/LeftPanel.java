package gui.cashier.mainframe;

import client.cashier.Cashier;
import client.cashier.DataBase;
import client.tools.Product;
import client.tools.Table;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class LeftPanel extends JPanel{
    Cashier manager;
    MainFrame frame;

    public LeftPanel (Cashier manager, MainFrame frame) {
        this.manager = manager;
        this.frame = frame;

        this.setLayout(new FlowLayout());
        this.setOpaque(true);
        this.setBackground(Color.lightGray);
        this.setPreferredSize(new Dimension(400,700));
    }

    //recebe uma lista com as labels das mesas abertas
    //e printa no painel
    public void printTables() {
        this.removeAll();

        DataBase base = manager.getBase();
        LinkedList<Table> tables = base.getAllTables();
        for (Table t : tables) {
            JLabel label = new JLabel(String.valueOf(t.getID()));
            label.setOpaque(true);
            label.setBackground(Color.RED);
            label.setPreferredSize(new Dimension(50,50));
            this.add(label);
        }
        frame.repaint();
        frame.setVisible(true);
    }

    public void showTableRequests(int tableID) {
        this.removeAll();
        DataBase base = manager.getBase();

        Table table = base.getTable(tableID);
        LinkedList<Product> requests = table.getRequests();
        for (Product p : requests) {

            JLabel label = new JLabel(p.getName());
            label.setOpaque(true);
            label.setBackground(Color.white);
            label.setPreferredSize(new Dimension(400,50));
            this.add(label);
        }
        frame.repaint();
        frame.setVisible(true);
    }
}
