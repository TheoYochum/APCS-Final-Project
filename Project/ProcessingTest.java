import processing.core.PApplet;

// Compile with: javac -classpath "../Libraries/core.jar;../Project" ProcessingTest.java
// Run with: java -classpath "../Libraries/core.jar;../Project" ProcessingTest

public class ProcessingTest extends PApplet{

    List<Point> points;

    @Override
    public void settings() {
        size(1000, 1000);
        Equation test = new Equation("-1 * (sqrt(90 + x) * sqrt(90 - x) ) + 40");
        points = test.values(-width/2, width/2, 0.1, "x");
    }

    @Override
    public void draw() {
      fill(255, 255, 0);
        strokeWeight(2);
        line(width/2, 0, width/2, height);
        line(0, height/2, width, height/2);
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
            line((float) (p1.getX().value() + width/2), (float) (height - p1.getY().value() - height/2),
                (float) (p2.getX().value() + width/2), (float) (height - p2.getY().value() - height/2) );
        }
    }

    public static void main (String[] args) {
        ProcessingTest pt = new ProcessingTest();
        PApplet.runSketch(new String[]{"ProcessingTest"}, pt);
    }
}
