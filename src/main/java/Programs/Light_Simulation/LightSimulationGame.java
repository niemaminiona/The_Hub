package Programs.Light_Simulation;

import Launcher.Launcher;
import Programs.HeatMap.HeatMapWindow;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
                    new Launcher();
                dispose();
                }
            }
        });

        this.add(graphicPanel);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
    
}
