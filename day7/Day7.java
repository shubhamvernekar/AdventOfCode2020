import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

public class Day7 {
  private HashMap<String, HashMap<String, Integer>> luggage;
  private HashSet<String> unique_bags_list;

  Day7() {
    luggage = new HashMap<String, HashMap<String, Integer>>();
    unique_bags_list = new HashSet<String>();
  }

  public void insertLuggage(String str) {
    String[] str_arr = str.split(" bags contain ");
    String container_bag = str_arr[0].split(" bags")[0];
    HashMap<String, Integer> bags_list = new HashMap<String, Integer>();
    String[] substr_arr = str_arr[1].split(", ");
    char chr;
    int remove_end = 0, number = 0;
  
    if (substr_arr.length == 1 && !substr_arr[0].equals("no other bags.")) {
      chr = substr_arr[0].charAt(substr_arr[0].length() - 2);
      number = Character.getNumericValue(substr_arr[0].charAt(0));

      if (chr == 's') {
        remove_end = 6;
      } else {
        remove_end = 5;
      }
      bags_list.put(substr_arr[0].substring(2, substr_arr[0].length() - remove_end), number);
    } else if (substr_arr.length != 1) {
      for (int i = 0; i < substr_arr.length; i++) {
        chr = substr_arr[i].charAt(substr_arr[i].length() - 1);
        number = Character.getNumericValue(substr_arr[i].charAt(0));

        if (chr == 's') {
          remove_end = 5;
        } else if (chr == '.') {
          if (substr_arr[i].charAt(substr_arr[i].length() - 2) == 's')
            remove_end = 6;
          else 
            remove_end = 5;
        } else {
          remove_end = 4;
        }
        bags_list.put(substr_arr[i].substring(2, substr_arr[i].length() - remove_end), number);
      }
    }
    luggage.put(container_bag, bags_list);
  }

  // Part 1
  public int countBagsPart1(String str) {
    int result = 0;

    for (Map.Entry<String, HashMap<String, Integer>> entry : luggage.entrySet()) {
      if (entry.getValue().containsKey(str)) {
        unique_bags_list.add(entry.getKey());
        countBagsPart1(entry.getKey());
      }
    }

    return unique_bags_list.size();
  }

  // Part 2
  public int countBagsPart2(String str) {
    int result = 1;

    HashMap<String, Integer> tmp = luggage.get(str);

    for (Map.Entry<String, Integer> entry : tmp.entrySet()) {
      result += entry.getValue() * countBagsPart2(entry.getKey());
    }

    return result;
  }

  public static void main(String args[]) {
    try {
      Day7 obj = new Day7();
      File file = new File("input.txt");
      Scanner sc = new Scanner(file);
      String str = "";
      int result = 0;

      while (sc.hasNextLine()) {
        obj.insertLuggage(sc.nextLine());
      }
      sc.close();

      // Part 1
      // result = obj.countBagsPart1("shiny gold");
      // System.out.println("Result : " + result);
      
      // Part 2
      result = obj.countBagsPart2("shiny gold");
      System.out.println("Result : " + --result);
    } catch(FileNotFoundException e) {
      e.printStackTrace();
    }
  }
}