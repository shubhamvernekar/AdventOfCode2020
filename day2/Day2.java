import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day2 {
	public static void main(String[] args) {
		try {

			File obj = new File("input.txt");
			Scanner sc = new Scanner(obj);
			int count = 0;

			while(sc.hasNextLine()) {
				if(validate(sc.nextLine()))
					count++;
			}
			System.out.println(count);
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static boolean validate(String str) {
		String[] str_arr = str.split(":");
		String[] checker_arr = str_arr[0].split(" ");
		char checker = checker_arr[1].charAt(0);
		String[] range_arr = checker_arr[0].split("-");
		int x = Integer.parseInt(range_arr[0]);
		int y = Integer.parseInt(range_arr[1]);
		String pass = str_arr[1].trim();
		
		if(pass.charAt(x-1) == checker && pass.charAt(y-1) != checker || pass.charAt(x-1) != checker && pass.charAt(y-1) == checker)
			return true;

		return false;
	}

}
