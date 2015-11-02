import java.util.Random;
import java.math.BigInteger;

import static java.lang.Math.pow;
   
public class Assignment {
   public static void main(String[] args) {
      Long p = 11l;
      Long q = 17l;
      Long N = p*q;
      Long phi_N = (p-1)*(q-1);
      Long e=-1l;
      
      for(Long i=6l; i<=phi_N; i+=6l) {

         System.out.println((i-1)+" gcd:"+gcd(phi_N, i-1));
         if(isPrime(i-1) && (gcd(phi_N, i-1)==1)) {
            e=i-1;
            break;
         }

         System.out.println((i+1)+" gcd:"+gcd(phi_N, i+1));
         if(isPrime(i+1) && (gcd(phi_N, i+1)==1)) {
            e=i+1;
            break;
         }
      }
      
      /*
         Don't quite get the formula:
            e^(-1) % phi(N)
         e^(-1) < 1, so not sure what modding it with phi(N) should produce
      */
      double d = (pow(e,-1l) % phi_N);
      System.out.println("p:"+p+" q:"+q+" phi_N:"+phi_N+" e:"+e+" d:"+d+" pow(e,-1l):"+pow(e,-1l));
   }
   
   static boolean isPrime(Long n) {
       for(Long i=2l;i<n;i++) {
           if(n%i==0)
               return false;
       }
       return true;
   }
   
   static Long gcd(Long a, Long b) {
      if (b==0) {
         return a;
      }
      return gcd(b,a%b);
   }
}
