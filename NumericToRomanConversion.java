package com.bbc.sathish;

public class NumericToRomanConversion implements RomanNumeralGenerator{

    private static final String[] romeCode = {"M", "CM", "D", "CD", "C", "XC", "L",
                                           		"XL", "X", "IX", "V", "IV", "I"};
    private static final int[]    numericValues  = {1000, 900, 500, 400,  100,   90,  50,
                                           		40,   10,    9,   5,   4,    1};
   
    
    
    /**
     * This method will convert numeric value into its rome equivalent.
     * @param inputValue
     * @return
     */
    public String generate(int inputValue) {
    	String romanResult = "";
        if (inputValue <= 0 || inputValue >= 3999) {
			try {
				throw new Exception("Only support numbers between 1 and 399");
			} catch (Exception e) {
				e.printStackTrace();
			}
        }
        
        for (int i = 0; i < romeCode.length; i++) {
            while (inputValue >= numericValues[i]) {
            	inputValue -= numericValues[i];
            	romanResult  += romeCode[i];
            }
        }
        return romanResult;
    }  

}
