package com.codeforgeworks;

import org.apache.commons.lang3.*;
import java.math.BigInteger;

import org.apache.commons.math3.random.RandomDataGenerator;
import org.apache.commons.math3.analysis.function.Pow;

import static java.lang.Math.pow;

public class ModularExponentiation {

  // determine if a^b mod n = 1
  private static int modExp(int a, int b, int n) {

    char[] k = (Integer.toBinaryString(b)).toCharArray();

    int c = 0, f = 1;
    for (int i = 0; i < k.length; i ++) {

      c *= 2;
      f = (f * f) % n;

      if (k[i] == '1') {
        c += 1;
        f = (f * a) % n;
      }
    }

    return f;
  }


  public static void main(String[] args) {

    int a = 7, b = 560;
    int n = 561;

    int f = modExp(7, 560, 561);

    System.out.println("f: " + f);


  }

}