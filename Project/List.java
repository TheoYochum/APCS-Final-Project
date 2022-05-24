import java.util.ArrayList;


/**
 * List varaible which abstracts functionality
 * and allows storage of a single type of variable
 */
public class List extends Variable {
  private ArrayList<Variable> list;
  
  /**
   * Provides The length of the list
   * @return The length of the list
   */
  public int length() {
    return list.size();
  }

  /**
   * Attempts to set the calue of the list to the provided element
   * @param index The index of the elemtn to be set
   * @param element The reference of the element which will be set
   * @return false if the set failed likely due to out of bounds, true if worked
   */
  public boolean set(int index, Variable element) {
    if (index >= list.size() || index < 0) {
      return false;
    }
    list.set(index, element);
    return true;
  }

  /**
   * Returns the value at a set index
   * @param index the value of the index to be returned
   * @return the value at the index, null if out of bounds
   */
  public Variable get(int index) {
    if (index >= list.size() || index < 0) {
      return null;
    }
    return list.get(index);
  }

}
