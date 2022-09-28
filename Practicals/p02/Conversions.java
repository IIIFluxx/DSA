import java.util.*;

public class Conversions
{
	public static void main(String[] args) 
	{
        Scanner sc = new Scanner(System.in); 
		int num, base;
		try
		{
			System.out.println("Enter the decimal value you wish to convert ");
			num = sc.nextInt();    //getting decimal value from user
			System.out.println("Select base \n Binary - 2 ;\n Octal - 8 ;\n Hexadecimal - 16 ;");
			base = sc.nextInt();   //getting user needs Base type
			System.out.println("Decimal equivalent of " + num	+ " in base " 
				+ base + " is " + " "+ convert(num,base)); 
		}
		catch(InputMismatchException e)
		{
			sc.nextLine();
			showError("Invalid type. Input must be a number.");
		}
		catch(IllegalArgumentException e)
		{
				sc.nextLine();
				showError(e.getMessage());
		}
		
	}
	/* Wrapper method */
	public static int conv(int num, int base)
	{
		return conv(num,base);
	}
	

	 private static String convert(int num, int base) 
	 {
		String hexLetter[] = new String[]{"0","1","2","3","4","5","6","7","8","9","A","B","C","D","E","F"};
		String ans = "";
		if (num == 0)
		{
			ans = "";
		}
		else if (num < 0)
		{
			throw new IllegalArgumentException("Number must be non-zero ");
		}
		else if (base == 16)
		{
			ans = convert(num / base, base) + hexLetter[(num % base)];
		}
		else
		{
			ans = convert(num / base, base) + (num % base);
		}
		return ans;
	 }
	public static void showError(String error) // Prints out a specific error message to user.
	{
		System.out.println(error);
	}
}