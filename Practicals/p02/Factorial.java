import java.util.*;
public class Factorial
{
	public static void main(String[]args)
	{
		long num;
		long answer;
		Scanner sc = new Scanner(System.in);
		try
		{
			System.out.println("Please enter a value to calculate the factorial of \n");
			num = sc.nextLong();
			answer = factRecursive(num);
			System.out.println("Answer: \n" + answer);
		}
		catch(InputMismatchException e)
		{
			sc.nextLine();
			showError("Invalid type. Input must be an integer.");
		}
		catch(IllegalArgumentException e)
		{
				sc.nextLine();
				showError(e.getMessage());
		}
	}
	
	
	/* Wrapper method */
	public static long fact(long num)
	{
		return factRecursive(num);
	}
	
	private static long factRecursive(long num)
	{
		long nFact = 1;
		if(num < 0)
		{
			throw new IllegalArgumentException("Value cannot be a negative number");
		}
		else if(num == 0)
		{
			nFact = 1;
		}
		else
		{
			nFact = num * factRecursive(num-1);
		}
		return nFact;
	}
	
	
	public static void showError(String error) // Prints out a specific error message to user.
	{
		System.out.println(error);
	}
	
}