package com.forrest.cinema.utils;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CinemaUtilitiesTest {

	@Test
	public void getLevenshteinDistanceTest() {
		final String str = "test";
		final String str2 = "Test";
		final String str3 = "T-est";
		final String str4 = "t.est";
		final String str5 = "t_est";
		final String str6 = "test6";
		final String str7 = "Autre";
		final String str8 = "te st";
		
		assertEquals(0, CinemaUtilities.getLevenshteinDistance(str, str));
		assertEquals(0, CinemaUtilities.getLevenshteinDistance(str, str2));
		assertEquals(0, CinemaUtilities.getLevenshteinDistance(str, str3));
		assertEquals(0, CinemaUtilities.getLevenshteinDistance(str, str4));
		assertEquals(0, CinemaUtilities.getLevenshteinDistance(str, str5));
		assertEquals(1, CinemaUtilities.getLevenshteinDistance(str, str6));
		assertEquals(5, CinemaUtilities.getLevenshteinDistance(str, str7));
		assertEquals(0, CinemaUtilities.getLevenshteinDistance(str, str8));
	}
	
	@Test
	public void removeExtensionTest() {
		String filePath = "test.avi";
		String filePath2 = "test.2-test.avi";
		
		String compare1 = "test";
		String compare2 = "test.2-test";
		
		assertEquals(compare1, CinemaUtilities.removeExtension(filePath));
		assertEquals(compare2, CinemaUtilities.removeExtension(filePath2));
	}
	
	@Test
	public void getStringLimitedTest() {
		int maxLength = 5;
		String str1 = "Hello";
		String str2 = "Hi!";
		String str3 = "Welcome";
		String str3Reduced = "Welco";
		
		assertEquals(str1, CinemaUtilities.getStringLimited(str1, maxLength));
		assertEquals(str2, CinemaUtilities.getStringLimited(str2, maxLength));
		assertEquals(str3Reduced, CinemaUtilities.getStringLimited(str3, maxLength));
	}

}
