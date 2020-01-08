import java.math.BigInteger;
import java.util.*;

public class Generate{
    public static boolean isPrime(int x) {
        if (x==1) {
            return true;
        } else {
            for(int i=2;i<=Math.sqrt(x);i++) {
                if (x%i==0) return false; 
            }         
        }
        return true;
    }

    public static void main(String[] args){
        //generate the 2 prime numbers
        BigInteger p = BigInteger.probablePrime(32, new Random());
        BigInteger q = BigInteger.probablePrime(32, new Random());
        System.out.println("p: " + p);
        System.out.println("q: " + q);

        //caclulate n
        BigInteger n = p.multiply(q);
        System.out.println("n: " + n);

        //calculate z
        BigInteger q2 = q.subtract(new BigInteger("1"));
		BigInteger z = p.subtract(new BigInteger("1"));
		z = z.multiply(q2);    
        System.out.println("z: " + z);

        //choose a value for D
        BigInteger d = new BigInteger("0");
        System.out.print("Possible d: ");
        BigInteger zero = new BigInteger("0");
        BigInteger i = new BigInteger("15000");
        for(int j = 15000; j<=16000; j++){
            if(z.mod(i).equals(zero) == false && Generate.isPrime(j) == true){                //if the number is a nonfactor, print it
                System.out.print(i + " ");
                d = i;
                break;
            }
            i = i.add(new BigInteger("1"));
        }
        System.out.println();
        System.out.println("d: "+ d);

        //caclulate the value for e
        BigInteger e = RSA.modInv(d, z);
        System.out.println("e: " + e);

        //encrypt my message
        BigInteger lost = new BigInteger("4815162342");
        System.out.println("Cypher: " + lost.modPow(e, n));

    }
}