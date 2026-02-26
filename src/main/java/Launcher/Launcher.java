package Launcher;

import Programs.Function_Graph.FunctionGraph;
import Programs.HeatMap.HeatMapWindow;
import Templates.*;
import Programs.Light_Simulation.LightSimulationGame;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Launcher extends JFrame {
    public Launcher(){
        JPanel mainPanel = new VerticalStackPanel();

        // Main modes panel with buttons
        mainPanel.add(getMainModesJPanel());

        // Test modes panel with its buttons
        JPanel testModesPanel = new HorizontalStackPanel();

        String[] lwjglTestNames = {
                "Test 0 - Initialization Test",
                "Test 1 - Movement Test (WSAD)",
                "Test 2 - Rotation Test",
                "Test 3 - Shader Test",
                "Test 4 - JOML Matrix Test"
        };

        JComboBox<String> lwjglTestPicker = new JComboBox<>(lwjglTestNames);
        testModesPanel.add(lwjglTestPicker);

        JButton lwjgl3_TestGoButton = getJButton(lwjglTestPicker);
        testModesPanel.add(lwjgl3_TestGoButton);

        // packs everything together

        mainPanel.add(testModesPanel);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
                    System.exit(0);
                }
            }
        });

        setFocusable(true);
        add(mainPanel);
        pack();
        setResizable(false);
        setTitle("The Hub");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private JButton getJButton(JComboBox<String> lwjglTestPicker) {
        JButton lwjgl3_TestGoButton = new JButton("Run Test");
        lwjgl3_TestGoButton.setFocusPainted(false);
        lwjgl3_TestGoButton.addActionListener(_ -> {
            int selectedIndex = lwjglTestPicker.getSelectedIndex();

            Runnable testLauncher = switch (selectedIndex){
                case 0 -> new TestPrograms.LWJGL3.Test.TestLauncher();
                case 1 -> new TestPrograms.LWJGL3.Test1.TestLauncher();
                case 2 -> new TestPrograms.LWJGL3.Test2.TestLauncher();
                case 3 -> new TestPrograms.LWJGL3.Test3.TestLauncher();
                case 4 -> new TestPrograms.LWJGL3.Test4.TestLauncher();
                default -> null;
            };

            if (testLauncher != null) {
                new Thread(testLauncher).start();
                dispose();
            }
        });
        return lwjgl3_TestGoButton;
    }

    private JPanel getMainModesJPanel() {
        JPanel mainModesPanel = new HorizontalStackPanel();

        JButton heatMapButton = new JButton("Heat Map");
        heatMapButton.addActionListener(_ -> {
            new HeatMapWindow();
            this.dispose();
        });
        heatMapButton.setFocusPainted(false);
        mainModesPanel.add(heatMapButton);

        JButton functionGraphButton = new JButton("Function Graph");
        functionGraphButton.addActionListener(_ -> {
            new FunctionGraph();
            this.dispose();
        });
        functionGraphButton.setFocusPainted(false);
        mainModesPanel.add(functionGraphButton);

        JButton lightSimulationButton = new JButton("Light Simulation");
        lightSimulationButton.addActionListener(_ -> {
            new LightSimulationGame();
            this.dispose();
        });
        lightSimulationButton.setFocusPainted(false);
        mainModesPanel.add(lightSimulationButton);
        return mainModesPanel;
    }
}
