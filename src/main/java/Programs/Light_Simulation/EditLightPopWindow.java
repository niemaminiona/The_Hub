package Programs.Light_Simulation;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

public class EditLightPopWindow extends JFrame {

    public EditLightPopWindow(Light light, GraphicPanel graphicPanel) { // Modify constructor
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setTitle("Edit Light");
        this.setResizable(false);

        this.addWindowFocusListener(new WindowFocusListener() {
            @Override
            public void windowGainedFocus(WindowEvent e) {

            }

            @Override
            public void windowLostFocus(WindowEvent e) {
                EditLightPopWindow.this.dispose();
            }

        });

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JPanel amountPanel = new JPanel();
        JPanel lengthPanel = new JPanel();
        JPanel radiusPanel = new JPanel();

        amountPanel.add(new JLabel("Amount: "));
        lengthPanel.add(new JLabel("Length: "));
        radiusPanel.add(new JLabel("Radius: "));

        JSlider amountSlider = new JSlider(0, 1000, light.beamsAmount);
        JSlider lengthSlider = new JSlider(0, 1000, light.length);
        JSlider radiusSlider = new JSlider(5, 100, light.radius);

        amountPanel.add(amountSlider);
        lengthPanel.add(lengthSlider);
        radiusPanel.add(radiusSlider);

        amountSlider.addChangeListener(_ -> {
            light.beamsAmount = amountSlider.getValue();
            graphicPanel.repaint();
        });

        lengthSlider.addChangeListener(_ -> {
            light.length = lengthSlider.getValue();
            graphicPanel.repaint();
        });

        radiusSlider.addChangeListener(_ -> {
            light.radius = radiusSlider.getValue();
            graphicPanel.repaint();
        });



        panel.add(amountPanel);
        panel.add(lengthPanel);
        panel.add(radiusPanel);

        this.add(panel);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
