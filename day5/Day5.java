import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

public class Day5 {

  public static int getRow(String str) {
    int a = 0, b = 127;
    for (int i = 0; i < str.length(); i++) {
      if (str.charAt(i) == 'F') {
        b = ((b - a) / 2) + a;
      } else if (str.charAt(i) == 'B') {
        a = ((b - a) / 2) + a + 1;
      }
    }
    return b;
  }

  public static int getCol(String str) {
    int a = 0, b = 7;
    for (int i = 0; i < str.length(); i++) {
      if (str.charAt(i) == 'L') {
        b = ((b - a) / 2) + a;
      } else if (str.charAt(i) == 'R') {
        a = ((b - a) / 2) + a + 1;
      }
    }
    return b;
  }

  public static int getSetId(String str) {
    String rowStr = str.substring(0, str.length() - 3);
    String colStr = str.substring(str.length() - 3);

    int row = getRow(rowStr);
    int col = getCol(colStr);

    return row * 8 + col;
  }

  public static void main(String[] args) {
    try {
      File obj = new File("input.txt");
      Scanner sc = new Scanner(obj);
      int setId = 0;
      ArrayList<Integer> setIds = new ArrayList<Integer>();

      while (sc.hasNextLine()) {
        String str = sc.nextLine();
        setIds.add(getSetId(str));

        // Find the Largest seat Id PART 1
        // int tempSetId = getSetId(str);
        
        // if (tempSetId > setId)
        //   setId = tempSetId;
      }
      //  System.out.println(setId);

       // PART 2
       Collections.sort(setIds);
       for (int i = 0; i < setIds.size() - 1; i++) {
         if (setIds.get(i+1) - setIds.get(i) != 1)
          setId = setIds.get(i+1);
       }
       System.out.println(setId - 1);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }
}