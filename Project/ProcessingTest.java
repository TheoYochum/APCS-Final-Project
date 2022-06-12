import processing.core.PApplet;

// Compile with: javac -classpath "../Libraries/core.jar;../Project" ProcessingTest.java
// Run with: java -classpath "../Libraries/core.jar;../Project" ProcessingTest

public class ProcessingTest extends PApplet{

    List<Point> points;

    @Override
    public void settings() {
        size(400, 400);
        Equation test = new Equation("100 * sin(x) - 200");
        points = test.values(0, 800, 1.0, "x");
    }

    @Override
    public void draw() {
        strokeWeight(4);
        graph(points);
    }

    public void graph(int[] x,  int[] y) {
        for (int i = 1; i < x.length; i++) {
            line(x[i - 1], y[i - 1], x[i], y[i]);
        }
    }

    public void graph(List<Point> in) {
        for (int i = 1; i < in.length(); i++) {
            Point p1 = in.get(i - 1);
            Point p2 = in.get(i);
            line((float) p1.getX().value(), (float) p1.getY().value() * -1, (float) p2.getX().value(), (float) p2.getY().value() * -1);
        }
    }
 
    public static void main (String[] args) {
        ProcessingTest pt = new ProcessingTest();
        PApplet.runSketch(new String[]{"ProcessingTest"}, pt);
    }
}