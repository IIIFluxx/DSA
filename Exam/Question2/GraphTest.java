/**
 * DSA Final Assessment Question 2 - GraphTest.java
 *
 * Name : Bharath Sukesh
 * ID   : 19982634
 *
 **/
public class GraphTest
{
	public static void main(String args[])
	{
			// put your code here
			DSAGraph g = new DSAGraph();
			spacer();
			
			System.out.println("Adding vertices: 1,2,3,4");
			g.addVertex("1", 1);
			g.addVertex("2", 2);
			g.addVertex("3", 3);
			g.addVertex("4", 4);
			
			System.out.println("Adding edges between vertices \n");
			g.addEdge("1", "2");
			g.addEdge("1", "3");
			g.addEdge("1", "4");
			g.addEdge("4", "2");
			g.addEdge("4", "3");

			spacer();
			System.out.println("\nAdjacency graph: \n");
			g.displayAsList();
			System.out.println("\nAdjacency matrix: \n");
			g.displayAsMatrix();

			System.out.println("Adding vertices: 8,9");
			g.addVertex("8", 8);
			g.addVertex("9", 9);

			System.out.println("Adding more edges between vertices \n");
			g.addEdge("8", "9");
			g.addEdge("8", "4");
			
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