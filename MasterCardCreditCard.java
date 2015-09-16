package com.mbelyamani.creditCard;

import java.util.Arrays;
import java.util.List;

public class MasterCardCreditCard extends AbstractCreditCard {
	
	private static final List<String> STARTS_WITH_LIST = Arrays.asList("51", "55");
	private static final List<Integer> LENGHT_POSSIBLE_LIST = Arrays.asList(16);
	
	public MasterCardCreditCard(String cardNumber, Validation validation){
		setValidation(validation);
		if (cardNumber == null)
			cardNumber = generateRandomNumber(STARTS_WITH_LIST.get(0), LENGHT_POSSIBLE_LIST.get(0));
		setCardNumber(cardNumber);
	}
	
	@Override
	public boolean validateStartCharacters() {
		boolean valid = false;
		String fomattedNumber = getCardNumber().trim().replace(" ", ""); 
		for (String head : STARTS_WITH_LIST){
			if (fomattedNumber.startsWith(head)) {
				valid = true;
				break;
			}
		}
		return valid;
	}
	
	@Override
	public boolean validateLenght() {
		if (!LENGHT_POSSIBLE_LIST.contains(getCardNumber().trim().replace(" ", "").length()))
			return false;
		return true;
	}
}
