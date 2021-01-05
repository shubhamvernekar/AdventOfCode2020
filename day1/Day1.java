import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day1 {
	public static void main(String args[]) {
try{
		File obj = new File("input.txt");
		Scanner sc = new Scanner(obj);
		ArrayList<Integer> arr = new ArrayList<Integer>();
		
		while(sc.hasNextLine()) {
			arr.add(Integer.parseInt(sc.nextLine()));
		}
		sc.close();
		
		Collections.sort(arr);
		
		int x = 0, y = 1, z = arr.size() - 1, count = 0, result = 0;
		while(x < z) {
			int a = arr.get(x), b = arr.get(y), c = arr.get(z);
			if(a + b + c == 2020) {
				System.out.println(a * b * c);
				return;
			} else if(a + b + c > 2020) {
				y = x+1;
				z--;
			} else if (y == z) {
				x++;
				y = x+1;
			} else {
				y++;
			}
		}
} catch(FileNotFoundException e) {
e.printStackTrace();
}
	}
}
