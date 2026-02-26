package Programs.HeatMap;

import Templates.VerticalStackPanel;
import Templates.HorizontalStackPanel;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class SettingsWindow extends JFrame {
    public SettingsWindow(HeatMapGraphicPanel heatMapPanel){
        JPanel mainPanel = new VerticalStackPanel();

        // --- Buttons row ---
        JPanel buttonPanel = new HorizontalStackPanel();
        JButton randomizeButton, countButton, countNeighboursButton;

        buttonPanel.add(randomizeButton = new Templates.ButtonT1("Randomize"));
        randomizeButton.addActionListener(_ -> heatMapPanel.randomizeHeatMap());

        buttonPanel.add(countButton = new Templates.ButtonT1("Count by Avg."));
        countButton.addActionListener(_ -> heatMapPanel.countHeatMapByAverage());

        buttonPanel.add(countNeighboursButton = new Templates.ButtonT1("Count by Nbr."));
        countNeighboursButton.addActionListener(_ -> heatMapPanel.countHeatMapByNeighbors());

        // --- Slider row ---
        JPanel sliderPanel = new HorizontalStackPanel();
        JLabel sliderValueLabel = new JLabel("Threshold value: " + heatMapPanel.activeThreshold);
        JSlider thresholdSlider = new JSlider(1, heatMapPanel.diversity - 1, heatMapPanel.activeThreshold);
        thresholdSlider.addChangeListener(_ -> {
            heatMapPanel.activeThreshold = thresholdSlider.getValue();
            sliderValueLabel.setText("Threshold value: " + thresholdSlider.getValue());
        });
        sliderPanel.add(sliderValueLabel);
        sliderPanel.add(thresholdSlider);

        // --- Check row ---
        JPanel checkPanel = new HorizontalStackPanel();
        JCheckBox drawOutLineCheckBox = new JCheckBox();
        drawOutLineCheckBox.setSelected(heatMapPanel.drawOutLine);
        drawOutLineCheckBox.addActionListener(_ -> {
            heatMapPanel.drawOutLine = drawOutLineCheckBox.isSelected();
            heatMapPanel.repaint();
        });
        checkPanel.add(new JLabel("Draw grid outline: "));
        checkPanel.add(drawOutLineCheckBox);

        // --- window Buttons row ---
        JPanel windowButtonPanel = new HorizontalStackPanel();
        JButton logWindowButton, patternWindowButton, advancedSettingsButton;

        windowButtonPanel.add(advancedSettingsButton = new Templates.ButtonT1("Adv Settings"));
        advancedSettingsButton.addActionListener(_ -> new AdvancedSettingsWindow(heatMapPanel));

        windowButtonPanel.add(patternWindowButton = new Templates.ButtonT1("Patterns"));
        patternWindowButton.addActionListener(_ -> new PatternWindow(heatMapPanel));

        windowButtonPanel.add(logWindowButton = new Templates.ButtonT1("View Logs"));
        logWindowButton.addActionListener(_ -> LogWindow.showWindow());

        // adds up all of the above panels
        mainPanel.add(buttonPanel);
        mainPanel.add(sliderPanel);
        mainPanel.add(checkPanel);
        mainPanel.add(windowButtonPanel);

        this.add(mainPanel);
        this.pack();
        this.setTitle("Settings");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                HeatMapWindow.closeAllAndOpenLauncher();
            }
        });

        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);

        requestFocusInWindow();
    }
}