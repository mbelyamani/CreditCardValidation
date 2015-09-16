package com.mbelyamani.creditCard;


public class CreditCardFactoryTest {
	
	public static CreditCard createRandomVisaCreditCardLuhnValidation(){
		Validation luhnValidation = ValidationFactory.generateValidation(ValidationFactory.ValidationType.LUHN);
		CreditCard visa = CreditCardFactory.generateCreditCard(CreditCardFactory.CreditCardType.VISA, luhnValidation);
		if (visa.isValid())
			System.out.println("VISA Created Card :: " + visa.getCardNumber() + " :: is valid!!");
		else 
			System.out.println("VISA Created Card :: " + visa.getCardNumber() + " :: is NOT valid!!");
		return visa;
	}

	public static CreditCard createRandomVisaCreditCardVerhoeffValidation(){
		Validation verhoeffValidation = ValidationFactory.generateValidation(ValidationFactory.ValidationType.VERHOEFF);
		CreditCard visa = CreditCardFactory.generateCreditCard(CreditCardFactory.CreditCardType.VISA, verhoeffValidation);
		if (visa.isValid())
			System.out.println("VISA Created Card :: " + visa.getCardNumber() + " :: is valid!!");
		else 
			System.out.println("VISA Created Card :: " + visa.getCardNumber() + " :: is NOT valid!!");
		return visa;
	}
	
	public static CreditCard createRandomMasterCardCreditCardLuhnValidation(){
		Validation luhnValidation = ValidationFactory.generateValidation(ValidationFactory.ValidationType.LUHN);
		CreditCard masterCard = CreditCardFactory.generateCreditCard(CreditCardFactory.CreditCardType.MASTER_CARD, luhnValidation);
		
		if (masterCard.isValid())
			System.out.println("Master Card Created Card :: " + masterCard.getCardNumber() + " :: is valid!!");
		else 
			System.out.println("Master Card Created Card :: " + masterCard.getCardNumber() + " :: is NOT valid!!");
		return masterCard;
	}
	
	public static CreditCard createMasterCardCreditCardLuhnValidation(String num){
		Validation luhnValidation = ValidationFactory.generateValidation(ValidationFactory.ValidationType.LUHN);
		CreditCard masterCard = CreditCardFactory.generateCreditCard(CreditCardFactory.CreditCardType.MASTER_CARD, luhnValidation, num);
		
		if (masterCard.isValid())
			System.out.println("Master Card Created Card :: " + masterCard.getCardNumber() + " :: is valid!!");
		else 
			System.out.println("Master Card Created Card :: " + masterCard.getCardNumber() + " :: is NOT valid!!");
		return masterCard;
	}
	
	public static CreditCard createVisaCreditCardLuhnValidation(String num){
		Validation luhnValidation = ValidationFactory.generateValidation(ValidationFactory.ValidationType.LUHN);
		CreditCard visa = CreditCardFactory.generateCreditCard(CreditCardFactory.CreditCardType.VISA, luhnValidation, num);
		if (visa.isValid())
			System.out.println("VISA Created Card :: " + visa.getCardNumber() + " :: is valid!!");
		else 
			System.out.println("VISA Created Card :: " + visa.getCardNumber() + " :: is NOT valid!!");
		return visa;
	}
	
}
