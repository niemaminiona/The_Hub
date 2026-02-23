package programs.Light_Simulation;

import Launcher.Launcher;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class LightSimulationGame extends JFrame {
    public LightSimulationGame(){
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                new Launcher();
                dispose();
            }
        });
        this.setTitle("Light simulation");
        GraphicPanel graphicPanel = new GraphicPanel();


        this.add(graphicPanel);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
    
}
