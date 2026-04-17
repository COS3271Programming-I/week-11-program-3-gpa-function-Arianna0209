package week11;
import java.util.InputMismatchException;
import java.util.Scanner;

public class GPAapp {
	
	// This program calculates GPAs using the grades and credits the user enters.
	
	static Scanner userinput = new Scanner(System.in);
	
	// Method for trapping InputMismatchException error on integers:
	static int intTry(String message) {
		int response;
		while (true) {
			try {
				System.out.print(message);
				response = userinput.nextInt();
				userinput.nextLine();
				return response;
			} 
			catch (InputMismatchException e) {
				System.out.println("Please enter a valid integer number (no decimal point).");
				userinput.nextLine();
				continue;
			}
		}
	}
	
	// Method for trapping InputMismatchException error on doubles:
	static double doubleTry(String message) {
		double response;
		while (true) {
			try {
				System.out.print(message);
				response = userinput.nextDouble();
				return response;
			} catch (InputMismatchException e) {
				System.out.println("Please enter a valid number.");
				userinput.nextLine();
				continue;
			}
		}
	}
		
	// Method for ensuring that an entered integer is between a certain range:
	static int intTryRange(String message, int start, int end) {
		int validNumber;
		while (true) {
			validNumber = intTry(message);
			if (validNumber >= start && validNumber <= end) {
				return validNumber;
			}
			else {
				System.out.format("Please enter a number between %d and %d.\n", start, end);
				continue;
			}
		}
	}
	
	// Method for ensuring that an entered integer is between a certain range:
	static double doubleTryRange(String message, double start, double end) {
		double validNumber;
		while (true) {
			validNumber = doubleTry(message);
			if (validNumber >= start && validNumber <= end) {
				return validNumber;
			}
			else {
				System.out.format("Please enter a number between %.2f and %.2f.\n", start, end);
				continue;
			}
		}
	}
	
	// Method for ensuring that a user can only answer "yes" or "no" to a yes or no question:
	static String yesOrNo(String message) {
		String response;
		while (true) {
			System.out.print(message);
			response = userinput.nextLine();
			response = response.toLowerCase();
			if (response.equals("yes") || response.equals("no")) {
				return response;
			}
			else {
				System.out.println("Please enter \"yes\" or \"no\".\n");
				continue;
			}
			
		}
	}
	
	static double computeGPA (double [] grades, double [] credits) {
		
		// Multiply each GPA grade with its corresponding credit to weigh it,
		// then add it to a running total:
		double total = 0;
		for (int count = 0; count < grades.length; count++) {
			double weightedGPA = grades[count]*credits[count];
			total += weightedGPA;
		}
		
		// Get the total number of credits:
		double creditsTotal = 0;
		for (int count = 0; count < credits.length; count++) {
			creditsTotal += credits[count];
		}
		
		// Divide the total weighted GPAs by the number of credits:
		double finalGPA = total/creditsTotal;
		return finalGPA;
	}
	
	
	public static void main(String[] args) {
		// Tell the user what the program does:
		System.out.println("This program allows you to enter a set of grades(0-4) and their corresponding"
				+ "\ncredits, and the program will tell you the overall GPA.");
		
		String anotherGPA = "yes";
		while (anotherGPA.equals("yes")) {
			// Ask how many values the user would like to enter:
			int numOfGrades = intTryRange("\nHow many grades and credits would you like to enter? You can enter up to 20. ",
									1, 20);
			
			// Have the user enter the grades and credits:
			double[] numberGrades = new double[numOfGrades];
			double[] courseCredits = new double[numOfGrades];
			for (int count = 0; count < numOfGrades; count++) {
				numberGrades[count] = doubleTryRange(String.format("\nEnter course grade number %d: ", (count+1)), 0.0, 4.0);
				courseCredits[count] = doubleTryRange(String.format("Enter the number of credits for course %d: ",
						(count+1)), 0.0, 6.0);
			}
			
			// Calculate the GPA:
			double gpa = computeGPA(numberGrades, courseCredits);
			
			// Print the GPA:
			System.out.format("\nThe GPA is %.2f.", gpa);
			
			// Ask the user if they'd like to compute another GPA:
			userinput.nextLine();
			anotherGPA = yesOrNo("\n\nWould you like to computer another GPA? Enter \"yes\" or \"no\": ");
		}
		
		// When the user breaks the loop, the program will end:
		System.out.println("\nProgram ended.");
	}

}
