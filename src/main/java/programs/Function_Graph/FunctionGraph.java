package programs.Function_Graph;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FunctionGraph extends JFrame{
    // constructor
    public FunctionGraph(){
        creator();
    }

    // this function is basically a real constructor, it was made in case it'll require to overload constructors
    // java is a beautiful language where you cant make default parameters in constructors
    private void creator(){
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setTitle("Functions Graph");

        // creates panel where functions are drawn in
        GraphicPanel graphicPanel = new GraphicPanel();

        // declares menuBar and menus
        JMenuBar menuBar = new JMenuBar();
        JMenu drawMenu = new JMenu("Draw");
        JMenu changeColorMenu = new JMenu("Color: Red");
        JMenu modifyMenu = new JMenu("Modify");
        JMenu engineMenu = new JMenu("Engine: Line");

        // this part goes through array and turns string names into menu items
        String[] availableFunctions = {"sin", "cos", "tan", "cotan"};
        for(String itemName : availableFunctions){
            JMenuItem item = new JMenuItem(itemName);
            item.addActionListener(_ -> graphicPanel.setFunction(itemName));
            drawMenu.add(item);
        }

        // this part goes through array and turns string colors names into menu items
        String[] availableColorsNames = {
                "red", "green", "blue", "orange", "black", "white",
                "yellow", "cyan", "magenta", "gray", "light_gray",
                "dark_gray", "pink"
        };
        for(String itemName : availableColorsNames){
            JMenuItem item = new JMenuItem(itemName);
            item.addActionListener(_ -> {
                graphicPanel.setColor(getColorFromString(itemName));
                changeColorMenu.setText("Color: " + itemName);
            });
            changeColorMenu.add(item);
        }

        // this part makes modify menu
        // this part is also very long and very unreadable, but you'll have to trust me it's just for this one menu...
        // you don't really have read this part to understand the rest of code
        // X Multiplier adding
        JLabel labelXAxisMultiplier = new JLabel("X multiplier: ");

        JTextField textFieldXAxisMultiplier = new JTextField();
        textFieldXAxisMultiplier.setPreferredSize(new Dimension(80,30));
        textFieldXAxisMultiplier.setText("1");

        JButton submitButtonXAxisMultiplier = new JButton("set");
        submitButtonXAxisMultiplier.setPreferredSize(new Dimension(60,30));
        submitButtonXAxisMultiplier.setFont(new Font("SansSerif", Font.PLAIN, 15));
        submitButtonXAxisMultiplier.setFocusable(false);
        submitButtonXAxisMultiplier.addActionListener(_ -> graphicPanel.setXMultiplier(Float.parseFloat(textFieldXAxisMultiplier.getText())));

        JSlider sliderXAxisMultiplier = new JSlider(0, 100, 50);
        sliderXAxisMultiplier.setPreferredSize(new Dimension(150, 30));
        sliderXAxisMultiplier.setPaintTicks(true);
        sliderXAxisMultiplier.setMinorTickSpacing(50);
        sliderXAxisMultiplier.addChangeListener(_ -> {
            int value = sliderXAxisMultiplier.getValue();
            float multiplier;

            if (value < 50) {
                multiplier = (float) (0.1 + (value / 49.0) * 0.8);
                multiplier = (float)Math.round(multiplier * 1000)/1000;
            } else if (value > 50){
                multiplier = (float) (1.1 + ((value - 51) / 49.0) * 4.9);
            } else {
                multiplier = 1.0f;
            }

            graphicPanel.setXMultiplier(multiplier);
            textFieldXAxisMultiplier.setText(String.valueOf(multiplier));
        });

        JPanel modifyPanelXAxisMultiplier = new JPanel();
        modifyPanelXAxisMultiplier.add(labelXAxisMultiplier);
        modifyPanelXAxisMultiplier.add(textFieldXAxisMultiplier);
        modifyPanelXAxisMultiplier.add(submitButtonXAxisMultiplier);
        modifyPanelXAxisMultiplier.add(sliderXAxisMultiplier);



        // Y Multiplier adding
        JLabel labelYAxisMultiplier = new JLabel("Y multiplier: ");

        JTextField textFieldYAxisMultiplier = new JTextField();
        textFieldYAxisMultiplier.setPreferredSize(new Dimension(80,30));
        textFieldYAxisMultiplier.setText("1");

        JButton submitButtonYAxisMultiplier = new JButton("set");
        submitButtonYAxisMultiplier.setPreferredSize(new Dimension(60,30));
        submitButtonYAxisMultiplier.setFont(new Font("SansSerif", Font.PLAIN, 15));
        submitButtonYAxisMultiplier.setFocusable(false);
        submitButtonYAxisMultiplier.addActionListener(_ -> graphicPanel.setYMultiplier(Float.parseFloat(textFieldYAxisMultiplier.getText())));

        JSlider sliderYAxisMultiplier = new JSlider(0, 100, 50);
        sliderYAxisMultiplier.setPreferredSize(new Dimension(150, 30));
        sliderYAxisMultiplier.setPaintTicks(true);
        sliderYAxisMultiplier.setMinorTickSpacing(50);
        sliderYAxisMultiplier.addChangeListener(_ -> {
            int value = sliderYAxisMultiplier.getValue();
            float multiplier;

            if (value < 50) {
                multiplier = (float) Math.round((0.1 + (value / 49.0) * 0.8)*1000)/1000;
            } else if (value > 50){
                multiplier = (float) (1.1 + ((value - 51) / 49.0) * 4.9);
            } else {
                multiplier = 1.0f;
            }

            graphicPanel.setYMultiplier(multiplier);
            textFieldYAxisMultiplier.setText(String.valueOf(multiplier));
        });

        JPanel modifyPanelYAxisMultiplier = new JPanel();
        modifyPanelYAxisMultiplier.add(labelYAxisMultiplier);
        modifyPanelYAxisMultiplier.add(textFieldYAxisMultiplier);
        modifyPanelYAxisMultiplier.add(submitButtonYAxisMultiplier);
        modifyPanelYAxisMultiplier.add(sliderYAxisMultiplier);

        // X Incremental adding
        JLabel labelXAxisIncremental = new JLabel("X Increment: ");

        JTextField textFieldXAxisIncremental = new JTextField();
        textFieldXAxisIncremental.setPreferredSize(new Dimension(80,30));
        textFieldXAxisIncremental.setText("0");

        JButton submitButtonXAxisIncremental = new JButton("set");
        submitButtonXAxisIncremental.setPreferredSize(new Dimension(60,30));
        submitButtonXAxisIncremental.setFont(new Font("SansSerif", Font.PLAIN, 15));
        submitButtonXAxisIncremental.setFocusable(false);
        submitButtonXAxisIncremental.addActionListener(_ -> graphicPanel.setXIncrement(Float.parseFloat(textFieldXAxisIncremental.getText())));

        JSlider sliderXAxisIncremental = new JSlider(-100, 100, 0);
        sliderXAxisIncremental.setPreferredSize(new Dimension(150, 30));
        sliderXAxisIncremental.setPaintTicks(true);
        sliderXAxisIncremental.setMinorTickSpacing(100);
        sliderXAxisIncremental.addChangeListener(_ -> {
            graphicPanel.setXIncrement((float)sliderXAxisIncremental.getValue()/10);
            textFieldXAxisIncremental.setText(String.valueOf((float)sliderXAxisIncremental.getValue()/10));
        });

        JPanel modifyPanelXAxisIncremental = new JPanel();
        modifyPanelXAxisIncremental.add(labelXAxisIncremental);
        modifyPanelXAxisIncremental.add(textFieldXAxisIncremental);
        modifyPanelXAxisIncremental.add(submitButtonXAxisIncremental);
        modifyPanelXAxisIncremental.add(sliderXAxisIncremental);

        // Y Incremental adding
        JLabel labelYAxisIncremental = new JLabel("Y Increment: ");

        JTextField textFieldYAxisIncremental = new JTextField();
        textFieldYAxisIncremental.setPreferredSize(new Dimension(80,30));
        textFieldYAxisIncremental.setText("0");

        JButton submitButtonYAxisIncremental = new JButton("set");
        submitButtonYAxisIncremental.setPreferredSize(new Dimension(60,30));
        submitButtonYAxisIncremental.setFont(new Font("SansSerif", Font.PLAIN, 15));
        submitButtonYAxisIncremental.setFocusable(false);
        submitButtonYAxisIncremental.addActionListener(_ -> graphicPanel.setYIncrement(Float.parseFloat(textFieldYAxisIncremental.getText())));

        JSlider sliderYAxisIncremental = new JSlider(-100, 100, 0);
        sliderYAxisIncremental.setPreferredSize(new Dimension(150, 30));
        sliderYAxisIncremental.setPaintTicks(true);
        sliderYAxisIncremental.setMinorTickSpacing(100);
        sliderYAxisIncremental.addChangeListener(_ -> {
            graphicPanel.setYIncrement((float)sliderYAxisIncremental.getValue()/10);
            textFieldYAxisIncremental.setText(String.valueOf((float)sliderYAxisIncremental.getValue()/10));
        });

        JPanel modifyPanelYAxisIncremental = new JPanel();
        modifyPanelYAxisIncremental.add(labelYAxisIncremental);
        modifyPanelYAxisIncremental.add(textFieldYAxisIncremental);
        modifyPanelYAxisIncremental.add(submitButtonYAxisIncremental);
        modifyPanelYAxisIncremental.add(sliderYAxisIncremental);

        // adds checkbox
        JPanel absPanel = new JPanel();
        JCheckBox absoluteValue = new JCheckBox("Absolute value on x-axis");
        absoluteValue.setFocusable(false);
        absoluteValue.addActionListener(_ -> graphicPanel.setAbsolute(absoluteValue.isSelected()));

        JSlider sliderStrokeSize = new JSlider(1, 11, 3);
        sliderStrokeSize.setPreferredSize(new Dimension(150, 30));
        sliderStrokeSize.setPaintTicks(true);
        sliderStrokeSize.setMinorTickSpacing(5);
        sliderStrokeSize.addChangeListener(_ -> graphicPanel.setStrokeSize(sliderStrokeSize.getValue()));

        absPanel.add(sliderStrokeSize);
        absPanel.add(absoluteValue);

        // adds checkbox
        JPanel piHighlightPanel = new JPanel();
        JCheckBox piHighlightCheck = new JCheckBox("Highlight Pi");
        piHighlightCheck.setFocusable(false);
        piHighlightCheck.setSelected(true);
        piHighlightCheck.addActionListener(_ -> graphicPanel.setPiHighlight(piHighlightCheck.isSelected()));
        piHighlightPanel.add(piHighlightCheck);


        // reset button
        JButton resetButton = new JButton("Reset");
        resetButton.setMaximumSize(new Dimension(300, 30));
        resetButton.setFont(new Font("SansSerif", Font.PLAIN, 15));
        resetButton.setFocusable(false);
        resetButton.addActionListener(_ -> {
            graphicPanel.setXMultiplier(1);
            graphicPanel.setYMultiplier(1);
            graphicPanel.setXIncrement(0);
            graphicPanel.setYIncrement(0);
            graphicPanel.setAbsolute(false);
            textFieldXAxisMultiplier.setText("1");
            textFieldYAxisMultiplier.setText("1");
            textFieldXAxisIncremental.setText("0");
            textFieldYAxisIncremental.setText("0");
            sliderXAxisMultiplier.setValue(50);
            sliderYAxisMultiplier.setValue(50);
            sliderXAxisIncremental.setValue(0);
            sliderYAxisIncremental.setValue(0);
            sliderStrokeSize.setValue(3);
            absoluteValue.setSelected(false);
        });

        // adding previous mess to the menu
        modifyMenu.add(modifyPanelXAxisMultiplier);
        modifyMenu.add(modifyPanelYAxisMultiplier);
        modifyMenu.add(modifyPanelXAxisIncremental);
        modifyMenu.add(modifyPanelYAxisIncremental);
        modifyMenu.add(absPanel);
        modifyMenu.add(piHighlightPanel);
        modifyMenu.add(resetButton);
        // END OF MODIFY MENU

        // this part declares engine type menu
        JMenuItem lineOption = new JMenuItem("Line");
        JMenuItem pointOption = new JMenuItem("Point");

        lineOption.addActionListener(_ -> {
            graphicPanel.setEngineVersion(2);
            engineMenu.setText("Engine: Line");
        });
        pointOption.addActionListener(_ -> {
            graphicPanel.setEngineVersion(1);
            engineMenu.setText("Engine: Point");
        });

        engineMenu.add(lineOption);
        engineMenu.add(pointOption);

        // rest of building window code
        menuBar.add(modifyMenu);
        menuBar.add(drawMenu);
        menuBar.add(changeColorMenu);
        menuBar.add(engineMenu);
        this.setJMenuBar(menuBar);
        this.add(graphicPanel);
        this.pack();

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                new Launcher.Launcher();
                dispose();
            }
        });

        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    // I REALLY COULDN'T COME UP WITH BETTER IDEA, it's the Color enum that sucks, please understand :(
    // this turns string into Color enum
    private Color getColorFromString(String colorName) {
        return switch (colorName.toLowerCase()) {
            case "red" -> Color.RED;
            case "green" -> Color.GREEN;
            case "blue" -> Color.BLUE;
            case "orange" -> Color.ORANGE;
            case "white" -> Color.WHITE;
            case "yellow" -> Color.YELLOW;
            case "cyan" -> Color.CYAN;
            case "magenta" -> Color.MAGENTA;
            case "gray" -> Color.GRAY;
            case "light_gray" -> Color.LIGHT_GRAY;
            case "dark_gray" -> Color.DARK_GRAY;
            case "pink" -> Color.PINK;
            default -> Color.BLACK;
        };
    }

}

