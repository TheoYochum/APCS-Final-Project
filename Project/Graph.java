import processing.core.PApplet;

// Compile with: javac -classpath "../Libraries/core.jar;../Project" ProcessingTest.java
// Run with: java -classpath "../Libraries/core.jar;../Project" ProcessingTest

public class Graph extends PApplet{

    List<Point> points, point;
    Equation test, test2;
    boolean list = false;
    float[] xVals, yVals;

    public Graph(String exp) {
      test = new Equation(exp);
      //test2 = new Equation("-1 * " + exp);
      list = true;
    }

    public Graph(float[] x, float[] y) {
      xVals = x;
      yVals = y;
    }

    @Override
    public void settings() {
        size(1800, 1000);
        if (list) {
          points = test.values(-width/2, width/2, 0.1, "x");
        }
        //point = test2.values(-width/2, width/2, 0.1, "x");
    }

    @Override
    public void draw() {
        stroke(0);
        strokeWeight(2);
        line(width/2, 0, width/2, height);
        line(0, height/2, width, height/2);
        strokeWeight(4);
        stroke(255, 0, 0);
        if (list) {
          graph(points);
          //graph(point);
        } else {
          graph(xVals, yVals);
        }
    }

    public void graph(float[] x,  float[] y) {
        for (int i = 1; i < x.length; i++) {
            line(x[i - 1] + width/2, height - y[i - 1] - height/2, x[i] + width/2, height - y[i] - height/2);
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
        //Graph pt = new Graph("((9 * sqrt(x + 36) * sqrt(x + 4) ) / 16) + 40");
        float[] x = {100, 200, 300};
        Graph pt1 = new Graph(x, x);
        PApplet.runSketch(new String[]{"Graph"}, pt1);
        //PApplet.runSketch(new String[]{"Graph"}, pt1);
        // hyperbola right side: "((9 * sqrt(x + 36) * sqrt(x + 4) ) / 16) + 40"
    }
}
