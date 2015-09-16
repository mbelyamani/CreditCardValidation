package com.mbelyamani.creditCard;


public class CreditCardFactory {
	
	public enum CreditCardType{
			VISA, MASTER_CARD;
	}
	
	public static CreditCard generateCreditCard(CreditCardType type, Validation validation){
		return generateCreditCard(type, validation, null);
	}
	
	public static CreditCard generateCreditCard(CreditCardType type, Validation validation, String cardNumber){
		CreditCard creditCard = null;
		if (type.equals(CreditCardType.VISA))
			creditCard = new VisaCreditCard(cardNumber, validation);
		if (type.equals(CreditCardType.MASTER_CARD))
			creditCard = new MasterCardCreditCard(cardNumber, validation);
		
		return creditCard;
	}
	
	
}
