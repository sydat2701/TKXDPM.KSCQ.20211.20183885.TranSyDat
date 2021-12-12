package controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ValidateAddressTest {

	private PlaceOrderController placeOrderController;
	@BeforeEach
	void setUp() throws Exception {
		placeOrderController = new PlaceOrderController();
	}

	@Test
	void test()
	{
		boolean verified= placeOrderController.validateAddress("ngo 815, duong Giai Phong");
		assertEquals(true, verified);

		boolean verified2= placeOrderController.validateAddress("@#$%^&*()");
		assertEquals(false, verified2);

		boolean verified3= placeOrderController.validateAddress("");
		assertEquals(false, verified3);

		boolean verified4= placeOrderController.validateAddress("5/6/4 Hai Ba Trung, HN.");
		assertEquals(true, verified4);

	}

}
