package hash2017;

import java.io.File;
import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
		System.out.println("Team FH* Reloaded");
		String[] fileNames = {
				"file1.in", 
				"file2.in",
				"file3.in"
			};
		
		for (String fileName : fileNames) {
			solveTaskUsingInput(new File(fileName));
		}
		
	}
	
	public static void solveTaskUsingInput(File file) throws IOException {
		AssignmentParser.parseFile(file);
	}

}
