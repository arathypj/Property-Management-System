package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TradeTest {

	private Trade trade;
	private Customer customer;
	private Property property;
	
	
	@Before
	public void setUp() throws Exception {
		property = new Property();
		trade = new Trade(property, customer);
	}

	@After
	public void tearDown() throws Exception {
		this.property = null;
		this.trade = null;
	}

	@Test
	public void testRentPayableAmount() {

		property.setPrice(1000);
		
		double price = trade.getRentPayableAmount();
		assertEquals(4300, price, 0);
	}
	
	
	@Test
	public void testSellPayableAmount() {
		
		property.setPrice(1000);
		
		double price = trade.getSellPayableAmount();
		assertEquals(1015, price, 0);
		
		
	}

}
