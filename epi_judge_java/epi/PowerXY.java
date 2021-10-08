package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class PowerXY {
    @EpiTest(testDataFile = "power_x_y.tsv")
    public static double power(double x, int y) {
        if (y == 0) return 1.0;
        if (y == 1) return x;

        double temp = 1.0;
        if(y < 0) return power( 1.0 / x, -y);

        if ((y & 1) == 1) {
            temp = x;
        }

        double result = power(x, y >>> 1);
        result = result * result;
        return temp * result;



    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "PowerXY.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
