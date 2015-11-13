package com.codeforgeworks;

import org.apache.commons.lang3.*;
import java.math.BigInteger;

import org.apache.commons.math3.random.RandomDataGenerator;
import org.apache.commons.math3.analysis.function.Pow;

import static java.lang.Math.pow;

// Determines if a user provided value is prime
// Miller-Rabin Algorithm
public class PrimeFinder {

  private static RandomDataGenerator r = new RandomDataGenerator();
  private static Pow pow = new Pow();

  private static boolean isPrime(long n) {

    // Test if candidate is greater than 2 and an odd number
    if (n < 2 && n % 2 != 1) return false;

    // Determine p and q from n
    long k = 0, q = n - 1;
    while(true) {
      if (q % 2 != 0) {
        break;
      }
      q = q / 2;
      k++;
    }

    // Select a random integer 'a' where '1 < a < n - 1' 
    long a = r.nextLong(2, n - 2);
    
    //System.out.println("k:" + k + " q:" + q + " n:" + n + " a:" + a);

    // If 'a^q % n == 1' then this value is 'maybe prime'
    if ((long) pow.value(a, q) % n == 1L) return true;

    // if 'a^((2^j)*q) mod n' is equal to 'n - 1' then it is maybe prime
    for (int j = 0; j <= k - 1; j++) {
      BigInteger twoTOPowerOfjTimesq = 
        (new BigInteger("" + 2)).pow(j).multiply(new BigInteger("" + q));
      BigInteger aToPowerOfTwoTOPowerOfjTimesq =
        (new BigInteger("" + a)).modPow(twoTOPowerOfjTimesq, new BigInteger("" + n));

      if (aToPowerOfTwoTOPowerOfjTimesq.intValue() == n - 1)
        return true;
    }

    // Determined the value 'n' not to be prime
    return false;
  }

  public static void main(String[] args) {

    if (args.length != 1 && !StringUtils.isNumeric(args[0]))
      throw new IllegalArgumentException("Invalid argument: no argument supplied or not integer");

    long n = Long.parseLong(args[0]);

    // Find the next 2 primes after 'n'
    int primeCount = 0;
    long currentValue = n;
    while (primeCount < 2) {
      boolean isPrime = false;
      int runCount = 0;
      while (runCount < 10) {
        isPrime = isPrime(currentValue);
        if (isPrime) break;
        else runCount++;
      }
      if (isPrime) { 
        System.out.println("Prime found: " + currentValue);
        primeCount++;
      }
      currentValue++;
    }

    // Repeat isPrime test with different random values 
    // to ensure high probability of success
    boolean isPrime = false;
    int runCount = 0;
    while (runCount < 10) {
      isPrime = isPrime(n);
      if (isPrime) break;
      else runCount++;
    }
      
  }

}