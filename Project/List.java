import java.util.ArrayList;

public class List extends Variable {
  private ArrayList<Variable> list;
  
  public int length() {
    return list.size();
  }

  public boolean set(int index, Variable element) {
    if (index >= list.size() || index < 0) {
      return false;
    }
    list.set(index, element);
    return true;
  }

  public Variable get(int index) {
    if (index >= list.size() || index < 0) {
      return null;
    }
    return list.get(index);
  }

}
