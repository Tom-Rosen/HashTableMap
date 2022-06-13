import java.util.NoSuchElementException;

// --== CS400 Project One File Header ==--
// Name: Tom Rosen
// Email: trrosen@wisc.edu 
// Team: blue
// Group: CI
// TA: C
// Lecturer: Florian Heimerl
// Notes to Grader: <optional extra notes>

/**
 * Creates 5 testers for the HashtableMap class.
 * 
 * @author Tom Rosen
 *
 */
public class HashtableMapTests {

	/**
	 * Tests the constructors created in the HashtableMap class.
	 * 
	 * @return true if code worked, false if bugs exist
	 */
	public static boolean test1() 
	{ 
		HashtableMap[] hashArr = new HashtableMap[15];
		if(hashArr.length != 15)
		{
			System.out.println("ERROR: constructor instantiated with wrong capacity");
			return false;
		}
		
		HashtableMap hashArr2 = new HashtableMap();
		for(int i = 0; i < 20; i++)
		{
			hashArr2.put(i, "Copy");
		}
		if(hashArr2.size() != 20)
		{
			System.out.println("ERROR: default constructor instantiated with wrong capacity");
			return false;
		}
		
		return true;
		
	}
	
	/**
	 * Tests to make sure the size() method works correctly without bugs.
	 * 
	 * @return true if code worked, false if bugs exist
	 */
	public static boolean test2() 
	{ 
		HashtableMap hashTab = new HashtableMap(20);
		if(hashTab.size() != 0)
		{
			System.out.println("ERROR: size considers array filled without any values in it.");
			return false;
		}
		
		hashTab.put(5, "Burger");
		hashTab.put(7, "Soda Pop");
		if(hashTab.size() != 2)
		{
			System.out.println("ERROR: size fails to count after items have been to the hashtable.");
			return false;
		}
		
		return true;
	}
	
	/**
	 * Tests to make sure the clear() method functions properly without bugs.
	 * 
	 * @return true if code worked, false if bugs exist
	 */
	public static boolean test3() 
	{ 
		HashtableMap hashTab = new HashtableMap(20);
		hashTab.put(5, "Burger");
		hashTab.put(7, "Soda Pop");
		
		if(hashTab.size() != 2)
		{
			System.out.println("ERROR: Early check for clear, ensure items are in first.");
			return false;
		}
		hashTab.clear();
		if(hashTab.size() != 0)
		{
			System.out.println("ERROR: hashtable not properly cleared.");
			return false;
		}
		
		return true;
	}
	
	/**
	 * Tests to ensure the put() method works correctly without bugs.
	 * 
	 * @return true if code worked, false if bugs exist
	 */
	public static boolean test4() 
	{  
		boolean testStore= true;
		HashtableMap hashTab = new HashtableMap(5);
		hashTab.put(5, "Burger");
		
		if(hashTab.size() != 1)
		{
			System.out.println("ERROR: item not put into hashtable.");
			return false;
		}
		
		if(!hashTab.containsKey(5))
		{
			System.out.println("ERROR: item not put into proper spot; key not found.");
			return false;
		}
		
		testStore = hashTab.put(5, "Bonk");
		if(hashTab.size() != 1 || testStore != false)
		{
			System.out.println("ERROR: added item to hashtable when shouldn't have.");
			return false;
		}
		
		testStore = hashTab.put(null, null);
		if(testStore != false || hashTab.size() != 1)
		{
			System.out.println("ERROR: add null items and keys to hashtable.");
			return false;
		}
		
		return true;
	}
	
	/**
	 * Tests to make sure the get() method functions correctly.
	 * 
	 * @return true if code worked, false if bugs exist
	 */
	public static boolean test5() 
	{ 
		HashtableMap hashTab = new HashtableMap(20);
		try
		{
			hashTab.get(5);
		}
		catch(NoSuchElementException noElem)
		{
			System.out.println("Exception thrown correctly");
		}
		
		hashTab.put(5, "Cool Pretzel");
		String retVal = (String) hashTab.get(5);
		if(!retVal.equals("Cool Pretzel"))
		{
			System.out.println("ERROR: get method doesn't return proper value.");
			return false;
		}
		
		hashTab.put(8, "Peanuts");
		retVal = (String) hashTab.get(8);
		if(!retVal.equals("Peanuts"))
		{
			System.out.println("ERROR: get fails post adding an element to buckets.");
			return false;
		}
		
		return true;
	}
	
	public static boolean fuckYou()
	{
		boolean testStore= true;
		HashtableMap hashTab = new HashtableMap(5);
		hashTab.put(5, "Burger");
		
		return true;
	}
	
	
	/**
	 * Main method to run the tests created.
	 * 
	 * @param args Unused.
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(test1());
		System.out.println(test2());
		System.out.println(test3());
		System.out.println(test4());
		System.out.println(test5());
	}

}
