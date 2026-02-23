package programs.Light_Simulation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.List;

public class GraphicPanel extends JPanel {

    List<Light> lights = new ArrayList<>();
    private Light draggedLight = null;
    private Light selectedLight = null;
    private Point lastRightClickPoint = null;

    public GraphicPanel(){
        this.setPreferredSize(new Dimension(800, 500));
        this.setBackground(Color.BLACK);
        Light.addRectBlocker(300, 100, 400, 200);
        Light.addRectBlocker(400, 200, 500, 400);
        Light.addBlocker(100, 25, 50, 100);
        Light.addBlocker(400, 100, 600, 100);

        Light.addMirror(700, 400, 575, 480);

        JPopupMenu lightMenu = new JPopupMenu();
        JMenuItem editLight = new JMenuItem("Edit");
        editLight.addActionListener(_ -> {
            if (selectedLight != null) {
                new EditLightPopWindow(selectedLight, this);
            }
        });
        JMenuItem deleteLight = new JMenuItem("Delete");
        deleteLight.addActionListener(_ -> {
            if (selectedLight != null) {
                lights.remove(selectedLight);
                repaint();
            }
        });
        lightMenu.add(editLight);
        lightMenu.add(deleteLight);

        JPopupMenu backgroundMenu = new JPopupMenu();
        JMenuItem addLight = new JMenuItem("Add light");
        addLight.addActionListener(_ -> {
            if (lastRightClickPoint != null) {
                Light light = new Light(lastRightClickPoint.x, lastRightClickPoint.y);
                new EditLightPopWindow(light, GraphicPanel.this);
                lights.add(light);
                repaint();
            }
        });
        backgroundMenu.add(addLight);

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                boolean clickedLight = false;
                for (var light : lights) {
                    if (light.isClicked(e.getX(), e.getY())) {
                        if(SwingUtilities.isLeftMouseButton(e)){
                            draggedLight = light;
                            clickedLight = true;
                            break;
                        } else if(SwingUtilities.isRightMouseButton(e)){
                            selectedLight = light;
                            lightMenu.show(GraphicPanel.this, e.getX(), e.getY());
                            clickedLight = true;
                        }
                    }
                }
                if(!clickedLight && SwingUtilities.isRightMouseButton(e)){
                    lastRightClickPoint = e.getPoint();
                    backgroundMenu.show(GraphicPanel.this, e.getX(), e.getY());
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                draggedLight = null;
            }
        });

        this.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (draggedLight != null) {
                    draggedLight.setPosition(e.getX(), e.getY());
                    repaint();
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (var light : lights) {
            light.draw(g);
        }

        g.setColor(Color.WHITE);
        for(var block : Light.blockers){
            g.drawLine((int)block.getX1(), (int)block.getY1(), (int)block.getX2(), (int)block.getY2());
        }

        g.setColor(Color.YELLOW);
        for(var mirror : Light.mirrors){
            g.drawLine((int)mirror.getX1(), (int)mirror.getY1(), (int)mirror.getX2(), (int)mirror.getY2());
        }
    }
}
