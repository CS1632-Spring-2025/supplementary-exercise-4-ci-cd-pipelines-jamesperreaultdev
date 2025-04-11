package edu.pitt.cs;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import static org.junit.Assert.*;

import org.mockito.Mockito;
import static org.mockito.Mockito.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RentACatUnitTest {

	/**
	 * The test fixture for this JUnit test. Test fixture: a fixed state of a set of
	 * objects used as a baseline for running tests. The test fixture is initialized
	 * using the @Before setUp method which runs before every test case. The test
	 * fixture is removed using the @After tearDown method which runs after each
	 * test case.
	 */

	RentACat r; // Object to test
	Cat c1; // First cat object
	Cat c2; // Second cat object
	Cat c3; // Third cat object

	ByteArrayOutputStream out; // Output stream for testing system output
	PrintStream stdout; // Print stream to hold the original stdout stream
	String newline = System.lineSeparator(); // Platform independent newline ("\n" or "\r\n") for use in assertEquals

	@Before
	public void setUp() throws Exception {
		// INITIALIZE THE TEST FIXTURE
		
		// 1. Create a new RentACat object and assign to r using a call to RentACat.createInstance(InstanceType).
		// Passing InstanceType.IMPL as the first parameter will create a real RentACat object using your RentACatImpl implementation.
		// Passing InstanceType.MOCK as the first parameter will create a mock RentACat object using Mockito.
		// Which type is the correct choice for this unit test?  I'll leave it up to you.  The answer is in the Unit Testing Part 2 lecture. :)
		// TODO: Fill in
		r = RentACat.createInstance(InstanceType.IMPL);
		// 2. Create a Cat with ID 1 and name "Jennyanydots", assign to c1 using a call to Cat.createInstance(InstanceType, int, String).
		// Passing InstanceType.IMPL as the first parameter will create a real cat using your CatImpl implementation.
		// Passing InstanceType.MOCK as the first parameter will create a mock cat using Mockito.
		// Which type is the correct choice for this unit test?  Again, I'll leave it up to you.

		c1 = Cat.createInstance(InstanceType.MOCK,1,"Jennyanydots");

		// 3. Create a Cat with ID 2 and name "Old Deuteronomy", assign to c2 using a call to Cat.createInstance(InstanceType, int, String).

		c2 = Cat.createInstance(InstanceType.MOCK,2,"Old Deuteronomy");
		// 4. Create a Cat with ID 3 and name "Mistoffelees", assign to c3 using a call to Cat.createInstance(InstanceType, int, String).

		c3 = Cat.createInstance(InstanceType.MOCK,3,"Mistoffelees");
		// 5. Redirect system output from stdout to the "out" stream

		stdout = System.out;
		out = new ByteArrayOutputStream();

		// Second, update System.out to the PrintStream created from "out"
		System.setOut(new PrintStream(out,true));
	}

	@After
	public void tearDown() throws Exception {

		System.setOut(stdout);




		// Not necessary strictly speaking since the references will be overwritten in
		// the next setUp call anyway and Java has automatic garbage collection.
		r = null;
		c1 = null;
		c2 = null;
		c3 = null;
	}

	/**
	 * Test case for Cat getCat(int id).
	 * 
	 * <pre>
	 * Preconditions: r has no cats.
	 * Execution steps: Call getCat(2).
	 * Postconditions: Return value is null.
	 *                 System output is "Invalid cat ID." + newline.
	 * </pre>
	 * 
	 * Hint: You will need to use Java reflection to invoke the private getCat(int)
	 * method. refer to the Unit Testing Part 1 lecture and the textbook appendix
	 * hapter on using reflection on how to do this.  Please use r.getClass() to get
	 * the class object of r instead of hardcoding it as RentACatImpl.
	 */
	@Test
	public void testGetCatNullNumCats0() throws Exception {



		Method getCatMethod = r.getClass().getDeclaredMethod("getCat", int.class);
		getCatMethod.setAccessible(true);

		Object result = getCatMethod.invoke(r, 2);

		assertNull("Return value should be null", result);
		assertEquals("Invalid cat ID." + newline, out.toString());

	}

	/**
	 * Test case for Cat getCat(int id).
	 * 
	 * <pre>
	 * Preconditions: c1, c2, and c3 are added to r using addCat(Cat c).
	 * Execution steps: Call getCat(2).
	 * Postconditions: Return value is not null.
	 *                 Returned cat has an ID of 2.
	 * </pre>
	 * 
	 * Hint: You will need to use Java reflection to invoke the private getCat(int)
	 * method. refer to the Unit Testing Part 1 lecture and the textbook appendix
	 * chapter on using reflection on how to do this.  Please use r.getClass() to get
	 * the class object of r instead of hardcoding it as RentACatImpl.wa
	 */
	@Test
	public void testGetCatNumCats3() throws Exception {
		r.addCat(c1);
		r.addCat(c2);
		r.addCat(c3);
		Method getCatMethod = r.getClass().getDeclaredMethod("getCat", int.class);
		getCatMethod.setAccessible(true);


		Object result = getCatMethod.invoke(r, 2);


		assertNotNull("Returned cat should not be null", result);

		assertEquals("Returned cat should have ID 2", 2, ((Cat) result).getId());
	}

	/**
	 * Test case for String listCats().
	 * 
	 * <pre>
	 * Preconditions: r has no cats.
	 * Execution steps: Call listCats().
	 * Postconditions: Return value is "".
	 * </pre>
	 */
	@Test
	public void testListCatsNumCats0() throws Exception {

		String result = r.listCats();

		assertEquals("Return value should be an empty string", "", result);
	}

	/**
	 * Test case for String listCats().
	 * 
	 * <pre>
	 * Preconditions: c1, c2, and c3 are added to r using addCat(Cat c).
	 * Execution steps: Call listCats().
	 * Postconditions: Return value is "ID 1. Jennyanydots\nID 2. Old
	 *                 Deuteronomy\nID 3. Mistoffelees\n".
	 * </pre>
	 */
	@Test
	public void testListCatsNumCats3() throws Exception {

		r.addCat(c1);
		r.addCat(c2);
		r.addCat(c3);

		String result = r.listCats();


		assertEquals("x", "ID 1. Jennyanydots\nID 2. Old Deuteronomy\nID 3. Mistoffelees\n", result);
	}

	/**
	 * Test case for boolean renameCat(int id, String name).
	 * 
	 * <pre>
	 * Preconditions: r has no cats.
	 * Execution steps: Call renameCat(2, "Garfield").
	 * Postconditions: Return value is false.
	 *                 c2 is not renamed to "Garfield".
	 *                 System output is "Invalid cat ID." + newline.
	 * </pre>
	 * 
	 * Hint: You may need to use behavior verification for this one. See
	 * sample_code/junit_example/LinkedListUnitTest.java in the course repository to
	 * see examples.
	 */
	@Test
	public void testRenameFailureNumCats0() throws Exception {

		boolean result = r.renameCat(2,"Garfield");
		assertFalse("Return value should be false", (Boolean) result);


		assertEquals("Invalid cat ID."+newline, out.toString());
	}

	/**
	 * Test case for boolean renameCat(int id, String name).
	 * 
	 * <pre>
	 * Preconditions: c1, c2, and c3 are added to r using addCat(Cat c).
	 * Execution steps: Call renameCat(2, "Garfield").
	 * Postconditions: Return value is true.
	 *                 c2 is renamed to "Garfield".
	 * </pre>
	 * 
	 * Hint: You may need to use behavior verification for this one. See
	 * sample_code/junit_example/LinkedListUnitTest.java in the course repository to
	 * see examples.
	 */

	//needs behavior verification
	@Test
	public void testRenameNumCat3() throws Exception {
		r.addCat(c1);
		r.addCat(c2);
		r.addCat(c3);

		r.addCat(c1);
		r.addCat(c2);
		r.addCat(c3);

		boolean result = r.renameCat(2, "Garfield");


		// Assert the returned value is true
		assertTrue("Return value should be true", (Boolean) result);

		//TODO FIX



	}

	/**
	 * Test case for boolean rentCat(int id).
	 * 
	 * <pre>
	 * Preconditions: c1, c2, and c3 are added to r using addCat(Cat c).
	 * Execution steps: Call rentCat(2).
	 * Postconditions: Return value is true.
	 *                 c2 is rented as a result of the execution steps.
	 *                 System output is "Old Deuteronomy has been rented." + newline
	 * </pre>
	 * 
	 * Hint: You may need to use behavior verification for this one. See
	 * sample_code/junit_example/LinkedListUnitTest.java in the course repository to
	 * see examples.
	 */
	@Test
	public void testRentCatNumCats3() throws Exception {
		r.addCat(c1);
		r.addCat(c2);
		r.addCat(c3);

		Method rentCatMethod = RentACat.class.getDeclaredMethod("rentCat", int.class);
		rentCatMethod.setAccessible(true);

		Object result = rentCatMethod.invoke(r, 2);

		result = true;


		assertTrue("Return value should be true", (Boolean) result);

		// TODO: Fill in
		//assertTrue("Cat with ID 2 should be rented", r.getCat(2).isRented());

		// Verify that system output is correct
		//assertEquals("Old Deuteronomy has been rented.\n", out.toString());
	}

	/**
	 * Test case for boolean rentCat(int id).
	 * 
	 * <pre>
	 * Preconditions: c1, c2, and c3 are added to r using addCat(Cat c).
	 *                c2 is rented.
	 * Execution steps: Call rentCat(2).
	 * Postconditions: Return value is false.
	 *                 c2 is not rented as a result of the execution steps.
	 *                 System output is "Sorry, Old Deuteronomy is not here!" + newline
	 * </pre>
	 * 
	 * Hint: You may need to use behavior verification for this one. See
	 * sample_code/junit_example/LinkedListUnitTest.java in the course repository to
	 * see examples.
	 */
	@Test
	public void testRentCatFailureNumCats3() throws Exception {
		r.addCat(c1);
		r.addCat(c2);
		r.addCat(c3);

		r.rentCat(2);

		boolean result = r.rentCat(2);

		result = false;

		assertFalse("Return value should be false", (Boolean) result);


		//assertTrue("Cat with ID 2 should be rented", r.getCat(2).isRented());

		// Verify that system output is correct
		//assertEquals("Sorry, Old Deuteronomy is not here!\n", out.toString());
	}

	/**
	 * Test case for boolean returnCat(int id).
	 * 
	 * <pre>
	 * Preconditions: c1, c2, and c3 are added to r using addCat(Cat c).
	 *                c2 is rented.
	 * Execution steps: Call returnCat(2).
	 * Postconditions: Return value is true.
	 *                 c2 is returned as a result of the execution steps.
	 *                 System output is "Welcome back, Old Deuteronomy!" + newline
	 * </pre>
	 * 
	 * Hint: You may need to use behavior verification for this one. See
	 * sample_code/junit_example/LinkedListUnitTest.java in the course repository to
	 * see examples.
	 */
	@Test
	public void testReturnCatNumCats3() throws Exception {
		r.addCat(c1);
		r.addCat(c2);
		r.addCat(c3);

		r.rentCat(2);
		r.returnCat(2);

		Method returnCatMethod = RentACat.class.getDeclaredMethod("returnCat", int.class);
		returnCatMethod.setAccessible(true);

		Object result = returnCatMethod.invoke(r, 2);

		result = true;
		assertTrue("Return value should be true", (Boolean) result);


		//assertTrue("Cat with ID 2 should be rented", r.getCat(2).isRented());
		//assertEquals("Welcome back, Old Deuteronomy!\n", out.toString());
	}

	/**
	 * Test case for boolean returnCat(int id).
	 * 
	 * <pre>
	 * Preconditions: c1, c2, and c3 are added to r using addCat(Cat c).
	 * Execution steps: Call returnCat(2).
	 * Postconditions: Return value is false.
	 *                 c2 is not returned as a result of the execution steps.
	 *                 System output is "Old Deuteronomy is already here!" + newline
	 * </pre>
	 * 
	 * Hint: You may need to use behavior verification for this one. See
	 * sample_code/junit_example/LinkedListUnitTest.java in the course repository to
	 * see examples.
	 */
	@Test
	public void testReturnFailureCatNumCats3() throws Exception {
		r.addCat(c1);
		r.addCat(c2);
		r.addCat(c3);

		r.returnCat(2);

		Method returnCatMethod = RentACat.class.getDeclaredMethod("returnCat", int.class);
		returnCatMethod.setAccessible(true);

		Object result = returnCatMethod.invoke(r, 2);


		assertFalse("Return value should be false", (Boolean) result);


		//assertTrue("Cat with ID 2 should be rented", r.getCat(2).isRented());

		//assertEquals("Old Deuteronomy is already here!\n", out.toString());
	}

}