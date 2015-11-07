package com.codeforgeworks;

import java.util.Random;
import java.math.BigInteger;

import org.apache.commons.lang3.*;

import static java.lang.Math.pow;

/**
 * Created by hegemon on 07.11.15.
 */
public class Assignment1 {

    private static boolean isPrime(int n) {
        if (n == 1) return true;
        int counter = 2;
        while(true) {
            if (counter == n) return true;
            else if (n % counter == 0) return false;
            else counter++;
        }
    }

    private static int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }

    private static int findE(int phi_N) {
        Random r = new Random();
        while (true) {
            int testE = r.nextInt(phi_N - 1) + 1;
            if (Assignment1.gcd(testE, phi_N) == 1)
                return testE;
        }
    }

    public static void main(String[] args) {

        if (args.length != 3 && !StringUtils.isNumeric(args[0]) && !StringUtils.isNumeric(args[1]))
            System.out.println("Incorrect usage: please run app wiht two arguments for p and q");

        int p = Integer.parseInt(args[0]);
        int q = Integer.parseInt(args[1]);
        String c = args[2];

        if (p < 1 || q < 1)
            throw new IllegalArgumentException("Invalid argument(s): less than 1");
        if (!Assignment1.isPrime(p) || !Assignment1.isPrime(q))
            throw new IllegalArgumentException("Invalid argument(s): Not prime");

        int N = p * q;
        int phi_N = (p - 1) * (q - 1);
        int e = findE(phi_N);
        // TODO: Have to work out the correct formula for d
        double d = Math.pow(e, -1) % phi_N;

        System.out.println("phi_N:" + phi_N + ", e:" + e + ", d:" + d);

    }

}
