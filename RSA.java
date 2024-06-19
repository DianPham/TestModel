import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Random;

public class RSA
{

    int primeSize ;
    BigInteger p, q ;
    BigInteger N ;
    BigInteger r ;
    BigInteger E, D ;
    public RSA(){
        this.primeSize = 8 ;
        // Generate two distinct large prime numbers p and q.
        generatePrimeNumbers() ;
        // Generate Public and Private Keys.
        generatePublicPrivateKeys() ;
    }

    public RSA( int primeSize )
    {
        this.primeSize = primeSize ;
        // Generate two distinct large prime numbers p and q.
        generatePrimeNumbers() ;
        // Generate Public and Private Keys.
        generatePublicPrivateKeys() ;
    }


    private void generatePrimeNumbers()
    {
        Random random = new Random();
        p = BigInteger.probablePrime(primeSize /2, random);
        do
        {
            q = BigInteger.probablePrime(primeSize /2, random);
        }
        while( q.compareTo( p ) == 0 ) ;
    }

    private void generatePublicPrivateKeys()
    {
        // N = p * q
        N = p.multiply( q ) ;
        // r = ( p - 1 ) * ( q - 1 )
        r = p.subtract( BigInteger.valueOf( 1 ) ) ;
        r = r.multiply( q.subtract( BigInteger.valueOf( 1 ) ) ) ;
        // Choose E, coprime to and less than r
        do
        {
            E = new BigInteger( 2 * primeSize, new Random() ) ;
        }
        while( ( E.compareTo( r ) != -1 ) ||
                ( E.gcd( r ).compareTo( BigInteger.valueOf( 1 ) ) != 0 ) ) ;
        // Compute D, the inverse of E mod r
        D = E.modInverse( r ) ;
    }

    public String performEncryption(String plaintext)
    {
        return convertBigIntArrayToString(encrypt(plaintext));
    }

    public String performDecryption(String cipher)
    {
        return decrypt(convertStringToBigIntArray(cipher), getD(), getN());
    }

    private String convertBigIntArrayToString(BigInteger[] bigIntArray) {
        // Use StringBuilder for efficient concatenation
        StringBuilder sb = new StringBuilder();

        // Iterate over the array and append each BigInteger's string representation
        for (BigInteger bigInt : bigIntArray) {
            sb.append(bigInt.toString());
            // Optionally, add a delimiter between numbers (e.g., a comma)
            sb.append(",");
        }

        // Remove the last delimiter if needed
        if (sb.length() > 0) {
            sb.setLength(sb.length() - 1);
        }

        return sb.toString();
    }
    private BigInteger[] convertStringToBigIntArray(String bigIntString) {
        // Split the string by the delimiter (comma in this case)
        String[] stringParts = bigIntString.split(",");

        // Create a BigInteger array of the same length
        BigInteger[] bigIntArray = new BigInteger[stringParts.length];

        // Convert each substring to a BigInteger and store it in the array
        for (int i = 0; i < stringParts.length; i++) {
            bigIntArray[i] = new BigInteger(stringParts[i]);
        }

        return bigIntArray;
    }
    private BigInteger[] encrypt( String message )
    {
        int i ;
        byte[] temp = new byte[1] ;
        byte[] digits = message.getBytes() ;
        BigInteger[] bigdigits = new BigInteger[digits.length] ;
        for( i = 0 ; i < bigdigits.length ; i++ )
        {
            temp[0] = digits[i] ;
            bigdigits[i] = new BigInteger( temp ) ;
        }
        BigInteger[] encrypted = new BigInteger[bigdigits.length] ;
        for( i = 0 ; i < bigdigits.length ; i++ )
            encrypted[i] = bigdigits[i].modPow( E, N ) ;
        return( encrypted ) ;
    }
    private BigInteger[] encrypt( String message,BigInteger userD,BigInteger userN)
    {
        int i ;
        byte[] temp = new byte[1] ;
        byte[] digits = message.getBytes() ;
        BigInteger[] bigdigits = new BigInteger[digits.length] ;
        for( i = 0 ; i < bigdigits.length ; i++ )
        {
            temp[0] = digits[i] ;
            bigdigits[i] = new BigInteger( temp ) ;
        }
        BigInteger[] encrypted = new BigInteger[bigdigits.length] ;
        for( i = 0 ; i < bigdigits.length ; i++ )
            encrypted[i] = bigdigits[i].modPow( userD, userN ) ;
        return( encrypted ) ;
    }

    private String decrypt( BigInteger[] encrypted,BigInteger D,BigInteger N )
    {
        int i ;
        BigInteger[] decrypted = new BigInteger[encrypted.length] ;
        for( i = 0 ; i < decrypted.length ; i++ )
            decrypted[i] = encrypted[i].modPow( D, N ) ;
        char[] charArray = new char[decrypted.length] ;
        for( i = 0 ; i < charArray.length ; i++ )
            charArray[i] = (char) ( decrypted[i].intValue() ) ;
        return( new String( charArray ) ) ;
    }

    private BigInteger getp()
    {
        return( p ) ;
    }

    private BigInteger getq()
    {
        return( q ) ;
    }

    private BigInteger getr()
    {
        return( r ) ;
    }

    private BigInteger getN()
    {
        return( N ) ;
    }

    private BigInteger getE()
    {
        return( E ) ;
    }

    private BigInteger getD()
    {
        return( D ) ;
    }

    public static void main( String[] args ) throws IOException
    {
        RSA rsa = new RSA(8);
        String cipher = rsa.performEncryption("mon thuc hanh bao mat thong tin");
        String plain = rsa.performDecryption(cipher);
        System.out.println(cipher);
        System.out.println(plain);
    }
}