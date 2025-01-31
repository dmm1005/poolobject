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
		cont = new Vector<Reusable>();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		if (cont.size() != 0) {
			for (Reusable c : cont) {
				pool.releaseReusable(c);
			}
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
				cont.add(pool.acquireReusable());
				assertTrue(cont.get(i) instanceof Reusable);
				assertNotNull(cont.get(i));

			}
		} catch (NotFreeInstanceException ex) {
			System.out.println("Ha sido imposible adquirir una instancia del objeto Reusable.");
		}
	}

	/**
	 * Test method for
	 * {@link ubu.gii.dass.c01.ReusablePool#releaseReusable(ubu.gii.dass.c01.Reusable)}.
	 */
	@Test
	public void testReleaseReusable() throws NotFreeInstanceException, DuplicatedInstanceException {
		Reusable r1 = pool.acquireReusable();
		Reusable r2 = pool.acquireReusable();

		try {
			pool.releaseReusable(r1);
			pool.releaseReusable(r2);
		} catch (DuplicatedInstanceException ex) {
			System.out.println("Error, instancia duplicada.\n");
		}
	}

}
