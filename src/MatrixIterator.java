import java.util.*;
/**
 * @author Misha Butt
 * @param <Edge> the type stored in the iterator
 */
public class MatrixIterator<Edge> implements Iterator<Edge> {
	
		//keeps track of current index
		private int currentIndex;
		
		//the collection the program will iterate over
		private ArrayList<Edge> collection;
		
		//the number of items in the collection
		private int size;
	
		
		/**
		 * @param list is what the program will iterate over
		 * @param count is the number of items in list
		 */
		public MatrixIterator(ArrayList<Edge> list, int count) {
			
			//current index is set as 0
			currentIndex = 0;
			
			collection = list;
			size = count;
	  }

		
	  /**
	   *returns true if there is another entry in the array after the current 
	   */
	   public boolean hasNext() {
		  if (currentIndex + 1 <= size)
			  return true;
		  else 
			  return false;
		}

	  
	  /**
	   * returns the next array entry 
	   */
		public Edge next() {
			
			//if there is no next item then null is returned
			if (!hasNext())
				return null;
			
			//the edge in the current index is returned and the index is updated
			Edge result = collection.get(currentIndex);
			currentIndex++;
			return result;
		}
		
		
		/**
		 * removes the current index from the array
		 */
		public void remove() {
			collection.remove(currentIndex);
		}
}