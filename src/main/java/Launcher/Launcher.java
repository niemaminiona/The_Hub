package Launcher;

import programs.Function_Graph.FunctionGraph;
import programs.HeatMap.HeatMapWindow;
import Templates.*;
import programs.Light_Simulation.LightSimulationGame;

import javax.swing.*;

public class Launcher extends JFrame {
    public Launcher(){
        JPanel mainPanel = new JPanel();

        JButton heatMapButton = new JButton("Heat Map");
        heatMapButton.addActionListener(_ -> {
            new HeatMapWindow();
            this.dispose();
        });
        mainPanel.add(heatMapButton);

        JButton functionGraphButton = new JButton("Function Graph");
        functionGraphButton.addActionListener(_ -> {
            new FunctionGraph();
            this.dispose();
        });
        mainPanel.add(functionGraphButton);

        JButton lightSimulationButton = new JButton("Light Simulation");
        lightSimulationButton.addActionListener(_ -> {
            new LightSimulationGame();
            this.dispose();
        });
        mainPanel.add(lightSimulationButton);


        this.add(mainPanel);
        this.pack();
        this.setResizable(false);
        this.setTitle("The Hub");
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
