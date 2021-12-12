package controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ValidateNameTest {
	private PlaceOrderController placeOrderController;
	@BeforeEach
	void setUp() throws Exception {
		placeOrderController = new PlaceOrderController();
	}

	@Test
	void test()
	{
		boolean verified= placeOrderController.validateName("Hoang Thi A");
		assertEquals(true, verified);

		boolean verified1= placeOrderController.validateName("324 Hoang B");
		assertEquals(false, verified1);

		boolean verified2= placeOrderController.validateName("@#$%^&*()");
		assertEquals(false, verified2);

		boolean verified3= placeOrderController.validateName("");
		assertEquals(false, verified3);

		boolean verified4= placeOrderController.validateName("T");
		assertEquals(true, verified4);
	}

}
