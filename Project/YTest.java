import processing.core.PApplet;

// Compile with: javac -cp ./core.jar ./YTest.java YTest.java
// Run with: java -cp ./core.jar ./YTest.java YTest.java

public class YTest extends PApplet{

    @Override
    public void settings() {
        size(600, 400);
    }

    @Override
    public void draw() {
      background(115, 118, 61);
      fill(0, 0, 0);
      textSize(38);
      text("Y=", width/2, 40);
      strokeWeight(8);
      line(0, 60, width, 60);
    }

    public static void main (String[] args) {
        ProcessingTest pt = new ProcessingTest();
        PApplet.runSketch(new String[]{"ProcessingTest"}, pt);
    }
}
