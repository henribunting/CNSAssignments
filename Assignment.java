import java.util.Random;
import java.math.BigInteger;

import static java.lang.Math.pow;
   
public class Assignment {
   public static void main(String[] args) {
      Long p = 11l;
      Long q = 17l;
      Long N = p*q;
      Long phi_N = (p-1)*(q-1);
      Random random = new Random();
      Long e=-1l;
      while(true) {
         Long temp = random.nextLong(phi_N);
         
         if(isPrime(temp) && temp>0) {
            Long mygcd = gcd(phi_N, temp);
            if(mygcd==1) {
               e=temp;
            }
            break;
         }
      }
      double d = (pow(e,-1) % phi_N);
      System.out.println("p:"+p+" q:"+q+" e:"+e+" d:"+d);
   }
   
   static boolean isPrime(int n) {
       for(Long i=2;i<n;i++) {
           if(n%i==0)
               return false;
       }
       return true;
   }

   private static Long gcd(Long a, Long b) {
      BigInteger b1 = BigInteger.valueOf(a);
      BigInteger b2 = BigInteger.valueOf(b);
      BigInteger gcd = b1.gcd(b2);
      return gcd.intValue();
   }
}
