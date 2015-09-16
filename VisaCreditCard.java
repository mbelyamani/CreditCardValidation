package com.mbelyamani.creditCard;

import java.util.Arrays;
import java.util.List;

public class VisaCreditCard extends AbstractCreditCard{

	private static final String STARTS_WITH = "4";
	private static final List<Integer> LENGHT_POSSIBLE_LIST = Arrays.asList(13, 16);
	
	
	public VisaCreditCard(String cardNumber, Validation validation){
		setValidation(validation);
		if (cardNumber == null)
			cardNumber = generateRandomNumber(STARTS_WITH, LENGHT_POSSIBLE_LIST.get(1));
		setCardNumber(cardNumber);
	}
	
	@Override
	public boolean validateStartCharacters() {
		if (!getCardNumber().startsWith(STARTS_WITH))
			return false;
		return true;
	}
	
	@Override
	public boolean validateLenght() {
		if (!LENGHT_POSSIBLE_LIST.contains(getCardNumber().trim().replace(" ", "").length()))
			return false;
		return true;
	}

}
