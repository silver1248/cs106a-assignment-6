package org.sweatshop.FunWithArrays;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

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
    
    public static <T> List<T> listReversal(List<T> in) {
        if (in == null || in.size() == 0) {
            return new LinkedList<T>();
        } else {
            int length = in.size();
            List<T> reversedIn = new ArrayList<T>(length);
            for (int i = 0; i < (length / 2)+1; i++) {
                int iComplement = length - (i + 1);
                reversedIn.set(i, in.get(iComplement));
                reversedIn.set(iComplement, in.get(i));
            }
            return reversedIn;
        }
    }
}