import processing.core.PApplet;
import java.util.*;
import java.lang.*;
import java.util.*;

// Compile with: javac -classpath "../Libraries/core.jar;../Project" ProcessingTest.java
// Run with: java -classpath "../Libraries/core.jar;../Project" ProcessingTest

public class Graph extends PApplet{

    List<Point> points;
    ArrayList<Equation> eqs;
    Equation test;
    boolean expre = false;
    boolean multG = false;
    double[] xVals, yVals;
    double limit;

    /**
     * Basic constructor taking a String expression
     * @param String expression that will be passed into an equation
     */
    public Graph(String exp) {
      test = new Equation(exp, "test");
      limit = 10 * 2;
      expre = true;
    }

    /**
     * Basic constructor taking a Equation
     * @param Equation with an expression that will be passed into the rest of the object
     */
    public Graph(Equation x) {
      test = x;
      limit = 10 * 2;
      expre = true;
    }

    /**
     * Basic constructor taking two arrays of values
     * @param Double array with set of x values
     * @param Double array with set of y values
     */
    public Graph(double[] x, double[] y) {
      xVals = x;
      yVals = y;
      limit = 10 * 2;
    }

    /**
     * Basic constructor taking an ArrayList of Equation objects so they may all be simultaneously graphed
     * @param ArrayList of Equation objects with their own seperate Lists of Point objects
     */
    public Graph(ArrayList<Equation> eq) {
      eqs = eq;
      multG = true;
      limit = 10 * 2;
    }

    /**
     * Sets the screen at a size of 500 x 500
     */
    @Override
    public void settings() {
        size(500, 500);
    }

    /**
     * If left click is pressed the values are scaled down by 5 and if right click is pressed the values are scalled up by 5
     */
    @Override
    public void mousePressed() {
      if (mouseButton == LEFT) {
        background(255, 255, 255);
        limit /= 5;
      } else if (mouseButton == RIGHT) {
        background(255, 255, 255);
        limit *= 5;
      }
    }

    /**
     * Draws the x and y axis as well as the ticks on them. Then draws the graph depending on what value was parsed into the class.
     */
    @Override
    public void draw() {
        background(255, 255, 255);
        stroke(0);
        strokeWeight(2);
        line(width/2, 0, width/2, height); // y axis
        line(0, height/2, width, height/2); // x axis
        for (int i = 0; i <= height; i+= 25) {
          if (i == 0 || i == height) {
            fill(0);
            text("" + limit / 2, width/2 + 20, i-10);
            text("" + -limit / 2, i, height/2 - 20);
          }
          line(width/2 - 10, i, width/2 + 10, i);
          line(i, height/2 - 10, i, height/2 + 10);
        }
        strokeWeight(4);
        if (expre) {
          points = test.values(-limit, limit, limit/10000, "x");
          stroke(255, 0, 0);
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
            stroke(0);
          }
        }
        else {
          stroke(255, 0, 0);
          graph(xVals, yVals);
        }

    }

    /**
     * Converts the two double arrays into Point objects that are stored into a List and then passed into another graph function that takes Lists of Point objects
     * @param Double array of x values
     * @param Double array of y values
     */
    public void graph(double[] x, double[] y) {
      points = new List<Point>();
      for (int i = 0; i < x.length; i++) {
          Point p1 = new Point(x[i], y[i], "point");
          points.add(p1);
      }
      graph(points);
    }

    /**
     * Draws lines between each adjacent Point object in the List
     * @param List of Point objects
     */
    public void graph(List<Point> in) {
      for (int i = 1; i < in.length(); i++) {
        Point p1 = in.get(i - 1);
        Point p2 = in.get(i);
        if (p1.getY().value() / p2.getY().value() != 1.0)  {
          if (p1.getY().value() != 0 && p2.getY().value() != 0 ){
            line((float) (p1.getX().value() * (width/limit) + width/2), (float) (height - p1.getY().value() * (height/limit) - height/2),
                 (float) (p2.getX().value() * (width/limit) + width/2), (float) (height - p2.getY().value() * (height/limit) - height/2) );
          }
        }
      }
    }

    public static void main (String[] args) {
        Graph pt = new Graph("100 * cos(x) + 200");
        ArrayList<Equation> eqs = new ArrayList<Equation>();
        //eqs.add(new Equation("sqrt(1 + x) * sqrt(1 - x)", "test"));
        //eqs.add(new Equation("sqrt((1 + x) * (1 - x))", "test"));
        //eqs.add(new Equation("-1 * sqrt((1 + x) * (1 - x)) + 9", "test"));
        //eqs.add(new Equation("7"));
        //eqs.add(new Equation("x ^ 4.2323", "test"));
        eqs.add(new Equation("100 * sin(x) + 20", "test"));
        Equation sin = new Equation("100000 * sin(x)", "test");
        Graph pt1 = new Graph(eqs);
        PApplet.runSketch(new String[]{"Graph"}, pt1);
        //PApplet.runSketch(new String[]{"Graph"}, pt1);
        // hyperbola right side: "((9 * sqrt(x + 36) * sqrt(x + 4) ) / 16) + 40"
    }
}
