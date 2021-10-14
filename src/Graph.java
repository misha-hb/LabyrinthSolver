import java.util.Iterator;
import java.util.ArrayList;
/**
 * @author Misha Butt
 * this class implements the methods for implementing a graph using an adjacency matrix
 */
public class Graph implements GraphADT {

	
	//array holding entries of type Node for the vertices
	private Node [] vertices;
	
	//2d array (matrix) holding entries of type Edge for the edges
	private Edge [][] edges;
	
	//the length of the vertices array
	private int length;
	
	
	/**
	 * @param n is the number of vertices the graph will have
	 */
	public Graph(int n) {
		
		length = n;
		vertices = new Node[length];
		
		//each entry in vertices will hold nodes from 0 to n-1
		for (int i = 0; i < length; i++) {
			Node node = new Node(i);
			vertices[i] = node;
		}
		
		//2d matrix will be n by n
		edges = new Edge[length][length];
	}
	
	
	/**
	 * @nodeu one of the two nodes connected by the edge
	 * @nodev second node connected by the edge
	 * @type type of edge
	 * @label is the label of the edge 
	 * this method inserts an edge into the edges matrix
	 */
	public void insertEdge(Node nodeu, Node nodev, int type, String label) throws GraphException {
		
		//if nodeu or nodev are out of range then an exception is thrown
		if (nodeu.getName() >= length || nodev.getName() >= length)
			throw new GraphException("The node does not exist.");
		
		//if an edge already connects the 2 nodes, an appropriate exception is thrown
		if (edges[nodeu.getName()][nodev.getName()] != null)
			throw new GraphException("Edge connecting the nodes already exists.");
		
		//first edge is inserted into matrix
		Edge firstEdge = new Edge(nodeu, nodev, type, label);
		edges[nodeu.getName()][nodev.getName()] = firstEdge;
		
		//second edge is inserted into matrix
		Edge secondEdge = new Edge(nodev, nodeu, type, label);
		edges[nodev.getName()][nodeu.getName()] = secondEdge;
	}
	
	
	/**
	 * @nodeu one of the two nodes connected by the edge
	 * @nodev second node connected by the edge
	 * @type type of edge
	 * second method for inserting an edge into the edges matrix
	 */
	public void insertEdge(Node nodeu, Node nodev, int type) throws GraphException {
				
		//if nodeu or nodev are out of range then an exception is thrown
		if (nodev.getName() < 0 || nodeu.getName() < 0 || nodeu.getName() >= length || nodev.getName() >= length)
			throw new GraphException("The node does not exist.");
		
		//if an edge already connects the 2 nodes, an appropriate exception is thrown
		if (edges[nodeu.getName()][nodev.getName()] != null)
			throw new GraphException("Edge connecting the nodes already exists.");
		
		//first edge is inserted into matrix
		Edge firstEdge = new Edge(nodeu, nodev, type);
		edges[nodeu.getName()][nodev.getName()] = firstEdge;
		
		//second edge is inserted into matrix
		Edge secondEdge = new Edge(nodev, nodeu, type);
		edges[nodev.getName()][nodeu.getName()] = secondEdge;
	}
	
	
	/**
	 * returns node indicated by u
	 */
	public Node getNode(int u) throws GraphException {
		
		//if u is out of range then an exception is thrown
		if (u < 0 || u >= length)
			throw new GraphException("The node does not exist.");
		
		//returns the node wanted
		return vertices[u];
	}	
	

	/**
	 * returns iterator containing all edges incident on u
	 */
	public Iterator incidentEdges(Node u) throws GraphException {
		
		//exception is thrown if node is out of range
		if (u.getName() < 0 || u.getName() >= length)
			throw new GraphException("This node does not exist in the graph.");
		
		//new arrayList is created to store all incident edges
		ArrayList<Edge> list = new ArrayList<Edge>();
		
		//loops through matrix to find all incident edges in entry u
		for (int i = 0; i < length; i++) {
			if (edges[u.getName()][i] != null)
				list.add(edges[u.getName()][i]);
		}

		//uses MatrixIterator to return an iterator of ArrayList
		return new MatrixIterator(list, list.size());			
	}
	
	
	/**
	 * returns edge indicated by u (row) and v (col) of the matrix
	 */
	public Edge getEdge(Node u, Node v) throws GraphException {
		
		//if u and v are out of range then an exception is thrown
		if (u.getName() >= length || v.getName() >= length || u.getName() < 0 || v.getName() < 0)
			throw new GraphException("Nodes do not exist in the graph.");
		
		return edges[u.getName()][v.getName()];
	}
	
	
	
	/**
	 * returns true if u and v are adjacent
	 */
	public boolean areAdjacent(Node u, Node v) throws GraphException {
		
		//if u and v are out of range then an exception is thrown
		if (u.getName() >= length || v.getName() >= length || u.getName() < 0 || v.getName() < 0)
			throw new GraphException("Nodes do not exist in the graph.");
					
		//if u and v are connected true is returned
		if (u.getName() == vertices[u.getName()].getName() && v.getName() == edges[u.getName()][v.getName()].secondEndpoint().getName())
			return true;
		
		else
			return false;
		
	}
}