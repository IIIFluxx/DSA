import java.util.*;
public class Fibonacci
{
	public static void main(String[]args)
	{
		int num;
		int answer;
		Scanner sc = new Scanner(System.in);
		try
		{
			System.out.println("Please enter an integer");
			num = sc.nextInt();
			answer = fibRecursive(num);
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
	public static int fib(int num)
	{
		return fibRecursive(num);
	}
	
	private static int fibRecursive(int num)
	{
		int x = 0;
		if(num < 0)
		{
			throw new IllegalArgumentException("Value cannot be a negative number");
		}
		else if (num == 0)
		{
			x = 0;
		}
		else if (num == 1)
		{
			x = 1;
		}
		else
		{
			x = fibRecursive(num-1) + fibRecursive(num-2);
		}
		return x;
	}
	
	public static void showError(String error) // Prints out a specific error message to user.
	{
		System.out.println(error);
	}

}