package com.mbelyamani.creditCard;

import java.util.Random;

public abstract class AbstractCreditCard implements CreditCard{

	private String cardNumber;
	private Validation validation;
	
	
	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public Validation getValidation() {
		return validation;
	}

	public void setValidation(Validation validation) {
		this.validation = validation;
	}

	@Override
	public boolean isValid(){
		boolean basicValidation = validateBasic();
		if (!basicValidation)
			return false;
		
		boolean cardStatCharacterValidation = validateStartCharacters();
		if (!cardStatCharacterValidation)
			return false;
		
		boolean cardLenghtValidation = validateLenght();
		if (!cardLenghtValidation)
			return false;
		
		return getValidation().validate(getCardNumber());
	}
	
	private boolean validateBasic(){
		return getValidation()!=null && getCardNumber()!=null;
	}
	
	abstract public boolean validateStartCharacters();
	
	abstract public boolean validateLenght();
	
	public String generateRandomNumber(final String head, final int length) {
		String ccardNumber = null;	
		boolean isValid = false;
		while (!isValid){
			StringBuilder generatedNumber = new StringBuilder();
			generatedNumber.append(head);
			int remainingLenght = length - head.length();
	
			Random rand = new Random();
			for (int i=0; i<remainingLenght; i++) {
			    int randomNum = rand.nextInt(10);
			    generatedNumber.append(randomNum);
			}
			ccardNumber = generatedNumber.toString();	
			isValid = getValidation().validate(ccardNumber);
		}
		
		return ccardNumber;
	}
	
}
