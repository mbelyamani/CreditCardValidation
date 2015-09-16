package com.mbelyamani.creditCard;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CompletableFuture;

public abstract class AbstractCreditCardConcurrency implements CreditCard{

	private String cardNumber;
	private Validation validation;
	private volatile boolean isValid = false;
	
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
		BlockingQueue<CompletableFuture<String>> generatedCards = new LinkedBlockingQueue<CompletableFuture<String>>();
		BlockingQueue<CompletableFuture<String>> validatedCards = new LinkedBlockingQueue<CompletableFuture<String>>();
		while (!isValid){
			System.out.println("While Mean Thread .... :: Current Thread " + Thread.currentThread().getId());
	
			CompletableFuture<String> generatedCardFuture = CompletableFuture.
					supplyAsync(() -> new Callable<String>(){
						public String call(){
							System.out.println("generatedCardFuture :: Current Thread " + Thread.currentThread().getId());
							StringBuilder generatedNumber = new StringBuilder();
							generatedNumber.append(head);
							int remainingLenght = length - head.length();
					
							Random rand = new Random();
							for (int i=0; i<remainingLenght; i++) {
							    int randomNum = rand.nextInt(10);
							    generatedNumber.append(randomNum);
							}
							return generatedNumber.toString();
						}
					}.call());
			
			generatedCards.add(generatedCardFuture);
			System.out.println(" .... generatedCards .... Size .... " + generatedCards.size());
			
			CompletableFuture<String> validatingFuture = CompletableFuture.
					supplyAsync(() -> new Callable<String>(){
								public String call(){
									System.out.println("validatingFuture :: Current Thread " + Thread.currentThread().getId());
									if (generatedCards.isEmpty())
										return null;
									
									List<CompletableFuture<String>> elementToRemove = new ArrayList<CompletableFuture<String>>();
									for (CompletableFuture<String> future : generatedCards) {
										if (future.isDone()) {
											String number = future.getNow("NULL");
											if (!"NULL".equals(number)) {
												elementToRemove.add(future);
												if(getValidation().validate(number))
													return number;
											}
										}
									}
									generatedCards.removeAll(elementToRemove);
									return null;
								}
							}.call()
					);

			validatedCards.add(validatingFuture);
			
			System.out.println(" .... validatedCards .... Size .... " + validatedCards.size());
			
			List<CompletableFuture<String>> elementToRemove = new ArrayList<CompletableFuture<String>>();
			for (CompletableFuture<String> vCard : validatedCards) {
				if (vCard.isDone()){
					ccardNumber = vCard.getNow("NULL");
					if (!"NULL".equals(ccardNumber)){
						elementToRemove.add(vCard);	
						if(ccardNumber!=null) {
							isValid = true;
							break;
						}
					}
				}	
			}
			validatedCards.removeAll(elementToRemove); 
		}
		return ccardNumber;
		
		
//		while (!isValid){
//			StringBuilder generatedNumber = new StringBuilder();
//			generatedNumber.append(head);
//			int remainingLenght = length - head.length();
//	
//			Random rand = new Random();
//			for (int i=0; i<remainingLenght; i++) {
//			    int randomNum = rand.nextInt(10);
//			    generatedNumber.append(randomNum);
//			}
//			ccardNumber = generatedNumber.toString();	
//			isValid = getValidation().validate(ccardNumber);
//		}
//		
//		return ccardNumber;
	
	
	}
	
}
