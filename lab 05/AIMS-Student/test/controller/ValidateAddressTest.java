package controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import validation.AddressValidation;

class ValidateAddressTest {

	private AddressValidation addressValidation;
	@BeforeEach
	void setUp() throws Exception {
		addressValidation = new AddressValidation();
	}

	@Test
	void test()
	{
		boolean verified= addressValidation.validateAddress("ngo 815, duong Giai Phong");
		assertEquals(true, verified);

		boolean verified2= addressValidation.validateAddress("@#$%^&*()");
		assertEquals(false, verified2);

		boolean verified3= addressValidation.validateAddress("");
		assertEquals(false, verified3);

		boolean verified4= addressValidation.validateAddress("5/6/4 Hai Ba Trung, HN.");
		assertEquals(true, verified4);

	}

}
