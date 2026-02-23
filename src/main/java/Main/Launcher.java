package Main;

import javax.swing.*;

public class Launcher extends JFrame {
    public Launcher(){
        JPanel mainPanel = new JPanel();

        JButton heatMapButton = new JButton("Heat Map");
        heatMapButton.addActionListener(_ -> {
            this.dispose();
        });
        mainPanel.add(heatMapButton);

        JButton functionGraphButton = new JButton("Function Graph");
        functionGraphButton.addActionListener(_ -> {
            this.dispose();
        });
        mainPanel.add(functionGraphButton);

        this.add(mainPanel);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
