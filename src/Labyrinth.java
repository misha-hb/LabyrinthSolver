import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * This class builds the labyrinth and finds the solution for the game
 * @author Misha Butt
 */
public class Labyrinth {
	
	
	//graph representing the edges and vertices of the labyrinth
	private Graph graph;
	
	//the node the entrance is in
	private int entrance;
	
	//the node or room the exit is in
	private int exit;
	
	//holds an array of all the keys available
	private int [] keys;

	
	
	/**
	 * 
	 * @param inputFile the file the program will read from and build the graph based on
	 * @throws IOException if the file can not be read
	 * @throws LabyrinthException if input file does not exist or the format of the file is wrong
	 * @throws GraphException by node class
	 */
	public Labyrinth(String inputFile) throws IOException, LabyrinthException, GraphException {
		
		//reader for reading the input file
	    BufferedReader reader = new BufferedReader(new FileReader(inputFile));
		
	    //executed under a try block for exception handling
		try {
			
	    reader = new BufferedReader(new FileReader(inputFile));
	    String readLine;
	    
	    int linecount = 0;
	    
	    int scale, width = 0, length = 0;
	    
	    //reads the first 3 lines
	    while (linecount < 3) {
	    	readLine = reader.readLine();
	    	linecount++;
	    	
	    	//first line is the scale
	    	if (linecount == 1) {
	    		scale = Integer.parseInt(readLine);
	    	}
	    	
	    	//second line is the width of the rooms
	    	if (linecount == 2) {
	    		width = Integer.parseInt(readLine);
	    	}
	    	
	    	//third line is the length
	    	if (linecount == 3) {
	    		length = Integer.parseInt(readLine);
	    	}
	    }
	    
	    //the number of rooms would be the length x the width
	    int numRooms = length * width;
	    
	    //creates a new graph with the specified number of rooms
	    graph = new Graph(numRooms);
	    
	    //reads the fourth line which will always have the keys
	    readLine = reader.readLine();
	    linecount++;
	    
	    //splits the line to store each of the keys in a string array
	    String [] key = readLine.split(" ");
	    
	    //array of integers
	    keys = new int[key.length];
	    
	    //each of the keys in the string array are converted to integer and stored in the integer array
	    for (int i = 0; i < key.length; i++) {
	    	keys[i] = Integer.parseInt(key[i]);
	    }
	    
	    //reads the next line to begin the loop
	    readLine = reader.readLine();

	    //the type of door
	    int typenum;
	    
	    //counts the number of rooms built in the graph
	    int roomcount = -1;

	    
	    //executed until end of file is reached
	    while (readLine != null) {

	    	linecount++;
		    
	    	//if the line count is odd then the line is of the form RHRHRH..
		    if (linecount%2 != 0) {
		    	
		    	//reads each character in the line
		    	for (int i = 0; i < readLine.length(); i++) {
		    		
		    		//if the character is in an even position then it will either be s, x or i
		    		if (i%2 == 0) {
		    			
		    			//since this character represents a room, roomcount is incremented
		    			roomcount++;
		    			
		    			//if the character indicates an entrance room number is stored
		    			if (readLine.charAt(i) == 's') {
		    				entrance = roomcount; 
		    			}
		    			
		    			//if the character indicates an exit, room number is stored
		    			else if (readLine.charAt(i) == 'x') {
		    				exit = roomcount; 
		    			}
		    		}
		    		
		    		//executed if the character is in an odd position
		    		//character will either be a corridor, wall or digit
		    		else {
		    			
		    			//if the character indicates a corridor then this is set as an edge with type -1
		    			if (readLine.charAt(i) == 'c') {
		    				Node nodeu = graph.getNode(roomcount);
		    				nodeu.setMark(false);
		    				Node nodev = graph.getNode(roomcount + 1);
		    				nodev.setMark(false);
		    				graph.insertEdge(nodeu, nodev, -1, "corridor");
		    			}
		    			
		    			//the character indicates a number if it is not w and so it will be set as an edge
		    			else if (readLine.charAt(i) != 'w') {
		    				
		    				//character is stored in num which is then converted to string then integer
		    				char num = readLine.charAt(i);
		    				String number = Character.toString(num);
		    				typenum = Integer.parseInt(number);
		    				
		    				//stores the door with the type as an edge connecting the room next to it
		    				Node nodeu = graph.getNode(roomcount);
		    				nodeu.setMark(false);
		    				Node nodev = graph.getNode(roomcount + 1);
		    				nodev.setMark(false);
		    				graph.insertEdge(nodeu, nodev, typenum, "door");
		    			}
		    		}
		    	}
		    }
	    
		    
		    //if the line count is odd then it is of the form VWVWVWVWV...
		    //this deals not with rooms but with edges connecting rooms in columns
		    else {
	    		
		    	//to count each row, count is set as the room count as calculated above
		    	int count = roomcount;
		    	
		    	//reads each character of the line
	    		for (int i = 0; i < readLine.length(); i++) {
		    		
		    		//if the character position is even 
	    			//character can either be c, w or a digit
		    		if (i%2 == 0) {
		    			count++;
		    			
		    			//if the character indicates a corridor it is set as an edge
		    			if (readLine.charAt(i) == 'c') {
		    				Node nodeu = graph.getNode(count);
		    				nodeu.setMark(false);
		    				
		    				//count - with because it is connecting two rooms in a column
		    				Node nodev = graph.getNode(count - width);
		    				nodev.setMark(false);
		    				graph.insertEdge(nodeu, nodev, -1, "corridor");
		    			}

		    			//otherwise if the character is not w then it is a digit
		    			else if (readLine.charAt(i) != 'w') {
		    				
		    				//the number is converted from a char to string to int
		    				char num = readLine.charAt(i);
		    				String number = Character.toString(num);
		    				typenum = Integer.parseInt(number);
		    				
		    				//stored as an edge with type door connecting 2 rooms in a column in the graph
		    				Node nodeu = graph.getNode(count);
		    				nodeu.setMark(false);
		    				Node nodev = graph.getNode(count - width);
		    				nodev.setMark(false);
		    				graph.insertEdge(nodeu, nodev, typenum, "door");
		    			}	
		    		}
		    	}
		    }
		    
		    //reads the next line
		    readLine = reader.readLine();
			}
		}
		
		//if the file is not read properly
		catch (IOException e) {
		    System.out.println(e.getMessage());
		}
		
		//file is closed
		finally {
			if (reader != null)
				reader.close();
		}
	}
	
	
	
