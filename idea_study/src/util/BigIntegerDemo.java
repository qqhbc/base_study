package util;

import java.math.BigInteger;

public class BigIntegerDemo {
    public static void main(String[] args) {
        BigInteger bigInteger = new BigInteger("0");
        BigInteger bit = bigInteger.setBit(5).setBit(10);
        System.out.println(bit);
        System.out.println(bit.testBit(5));
        System.out.println(bit.testBit(11));
    }
}
