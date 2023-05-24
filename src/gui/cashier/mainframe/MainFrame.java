package gui.cashier.mainframe;

import client.cashier.Cashier;

import javax.swing.*;
import java.awt.*;

/** Frame principal (primeiro frame ao abrir)
 */
public class MainFrame extends JFrame {
    private Cashier manager;

    private LeftPanel leftPanel;
    private RightPanel rightPanel;

    public MainFrame(String name, Cashier manager) {
        super(name);
        this.manager = manager;

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setResizable(false);
        this.setSize(1000,700);

        leftPanel = new LeftPanel(manager,this);
        rightPanel = new RightPanel(manager,this);

        this.add(rightPanel,BorderLayout.EAST);
        this.add(leftPanel,BorderLayout.WEST);
        this.setVisible(true);
    }

    public LeftPanel getLeftPanel() {
        return leftPanel;
    }

    public RightPanel getRightPanel() {
        return rightPanel;
    }

}
