import java.util.Scanner;

public class Matrices extends Functions {
  
  public static void main(String[] args) {
    System.out.println(emptyMatrix(4, 4));
    Matrix test1 = new Matrix(new Scanner("1 2 3 4"), 2, 2);
    Matrix test2 = new Matrix(new Scanner("5 6 7 8"), 2, 2);
    System.out.println(multiplication(test1, test2));
  }

  public static Matrix emptyMatrix(int row, int col) {
    String in = "";
    for (int i = 0; i < row * col; i++) {
      in += "0 ";
    }
    return new Matrix(new Scanner(in), row, col);
  }

  public static Matrix multiplication(Matrix a, Matrix b) {
    Matrix out = emptyMatrix(a.rows(), b.cols());
    
    for (int i = 0; i < out.rows(); i++) {
      for (int j = 0; j < out.cols(); j++) {
        int val = 0;
        for (int k = 0; k < out.cols(); k++) {
          val += a.get(j, k) * b.get(k, i)  ;
        }
        out.set(i, j, val);
      }
    }
    return out;
  }

}
