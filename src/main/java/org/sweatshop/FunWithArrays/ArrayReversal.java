package org.sweatshop.FunWithArrays;

public class ArrayReversal {

	public static int[] reversal(int[] in) {
		int length = in.length;
		for(int i = 0; i < length / 2; i++) {
			int holder = in[length - i];
			in[length - i] = in[i];
			in[i] = holder;
		}
		return in;
	}
}