	/**
	 * @return graph representing the labyrinth
	 * @throws LabyrinthException if graph is null
	 */
	public Graph getGraph() throws LabyrinthException {		
		if (graph == null)
			throw new LabyrinthException("The graph is null.");
		else
			return graph;
	}
	
	
	
	/**
	 * @return an iterator storing nodes from entrance to the exit
	 * @throws GraphException
	 */
	public Iterator solve() throws GraphException {
		
		//arrayList is created to hold the nodes in the path
		ArrayList<Node> path = new ArrayList<Node>();
		
		//entrance node is used to call DFS for the graph traversal
		Node entranceNode = graph.getNode(entrance);
		path = DFS(entranceNode, path);
		
		//if there are no items in the iterator returned by DFS then message indicating no solution is printed
		if (!path.iterator().hasNext())
			System.out.println("No solution was found");
		
		//the iterator containing the path to the exit is returned
		return path.iterator();
	}
	
	
	
	/**
	 * @param u the current node from which the next node in the path is to be found
	 * @param path the ArrayList storing the path from entrance to exit
	 * @return the iterator path
	 * @throws GraphException 
	 */
	private ArrayList<Node> DFS (Node u, ArrayList<Node> path) throws GraphException {
		
		//the current node is marked true indicating it was visited and it is added to the path
		u.setMark(true);
		path.add(u);
		
		//keeps track of whether exit is found or not
		boolean exitfound = false;
		
		//gets all edges incident on current node u
		Iterator incidentIterator = graph.incidentEdges(u);
		
		
		//keeps going until all edges have been searched
		while (incidentIterator.hasNext()) {
			
			//indicates whether a key was used or not
			boolean keyfound = false;
			
			//if the key was used, keyindex keeps track of which one
			int keyindex = 0;
			
			//next holds the next edge in the iterator
			Edge next = (Edge)incidentIterator.next();
			
			//if the next endpoint is not marked and the edge is a door
			if (!next.secondEndpoint().getMark() && next.getLabel().contentEquals("door")) {
				
				//each key in the keyarray is searched until a key is found that the type is less than or equal to
				for (int i = 0; i < keys.length; i++) {
					if (!keyfound && keys[i] > 0 && next.getType() <= i) {
						
						//the number of keys of the key used is decremented in array
						keys[i]--;
						
						//key used is stored
						keyindex = i;
						keyfound = true;
					}
				}
			}
			
			//executed if the second endpoint is unmarked and the edge is a key and door is found or the edge is a corridor
			if (!next.secondEndpoint().getMark() && keyfound && next.getLabel().contentEquals("door") || !next.secondEndpoint().getMark() && next.getLabel().contentEquals("corridor")) {
				
				//if the next endpoint is the exit then the path is complete and it is returned
				if (next.secondEndpoint().getName() == exit) {
					path.add(next.secondEndpoint());
					exitfound = true;
					return path;
				}
				
				if (!exitfound) {
					
					//recursive call as the exit has not been found yet
					path = DFS(next.secondEndpoint(), path);
					
					//if the path returned has the exit in it then the path is returned
					if (path.get(path.size()-1).getName() == exit)
						return path;
					
					//executed if after returning from the recursive call, there are no new edges to visit
					if (!incidentIterator.hasNext()) {
						
						//the room is unmarked
						next.firstEndpoint().setMark(false);
						
						//updating the key used back in array if the edge was of type door
						if (path.get(path.size()-1).equals(next.firstEndpoint()) && next.getLabel().contentEquals("door") && keyfound) 
							keys[keyindex]++;
						
						//removes most recent element added into path
						if (path.get(path.size()-1).equals(next.firstEndpoint())) 
						path.remove(path.size()-1);
						
						return path;	
					}
				}
			}
			
			//executed if there are no more edges
			if (!incidentIterator.hasNext()) {
				
				//node is unmarked
				next.firstEndpoint().setMark(false);
				
				//updating the key used back in array
				if (path.get(path.size()-1).equals(next.firstEndpoint()) && next.getLabel().contentEquals("door") && keyfound) {
					keys[keyindex]++;
				}
				
				//removes most recent element added into path
				if (path.get(path.size()-1).equals(next.firstEndpoint())) 
				path.remove(path.size()-1);
				
			}
			
			//if a key was used key is updated back into array
			if (path.get(path.size()-1).equals(next.firstEndpoint()) && next.getLabel().contentEquals("door") && keyfound) 
				keys[keyindex]++;
				
		}
		
		return path;
		
	}
	
}