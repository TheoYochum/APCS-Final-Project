import java.util.Scanner;

/**
 * A matrix class made up of Fractions
 */
public class Matrix extends Variable {
  /**
   * The underlying 2d Fraction array and its dimensions
   */
  private Fraction[][] matrix;
  private int rows;
  private int cols;

  /**
   * The basic constructor using a scanner input loop
   * @param name the name of the Matrix
   */
  public Matrix(String name) {
    super(name, "matrix");
    Scanner input = new Scanner(System.in);
    System.out.println("# of rows:");
    rows = input.nextInt();
    System.out.println("# of cols:");
    cols = input.nextInt();
    matrix = new Fraction[rows][cols];
    for (int i = 0; i < cols; i++) {
      for (int j = 0; j < rows; j++) {
        Display.clear();
        Display.display();
        Display.printAt("Enter the values", 1, 3);
        Display.printAt(toString(), 1, 3);
        matrix[i][j] = new Fraction(input.nextDouble(), 2, "matrix");
      }
    }
  }

  /**
   * The basic constructor using a prefilled scanner
   * @param input the scanner input
   * @param row the row dimensions
   * @param col the column dimensions
   */
  public Matrix(Scanner input, int row, int col) {
    super("name", "matrix");
    rows = row;
    cols = col;
    matrix = new Fraction[rows][cols];
    for (int i = 0; i < cols; i++) {
      for (int j = 0; j < rows; j++) {
        matrix[i][j] = new Fraction(input.nextDouble(), "matrix");
      }
    }
  }

  /**
   * The basic constructor taking a 2d integer array
   * @param in the 2d integer array
   */
  public Matrix(int[][] in) {
    super("name", "matrix");
    rows = in.length;
    cols = in[0].length;
    matrix = new Fraction[rows][cols];
    for (int i = 0; i < cols; i++) {
      for (int j = 0; j < rows; j++) {
        matrix[i][j] = new Fraction(in[i][j], "matrix");
      }
    }
  }


  /**
   * A toString to print out the matrix
   */
  public String toString() {
    String out = "\n";
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        if (matrix[i][j] == null) {
          out += "0 ";
        } else {
          out += matrix[i][j] + " ";
        }
      }
      out += "\n";
    }
    return out;
  }

  /**
   * Returns the amount of rows
   * @return the amount of rows
   */
  public int rows() {
    return rows;
  }

  /**
   * Returns the amount of columns
   * @return the amount of columns
   */
  public int cols() {
    return cols;
  }

  /**
   * Sets the matrix value at a given row and column
   * @param row the row to be set
   * @param col the column to be set
   * @param val the value to be set there
   */
  public void set(int row, int col, Fraction val) {
    matrix[row][col] = val;
  }

  /**
   * Gives the Fraction at a set location
   * @param row the row value to be get
   * @param col the column value to be get
   * @return the value to be returned
   */
  public Fraction get(int row, int col) {
    return matrix[row][col];
  }

}
