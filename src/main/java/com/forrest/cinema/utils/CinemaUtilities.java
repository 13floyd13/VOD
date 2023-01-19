/**
 * 
 */
package com.forrest.cinema.utils;

import java.io.File;
import java.util.Arrays;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

/**
 * @author martin
 *
 */
public final class CinemaUtilities {
	
	private static final Logger Logger = LoggerFactory.getLogger(CinemaUtilities.class);

	public static int getLevenshteinDistance(String str1, String str2) {
		String regexAlphaNum = "[^a-zA-Z0-9]";
		
		str1 = str1.replaceAll(regexAlphaNum, "");
		str1 = str1.toLowerCase();
		
		str2 = str2.replaceAll(regexAlphaNum, "");
		str2 = str2.toLowerCase();
		
		int[][] dp = new int[str1.length() + 1][str2.length() + 1];

		for (int i = 0; i <= str1.length(); i++)
		{
			for (int j = 0; j <= str2.length(); j++) {

				// If str1 is empty, all characters of
				// str2 are inserted into str1, which is of
				// the only possible method of conversion
				// with minimum operations.
				if (i == 0) {
					dp[i][j] = j;
				}

				// If str2 is empty, all characters of str1
				// are removed, which is the only possible
				// method of conversion with minimum
				// operations.
				else if (j == 0) {
					dp[i][j] = i;
				}

				else {
					// find the minimum among three
					// operations below

					
					dp[i][j] = minm_edits(dp[i - 1][j - 1]
						+ NumOfReplacement(str1.charAt(i - 1),str2.charAt(j - 1)), // replace
						dp[i - 1][j] + 1, // delete
						dp[i][j - 1] + 1); // insert
				}
			}
		}

		return dp[str1.length()][str2.length()];
	}

	// check for distinct characters
	// in str1 and str2

	static int NumOfReplacement(char c1, char c2)
	{
		return c1 == c2 ? 0 : 1;
	}

	// receives the count of different
	// operations performed and returns the
	// minimum value among them.

	static int minm_edits(int... nums)
	{

		return Arrays.stream(nums).min().orElse(
			Integer.MAX_VALUE);
	}

	public static double convertSizeFileToGigabytes(File file) {

		double value = file.length() / Math.pow(1024, 3);
		return (Math.round(value * 100.0) / 100.0);
	}
	
	public static String removeExtension(String str) {
		int lastPointPosition = str.lastIndexOf('.');
		
		if (lastPointPosition <= 0) {
			return str;
		} else {
			return str.substring(0, lastPointPosition);
		}
	}
	
	public static String getStringLimited(String str, int maxLength) {
		
		if (str.length() > maxLength) {
			Logger.warn("STRING_TOO_LONG : " + str + "MAX_LENGTH : " + maxLength);
			return str.substring(0, maxLength);
		}
		
		return str;
	}
}
