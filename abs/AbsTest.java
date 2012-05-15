package example;

import junit.framework.TestCase;

public class AbsTest extends TestCase {
	private Abs abs1;
	protected void setUp() throws Exception {
		
		super.setUp();
		abs1=new Abs();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	/*
	 * Test method for 'example.Abs.abs()'
	 */
	public void testAbs() {
		assertEquals(abs1.abs(14),14);
	}
}