// this is the class that creates panel where functions are drawn
class GraphicPanel extends JLabel implements MouseWheelListener{

    private int plotSize = 50;  // does what it says...  (plots are that background squares) (everything here is scaled to plots)
    private String selectedFunction = "sin"; //default function
    private Color selectedColor = Color.RED; //default color
    private Point middlePoint; // global declaration of middle of the screen
    private boolean absolute = false;
    private boolean piHighlight = true;
    private int strokeSize = 3;

    // variables that will let to modify functions
    private float xMultiplier = 1;
    private float yMultiplier = 1;
    private int xIncrement = 0;
    private int yIncrement = 0;

    private float savedXIncrement = 0; // fixing the issue where when plot size changes x increment scaled wrong
    private float savedYIncrement = 0;

    // this variable sets how function will be draws (for now there are only 2 versions)
    private int engineVersion = 2; // default version (line)

    //label that displays function
    private final JLabel functionLabel;

    // constructor
    public GraphicPanel(){
        this.setPreferredSize(new Dimension(825,525));

        functionLabel = new JLabel("y = " + selectedFunction + "(x)");
        functionLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        functionLabel.setForeground(Color.BLACK);
        functionLabel.setBackground(Color.WHITE);
        functionLabel.setOpaque(true);
        functionLabel.setBorder(BorderFactory.createLineBorder(Color.black, 4));
        functionLabel.setHorizontalAlignment(JLabel.CENTER);
        functionLabel.setVerticalAlignment(JLabel.CENTER);


        this.addMouseWheelListener(this);
        this.setLayout(null);
        this.add(functionLabel);
    }

