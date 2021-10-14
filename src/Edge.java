/**
 * @author Misha Butt
 * represents an edge of the graph
 */
public class Edge {
	
	//first endpoint of the edge
	private Node firstEndpoint;
	
	//second endpoint of the edge
	private Node secondEndpoint;
	
	//type of edge
	private int Type;
	
	//label of edge
	private String Label;
	
	
	/** 
	 * @param u represents first endpoint
	 * @param v represents second endpoint
	 * @param type is the type of edge
	 * this is the first constructor
	 */
	public Edge(Node u, Node v, int type) {
		firstEndpoint = u;
		secondEndpoint = v;
		Type = type;
	}
	
	
	/**
	 * @param u represents the first endpoint
	 * @param v represents the second endpoint
	 * @param type is the type of edge
	 * @param label of the edge
	 * second constructor
	 */
	public Edge(Node u, Node v, int type, String label) {
		firstEndpoint = u;
		secondEndpoint = v;
		Type = type;
		Label = label;
	}
	
	
	/**
	 * @return the first endpoint
	 */
	public Node firstEndpoint() {
		return firstEndpoint;
	}
	
	
	/**
	 * @return the second endpoint
	 */
	public Node secondEndpoint() {
		return secondEndpoint;
	}
	
	
	/**
	 * @return the type
	 */
	public int getType() {
		return Type;
	}
	
	
	/** 
	 * @param newType is the new type to be set
	 */
	public void setType(int newType) {
		Type = newType;
	}
	
	
	/**
	 * @return label
	 */
	public String getLabel() {
		return Label;
	}
	
	
	/**
	 * @param newLabel is the new label to set
	 */
	public void setLabel(String newLabel) {
		Label = newLabel;
	}
	
}