import java.util.Scanner;

public class Matrices extends Functions {
  
  public static void main(String[] args) {
    int[][] matrix1 = { {14, 2, 3, 4},
                        {5, 6, 2, 8,},
                        {9, 10, 11, 12},
                        {13, 14, 15, 16}};
    int[][] matrix2 = { {5, 6},
                        {7, 8}};
    Matrix test1 = new Matrix(matrix1);
    Matrix test2 = new Matrix(matrix2);
    System.out.println(det(test1));
  }

  public static Matrix emptyMatrix(int row, int col) {
    String in = "";
    for (int i = 0; i < row * col; i++) {
      in += "0 ";
    }
    return new Matrix(new Scanner(in), row, col);
  }

  public static Matrix multiplication(Matrix a, Matrix b) {
    if (a.rows() != b.cols()) {
      throw new IllegalArgumentException("The number of rows in matrix a must be equal to the number of columns in matrix b for multiplication");
    }
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

  public static Matrix addition(Matrix a, Matrix b) {
    if (a.rows() != b.rows() || a.cols() != b.cols()) {
      throw new IllegalArgumentException("Matrix size must be the same for addition");
    }
    Matrix out = emptyMatrix(a.rows(), a.cols());
    
    for (int i = 0; i < out.rows(); i++) {
      for (int j = 0; j < out.cols(); j++) {
        out.set(i, j, a.get(i, j) + b.get(i, j));
      }
    }
    return out;
  }

  public static Matrix scale(int scalar, Matrix in) {
    Matrix out = emptyMatrix(in.rows(), in.cols());
    for (int i = 0; i < out.rows(); i++) {
      for (int j = 0; j < out.cols(); j++) {
        out.set(i, j, in.get(i,j) * scalar);
      }
    }
    return out;
  }

  public static int det(Matrix in) {
    if (in.rows() != 2 || in.cols() != 2) {
      if (in.rows() == in.cols()) {
        return bigDet(in);
      }
      throw new IllegalArgumentException("Determinant calculation requires a square matrix");
    }
    return in.get(0, 0) * in.get(1, 1) - in.get(0, 1) * in.get(1, 0);
  }

  private static int bigDet(Matrix in) {
    int size = in.rows();
    int sum = 0;
    Matrix sub;
    for (int i = 0; i < size; i++) {
      int sign = 1;
      String pass = "";
      for (int j = 1; j < size; j++) {
        for (int k = 0; k < size; k++) {
          if (k != i) {
            pass += in.get(j, k) + " ";
          }
        }
      }
      sub = new Matrix(new Scanner(pass), size - 1, size - 1);
      if (i % 2 == 1) {
        sign = -1;
      }
      sum += sign * in.get(0, i) * det(sub);
    }
    return sum;
  }

}
