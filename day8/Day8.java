import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

public class Day8 {
  private HashMap<Integer, HashMap<String, Integer>> instructions;
  private ArrayList<Integer> tracker;

  Day8() {
    instructions = new HashMap<Integer, HashMap<String, Integer>>();
    tracker = new ArrayList<Integer>();
  }

  public void insertInstructions(int id, String str) {
    String arr[] = str.split(" ");
    HashMap<String, Integer> pairs = new HashMap<String, Integer>();

    pairs.put(arr[0], Integer.parseInt(arr[1]));
    instructions.put(id, pairs);
  }

  public int replaceCorrupted() {
    int result[] = new int[2];

    for (int i = 0; i < instructions.size(); i++) {
      HashMap<String, Integer> tmp =
        new HashMap<String, Integer>(instructions.get(i));

      if (tmp.containsKey("nop") || tmp.containsKey("jmp")) {
        HashMap<Integer, HashMap<String, Integer>> tmp_inst =
          new HashMap<Integer, HashMap<String, Integer>>(instructions);

        String inst = tmp.containsKey("nop") ? "nop" : "jmp";
        int val = tmp.get(inst);
  
        tmp.remove(inst);
        inst = inst.equals("nop") ? "jmp" : "nop";
        tmp.put(inst, val);

        tmp_inst.remove(i);
        tmp_inst.put(i, tmp);

        result = getAccumulatorCount(tmp_inst);

        if (result[1] == -1) {
          break;
        }
      }
    }

    return result[0];
  }

  public int[] getAccumulatorCount(HashMap<Integer, HashMap<String, Integer>> inst) {
    int acc_count = 0, id = 0, is_looped = 1;
    HashMap<String, Integer> tmp_value;

    while(inst.containsKey(id)) {
      if (!tracker.contains(id)) {
        tracker.add(id);
        tmp_value = inst.get(id);

        if (tmp_value.containsKey("acc")) {
          acc_count += tmp_value.get("acc");
          id++;
        } else if (tmp_value.containsKey("jmp")) {
          id += tmp_value.get("jmp");
        } else {
          id++;
        }
      } else {
        break;
      }
    }

    is_looped = inst.containsKey(id) ? 1 : -1;

    tracker.clear();
    return new int[]{acc_count, is_looped};
  }

  public static void main(String args[]) {
    try {
      Day8 obj = new Day8();
      File file = new File("input.txt");
      Scanner sc = new Scanner(file);
      int id = 0;

      while (sc.hasNextLine()) {
        obj.insertInstructions(id++, sc.nextLine());
      }
      sc.close();

      // Part 1
      System.out.println(obj.getAccumulatorCount(obj.instructions)[0]);

      // Part 2
      System.out.println(obj.replaceCorrupted());
    } catch(FileNotFoundException e) {
      e.printStackTrace();
    }
  }
}