import java.util.Scanner;

public class Matrices extends Functions {

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
          val += a.get(j, k).value() * b.get(k, i).value();
        }
        out.set(i, j, new Fraction(val, "matrix"));
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
        out.set(i, j, new Fraction(a.get(i, j).value() + b.get(i, j).value(), "matrix"));
      }
    }
    return out;
  }

  public static Matrix scale(double scalar, Matrix in) {
    Matrix out = emptyMatrix(in.rows(), in.cols());
    for (int i = 0; i < out.rows(); i++) {
      for (int j = 0; j < out.cols(); j++) {
        // System.out.println(in.get(i, j).value() * scalar);
        out.set(i, j, new Fraction(in.get(i,j).value() * scalar, "matrix"));
      }
    }
    return out;
  }

  public static double det(Matrix in) {
    if (in.rows() != 2 || in.cols() != 2) {
      if (in.rows() == 1 && in.cols() == 1) {
        return in.get(0, 0).value();
      }
      if (in.rows() == in.cols()) {
        return bigDet(in);
      }
      throw new IllegalArgumentException("Determinant calculation requires a square matrix");
    }
    return in.get(0, 0).value() * in.get(1, 1).value() - in.get(0, 1).value() * in.get(1, 0).value();
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
            pass += in.get(j, k).value() + " ";
          }
        }
      }
      sub = new Matrix(new Scanner(pass), size - 1, size - 1);
      if (i % 2 == 1) {
        sign = -1;
      }
      sum += sign * in.get(0, i).value() * det(sub);
    }
    return sum;
  }


  // https://semath.info/src/cofactor-matrix.html
  public static Matrix cofactor(Matrix in) {
    Matrix out = emptyMatrix(in.rows(), in.cols());
    int size = in.rows();
    Matrix sub;
    for (int i = 0; i < out.rows(); i++) {
      for (int ii = 0; ii < out.cols(); ii++) {
        int sign = 1;
        String pass = "";
        for (int j = 0; j < size; j++) {
          for (int k = 0; k < size; k++) {
            if (k != i && j != ii) {
              pass += in.get(j, k).value() + " ";
            }
          }
        }
        sub = new Matrix(new Scanner(pass), size - 1, size - 1);
        if (i % 2 + ii % 2 == 1) {
          sign = -1;
        }
        out.set(i, ii, new Fraction(sign * det(sub), 2, "matrix"));
      }
    }
    return out;
  }


  // https://mathinsight.org/matrix_transpose
  public static Matrix transpose(Matrix in) {
    Matrix out = emptyMatrix(in.cols(), in.rows());
    for (int i = 0; i < in.rows(); i++) {
      for (int j = 0; j < in.cols(); j++) {
        out.set(j, i, in.get(i, j));
      }
    }
    return out;
  }


  // https://www.varsitytutors.com/hotmath/hotmath_help/topics/adjoint-of-a-matrix
  public static Matrix adjoint(Matrix in) {
    Matrix out = transpose(cofactor(in));
    return out;
  }

  // https://www.cuemath.com/algebra/inverse-of-3x3-matrix/
  public static Matrix inverse(Matrix in) {
    Matrix adjoint = adjoint(in);
    double det = det(in);
    if (det == 0) {
      throw new IllegalArgumentException("The determinant of the matrix is 0, it does not have an inverse"); // Throw an error
    }
    return transpose(scale((1.0 / det), adjoint));
  }
}
