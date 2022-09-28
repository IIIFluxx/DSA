import java.util.*;
public class GCD 
{
	public static void main(String args[])
	{
		long num1, num2;
		// Enter two numbers whose GCD needs to be calculated.
		Scanner sc = new Scanner(System.in);
        try 
		{
			System.out.println("Please enter first number to find GCD");
			num1 = sc.nextLong();
			System.out.println("Please enter second number to find GCD");
			num2 = sc.nextLong();
			System.out.println("GCD of two numbers " + num1 +" and " + num2 +" is :" + findGCD(num1,num2));
		}
		catch(InputMismatchException e) // Catches any input that isn't an integer.
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
	public static long fgcd(long a, long b) 
	{
		return findGCD(a,b);
	}
	
	
	
	private static long findGCD(long a, long b)
	{
		long answer;
		
		if(a == 0)
		{
			answer = b;
		}
		else if(b == 0) 
		{
			answer = a;
		}
		else if(a > b) 
		{
			answer = findGCD(b, a % b);
		}
		else
		{
			answer = findGCD(a, b % a);
		}
		return answer;
	}
	
	public static void showError(String error) // Prints out a specific error message to user.
	{
		System.out.println(error);
	}

}