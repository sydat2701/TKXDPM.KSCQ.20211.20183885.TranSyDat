package controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ValidateRushAddress {

	private PlaceRushOrderController placeRushOrderController;
	@BeforeEach
	void setUp() throws Exception {
		placeRushOrderController = new PlaceRushOrderController();
	}

	@Test
	void test()
	{
		boolean verified= placeRushOrderController.validateRushAddress("ngo 815, duong Giai Phong, Ha Noi");
		assertEquals(true, verified);

		boolean verified2= placeRushOrderController.validateRushAddress("@#$%^&*()");
		assertEquals(false, verified2);

		boolean verified3= placeRushOrderController.validateRushAddress("  ");
		assertEquals(false, verified3);

		boolean verified4= placeRushOrderController.validateRushAddress("5/6/4 Hai Ba Trung, HN.");
		assertEquals(true, verified4);

	}
}
