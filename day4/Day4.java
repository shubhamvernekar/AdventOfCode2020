import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

public class Day4 {

  public static boolean validateBYR(int val) {
    if (val >= 1920 && val <= 2002)
      return true;
    return false;
  }

  public static boolean validateIYR(int val) {
    if (val >= 2010 && val <= 2020)
      return true;
    return false;
  }

  public static boolean validateEYR(int val) {
    if (val >= 2020 && val <= 2030)
      return true;
    return false;
  }

  public static boolean validateHGT(String str) {
    String measure = str.substring(str.length() - 2);

    if (measure.equals("cm")) {
      int val = Integer.parseInt(str.substring(0, str.length() - 2));
      if (val >= 150 && val <= 193)
        return true;
    } else if (measure.equals("in")) {
      int val = Integer.parseInt(str.substring(0, str.length() - 2));
      if (val >= 59 && val <= 76)
        return true;
    }

    return false;
  }

  public static boolean validateHCL(String str) {
    if (str.charAt(0) != '#' || str.length() != 7)
      return false;
    
    for (int i = 1; i < str.length(); i++) {
      int ascii = (int) str.charAt(i);
      if (ascii >= 48 && str.charAt(i) <= 57 ||
       ascii >= 97 && ascii <= 102)
       {
       } else {
         return false;
       }
    }
    return true;
  }

  public static boolean validateECL(String str) {
    List<String> colors = new ArrayList<String>(){{
      add("amb");
      add("blu");
      add("brn");
      add("gry");
      add("grn");
      add("hzl");
      add("oth");
    }};

    if (colors.contains(str))
      return true;
    return false;
  }

  public static boolean validatePID(String str) {
    if (str.length() != 9)
      return false;
    
    for (int i = 1; i < str.length(); i++) {
      int ascii = (int) str.charAt(i);
      if (!(ascii >= 48 && str.charAt(i) <= 57))
        return false;
    }

    return true;
  }

  public static boolean validateValue(String str) {
    boolean result = true;
    String[] arr = str.split(":");

    if (arr[0].equals("byr"))
      result = validateBYR(Integer.parseInt(arr[1]));
    else if (arr[0].equals("iyr"))
      result = validateIYR(Integer.parseInt(arr[1]));
    else if (arr[0].equals("eyr"))
      result = validateEYR(Integer.parseInt(arr[1]));
    else if (arr[0].equals("hgt"))
      result = validateHGT(arr[1]);
    else if (arr[0].equals("hcl"))
      result = validateHCL(arr[1]);
    else if (arr[0].equals("ecl"))
      result = validateECL(arr[1]);
    else if (arr[0].equals("pid"))
      result = validatePID(arr[1]);

    return result;
  }

  public static boolean check(String str) {
    String[] fields = new String[]{"byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid"};
    HashMap<String, Integer> required_fields = new HashMap<String, Integer>();

    for(int i = 0; i < fields.length; i++) {
      required_fields.put(fields[i], 0);
    }

    str = str.trim();
    String[] pairs = str.split(" ");
    for (int i = 0; i < pairs.length; i++) {
      if (!validateValue(pairs[i]))
        return false;
      String key = pairs[i].split(":")[0];
      required_fields.put(key,
       (required_fields.get(key) == null ?
        0 : required_fields.get(key)) + 1);
    }

    for (int val : required_fields.values())
      if (val <= 0) 
        return false;

    return true;
  }

  public static void main(String[] args) {
    try {
      File obj = new File("input.txt");
      Scanner sc = new Scanner(obj);
      StringBuilder passport = new StringBuilder("");
      int count = 0;

      while (sc.hasNextLine()) {
        String passport_part = sc.nextLine();
        if (passport_part.length() == 0) {
          if (check(passport.toString())) {
            count++;
          }
          passport.replace(0, passport.length(), "");
        } else {
          passport.append(" " + passport_part);
        }
      }
    
      if (check(passport.toString())) {
        count++;
      }
      System.out.println(count);
      sc.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }
}