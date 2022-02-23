/**
 * 
 */
package ubu.gii.dass.test.c01;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ubu.gii.dass.c01.DuplicatedInstanceException;
import ubu.gii.dass.c01.NotFreeInstanceException;
import ubu.gii.dass.c01.Reusable;
import ubu.gii.dass.c01.ReusablePool;

import java.util.Vector;

/**
 * @author Daniel Meruelo Monzon
 * @author Yanela Lozano Perez
 *
 */
public class ReusablePoolTest {

	private ReusablePool pool;
	private Vector<Reusable> cont;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		pool = ReusablePool.getInstance();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		for (Reusable c : cont) {
			pool.releaseReusable(c);
		}
	}

	/**
	 * Test method for {@link ubu.gii.dass.c01.ReusablePool#getInstance()}.
	 */
	@Test
	public void testGetInstance() {
		ReusablePool instancia = ReusablePool.getInstance();
		assertTrue(instancia instanceof ReusablePool);
		assertTrue(pool instanceof ReusablePool);

		assertTrue(
				"No se cumple la condicion de unicidad que deberia proveer el patron Singleton, dado que hay mas de una instancia.",
				(pool == instancia) && (pool.equals(instancia)));
	}

	/**
	 * Test method for {@link ubu.gii.dass.c01.ReusablePool#acquireReusable()}.
	 */
	@Test
	public void testAcquireReusable() {
		try {
			for (int i = 0; i < 3; i++) {
				vector.add(pool.acquireReusable());
				assertTrue(vector.get(i) instanceof Reusable);
				assertNotNull(vector.get(i)); 
	
			}
		} catch (NotFreeInstanceException ex) {
			System.out.println("Ha sido imposible adquirir una instancia del objeto Reusable.");
		}
	}
	}

	/**
	 * Test method for
	 * {@link ubu.gii.dass.c01.ReusablePool#releaseReusable(ubu.gii.dass.c01.Reusable)}.
	 */
	@Test
	public void testReleaseReusable() {
		fail("Not yet implemented");
	}

}
