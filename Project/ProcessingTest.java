import processing.core.PApplet;

// Compile with: javac -cp ./core.jar ./ProcessingTest.java ProcessingTest.java
// Run with: java -cp ./core.jar ./ProcessingTest.java ProcessingTest

public class ProcessingTest extends PApplet{

    List<Point> points;

    @Override
    public void settings() {
        size(800, 800);
    }

    @Override
    public void draw() {
        strokeWeight(4);
        graph(x, y);
    }

    public void graph(int[] x,  int[] y) {
        for (int i = 1; i < x.length; i++) {
            line(x[i - 1], y[i - 1], x[i], y[i]);
        }
    }

    public static void main (String[] args) {
        ProcessingTest pt = new ProcessingTest();
        PApplet.runSketch(new String[]{"ProcessingTest"}, pt);
    }
}