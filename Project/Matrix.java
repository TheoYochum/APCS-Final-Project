import java.util.Scanner;

public class Matrix extends Variable {
  
  private int[][] matrix;
  private int rows;
  private int cols;

  public Matrix(String name) {
    super(name, "Matrix");
    Scanner input = new Scanner(System.in);
    System.out.println("# of rows:");
    rows = input.nextInt();
    System.out.println("# of cols:");
    cols = input.nextInt();
    matrix = new int[rows][cols];
    System.out.println();
    for (int i = 0; i < cols; i++) {
      for (int j = 0; j < rows; j++) {
        Display.clear();
        Display.display();
        Display.printAt("Enter values left to right top to bottom", 1, 3);
        Display.printAt(toString(), 1, 5);
        matrix[i][j] = input.nextInt();
      }
    }
  }

  public Matrix(Scanner input, int row, int col) {
    rows = row;
    cols = col;
    matrix = new int[rows][cols];
    for (int i = 0; i < cols; i++) {
      for (int j = 0; j < rows; j++) {
        matrix[i][j] = input.nextInt();
      }
    }
  }

  public Matrix(int[][] in) {
    rows = in.length;
    cols = in[0].length;
    matrix = new int[rows][cols];
    for (int i = 0; i < cols; i++) {
      for (int j = 0; j < rows; j++) {
        matrix[i][j] = in[i][j];
      }
    }
  }

  public String toString() {
    String out = "\n";
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        out += matrix[i][j] + " ";
      }
      out += '\n';
    }
    return out;
  }

  public int rows() {
    return rows;
  }

  public int cols() {
    return cols;
  }

  public void set(int row, int col, int val) {
    matrix[row][col] = val;
  }

  public int get(int row, int col) {
    return matrix[row][col];
  }

}
