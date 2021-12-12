package controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ValidatePhoneNumberTest {
	private PlaceOrderController placeOrderController;

	@BeforeEach
	void setUp() throws Exception {
		placeOrderController = new PlaceOrderController();
	}

	@Test
	void test() {
		boolean verified= placeOrderController.validatePhoneNumber("0987105814");
		assertEquals(true, verified);

		boolean verified1= placeOrderController.validatePhoneNumber("0175823745554");
		assertEquals(false, verified1);

		boolean verified2= placeOrderController.validatePhoneNumber("1234567");
		assertEquals(false, verified2);


	}

}
