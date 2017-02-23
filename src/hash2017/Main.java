package hash2017;

import java.io.File;
import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
		System.out.println("Team FH* Reloaded");
		String current = new java.io.File( "." ).getCanonicalPath();
		System.out.printf(current + "\n");
	/*	String[] fileNames = {
				"kittens.in", 
				"me_at_the_zoo.in",
				"trending_today.in",
				"videos_worth_spreading.in"
			};*/
		
		String[] fileNames = {
				"kittens.in"
			};
		
		
		for (String fileName : fileNames) {
			solveTaskUsingInput(new File(fileName));
		}
		
	}
	
	public static void solveTaskUsingInput(File file) throws IOException {
		if(!file.exists())
		{
			throw new IOException();
		}
		AssignmentParser.parseFile(file);
	}

}
