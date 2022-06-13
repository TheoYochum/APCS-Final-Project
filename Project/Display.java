public class Display {

  public static void goTo(int x, int y) {
    System.out.print("\033[" + y + ";" + x + "H");
  }

  public static void printAt(String in, int x, int y) {
    goTo(x, y);
    System.out.println(in);
  }

  public static void display() {
    printAt("The Thakram Calculator™,  Type help if you need it", 1, 1);
  }

  public static void clear() {
    System.out.println("\033[2J");
    start();
  }

  public static void start() {
    System.out.println("\033[1;1H");
  }
}
