package za.ac.nwu.ac.logic.flow.impl;

public class Modulo {

    public Integer doMod(int numerator, int denominator){
        if(denominator == 0){
            throw new RuntimeException("Some reason");
        }
        return numerator%denominator;
    }
}
