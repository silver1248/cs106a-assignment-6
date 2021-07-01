package ProblemSetNine;


public class ExpandableArray {
    
    public ExpandableArray() {
        
    }

    String[] array = new String[0];

    /*
     * Sets the element at the given index position to the specified.
     * value. If the internal array is not large enough to contain that
     * element, the implementation expands the array to make room.
     */
    public ExpandableArray set(int index, String value) {
        if (index+1 > array.length) {
            String[] copy = new String[index+1];
            for (int i = 0; i < array.length; i++) {
                copy[i] = array[i];
            }
            copy[index] = value;
            array = copy;
        } else {
            array[index] = value;
        }
        return this;
    }

    /*
     * Returns the element at the specified index position, or null if
     * no such element exists.
     */
    public String get(int index) throws IndexOutOfBoundsException {
        return array[index];
    }

    public String[] getArray() {
        return array.clone();
    }
}
