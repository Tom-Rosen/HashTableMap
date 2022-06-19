// Name: Tom Rosen
// Email: trrosen @wisc.edu 

import java.util.LinkedList;
import java.util.NoSuchElementException;

public class Hashtable<KeyType, ValueType> implements MapADT {
	
	private LinkedList<DoubleHash>[] hashArr;
	//CREATE AN ARRAY OF LINKED LISTS TO AVOID ISSUES.

	public Hashtable(int capacity) {
		hashArr = new LinkedList[capacity];
	}

	public Hashtable() {
		hashArr = new LinkedList[20];
	}

	public int size() {
		int count = 0;
		for (int i = 0; i < hashArr.length; i++) {
			if (hashArr[i] == null) {
				continue;
			}
			else if (hashArr[i].size() > 1)
			{
				int linkSize = hashArr[i].size();
				count += linkSize;
			} else {
				count += 1;
			}
		}
		return count;
	}

	/*
	 * 
	 */
	@Override
	public void clear() {
		for (int i = 0; i < hashArr.length; i++) {
			hashArr[i] = null;
		}
	}
	
	//ERROR Fix This Code
	private void growArr(KeyType key) {
		int loadFactor = size() / hashArr.length;
		if (loadFactor >= 80) {
			LinkedList<DoubleHash>[] tempArr = new LinkedList[hashArr.length * 2];
			LinkedList<DoubleHash>[] savedVals = new LinkedList[hashArr.length];
			for(int j = 0; j < hashArr.length; j++)
			{
				savedVals[j] = hashArr[j];
			}
			
			hashArr = tempArr;
			for(int i = 0; i < savedVals.length; i++)
			{
				
			}
		}
	}

	@Override
	public boolean put(Object key, Object value) {
		DoubleHash pair = new DoubleHash(key, value);
		if (key == null) {
			return false;
		}
		int local = (Math.abs(key.hashCode())) % hashArr.length;
		if (containsKey(key)) { //Check or us containsKey
			return false;
		} else if (hashArr[local] != null && !hashArr[local].get(0).equals(pair)) {
			hashArr[local].add(pair);
			return true;
		} else {
			hashArr[local].addFirst(pair);
			return true;
		}
	}

	@Override
	public Object get(Object key) throws NoSuchElementException {
		int local = (Math.abs(key.hashCode())) % hashArr.length;
		if (hashArr[local] == null) {
			throw new NoSuchElementException("ERROR: No value at key loction.");
		}
		else if(hashArr[local].get(0).getKey() != key)
		{
			int listSize = hashArr[local].size();
			DoubleHash current = hashArr[local].get(1);
			for(int i = 1; i < listSize; i+=0)
			{
				if(current.getKey() == key)
				{
					return (ValueType) current.getValue();
				}
				else
				{
					i++;
					current = hashArr[local].get(i);
				}
			}
		} else {
			return (ValueType) hashArr[local].get(0).getValue();
		}
		return null;
	}

	@Override
	public boolean containsKey(Object key) {
		int local = Math.abs(key.hashCode()) % hashArr.length;
		if (hashArr[local] != null) {
			if(hashArr[local].size() > 1)
			{
				int sizeList = hashArr[local].size();
				DoubleHash curr = hashArr[local].get(0);
				for(int i = 1; i < sizeList; i++)
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

	@Override
	public ValueType remove(Object key) {
		int local = (Math.abs(key.hashCode())) % hashArr.length;
		if (hashArr[local] == null) {
			return null;
		} else if(containsKey(key) == false) {
			return null;
		} else {	
			if(hashArr[local].size() > 1)
			{
				int sizeList = hashArr[local].size();
				for(int i = 1; i < sizeList; i++)
				{
					if(hashArr[local].get(i).getKey().equals(key))
					{
						DoubleHash temp = hashArr[local].get(0);
						hashArr[local] = null;
						return (ValueType) temp.getValue();
					}
				}
			}
			else
			{
				DoubleHash temp = hashArr[local].get(0);
				hashArr[local] = null;
				return (ValueType) temp.getValue();
			}
		}
		return null;
	}
}

