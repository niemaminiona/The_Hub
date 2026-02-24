package Programs.Light_Simulation;

import java.awt.*;
import java.awt.geom.Line2D;
import java.util.*;
import java.util.List;

public class Light {
    private int posX, posY;
    public int radius;
    public int beamsAmount;
    public int length;

    public static List<Line2D> blockers = new ArrayList<>();
    public static List<Line2D> mirrors = new ArrayList<>();

    public static void addBlocker(int x, int y, int x2, int y2){blockers.add(new Line2D.Float(x, y, x2, y2));}
    public static void addMirror(int x, int y, int x2, int y2){mirrors.add(new Line2D.Float(x, y, x2, y2));}

    public static void addRectBlocker(int x, int y, int x2, int y2) {
        Point rectStart = new Point(x, y);
        Point rectEnd = new Point(x2, y2);
        blockers.add(new Line2D.Float(rectStart.x, rectStart.y, rectStart.x, rectEnd.y));
        blockers.add(new Line2D.Float(rectStart.x, rectStart.y, rectEnd.x, rectStart.y));
        blockers.add(new Line2D.Float(rectEnd.x, rectStart.y, rectEnd.x, rectEnd.y));
        blockers.add(new Line2D.Float(rectStart.x, rectEnd.y, rectEnd.x, rectEnd.y));
    }

    public Light() {
        this(100, 100, 25, 100, 300);
    }

    public Light(int X, int Y) {
        this(X, Y, 25, 100, 300);
    }

    public Light(int X, int Y, int radius) {
        this(X, Y, radius, 100, 300);
    }

    public Light(int X, int Y, int radius, int beamsAmount, int length) {
        posX = X;
        posY = Y;
        this.radius = radius;
        this.beamsAmount = beamsAmount;
        this.length = length;
    }

    public void setPosition(int X, int Y) {
        posX = X;
        posY = Y;
    }

    public boolean isClicked(int x, int y) {
        int dx = x - posX;
        int dy = y - posY;
        return dx * dx + dy * dy <= radius * radius;
    }

    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillOval(posX - radius, posY - radius, 2 * radius, 2 * radius);
        for (int i = 0; i < beamsAmount; i++) {
            double angle = ((2 * Math.PI) * i / beamsAmount);
            int sin = (int) (Math.sin(angle) * radius + posX);
            int cos = (int) (Math.cos(angle) * radius + posY);

            Point startingPoint = new Point(sin, cos);

            int endX = (int) (Math.sin(angle) * (radius + length) + posX);
            int endY = (int) (Math.cos(angle) * (radius + length) + posY);

            boolean lineIntersected = false;

            Line2D lightBeam = new Line2D.Float(sin, cos, endX, endY);
            if (blockers.isEmpty() && mirrors.isEmpty()) {
                g.drawLine((int) lightBeam.getX1(), (int) lightBeam.getY1(),
                           (int) lightBeam.getX2(), (int) lightBeam.getY2());
            }
            else {
                class BlockedBeam{
                    public final Point point;
                    public final String type;
                    public final Line2D line;

                    public BlockedBeam(Point point, Line2D line, String type){
                        this.point = point;
                        this.line = line;
                        this.type = type;
                    }
                }
                List<BlockedBeam> listOfIntersectedPoints = new ArrayList<>();

                for (var blockingLine : blockers) {
                    if (lightBeam.intersectsLine(blockingLine) && getIntersectPoint(lightBeam, blockingLine) != null) {
                        lineIntersected = true;
                        listOfIntersectedPoints.add(new BlockedBeam(getIntersectPoint(lightBeam, blockingLine), blockingLine, "block"));
                    }
                }

                for(var mirrorLine : mirrors){
                    if (lightBeam.intersectsLine(mirrorLine) && getIntersectPoint(lightBeam, mirrorLine) != null) {
                        lineIntersected = true;
                        listOfIntersectedPoints.add(new BlockedBeam(getIntersectPoint(lightBeam, mirrorLine), mirrorLine, "mirror"));
                    }
                }

                if(lineIntersected){
                    BlockedBeam smallestPoint = listOfIntersectedPoints.getFirst();

                    for(var point : listOfIntersectedPoints){
                        if(getLength(startingPoint, point.point) < getLength(startingPoint, smallestPoint.point)){
                            smallestPoint = point;
                        }
                    }
                    switch (smallestPoint.type){
                        case "block":
                            g.drawLine((int) lightBeam.getX1(), (int) lightBeam.getY1(), smallestPoint.point.x, smallestPoint.point.y);
                            break;
                        case "mirror":
                            g.setColor(Color.yellow);
                            g.drawLine((int) lightBeam.getX1(), (int) lightBeam.getY1(), smallestPoint.point.x, smallestPoint.point.y);
                            int reflectedLength = (int)(getLength(startingPoint, new Point(endX, endY)) - getLength(startingPoint, smallestPoint.point));

                            double x1 = startingPoint.x,
                                    y1 = startingPoint.y,
                                    x2 = smallestPoint.point.x,
                                    y2 = smallestPoint.point.y;
                            double x3 = smallestPoint.line.getX1(),
                                    y3 = smallestPoint.line.getY1(),
                                    x4 = smallestPoint.line.getX2(),
                                    y4 = smallestPoint.line.getY2();

                            double m1 = (y2 - y1) / (x2 - x1);
                            double m2 = (y4 - y3) / (x4 - x3);

                            double theta = Math.abs(Math.atan((m2 - m1) / (1 + m1 * m2)));

//                            System.out.println(theta * (180/Math.PI));

                            break;
                    }
                    g.setColor(Color.white);

                } else {
                    g.drawLine((int) lightBeam.getX1(), (int) lightBeam.getY1(), (int) lightBeam.getX2(), (int) lightBeam.getY2());

                }
                listOfIntersectedPoints.clear();
            }
        }
    }

    private Point getIntersectPoint(Line2D lightLine, Line2D blockLine){
        // those x's and y's are made for code readability, 1st and 2nd are for light line, 3rd and 4th are for blocking line
        double x1 = lightLine.getX1(),
                y1 = lightLine.getY1(),
                x2 = lightLine.getX2(),
                y2 = lightLine.getY2();
        double x3 = blockLine.getX1(),
                y3 = blockLine.getY1(),
                x4 = blockLine.getX2(),
                y4 = blockLine.getY2();
        // this formula is NOT mine, im not that crazy
        // it calculates exact point where lines are intersecting
        double determinant = (x1 - x2) * (y3 - y4) - (y1 - y2) * (x3 - x4);

        if (determinant == 0) {
            return null;
        }

        double x = ((x1 * y2 - y1 * x2) * (x3 - x4) - (x1 - x2) * (x3 * y4 - y3 * x4)) / determinant;
        double y = ((x1 * y2 - y1 * x2) * (y3 - y4) - (y1 - y2) * (x3 * y4 - y3 * x4)) / determinant;
        return new Point((int) x, (int) y);
    }

    private double getLength(Point startingPoint, Point endPoint){
        // simple pythagoras theorem
        double x1 = startingPoint.x,
                y1 = startingPoint.y,
                x2 = endPoint.x,
                y2 = endPoint.y;
        return Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
    }

}


