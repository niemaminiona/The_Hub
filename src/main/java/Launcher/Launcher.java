package Launcher;

import Programs.Function_Graph.FunctionGraph;
import Programs.HeatMap.HeatMapWindow;
import Templates.*;
import Programs.Light_Simulation.LightSimulationGame;

import javax.swing.*;

public class Launcher extends JFrame {
    public Launcher(){
        JPanel mainPanel = new VerticalStackPanel();

        // Main modes panel with buttons
        JPanel mainModesPanel = getMainModesJPanel();

        // Test modes panel with its buttons
        JPanel testModesPanel = new HorizontalStackPanel();

        String[] lwjglTestNames = {
                "Test 0 - Initialization Test",
                "Test 1 - Movement Test (WSAD)",
                "Test 2 - Rotation Test",
                "Test 3 - Shader Test"
        };

        JComboBox<String> lwjglTestPicker = new JComboBox<>(lwjglTestNames);
        testModesPanel.add(lwjglTestPicker);

        JButton lwjgl3_TestGoButton = new JButton("Run Test");
        lwjgl3_TestGoButton.addActionListener(_ -> {
            int selectedIndex = lwjglTestPicker.getSelectedIndex();

            Runnable testLauncher = switch (selectedIndex){
                case 0 -> new TestPrograms.LWJGL3.Test.TestLauncher();
                case 1 -> new TestPrograms.LWJGL3.Test1.TestLauncher();
                case 2 -> new TestPrograms.LWJGL3.Test2.TestLauncher();
                case 3 -> new TestPrograms.LWJGL3.Test3.TestLauncher();
                default -> null;
            };

            if (testLauncher != null) {
                new Thread(testLauncher).start();
                dispose();
            }
        });
        testModesPanel.add(lwjgl3_TestGoButton);

        // packs everything together
        mainPanel.add(mainModesPanel);
        mainPanel.add(testModesPanel);

        this.add(mainPanel);
        this.pack();
        this.setResizable(false);
        this.setTitle("The Hub");
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    private JPanel getMainModesJPanel() {
        JPanel mainModesPanel = new HorizontalStackPanel();

        JButton heatMapButton = new JButton("Heat Map");
        heatMapButton.addActionListener(_ -> {
            new HeatMapWindow();
            this.dispose();
        });
        mainModesPanel.add(heatMapButton);

        JButton functionGraphButton = new JButton("Function Graph");
        functionGraphButton.addActionListener(_ -> {
            new FunctionGraph();
            this.dispose();
        });
        mainModesPanel.add(functionGraphButton);

        JButton lightSimulationButton = new JButton("Light Simulation");
        lightSimulationButton.addActionListener(_ -> {
            new LightSimulationGame();
            this.dispose();
        });
        mainModesPanel.add(lightSimulationButton);
        return mainModesPanel;
    }
}
