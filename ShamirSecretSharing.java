import java.math.BigInteger;
import java.util.*;

public class ShamirSecretSharing {
    static BigInteger convertToDecimal(String value, int base) {
        return new BigInteger(value, base);
    }
    static BigInteger lagrangeInterpolationAtZero(int[] x, BigInteger[] y, int k) {
        BigInteger secret = BigInteger.ZERO;

        for (int i = 0; i < k; i++) {
            BigInteger term = y[i];

            for (int j = 0; j < k; j++) {
                if (i != j) {
                    BigInteger numerator = BigInteger.ZERO.subtract(BigInteger.valueOf(x[j]));
                    BigInteger denominator = BigInteger.valueOf(x[i] - x[j]);
                    term = term.multiply(numerator).divide(denominator);
                }
            }
            secret = secret.add(term);
        }

        return secret;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[] x = new int[n];
        for(int i=0;i<n;i++)
        {
            x[i] = sc.nextInt();
        }

        String[] bases = new String[n];
        for(int i=0;i<n;i++)
        {
            bases[i] = sc.next();
        }

        String[] values = new String[n];
        for(int i=0;i<n;i++)
        {
            values[i] = sc.next();
        }

        BigInteger[] y = new BigInteger[n];
        for (int i = 0; i < n; i++) {
            y[i] = convertToDecimal(values[i], Integer.parseInt(bases[i]));
        }

        int[] xk = Arrays.copyOfRange(x, 0, k);
        BigInteger[] yk = Arrays.copyOfRange(y, 0, k);
        BigInteger secret = lagrangeInterpolationAtZero(xk, yk, k);
        System.out.println("Secret (c) = " + secret);
    }
}
