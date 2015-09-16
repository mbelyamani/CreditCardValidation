package com.mbelyamani.creditCard;

import java.util.Optional;
import java.util.stream.IntStream;

public class LuhnValidation implements Validation{
	
	private volatile static LuhnValidation luhnValidation;
	
//	private static ExecutorService executor = Executors.newCachedThreadPool();

	private LuhnValidation() { }

    public static LuhnValidation getInstance() {
        if (luhnValidation == null) {
            synchronized(LuhnValidation.class) {            
                if (luhnValidation == null) {
                	luhnValidation = new LuhnValidation();
                }        
            }
        }

        return luhnValidation;
    }
	
	
	public static Boolean Mod10Check(String creditCardNumber) {
		System.out.println("Credit Card is :: " + creditCardNumber);
		
		// check whether input string is null or empty
		if (creditCardNumber == null || creditCardNumber.isEmpty())
			return false;
		
		// check whether input string contains non numerical characters
		String ccWithoutSpace = creditCardNumber.replace(" ", "");
		String regex = "\\d+";
		if (!ccWithoutSpace.matches(regex))
			return false;
		
		// 1.	Starting with the check digit double the value of every other digit 
		// 2.	If doubling of a number results in a two digits number, add up
		//   the digits to get a single digit number. This will results in eight single digit numbers                    
		// 3. Get the sum of the digits
		
		Optional<Integer> result = IntStream.range(0, ccWithoutSpace.length()).mapToObj(e -> {
												if(e%2 == 0) {
													int val = Character.getNumericValue(ccWithoutSpace.charAt(e)) * 2;
													if(val > 9)
														val = (int)(val / 10) + val % 10;										
													return val;					
												} else {
													return Character.getNumericValue(ccWithoutSpace.charAt(e));
												}
											}).reduce((a,b) -> a+b);
		
		
		System.out.println("Result :: " + result.get());
		return result.isPresent() && result.get().intValue()%10==0;
	}

	@Override
	public boolean validate(String creditCardNum) {
		return Mod10Check(creditCardNum);
	}

}
