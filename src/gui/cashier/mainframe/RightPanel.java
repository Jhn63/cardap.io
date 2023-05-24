package gui.cashier.mainframe;

import client.cashier.Cashier;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RightPanel extends JPanel implements ActionListener {
    private Cashier manager;
    private MainFrame frame;

    private JButton openButton;
    private JButton closeButton;
    private JButton launchButton;
    private JButton cancelButton;
    //private JButton registerButton;

    public RightPanel(Cashier manager, MainFrame frame) {
        this.manager = manager;
        this.frame = frame;

        this.setLayout(new BorderLayout());
        this.setOpaque(true);
        this.setBackground(Color.GRAY);
        this.setPreferredSize(new Dimension(600,700));

        this.add(setButtons(), BorderLayout.EAST);
    }

    //adicionado botões ao painel
    private JPanel setButtons() {
        JPanel panel = new JPanel(new FlowLayout());
        panel.setPreferredSize(new Dimension(400,700));
        panel.setOpaque(false);

        //registerButton = new JButton("register");
        //registerButton.setPreferredSize(new Dimension(85,85));
        //registerButton.addActionListener(this);
        //registerButton.setFocusable(false);

        openButton = new JButton("open");
        openButton.setPreferredSize(new Dimension(85,85));
        openButton.addActionListener(this);
        openButton.setFocusable(false);

        closeButton = new JButton("close");
        closeButton.setPreferredSize(new Dimension(85,85));
        closeButton.addActionListener(this);
        closeButton.setFocusable(false);

        launchButton = new JButton("launch");
        launchButton.setPreferredSize(new Dimension(85,85));
        launchButton.addActionListener(this);
        launchButton.setFocusable(false);

        cancelButton = new JButton("cancel");
        cancelButton.setPreferredSize(new Dimension(85,85));
        cancelButton.addActionListener(this);
        cancelButton.setFocusable(false);

        //panel.add(registerButton);
        panel.add(cancelButton);
        panel.add(launchButton);
        panel.add(closeButton);
        panel.add(openButton);
        return panel;
    }

    //utilizar exceções para tratar operações proibidas
    // tratamento ações dos botões
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == openButton) {
            //precisa de tratamento para null e strings não numericas
            String table = JOptionPane.showInputDialog("Abrir Mesa");
            manager.openTable(Integer.parseInt(table));
            frame.getLeftPanel().printTables();
        }
        else if (e.getSource() == closeButton) {
            String table = JOptionPane.showInputDialog("Fechar Mesa");
            manager.closeTable(Integer.parseInt(table));
            frame.getLeftPanel().printTables();
        }
        else if (e.getSource() == launchButton) {
            String table = JOptionPane.showInputDialog("Codigo da Mesa");
            String request = JOptionPane.showInputDialog("Codigo do Produto");

            manager.launchProduct(Integer.parseInt(table), Integer.parseInt(request));
            frame.getLeftPanel().showTableRequests(Integer.parseInt(table)); //inlegivel
        }
        else if (e.getSource() == cancelButton) {
            String table = JOptionPane.showInputDialog("Codigo da Mesa");
            String request = JOptionPane.showInputDialog("Codigo do Produto");

            manager.cancelRequest(Integer.parseInt(table), Integer.parseInt(request));
            frame.getLeftPanel().showTableRequests(Integer.parseInt(table)); //inlegivel
        }
    }
}
