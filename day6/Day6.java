import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

public class Day6 {

  public static int getCount(String str, int users_count) {
    HashMap<Character, Integer> map = new HashMap<Character, Integer>();

    // PART 1
    // for (int i = 0; i < str.length(); i++) {
    //   map.put(str.charAt(i), 1);
    // }
    // return map.size();

    // PART 2
    int max_selected = 0, yes_count = 0;
    for (int i = 0; i < str.length(); i++) {
      char qus = str.charAt(i);
      map.put(qus, map.get(qus) == null ?
       1 : map.get(qus) + 1);
    }

    for (Integer i : map.values()) {
      if (i == users_count)
        yes_count++;
    }
    return yes_count;
  }

  public static void main(String[] args) {
    try {
      File obj = new File("input.txt");
      Scanner sc = new Scanner(obj);
      int count = 0, users_count = 0;
      StringBuilder groupAns = new StringBuilder("");

      while (sc.hasNextLine()) {
        String str = sc.nextLine();
        if (str.length() == 0) {
          count += getCount(groupAns.toString(), users_count);
          groupAns.replace(0, groupAns.length(), "");
          users_count = 0;
        } else {
          users_count++;
          groupAns.append(str);
        }
      }
      count += getCount(groupAns.toString(), users_count);
      sc.close();
      
      System.out.println(count);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }
}