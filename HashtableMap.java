// Name: Tom Rosen
// Email: trrosen @wisc.edu 

import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * Class creates the basic implementation for a Hashtable that implements the MapADT.
 * Hashtable uses chaining during collisions and doubles array capacity after load factor 80%.
 * 
 * @author Tom Rosen
 *
 * @param <KeyType> Type for all keys input into table.
 * @param <ValueType> Type for all values stored in table.
 */
public class HashtableMap<KeyType, ValueType> implements MapADT<KeyType, ValueType> {
	
	private LinkedList<DoubleHash>[] hashArr; //Array of LinkedLists using type DoubleHash.

	/**
	 * Basic constructor for hashtable given the capacity.
	 * 
	 * @param capacity The initial capacity for the hashtable.
	 */
	public HashtableMap(int capacity) {
		hashArr = new LinkedList[capacity];
	}

	/**
	 * Default constructor for a hashtable; initial capacity 20.
	 */
	public HashtableMap() {
		hashArr = new LinkedList[20];
	}

	/**
	 * Finds the size of the hashtable counting all elements including chains.
	 * 
	 * @return the size of the array.
	 */
	public int size() {
		int count = 0;
		for (int i = 0; i < hashArr.length; i++) 
			{
			if (hashArr[i] == null) {
				continue;
			}
			else if (hashArr[i].size() >= 1)
			{
				int linkSize = hashArr[i].size();
				count += linkSize;
			}
		}
		return count;
	}

	/**
	 * Method that clears all the items inside of the hashtable without changing the size.
	 */
	@Override
	public void clear() {
		for (int i = 0; i < hashArr.length; i++) {
			hashArr[i] = null;
		}
	}
	
	/**
	 * Helper method in class that grows the hashtable in capacity in event that the load factor
	 * reaches above 80%.  Doubles the capacity and moves elements into doubled array.
	 */
	private void growArr() {
		double loadFactor = ((double) size() / hashArr.length) * 100;
		if(loadFactor >= 80.0) 
		{ 
			LinkedList<DoubleHash>[] tempArr = new LinkedList[hashArr.length * 2];
			LinkedList<DoubleHash>[] savedVals = new LinkedList[hashArr.length];
			
			for(int j = 0; j < hashArr.length; j++)
			{
				savedVals[j] = hashArr[j];
			}
			
			hashArr = tempArr;
			clear();
		
			for(int i = 0; i < savedVals.length; i++)
			{
				if(savedVals[i] != null)
				{
					for(int z = 0; z < savedVals[i].size(); z++)
					{
						if(savedVals[i].get(z) != null)
						{
							put((KeyType) savedVals[i].get(z).getKey(), 
									(ValueType) savedVals[i].get(z).getValue());
						}
					}
				}
			}
			
		}
	}

	/**
	 * Method enables users to fill in the hashtable.  Does this by caluclating hash code and than
	 * and function and that stores the key-value pair uses the DoubleHash class.  Checks to see if
	 * the aray needs to be doubled in size after each addition.
	 * 
	 * @param key The key value to put into hash function.
	 * @param value The value to be stored in the hashtable.
	 * 
	 * @return boolean true or false if element is added or not.
	 */
	@Override
	public boolean put(KeyType key, ValueType value) {	
		DoubleHash pair = new DoubleHash(key, value);
		
		if (key == null || value == null) {
			return false;
		}
		int local = (Math.abs(key.hashCode())) % hashArr.length;
		if (containsKey(key)) 
		{ 
			return false;
		} 
		else if (hashArr[local] != null && !hashArr[local].get(0).equals(pair))
		{
			hashArr[local].add(pair);
			growArr();
			return true;
		} 
		else 
		{
			hashArr[local] = new LinkedList();
			hashArr[local].add(pair);
			growArr();
			return true;
		}
		
	}

	/**
	 * The get method finds a specific key value and return it's stored value.
	 * 
	 * @param key The key value to find and return in the hashtable.
	 * 
	 * @return the stored ValueType at the specific location.
	 */
	@Override
	public ValueType get(KeyType key) throws NoSuchElementException {
		int local = Math.abs(key.hashCode()) % hashArr.length;
		
		if (hashArr[local] == null) 
		{
			throw new NoSuchElementException("ERROR: No value at key loction.");
		}
		else if(hashArr[local].get(0).getKey() != key)
		{
			int listSize = hashArr[local].size();
			DoubleHash current = hashArr[local].get(1);
			
			for(int i = 1; i < listSize; i++)
			{
				if(current.getKey() == key)
				{
					return (ValueType) current.getValue();
				}
				else
				{
					current = hashArr[local].get(i);
				}
			}
		} 
		else 
		{
			if(hashArr[local].get(0).getKey() == key)
			{
				return (ValueType) hashArr[local].get(0).getValue();
			}
		}
		throw new NoSuchElementException("ERROR: Key not in the table.");
	}

	/**
	 * Checks to see if a key exists inside the hashtable.
	 * 
	 * @param key The key value to see if contained.
	 * 
	 * @return true if it contains the key value, flase otherwise.
	 */
	@Override
	public boolean containsKey(KeyType key) {
		int local = Math.abs(key.hashCode()) % hashArr.length;
		
		if (hashArr[local] != null) 
		{
			if(hashArr[local].size() >= 1)
			{
				int sizeList = hashArr[local].size();
				DoubleHash curr = hashArr[local].get(0);
				
				for(int i = 0; i < sizeList; i++)
				{
					if(curr.getKey() == key)
					{
						return true;
					}
					curr = hashArr[local].get(i);
				}
			}
			else
			{
				if(hashArr[local].get(0).getKey() == key)
				{
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Removes a value from the table based on a key given.
	 * 
	 * @param key The key value to be found and removed
	 * 
	 * @return The value that has been removed from the hashtable.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public ValueType remove(KeyType key) {
		int local = (Math.abs(key.hashCode())) % hashArr.length;
		
		if (hashArr[local] == null) 
		{
			return null;
		} 
		else if(containsKey(key) == false) 
		{
			System.out.println(containsKey(key));
			return null;
		} 
		else 
		{	
			int sizeList = hashArr[local].size();
				
			for(int i = 0; i < sizeList; i++)
			{
				if(hashArr[local].get(i).getKey().equals(key))
				{
					DoubleHash temp = hashArr[local].get(i);
					hashArr[local].remove(i);
					if(hashArr[local].size() == 0)
					{
						hashArr[local] = null;
					}
					return (ValueType) temp.getValue();
				}
			}

		}
		return null;
	}
	
}
