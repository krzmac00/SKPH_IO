package com.example.skph;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(classes = {SkphApplication.class})
@ActiveProfiles("dev")
class SkphApplicationTest {

	@Test
	void contextLoads() {
		// Test to ensure the application context loads successfully
		assert true;
	}
}