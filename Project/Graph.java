import processing.core.PApplet;

// Compile with: javac -classpath "../Libraries/core.jar;../Project" ProcessingTest.java
// Run with: java -classpath "../Libraries/core.jar;../Project" ProcessingTest

public class Graph extends PApplet{

    List<Point> points, point;
    Equation test, test2;
    boolean list = false;
    double[] xVals, yVals;
    double limit;

    public Graph(String exp, double max) {
      test = new Equation(exp);
      limit = max;
      //test2 = new Equation("-1 * " + exp);
      list = true;
    }

    public Graph(double[] x, double[] y, double max) {
      xVals = x;
      yVals = y;
      limit = max;
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

    public void graph(double[] x, double[] y) {
        points = new List<Point>();
        for (int i = 0; i < x.length; i++) {
            Point p1 = new Point(x[i], y[i], "point");
            points.add(p1);
        }
        graph(points);
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
        double[] x = {10, 20, 30};
        Graph pt1 = new Graph(x, x, 40);
        PApplet.runSketch(new String[]{"Graph"}, pt1);
        //PApplet.runSketch(new String[]{"Graph"}, pt1);
        // hyperbola right side: "((9 * sqrt(x + 36) * sqrt(x + 4) ) / 16) + 40"
    }
}
