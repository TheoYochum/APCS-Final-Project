import processing.core.PApplet;

// Compile with: javac -classpath "../Libraries/core.jar;../Project" ProcessingTest.java
// Run with: java -classpath "../Libraries/core.jar;../Project" ProcessingTest

public class ProcessingTest extends PApplet{

    List<Point> points;

    @Override
    public void settings() {
        size(1000, 1000);
        Equation test = new Equation("200 * sin(x) - 250");
        points = test.values(0, width, 1, "x");
    }

    @Override
    public void draw() {
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

    private static void wait(int millis){
      try {
        Thread.sleep(millis);
      }
      catch (InterruptedException e) {
      }
    }

    public void graph(List<Point> in) {
        for (int i = 1; i < in.length(); i++) {
            Point p1 = in.get(i - 1);
            Point p2 = in.get(i);
            line((float) (p1.getX().value() + width/2), (float) (height - p1.getY().value() - height/2),
                (float) (p2.getX().value() + width/2), (float) (height - p2.getY().value() - height/2) );
            wait(10);
        }
    }

    public static void main (String[] args) {
        ProcessingTest pt = new ProcessingTest();
        PApplet.runSketch(new String[]{"ProcessingTest"}, pt);
    }
}
