public class Display {
  public static void clear() {
    System.out.println("\033[2J");
    start();
  }

  public static void start() {
    System.out.println("\033[1;1H");
  }
}
