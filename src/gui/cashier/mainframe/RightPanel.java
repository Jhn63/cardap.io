package gui.cashier.mainframe;

import client.cashier.Cashier;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class RightPanel extends JPanel implements ActionListener, KeyListener {
    private Cashier manager;

    private JTextField tableField;
    private JTextField requestField;

    private JButton openButton;
    private JButton closeButton;
    private JButton launchButton;
    private JButton cancelButton;

    public RightPanel(Cashier manager) {
        this.manager = manager;

        this.setLayout(new BorderLayout());
        this.setOpaque(true);
        this.setBackground(Color.GRAY);
        this.setPreferredSize(new Dimension(600,700));

        this.add(setTextFields(), BorderLayout.WEST);
        this.add(setButtons(), BorderLayout.EAST);
    }

    //adicionado botões ao painel
    private JPanel setButtons() {
        JPanel panel = new JPanel(new FlowLayout());
        panel.setOpaque(false);

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

        panel.add(cancelButton);
        panel.add(launchButton);
        panel.add(closeButton);
        panel.add(openButton);
        return panel;
    }

    //adiconando campos de entrada de texto
    private JPanel setTextFields() {
        JPanel panel = new JPanel(new FlowLayout());
        panel.setOpaque(false);

        tableField = new JTextField();
        tableField.setEditable(false);
        tableField.addKeyListener(this);
        tableField.setPreferredSize(new Dimension(70,40));

        requestField = new JTextField();
        requestField.setEditable(false);
        requestField.addKeyListener(this);
        requestField.setPreferredSize(new Dimension(100,40));

        panel.add(tableField);
        panel.add(requestField);
        return panel;
    }

    // tratamento ações dos botões
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == openButton) {
            tableField.setEditable(true);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    // tratamento de ações dos campod de texto
    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == 10) {
            String tableID = tableField.getText();
            tableField.setText("");
            tableField.setEditable(false);

            manager.openTable(Integer.parseInt(tableID));
        }
    }
}
