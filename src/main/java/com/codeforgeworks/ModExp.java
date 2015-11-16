package com.codeforgeworks;

import org.apache.commons.lang3.*;
import java.math.BigInteger;

import org.apache.commons.math3.random.RandomDataGenerator;
import org.apache.commons.math3.analysis.function.Pow;

// Modular Exponentiation
public class ModExp {

  // determine if a^b mod n = 1
  private static int modExp(int a, int b, int n) {

    // get a char array of the bits in b
    char[] k = (Integer.toBinaryString(b)).toCharArray();

    // initialize variables
    int c = 0, f = 1;
    // loop over every bit in k
    for (int i = 0; i < k.length; i ++) {

      // for every bit, i.e. if 0 or 1, do this
      c *= 2;
      f = (f * f) % n;

      // If current bit is equal to 1, then do this
      if (k[i] == '1') {
        c += 1;
        f = (f * a) % n;
      }
    }

    // returns the remainder
    return f;
  }


  public static void main(String[] args) {

    int a = 7, b = 560;
    int n = 561;

    int f = modExp(7, 560, 561);

    System.out.println("f: " + f);


  }

}