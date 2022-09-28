import java.util.*;
public class CallStack
{
	public static void main(String[] args)
	{
		String capsule = "[---]";
		String s = "";
		DSAStack nStack = new DSAStack();
		
		sub1(capsule, nStack);
		sub4(s, nStack);
		return;
	}
	
	
	
	public static void sub1(String capsule, DSAStack nStack)
	{
		System.out.println("Method: sub1");
		nStack.push(capsule);
		
		nStack.displayStack();
		
		sub2(capsule, nStack);
		
		nStack.displayStack();
		
		sub3(capsule, nStack);
		
		System.out.println("Method: back to sub1");
		
		nStack.pop();
		
		nStack.displayStack();
	}
	
	public static void sub2(String capsule, DSAStack nStack)
	{
		System.out.println("Method: sub2");
		nStack.push(capsule);
		nStack.displayStack();
		return;
	}
	
	public static void sub3(String capsule, DSAStack nStack)
	{
		System.out.println("Method: sub3");
		nStack.push(capsule);
		nStack.displayStack();
		return;
	}
	
	public static void sub4(String s, DSAStack nStack)
	{
		System.out.println("Method: sub4");
		String ship = "(---)";
		nStack.push(ship);
		nStack.displayStack();
		return;
	}
}
