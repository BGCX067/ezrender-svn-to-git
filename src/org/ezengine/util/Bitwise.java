package org.ezengine.util;

public class Bitwise {
	
	public static final int getBit(int value, int position) {
		return (value >> position) & 1;
	}
	
	public static final boolean test(int val, int test) {
		return val == (test & val);
	}

	public static final boolean test(int val1, int val2, int test) {
		return val1 == (test & val2);
	}

}
