import java.util.Scanner;

public class Matrix extends Variable {
  
  double[][] matrix;
  int rows;
  int cols;

  public Matrix(Scanner input) {
    System.out.println("Name:");
    String name = input.nextLine();
    System.out.println("# of rows:");
    rows = input.nextInt();
    System.out.println("# of cols:");
    cols = input.nextInt();
  }

  public String toString() {
    String out = "";
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        out += matrix[i][j] + " ";
      }
      out += '\n';
    }
    return out;
  }

}
