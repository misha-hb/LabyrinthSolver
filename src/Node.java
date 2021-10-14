/**
 * @author Misha Butt
 * this class represents a node of the graph
 */
public class Node {
	
	//name of node
	private int Name;
	
	//node can be marked true or false
	private boolean Mark;
	
	
	/**
	 * @param name is a number that the node will be referred with
	 */
	public Node (int name) {
		Name = name;
	}
	
	
	/**
	 * @param mark that sets the node as either true or false
	 */
	public void setMark(boolean mark) {
		Mark = mark;
	}
	
	
	/**
	 * @return Mark that is either true or false
	 */
	public boolean getMark() {
		return Mark;
	}
	
	
	/**
	 * @return Name of the node
	 */
	public int getName() {
		return Name;
	}
	
}
