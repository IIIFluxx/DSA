import java.util.*;
public class TOH
{
	
    /*
	
	METHOD towers IMPORT n, src, dest
	if(n==1)
	{
		moveDisk(src,dest);
	}
	else
	{
		tmp = 6 - src - dest;
		towers = (n-1, src, tmp);
		moveDisk(src,dest);
		towers(n-1,tmp,dest);
	}
	
	
	*/
	public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
		try
		{
			System.out.print("Enter number of discs: ");
			int numDisks = sc.nextInt();
			towers(numDisks, 1, 3);
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
	
	private static void towers(int n, int src, int dst)
    {
		int tmp; // n.o of free pegs		
        if (n == 1)
        {
            moveDisk(n, src, dst);
        }
		else
		{
			tmp = 6 - src - dst;
			towers(n - 1,src,tmp);
			moveDisk(n, src, dst);
			towers(n - 1,tmp,dst);
		}
    }
		
	
	public static void moveDisk(int n, int src, int dst)
    {
		System.out.println("Moving top disk " + n + " from " + src + " --> " + dst);
	}
	
	public static void showError(String error) // Prints out a specific error message to user.
	{
		System.out.println(error);
	}

	
}