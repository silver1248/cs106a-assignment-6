package org.sweatshop.FunWithArrays;

public class ArrayReversal {

    public static int[] elementReversal(int[] in) {
        if (in == null || in.length == 0) {
            return new int[0];
        } else {
            int length = in.length;
            int[] reversedIn = new int[length];
            for (int i = 0; i < (length / 2)+1; i++) {
                int iComplement = length - (i + 1);
                reversedIn[i] = in[iComplement];
                reversedIn[iComplement] = in[i];
            }
            return reversedIn;
        }
    }
    
    public static int[][] arrayReversal(int[][] in) {
        if (in == null || in.length == 0) {
            return new int[0][0];
        } else {
            int length = in.length;
            int[][] reversedIn = new int[length][];
            for (int i = 0; i < (length / 2)+1; i++) {
                int iComplement = length - (i + 1);
                reversedIn[i] = in[iComplement];
                reversedIn[iComplement] = in[i];
            }
            return reversedIn;
        }
    }
}