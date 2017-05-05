/**
 * 
 */
package com.org.utils;

import java.util.Random;

/**
 * @author M1030876
 *
 */
public class UtilityFunctions {

	public static String randomString() {
		char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < 2; i++) {
			char c = chars[random.nextInt(chars.length)];
			sb.append(c);
		}
		return sb.toString();
	}
}
