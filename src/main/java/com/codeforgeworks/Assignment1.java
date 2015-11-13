package com.codeforgeworks;

import java.util.Random;
import java.math.BigInteger;

import org.apache.commons.lang3.*;

import static java.lang.Math.pow;

/**
 * Created by hegemon on 07.11.15.
 */
public class Assignment1 {

    private static boolean isPrime(long n) {
        if (n == 1) return true;
        long counter = 2;
        while(true) {
            if (counter == n) return true;
            else if (n % counter == 0) return false;
            else counter++;
        }
    }

    /* EXAMPLE
        
        a       b       d       remainder
        341     231     1       110
        231     110     2       11
        110     11      10      0

    */
    private static long gcd(long a, long b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }

    private static long findE(long phi_N) {
        Random r = new Random();
        while (true) {
            long testE = r.nextLong() * (phi_N - 1);
            if (Assignment1.gcd(testE, phi_N) == 1)
                return testE;
        }
    }

    public static void main(String[] args) {

        if (args.length != 3 && !StringUtils.isNumeric(args[0]) && !StringUtils.isNumeric(args[1]))
            System.out.println("Incorrect usage: please run app wiht two arguments for p and q");

        long p = Long.parseLong(args[0]);
        long q = Long.parseLong(args[1]);
        String c = args[2];

        if (p < 1 || q < 1)
            throw new IllegalArgumentException("Invalid argument(s): less than 1");
        if (!Assignment1.isPrime(p) || !Assignment1.isPrime(q))
            throw new IllegalArgumentException("Invalid argument(s): Not prime");

        long N = p * q;
        long phi_N = (p - 1) * (q - 1);
        long e = findE(phi_N);
        // TODO: Have to work out the correct formula for d
        double d = Math.pow(e, -1) % phi_N;

        System.out.println("phi_N:" + phi_N + ", e:" + e + ", d:" + d);

    }

}
