package com.codeforgeworks;
import java.math.BigInteger;
import java.util.Random;

class ModularExponentiation {

    public static void main(String[] args)
    {
	int c = 0;
	int f = 1;
	int n = 561;
	int a = 7;
	long b = 560;
	String k = Long.toBinaryString(b);
	char[] charArray = k.toCharArray();
	
	for (int i = 0; i < k.length(); i++) {
	    c = 2 * c;
	    f = (f * f) % n;
	    if (charArray[i] == 1) {
		c++;
		f = (f * a) % n;
	    }
	    System.out.println(f);
	}
    }
}
