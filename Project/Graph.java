import processing.core.PApplet;
import java.util.*;
import java.lang.*;
import java.util.*;

// Compile with: javac -classpath "../Libraries/core.jar;../Project" ProcessingTest.java
// Run with: java -classpath "../Libraries/core.jar;../Project" ProcessingTest

public class Graph extends PApplet{

    List<Point> points, point;
    ArrayList<Equation> eqs;
    Equation test, test2;
    boolean expre = false;
    boolean multG = false;
    double[] xVals, yVals;
    double limit;

    public Graph(String exp, double max) {
      test = new Equation(exp);
      limit = max * 2;
      expre = true;
    }

    public Graph(Equation x, double max) {
      test = x;
      limit = max * 2;
      expre = true;
    }

    public Graph(double[] x, double[] y, double max) {
      xVals = x;
      yVals = y;
      limit = max * 2;
    }

    public Graph(ArrayList<Equation> eq, double max) {
      eqs = eq;
      multG = true;
      limit = max * 2;
    }

    @Override
    public void settings() {
        size(500, 500);
    }

    @Override
    public void mousePressed() {
      if (mouseButton == LEFT) {
        background(255, 255, 255);
        limit /= 4;
      } else if (mouseButton == RIGHT) {
        background(255, 255, 255);
        limit *= 4;
      }
    }

    @Override
    public void draw() {
        stroke(0);
        strokeWeight(2);
        line(width/2, 0, width/2, height); // y axis
        line(0, height/2, width, height/2); // x axis
        strokeWeight(4);
        stroke(255, 0, 0);
        if (expre) {
          points = test.values(-limit, limit, limit/10000, "x");
          graph(points);
        } else if (multG) {
          for (int i = 0; i < eqs.size(); i++) {
            if (i == 0) {
              stroke(255, 0, 0);
            } else if (i == 1) {
              stroke(0, 255, 0);
            } else if (i == 2) {
              stroke(0, 0, 255);
            } else {
              stroke((float)Math.random() * 256, (float)Math.random() * 256, (float)Math.random() * 256);
            }
            points = eqs.get(i).values(-limit, limit, limit/10000, "x");
            graph(points);
          }
        }
        else {
          graph(xVals, yVals);
        }

    }

    public List<Point> getPoints() {
      return points;
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
            line((float) (p1.getX().value() * (width/limit) + width/2), (float) (height - p1.getY().value() * (height/limit) - height/2),
                 (float) (p2.getX().value() * (width/limit) + width/2), (float) (height - p2.getY().value() * (height/limit) - height/2) );
        }
    }

    public static void main (String[] args) {
        Graph pt = new Graph("100 * cos(x) + 200", 1000);
        ArrayList<Equation> eqs = new ArrayList<Equation>();
        //eqs.add(new Equation("sqrt(1 + x) * sqrt(1 - x)"));
        eqs.add(new Equation("sqrt((1 + x) * (1 - x))"));
        eqs.add(new Equation("-1 * sqrt((1 + x) * (1 - x))"));
        //eqs.add(new Equation("7"));
        eqs.add(new Equation("9.2 + 2.5 * x ^ 3"));
        eqs.add(new Equation("100 * sin(x) + 20"));
        Graph pt1 = new Graph(eqs, 10);
        PApplet.runSketch(new String[]{"Graph"}, pt1);
        //PApplet.runSketch(new String[]{"Graph"}, pt1);
        // hyperbola right side: "((9 * sqrt(x + 36) * sqrt(x + 4) ) / 16) + 40"
    }
}
