package com.example;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class HelloTest {

	@Test
	void test() {
		 get("http://localhost:8080/api/auth/signin");
	}

	private void get(String string) {
		// TODO Auto-generated method stub
		
	}

}