    // this is code that sets which function to draw
    public void setFunction(String functionName) {
        this.selectedFunction = functionName;
        repaint();
    }

    // this is code that sets color of drawn function
    public void setColor(Color Color) {
        this.selectedColor = Color;
        repaint();
    }

    // this is code that sets x multiplier of functions
    public void setXMultiplier(float xMultiplier) {
        this.xMultiplier = xMultiplier;
        repaint();
    }
    // this is code that sets y multiplier of functions
    public void setYMultiplier(float yMultiplier) {
        this.yMultiplier = yMultiplier;
        repaint();
    }
    // this is code that sets x Incrementation of functions
    public void setXIncrement(float xIncrement) {
        this.xIncrement = (int) (xIncrement * plotSize);
        this.savedXIncrement = xIncrement;
        repaint();
    }
    // this is code that sets y Incrementation of functions
    public void setYIncrement(float yIncrement) {
        this.yIncrement = (int) (yIncrement * plotSize);
        this.savedYIncrement = yIncrement;
        repaint();
    }

    // sets engine version
    public void setEngineVersion (int engineVersion){
        this.engineVersion = engineVersion;
        repaint();
    }
    // sets absolute value on x-axis
    public void setAbsolute (boolean absolute){
        this.absolute = absolute;
        repaint();
    }

