import java.math.BigInteger;

public class Decrypt{
    public static final BigInteger INIT_NUMBER = new BigInteger("2");
    public static BigInteger pf;
    public static BigInteger qf;

    //function to bruteforce p and q found on stack overflow 
    public static void pq(BigInteger n){
        BigInteger p = INIT_NUMBER;

        //For each prime p
        while(p.compareTo(n.divide(INIT_NUMBER)) <= 0){

            //If we find p
            if(n.mod(p).equals(BigInteger.ZERO)){
                //Calculate q
                BigInteger q = n.divide(p);
                //Displays the result
                System.out.println("(" + p + ", " + q + ")");
                pf = p;
                qf = q;
                
                //The end of the algorithm
                return;
            }
            //p = the next prime number
            p = p.nextProbablePrime();
        }
        System.out.println("No solution exists");
    }

    public static BigInteger Decrypter(BigInteger n, BigInteger e, BigInteger cypher){
        //run algorithm to find p, q
        pq(n);

        //now calculate z
        BigInteger q2 = qf.subtract(new BigInteger("1"));
        BigInteger z = pf.subtract(new BigInteger("1"));
        z = z.multiply(q2);
        System.out.println(z);

        //now calculate rsa
        BigInteger d = RSA.modInv(e, z);
        System.out.println(d);

        //now decrypt the message
        return cypher.modPow(d, n);
    }



    public static void main(String[] args){
        BigInteger n = new BigInteger("91");
        BigInteger e = new BigInteger("59");
        BigInteger cypher = new BigInteger("878732");

        System.out.println("The message is: " + Decrypter(n, e, cypher));
    }
}