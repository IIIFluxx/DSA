import java.util.*;
public class EquationSolver 
{	
		private DSAQueue postfixQueue = new DSAShufflingQueue();
		private DSAStack opStack = new DSAStack();
		
	public double solve(String equation)
	{
		return evaluatePostfix(parseInfixToPostfix(equation));
		/* Should call parseInfixToPostfix() then evaluatePostfix()*/
	}
	
	private DSAQueue parseInfixToPostfix(String equation)  // Conversion method (Use Stack). 
                                                           // Equation = infix input. Send/output to postfixQueue.
	{
		String [] lineArray;
        double numberToken;
        Character term;
		lineArray = equation.split(" ");
		
		/* Use a DSAStack to stack up the operators 
		   
		   Use Doubles for operands and Characters for operators but put them all in the queue in postfix order
		*/
		   
		for(int ii=0;ii<lineArray.length;ii++)
		{
           
            if(lineArray[ii].charAt(0) == '*' || lineArray[ii].charAt(0) == '/' || lineArray[ii].charAt(0) == '+' || lineArray[ii].charAt(0) == '-')
            {
                    term = (Character)(lineArray[ii].charAt(0)); 
                    while(!(opStack.isEmpty()) && ((Character)opStack.top()).charValue() != '(' && precedenceOf((Character)opStack.top()) >= precedenceOf(term))
                    {
                        postfixQueue.enqueue(opStack.pop());
                    }
                    opStack.push(term); // <><><><>< push the new operator onto the stack after precedence check.
            }
                                              // 
            else if(lineArray[ii].charAt(0) == '(')
            {
				term = (Character)(lineArray[ii].charAt(0));
                opStack.push(term); // Use STACK for OPERATORS ~ Char (+-/*)
            }
            else if(lineArray[ii].charAt(0) == ')')
            {
				term = (Character)(lineArray[ii].charAt(0));
				
				while(((Character)opStack.top()).charValue() != '(')  
// 	Finds all OPERANDS/numbers and places them in the stack. After the ), and before the next '(' 
                {
                    postfixQueue.enqueue(opStack.pop());	
                }
                opStack.pop(); // Pop the ) but don't enqueue it.
            }	
			else //must be a number/operand
			{
				numberToken = Double.parseDouble(lineArray[ii]); 
// NEED TO MAKE lineArray[ii] into a double for it to be enqueued into the queue.
				postfixQueue.enqueue(numberToken); 
			}
        }
		while(!(opStack.isEmpty()))
		{   
            postfixQueue.enqueue(opStack.pop());
            /* "transfer the remaining operators on the stack to the postfixQueue in pop()-order" ?? */
		}
		return postfixQueue;
	}
	
	
	
	private double evaluatePostfix(DSAQueue postfixQueue) // Evaluation method (use Queue)
	{
		
		double result;
		double s1, s2;
		double tempVar;
		DSAStack numStack = new DSAStack();
		Object next;
		Character operator;

		System.out.println("Test statement 1 - reached");
		while(!postfixQueue.isEmpty())
		{
			next = postfixQueue.dequeue();

			System.out.println("Test statement - dequeued ");
			if(next instanceof Double)
			{
				numStack.push((double)next); // error
				System.out.println("Test statement - next pushed");
			}
			else if(next instanceof Character)
			{
				operator = (Character)next;
				s1 = (double)numStack.pop();
				s2 = (double)numStack.pop();
				numStack.push(executeOperation(operator, s1, s2));
				System.out.println("Test statement - executed op");

				/* tempVar = executeOperation((Character)postfixQueue.dequeue(),s1,s2); 
				//tempVar = executeOperation((Character)postfixQueue.dequeue(),(double)numStack.pop(),(double)numStack.pop());
				numStack.push(tempVar); */
			}
			else
			{
				throw new IllegalArgumentException("Error in evaluating postfix. \n Program terminated");
			}
		}
		result = (double)numStack.pop();
		return result;
	}
		/* 
        while postfixQueue is NOT empty
           if postfixQueue.peek instanceof Double
                newStack.push(postfixQueue.dequeue())
            elseif postfixQueue.peek instanceof Character
           
		• If an item is instanceof Double [Java] , it is an operand – push it onto the numStack
		• If an item is instanceof Character [Java], it is an operator – grab the top two operands from the stack
		  and evaluate the binary operation. Push the result back on to the numStack*/
	
	private int precedenceOf(Character theOp)   
	{
		// use  DSAStack.top() how?
		
		int returnValue;
		switch(theOp.charValue())
		{
			case '(' : 
				returnValue = 0; 
				break;
			case ')' : 
				returnValue = 0;
				break;
			case '+': 
				returnValue = 1;
				break;
			case '-': 
				returnValue = 1;
				break;
			case '*': 
				returnValue = 2; 
				break;
			case '/':
				returnValue = 2;
				break;
			default: returnValue = -1;
				break;
		} // throw Exception is returnValue -1?
		return returnValue;
	}

	private double executeOperation(Character op,double op1,double op2)
	{
		double ans = 0.0;
		switch(op)
		{
			case '+': 	
				ans = op1 + op2;
				break;
			case '-':
				ans = op2 - op1;
				break;
			case '*':
				ans = op1 * op2;
				break;
			case '/':
				if (op1 == 0)
				{
					throw new IllegalArgumentException("Argument 'divisor' is 0");
				}
				ans = op2 / op1;
		}
		return ans;			
		/* Helper function for evaluatePostfix(). Executes the binary operation implied by op,
		   either op1 + op2, op1 – op2, op1 * op2 or op1 / op2, and returns the result.*/
	}
}