    // sets pi highlight on x-axis
    public void setPiHighlight (boolean piHighlight){
        this.piHighlight = piHighlight;
        repaint();
    }
    //sets stroke size
    public void setStrokeSize (int strokeSize){
        this.strokeSize = strokeSize;
        repaint();
    }

    // part where EVERYTHING is drawn
    public void paintComponent(Graphics g){
        try{
            Graphics2D g2D = (Graphics2D) g; // casts g to g2D since graphics2D is refreshed version and better of graphics
            middlePoint = new Point(this.getWidth()/2, this.getHeight()/2); // sets the exact middle of the screen

            // this calculates and sets the point from which plots are drawn from
            Point startingPoint = new Point(
            (((this.getWidth() / 2) + 1) - this.getWidth()) % plotSize,
            (((this.getHeight() / 2) + 1) - this.getHeight()) % plotSize
            );

            // sets plot color
            g2D.setPaint(Color.black);

            // draws the plots based on the middle of the screen and earlier calculated startPoint
            for(int i = -1; i <= this.getWidth()/plotSize; i++){
                for(int j = -1; j <= this.getHeight()/plotSize; j++){
                    g2D.drawRect(i * plotSize - (int)startingPoint.getX(), j * plotSize - (int)startingPoint.getY(), plotSize, plotSize);
                }
            }

            // draws the x and y-axis through the middle of the screen
            g2D.setStroke(new BasicStroke(3));
            g2D.drawLine((int)middlePoint.getX(), 0, (int)middlePoint.getX(), this.getHeight());
            g2D.drawLine(0, (int)middlePoint.getY(), this.getWidth(), (int)middlePoint.getY());

            // highlights pi on x-axis
            if(piHighlight){
                double startingPointOfPi = middlePoint.x % (plotSize * Math.PI);
                double startingPointOfHalfPi = (middlePoint.x % (plotSize * Math.PI)) - (plotSize * Math.PI / 2);

                g2D.setStroke(new BasicStroke(2));

                // calculates how much pi we see on screen from the middle of the screen to right
                int numberOfPiWrittenOnScreen = (int)(Math.floor(this.getWidth() / (plotSize * Math.PI) / 2));
                // reverses that, and it's now a number that we will increase until the end of visible screen
                int numberOfPi = -numberOfPiWrittenOnScreen;

                for (int i = 0; i <= (int)(Math.ceil(this.getWidth() / (plotSize * Math.PI))); i++){
                    g2D.drawLine((int)startingPointOfHalfPi,middlePoint.y + plotSize / 10, (int)startingPointOfHalfPi, middlePoint.y - plotSize / 10);

                    // draw if pi isn't between -0.25 and 0.25 (it ignores middle of screen [zero])
                    if (Math.abs(startingPointOfPi - middlePoint.x) > (plotSize * 0.25)) {
                        //draws half pi
                        g2D.drawLine((int)startingPointOfPi, middlePoint.y + plotSize / 5, (int)startingPointOfPi, middlePoint.y - plotSize / 5);
                        //draws pi in different formats according to its number
                        if(numberOfPi > 1 || numberOfPi < -1){
                            g2D.drawString(numberOfPi+"π", (int)startingPointOfPi - plotSize / 10,  (int)(middlePoint.y + plotSize / 1.5));
                        } else if (numberOfPi == -1) {
                            g2D.drawString("-π", (int)startingPointOfPi - plotSize / 10,  (int)(middlePoint.y + plotSize / 1.5));
                        } else{
                            g2D.drawString("π", (int)startingPointOfPi - plotSize / 10,  (int)(middlePoint.y + plotSize / 1.5));
                        }

                    }
                    numberOfPi++;
                    startingPointOfPi += plotSize * Math.PI;
                    startingPointOfHalfPi += plotSize * Math.PI;
                }
            }


            // finally the call of function draw method
            drawFunction(selectedFunction, g2D, selectedColor, strokeSize);

            // label that displays
            int scaleSizeOfLabelX = 120;
            String function = "y = ";
            if(absolute){
                function += "|";
            }
            function += selectedFunction +"(";
            if (xMultiplier != 1.0 && xMultiplier != -1.0) {
                function += xMultiplier;
                scaleSizeOfLabelX += 50;
            } else if (xMultiplier == -1.0) {
                function += "-";
                scaleSizeOfLabelX += 50;
            }
            function += "x";
            float xIncrementDescaled = (float)Math.round((float)xIncrement / plotSize * 10) / 10;
            if(xIncrementDescaled > 0){
                function += " + " + xIncrementDescaled;
                scaleSizeOfLabelX += 50;
            } else if (xIncrementDescaled < 0) {
                function += " - " + Math.abs(xIncrementDescaled);
                scaleSizeOfLabelX += 50;
            }
            function += ")";

            if (yMultiplier != 1.0) {
                function += " * " + yMultiplier;
                scaleSizeOfLabelX += 50;
            }

            float yIncrementDescaled = (float)Math.round((float)yIncrement / plotSize * 10) / 10;
            if(yIncrementDescaled > 0){
                function += " + " + yIncrementDescaled;
                scaleSizeOfLabelX += 50;
            } else if (yIncrementDescaled < 0) {
                function += " - " + Math.abs(yIncrementDescaled);
                scaleSizeOfLabelX += 50;
            }
            if(absolute){
                function += "|";
            }

            functionLabel.setBounds(10, this.getHeight() - 60, scaleSizeOfLabelX, 50);
            functionLabel.setText(function);

        }catch (ArithmeticException e){

            // problems are unexpected, but I added that part just in case
            System.exit(0);
        }catch (Exception e){
            System.exit(0);
        }
    }

