package controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Date;

class ValidateDateTest {

	private PlaceRushOrderController placeRushOrderController;
	@BeforeEach
	void setUp() throws Exception {
		placeRushOrderController = new PlaceRushOrderController();
	}

	@ParameterizedTest
	@CsvSource({
			"08/07/2019, 05/07/2019, true",
			"04/06/2025, 01/06/2025, true",
			"13/6/2017, 16/8/2017, false",
			"28/1/2019, 12/3/1000, true",
			"2/10/1009, 20/1/2088, false"
	})
	void test(Date expectedDate, Date currDate , boolean expect) {
		boolean isValid = placeRushOrderController.validateDate(expectedDate, currDate);
		assertEquals(isValid, expect);
	}
}