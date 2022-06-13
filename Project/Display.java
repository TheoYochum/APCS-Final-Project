/**
 * The class containing escape sequences to format the terminal
 */
public class Display {

  /**
   * Moves the cursor to the provided coords
   * @param x the column to be moved to
   * @param y the row to be moved to
   */
  public static void goTo(int x, int y) {
    System.out.print("\033[" + y + ";" + x + "H");
  }

  /**
   * Moves the cursour to the provided coords and prints a string at those coords
   * @param in the string to be printed
   * @param x the column to be printed at
   * @param y the row to be printed at
   */
  public static void printAt(String in, int x, int y) {
    goTo(x, y);
    System.out.println(in);
  }

  /**
   * The standard display formatting
   */
  public static void display() {
    printAt("The Thakram Calculator™  Type help if you need it", 1, 1);
  }

  /**
   * Clears the screen
   */
  public static void clear() {
    System.out.println("\033[2J");
  }
}
