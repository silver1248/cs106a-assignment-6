package ProblemSetFive;

public class Histogram {

    public static void main(String[] args) {
        System.out.format("%16.2f\n",5.564654764);
        System.out.println(makeHistogram(new int[]{98, 47, 48, 10, 60, 20, 23}));
    }
    
    public static String makeHistogram(int[] scores) {
        StringBuilder histogram = new StringBuilder();
        int[] sorted = sortIntoRanges(scores);
        for (int i = 0; i < sorted.length; i++) {
            if (i != 9) {
                histogram.append(String.format("%d0-%d9: ", i, i));
            } else {
                histogram.append(String.format("%d0-100: ", i));
            }
            for (int j = 0; j < sorted[i]; j++) {
                histogram.append("*");
            }
            if (i != 9) {
                histogram.append("\n");                
            }
        }
        return histogram.toString();
    }
    
    public static int[] sortIntoRanges(int[] toBeSorted) {
        //for loop i times 10 and hecc
        int[] histogramData = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        for (int i = 0; i < toBeSorted.length; i++) {
            if (toBeSorted[i] >= 0 && toBeSorted[i] <= 9) {
                histogramData[0]++;
            }
            if (toBeSorted[i] >= 10 && toBeSorted[i] <= 19) {
                histogramData[1]++;
            }
            if (toBeSorted[i] >= 20 && toBeSorted[i] <= 29) {
                histogramData[2]++;
            }
            if (toBeSorted[i] >= 30 && toBeSorted[i] <= 39) {
                histogramData[3]++;
            }
            if (toBeSorted[i] >= 40 && toBeSorted[i] <= 49) {
                histogramData[4]++;
            }
            if (toBeSorted[i] >= 50 && toBeSorted[i] <= 59) {
                histogramData[5]++;
            }
            if (toBeSorted[i] >= 60 && toBeSorted[i] <= 69) {
                histogramData[6]++;
            }
            if (toBeSorted[i] >= 70 && toBeSorted[i] <= 79) {
                histogramData[7]++;
            }
            if (toBeSorted[i] >= 80 && toBeSorted[i] <= 89) {
                histogramData[8]++;
            }
            if (toBeSorted[i] >= 90 && toBeSorted[i] <= 100) {
                histogramData[9]++;
            }
        }
        return histogramData;
    }
}