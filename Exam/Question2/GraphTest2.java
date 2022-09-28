/**
 * DSA Final Assessment Question 2 - GraphTest.java
 *
 * Name : Bharath Sukesh
 * ID   : 19982634
 *
 **/
public class GraphTest2
{
	public static void main(String args[])
	{
			// put your code here
			DSAGraph g = new DSAGraph();
			spacer();
			
			System.out.println("Adding vertices: 1,2,3,4");
			g.addVertex("one", 1);
			g.addVertex("two", 2);
			g.addVertex("three", 3);
			g.addVertex("four", 4);
			
			System.out.println("Adding edges between vertices \n");
			g.addEdge("one", "two");
			g.addEdge("one", "three");
			g.addEdge("one", "four");
			g.addEdge("four", "two");
			g.addEdge("four", "three");

			spacer();
			System.out.println("\nAdjacency graph: \n");
			g.displayAsList();
			System.out.println("\nAdjacency matrix: \n");
			g.displayAsMatrix();

			System.out.println("Adding vertices: 8,9");
			g.addVertex("eight", 8);
			g.addVertex("nine", 9);

			System.out.println("Adding more edges between vertices \n");
			g.addEdge("eight", "nine");
			g.addEdge("eight", "four");
			
			spacer();
			System.out.println("\nAdjacency graph: \n");
			g.displayAsList();
			System.out.println("\nAdjacency matrix: \n");
			g.displayAsMatrix();
	}

	public static void spacer()
	{
		System.out.println("==========================================================");
	}
}