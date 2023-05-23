package gui.cashier.mainframe;

import javax.swing.*;
import java.awt.*;

public class LeftPanel extends JPanel{

    public LeftPanel () {
        this.setLayout(new FlowLayout());
        this.setOpaque(true);
        this.setBackground(Color.lightGray);
        this.setPreferredSize(new Dimension(400,700));
    }
}
