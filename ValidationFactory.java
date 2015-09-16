package com.mbelyamani.creditCard;


public class ValidationFactory {

	public enum ValidationType{
		LUHN, VERHOEFF;
	}
	
	public static Validation generateValidation(ValidationType type){
		Validation validation = null;
		if (type.equals(ValidationType.LUHN))
			validation = LuhnValidation.getInstance();
		if (type.equals(ValidationType.VERHOEFF))
			validation = VerhoeffValidation.getInstance();
		
		return validation;
	}

}
