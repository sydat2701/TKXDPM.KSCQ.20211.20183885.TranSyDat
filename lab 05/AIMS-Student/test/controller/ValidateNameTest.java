package controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import validation.NameValidation;

class ValidateNameTest {
	private NameValidation nameValidation;
	@BeforeEach
	void setUp() throws Exception {
		nameValidation = new NameValidation();
	}

	@Test
	void test()
	{
		boolean verified= nameValidation.validateName("Hoang Thi A");
		assertEquals(true, verified);

		boolean verified1= nameValidation.validateName("324 Hoang B");
		assertEquals(false, verified1);

		boolean verified2= nameValidation.validateName("@#$%^&*()");
		assertEquals(false, verified2);

		boolean verified3= nameValidation.validateName("");
		assertEquals(false, verified3);

		boolean verified4= nameValidation.validateName("T");
		assertEquals(true, verified4);
	}

}