    // this method draws functions
    private void drawFunction(String functionName, Graphics2D g2D, Color color, int strokeSize) {
        g2D.setPaint(color);
        g2D.setStroke(new BasicStroke(strokeSize));
        int i = -5;
        boolean firstLoop = true;
        int prevX = 0, prevY = 0;

        for (float x = middlePoint.x - this.getWidth() + i; x <= middlePoint.x; x++) {
            int y = 0;
            boolean skipPoint = false;

            switch (functionName) {
                case "sin":
                    y = (int) (-Math.sin((x + xIncrement) / plotSize * xMultiplier) * plotSize * yMultiplier - yIncrement) + middlePoint.y;
                    break;
                case "cos":
                    y = (int) (-Math.cos((x + xIncrement) / plotSize * xMultiplier) * plotSize * yMultiplier - yIncrement) + middlePoint.y;
                    break;
                case "tan":
                    if (Math.abs(Math.cos((x + xIncrement) / plotSize * xMultiplier)) < 0.05) {
                        skipPoint = true;
                    } else {
                        y = (int) (-Math.tan((x + xIncrement) / plotSize * xMultiplier) * plotSize * yMultiplier - yIncrement) + middlePoint.y;
                    }
                    break;
                case "cotan":
                    if (Math.abs(Math.sin((x + xIncrement) / plotSize * xMultiplier)) < 0.05) {
                        skipPoint = true;
                    } else {
                        y = (int) (-1 / Math.tan((x + xIncrement) / plotSize * xMultiplier) * plotSize * yMultiplier - yIncrement) + middlePoint.y;
                    }
                    break;
            }
            if (absolute) {
                y = middlePoint.y - Math.abs(y - middlePoint.y);
            }


            if (skipPoint) {
                firstLoop = true;
                i++;
                continue;
            }

            switch (engineVersion) {
                case 1: // Point mode
                    g2D.drawRect(i, y, 1, 1);
                    break;
                case 2: // Line mode
                    if (firstLoop) {
                        prevX = i;
                        prevY = y;
                        firstLoop = false;
                        break;
                    }
                    g2D.drawLine(prevX, prevY, i, y);
                    prevX = i;
                    prevY = y;
                    break;
            }
            i++;
        }
    }

    // this is the code where if increases or decreases plot size based on scroll (its just scaling, everything here is based on plot size)
    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        final int maxPlotSize = 250;
        final int minPlotSize = 10;

        // if scrolled upwards scale up else scale down, simple!
        // it obviously has some limits which cant be crossed in any way
        if(e.getWheelRotation() < 0){
            if(maxPlotSize > plotSize){
                plotSize = (int)(plotSize * 1.1);
            }else{
                plotSize = maxPlotSize;
            }
        }else{
            if(minPlotSize < plotSize){
                plotSize = (int)(plotSize * 0.9);
            }else{
                plotSize = minPlotSize;
            }
        }
        // fixing scaling issue
        xIncrement = (int)(savedXIncrement * plotSize);
        yIncrement = (int)(savedYIncrement * plotSize);
        repaint();
    }
}