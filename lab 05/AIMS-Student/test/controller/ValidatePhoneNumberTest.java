package controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import validation.PhoneValidation;

class ValidatePhoneNumberTest {
	private PhoneValidation phoneValidation;


	@BeforeEach
	void setUp() throws Exception {
		phoneValidation = new PhoneValidation();
	}

	@Test
	void test() {
		boolean verified= phoneValidation.validatePhoneNumber("0987105814");
		assertEquals(true, verified);

		boolean verified1= phoneValidation.validatePhoneNumber("0175823745554");
		assertEquals(false, verified1);

		boolean verified2= phoneValidation.validatePhoneNumber("1234567");
		assertEquals(false, verified2);


	}

}
