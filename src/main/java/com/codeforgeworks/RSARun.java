package com.codeforgeworks;

import java.math.BigInteger;
import java.util.Random;

import scala.Tuple2;

public class RSARun {

  // Method to find phi of N, i.e. phi_N = (p - 1)(q - 1)
  private static BigInteger findPhiOfN(BigInteger p, BigInteger q) {
    return (p.subtract(new BigInteger("1"))).multiply((q.subtract(new BigInteger("1"))));
  }

  // Method to find greatest common denominator between two values
  private static BigInteger gcd(BigInteger a, BigInteger b) {
    if (b.compareTo(BigInteger.ZERO) == 0) 
      return a; 
    return gcd(b, a.mod(b));
  }

  // Method to find random encryption key e
  private static BigInteger findE(BigInteger phi_N) {
    while (true) {
      // aka. long testE = r.nextLong() * (phi_N - 1)
      BigInteger testE = (new BigInteger(8, new Random())).multiply(phi_N.subtract(new BigInteger("1")));
      if (gcd(testE, phi_N).compareTo(new BigInteger("1")) == 0)
        return testE;
    }
  }

  // TODO: Method to calculate co-primes up to N, totient function
  private static BigInteger totient(BigInteger n, BigInteger current, BigInteger coPrimeCount) {
    if ((gcd(n, current)).compareTo(new BigInteger("1")) == 0)
      coPrimeCount = coPrimeCount.add(new BigInteger("1"));

    // test if current equals n, if true increment by one to include n as a co-prime and return
    if (current.compareTo(n) == 0)
      return coPrimeCount.add(new BigInteger("1"));

    return totient(n, current.add(new BigInteger("1")), coPrimeCount);
  }

  // Square and Multiply method to determine if a^b mod n = 1
  private static BigInteger modExp(BigInteger a, BigInteger b, BigInteger n) {

    // get a char array of the bits in b
    char[] k = (b.toString(2)).toCharArray();

    // initialize variables
    int c = 0;
    BigInteger f = new BigInteger("1");
    // loop over every bit in k
    for (int i = 0; i < k.length; i ++) {

      // for every bit, i.e. if 0 or 1, do this
      c *= 2;
      f = (f.multiply(f)).mod(n);

      // If current bit is equal to 1, then do this
      if (k[i] == '1') {
        c += 1;
        f = (f.multiply(a)).mod(n);
      }
    }

    // returns the remainder
    return f;
  }

  public static void main(String[] args) {

    // Select 2 large primes at random -> p, q
    BigInteger p = new BigInteger("5");
    BigInteger q = new BigInteger("11");

    // The message to encrypt
    BigInteger message = new BigInteger("9");

    // Compute N
    BigInteger N = p.multiply(q);

    // Compute phi of N
    BigInteger phiN = findPhiOfN(p, q);

    // Select random encryption key -> e
    //BigInteger e = new BigInteger("3");
    BigInteger e = findE(phiN);

    BigInteger phiPhiN = totient(phiN, new BigInteger("2"), BigInteger.ZERO);

    // Solve for encryption key d
    BigInteger d = modExp(e, phiPhiN.subtract(new BigInteger("1")), phiN); 

    System.out.println("N: " + N + " phi_N: " + phiN + " e: " + e + " phiPhiN: " + phiPhiN + " d: " + d);

    // create the public plus private key
    Tuple2<BigInteger, BigInteger> publicKey = new Tuple2<>(e, N);
    Tuple2<BigInteger, BigInteger> privateKey = new Tuple2<>(d, N);

    // result of encrypting message with public key
    BigInteger cipherText = modExp(message, publicKey._1, publicKey._2);
    System.out.println("message: " + message + " cipherText: " + cipherText);

    // result of decrypting message with private key
    BigInteger decryptedMessage = modExp(cipherText, privateKey._1, privateKey._2);
    System.out.println("cipherText: " + cipherText + " dectyptedMessage: " + decryptedMessage);

  }

}