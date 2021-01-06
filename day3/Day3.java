import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day3 {
	public static void main(String[] args) {
		try {
			File obj = new File("input.txt");
			long result = 1;
			
			int[][] slope = {{1,1},{3,1},{5,1},{7,1},{1,2}};

			for (int i=0; i<slope.length; i++) {
				int trees_count = 0, down = 0;
				Scanner sc = new Scanner(obj);
				sc.nextLine();
				while(sc.hasNextLine()) {
					down += slope[i][0];
					String str = "";
					for(int c=0; c<slope[i][1]; c++) {
						if(sc.hasNextLine())
							str = sc.nextLine();
					}
					if(str.length() -1 < down)
						down -= str.length();
					if(str.charAt(down) == '#')
						trees_count++;
				}
        System.out.println(trees_count);
				result *= trees_count;
				sc.close();
			}
			System.out.println(result);
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
