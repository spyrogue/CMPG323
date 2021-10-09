package za.ac.nwu.ac.logic.flow.impl;

public class Modulo {
    public Integer doMod(int numerator, int denominator){
        if(numerator == 0)
        {
            throw new RuntimeException("Error for some reason");
        }
        return numerator%denominator;
    }
}
