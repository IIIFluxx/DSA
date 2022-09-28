public class EquationSolverTestHarness 
{
 	public static void main(String[] args) 
 	{
		 try
		 {
			EquationSolver es = new EquationSolver();
			String equation;
			double solution;
			equation = "( 5 + 1 )";
			// equation = " ( 10.3 * ( 14 + 3.2 ) ) / ( 5 + 2 - 4 * 3 ) ";
			solution = es.solve(equation);
			System.out.println("Postfix value: " + solution + " \n");
		 }
		 catch(IllegalArgumentException e)
		 {
			 System.out.println(e.getMessage());
		 }
	 }
	
	/*
		System.out.println("Testing postfix expressions with\n" +
		"a = 2.0, b = 3.0, c = 4.0, d = 5.0\n");
		testPostfix("a+b");
		testPostfix("a-b+c*d");
		testPostfix("(a+b)*c-d");
		testPostfix("a+b*(c-d)");
		testPostfix("(a+b)/(c-d)");
		testPostfix("a*(b/(c-d))");
	} // end main

 	public static void testPostfix(String infixExpression) 
	 {
		String postfixExpression = Postfix.convertToPostfix(infixExpression);
		System.out.println("Infix: " + infixExpression);
		System.out.println("Postfix: " + postfixExpression);
		System.out.println("Value: " + Postfix.evaluatePostfix(postfixExpression));
		System.out.println();
 	} */
}



