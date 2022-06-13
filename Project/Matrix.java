import java.util.Scanner;

public class Matrix extends Variable {
  
  private Fraction[][] matrix;
  private int rows;
  private int cols;

  public Matrix(String name) {
    super(name, "Matrix");
    Scanner input = new Scanner(System.in);
    System.out.println("# of rows:");
    rows = input.nextInt();
    System.out.println("# of cols:");
    cols = input.nextInt();
    matrix = new Fraction[rows][cols];
    for (int i = 0; i < cols; i++) {
      for (int j = 0; j < rows; j++) {
        System.out.println(toString());
        matrix[i][j] = new Fraction(input.nextDouble(), 2, "matrix");
      }
    }
  }

  public Matrix(Scanner input, int row, int col) {
    rows = row;
    cols = col;
    matrix = new Fraction[rows][cols];
    for (int i = 0; i < cols; i++) {
      for (int j = 0; j < rows; j++) {
        matrix[i][j] = new Fraction(input.nextDouble(), "matrix");
        // System.out.println(matrix[i][j]);
      }
    }
  }

  public Matrix(int[][] in) {
    rows = in.length;
    cols = in[0].length;
    matrix = new Fraction[rows][cols];
    for (int i = 0; i < cols; i++) {
      for (int j = 0; j < rows; j++) {
        matrix[i][j] = new Fraction(in[i][j], "matrix");
      }
    }
  }

  public String toString() {
    String out = "\n";
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        out += matrix[i][j] + " ";
      }
      out += "\n";
    }
    return out;
  }

  public int rows() {
    return rows;
  }

  public int cols() {
    return cols;
  }

  public void set(int row, int col, Fraction val) {
    matrix[row][col] = val;
  }

  public Fraction get(int row, int col) {
    return matrix[row][col];
  }

}
