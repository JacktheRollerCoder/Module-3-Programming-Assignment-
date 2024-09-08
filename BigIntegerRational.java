
/** *13.15 (USE BIGINTEGER FOR THE RATIONAL CLASS) 
 * Redesign and implement the Rational class in Listing 13.13
 * using BigInteger for the numerator and denominator. 
 * Write a test program that prompts the user to
 * enter two rational ­numbers and ­display the results as shown in 
 * the following sample run: */
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.InputMismatchException;
import java.util.Scanner;

public class BigIntegerRational extends Number implements Comparable<BigIntegerRational> {
    //Data fields for numerator and denominator
    private BigInteger numerator;
    private BigInteger denominator;


    public BigIntegerRational() {
        this(BigInteger.ZERO, BigInteger.ONE);
    }

    
    /** Construct a rational with specified numerator and denominator */
    public BigIntegerRational (BigInteger numerator, BigInteger denominator) {
        if (denominator.equals(BigInteger.ZERO)){
            throw new ArithmeticException("Denominator cannot be zero.");
        }
        this.numerator = numerator;
        this.denominator = denominator;
        normalize();
    }

    //Normalize the Rational number
    private void normalize() {
        if (denominator.compareTo(BigInteger.ZERO) < 0) {
            numerator = numerator.negate();
            denominator = denominator.negate();
        }

        //Reduce to two lowest terms.
        BigInteger gcd = numerator.gcd(denominator);
        numerator = numerator.divide(gcd);
        denominator = denominator.divide(gcd);
    }

    /** Add a rational number to this rational */
    public BigIntegerRational add(BigIntegerRational other) {
        BigInteger newNumerator = this.numerator.multiply(other.denominator)
        .add(other.numerator.multiply(this.denominator));
        BigInteger newDenominator = this.denominator.multiply(other.denominator);
        return new BigIntegerRational(newNumerator, newDenominator);            
    }

    /** Subtract a rational number from this rational */
    public BigIntegerRational subtract(BigIntegerRational other) {
        BigInteger newNumerator = this.numerator.multiply(other.denominator)
        .subtract(other.numerator.multiply(this.denominator));
        BigInteger newDenominator = this.denominator.multiply(other.denominator);
        return new BigIntegerRational(newNumerator, newDenominator);            
    }

    /** Multiply a rational number by this rational */
    public BigIntegerRational multiply(BigIntegerRational other) {
        BigInteger newNumerator = this.numerator.multiply(other.numerator);
        BigInteger newDenominator = this.denominator.multiply(other.denominator);
        return new BigIntegerRational(newNumerator, newDenominator);
    }
    
    /** Divide a rational number by this rational */
    public BigIntegerRational divide(BigIntegerRational other) {
        BigInteger newNumerator = this.numerator.multiply(other.denominator);
        BigInteger newDenominator = this.denominator.multiply(other.numerator);
        return new BigIntegerRational (newNumerator, newDenominator);
    }
    
    /**Convert this rational number to a BigDecimal */
    public BigDecimal toBigDecimal () {
        return new BigDecimal(numerator).divide(new BigDecimal(denominator), 20, RoundingMode.HALF_UP);
    }
    
    @Override
    public String toString() {
        if (denominator.equals(BigInteger.ONE)) {
            return numerator.toString();
        } else {
            return numerator + "/" + denominator;
        }
    }

    @Override //Override the equals method in the Object class
    public boolean equals (Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        BigIntegerRational that = (BigIntegerRational) other;
        return this.numerator.multiply(that.denominator).equals(that.numerator.multiply(this.denominator));
    }

    @Override //Implement the abstract intValue method in Number
    public int intValue() {
        return (int)doubleValue();
    }

    @Override /** Implement the abstract floatValue method in Number */
    public float floatValue() {
        return (float)doubleValue();
    }

    @Override /** Implement the doubleValue method in Number */
    public double doubleValue() {
        return numerator.doubleValue() / denominator.doubleValue();
    }

    @Override /** Implement the abstract longValue method in Number */
    public long longValue() {
        return (long)doubleValue();
    }

    @Override
    public int compareTo(BigIntegerRational o) {
        return this.subtract(o).numerator.compareTo(BigInteger.ZERO);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        BigIntegerRational firstRational = null;
        BigIntegerRational secondRational = null;

        //Read and parse the first rational number
        while (firstRational == null) {
            System.out.println("Enter the first rational number (format: numerator/denominator): ");
            String input = scanner.nextLine();
            try {
                String[] parts = input.split("/");
                if (parts.length != 2) {
                    throw new InputMismatchException("Invalid format. Expected format: numerator/denominator");
                }
                BigInteger num1 = new BigInteger(parts[0].trim());
                BigInteger den1 = new BigInteger(parts[1].trim());
                firstRational = new BigIntegerRational(num1, den1);
            } catch (NumberFormatException e) {
                System.out.println("Invalid number format. Please try again.");
            } catch (ArithmeticException e) {
                System.out.println(e.getMessage());
            } catch (InputMismatchException e) {
                System.out.println(e.getMessage());
            }
        }

        //Read and parse the second rational number
        while (secondRational == null) {
            System.out.println("Enter the second rational number (format: numerator/denominator): ");
            String input = scanner.nextLine();
            try {
                String[] parts = input.split("/");
                if (parts.length != 2) {
                    throw new InputMismatchException("Invalid format. Expected format: numerator/denominator");
                }
                BigInteger num2 = new BigInteger(parts[0].trim());
                BigInteger den2 = new BigInteger(parts[1].trim());
                secondRational = new BigIntegerRational(num2, den2);
            } catch (NumberFormatException e) {
                System.out.println("Invalid number format. Please try again.");
            } catch (ArithmeticException e) {
                System.out.println(e.getMessage());
            } catch (InputMismatchException e) {
                System.out.println(e.getMessage());
            }
        }

        //Perform the operations
        BigIntegerRational add = firstRational.add(secondRational);
        BigIntegerRational subtract = firstRational.subtract(secondRational);
        BigIntegerRational multiply = firstRational.multiply(secondRational);
        BigIntegerRational divide = firstRational.divide(secondRational);

        System.out.println(firstRational + " + " + secondRational + " = " + add);
        System.out.println(firstRational + " - " + secondRational + " = " + subtract);
        System.out.println(firstRational + " * " + secondRational + " = " + multiply);
        System.out.println(firstRational + " / " + secondRational + " = " + divide);
        System.out.println(secondRational + " is " + secondRational.toBigDecimal().toPlainString());
    }
    
}