package uk.ac.liv.comp201;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Card {
	// TO DO 
	// 1. Needs to add in bad code count for fire code
	// and burglar alarm
	// 2. You need to modify loadCard and saveCard
	// 3. As soon as the card has a valid burglar code
	// or fire code change its status from CARD_NEW
	// to CARD_OK

	// ADD BAD CODE COUNT FOR FIRE/BURGLARY ALARM
	public int badCodeAttempts = 0;

	private static final int CARD_ID_LENGTH=9;
	
/**
 * The fire code must be between 10 and 14 characters
 * It is made up of alphanumeric characters only 	
 */
	private String cardFireCode="";
/**
  * The burglary code must be between 8 and 10 characters
  * It is made up of numeric digits only 0 to 9 	
*/
	private String cardBurglaryCode="";

/* If a CARD_STATUS = CARD_NEW   doing any form of authentication
 * (checkFireCode or checkBurglaryCode)
 * will throw the CardException exception, with  CARD_STATUS_BAD 
 */
	private CardStatus cardStatus=CardStatus.CARD_NEW;
	
	
	/**
	 * User of card, this is a alpha string 9 characters long
	 * The id is case insensivite so SEBCOOPET = sebcoopet
	 */
	private String cardUsername="";
	
	public Card(String cardUsername) throws CardException {
		checkCardName(cardUsername);
		this.cardUsername=cardUsername;
	}
	
	 



	private void checkCardName(String cardUsername) throws CardException {
		if (cardUsername.length()!=CARD_ID_LENGTH) {
			throw new CardException(ResponseCode.INVALID_CARD_ID_LENGTH);			
		}
		if (!cardUserNameValid(cardUsername)) {
			throw new CardException(ResponseCode.INVALID_CARD_ID);			
		}
	}
	
	
	


	public static void createNewCard(String cardUsername) throws CardException{
		Card card=new Card(cardUsername);
		card.saveCard();
	}
		
	
	private boolean cardUserNameValid(String cardUsername) {
		boolean returnValue=true; // default to card is ok
		for (int idx=0;idx<cardUsername.length();idx++) {
			if (!Character.isAlphabetic(cardUsername.charAt(idx))) {
				returnValue=false;break;	// oops bad character
			}
		}
		return(returnValue);
	}
	
	
	private void saveCard() {
//		try {
//		      FileWriter fileWriter = new FileWriter(cardUsername);
//		      fileWriter.write(cardFireCode+"\n");
//		      fileWriter.write(cardBurglaryCode+"\n");
//		      fileWriter.write(""+cardStatus+"\n");
//		      fileWriter.close();
//		    } catch (IOException e) {
//		 }
		// FileWriter用于将文本写入文件。(若不存在)创建一个文件并写入卡片的数据
		// write方法将字符串写入文件。每个write调用写入一行数据，每行之后通过添加“\n”（换行符）来分隔不同的数据。
		try {
			FileWriter fileWriter = new FileWriter(cardUsername);
			fileWriter.write(cardFireCode + "\n");
			fileWriter.write(cardBurglaryCode + "\n");
			fileWriter.write("" + cardStatus + "\n");

			fileWriter.write(badCodeAttempts + "\n");
			fileWriter.close();
		} catch (IOException e) {
		}
	}
	
	public static Card loadCard(String cardUsername) throws CardException {
//		Card card=new Card(cardUsername);
//		try {
//		      File file = new File(cardUsername);
//		      Scanner myReader = new Scanner(file);
//		      if (myReader.hasNextLine()) {
//		         card.cardFireCode = myReader.nextLine();
//		      }
//		      if (myReader.hasNextLine()) {
//			     card.cardBurlaryCode = myReader.nextLine();
//			  }
//		      if (myReader.hasNextLine()) {
//				 card.cardStatus =CardStatus.valueOf(myReader.nextLine());
//		      }
//		      myReader.close();
//		    } catch (FileNotFoundException e) {
//		    	throw new CardException(CARD_NOT_FOUND,cardUsername);
//		    }
//			return(card);
		Card card = new Card(cardUsername);
		try {
			File file = new File(cardUsername);
			Scanner myReader = new Scanner(file);
			if (myReader.hasNextLine()) {
				card.cardFireCode = myReader.nextLine();
			}
			if (myReader.hasNextLine()) {
				card.cardBurglaryCode = myReader.nextLine();
			}
			if (myReader.hasNextLine()) {
				card.cardStatus = CardStatus.valueOf(myReader.nextLine());
			}

			if (myReader.hasNextLine()) {
				card.badCodeAttempts = Integer.parseInt(myReader.nextLine());
			}
//			if (myReader.hasNextLine()) {
//				card.badBurglaryCodeAttempts = Integer.parseInt(myReader.nextLine());
//			}

			myReader.close();
		} catch (FileNotFoundException e) {
			throw new CardException(ResponseCode.CARD_NOT_FOUND, cardUsername);
		}
		return card;
	}

	public String getCardFireCode() {
		return cardFireCode;
	}

	private void setCardFireCode(String cardFireCode) throws CardException {
		/**
		 * The fire code must be between 10 and 14 characters
		 * It is made up of alphanumeric characters only
		 */
//		// TO DO
//		// add in validation, throw Exception if
//		// code is invalid
//		this.cardFireCode = cardFireCode;
		if (cardFireCode == null || cardFireCode.length() < 10
				|| cardFireCode.length() > 14
				|| !cardFireCode.matches("[a-zA-Z0-9]+")) {
			throw new CardException(ResponseCode.INVALID_FIRE_CODE);
		}
		this.cardFireCode = cardFireCode;

		if (this.cardStatus == CardStatus.CARD_NEW) {
			this.cardStatus = CardStatus.CARD_OK;
		}
	}



	public String getCardBurlaryCode() throws CardException {
		return cardBurglaryCode;
	}



	private void setCardBurlaryCode(String cardBurglaryCode) throws CardException {
		/**
		 * The burglary code must be between 8 and 10 characters
		 * It is made up of numeric digits only 0 to 9
		 */
//		// TO DO
//		// add in validation, throw Exception if
//		// code is invalid
//		this.cardBurlaryCode = cardBurglaryCode;

		// "\\d+"意味着代码应该只包含一个或多个数字
		if (cardBurglaryCode == null
				|| cardBurglaryCode.length() < 8
				|| cardBurglaryCode.length() > 10
				|| !cardBurglaryCode.matches("\\d+")) {
			throw new CardException(ResponseCode.INVALID_BURGLARY_CODE);
		}
		this.cardBurglaryCode = cardBurglaryCode;

		if (this.cardStatus == CardStatus.CARD_NEW) {
			this.cardStatus = CardStatus.CARD_OK;
		}
	}


	/**
	 * Set's the codes in the card  
	 * @param cardFireCode  
	 * @param cardBurglaryCode
	 * @throws CardException  If the either code is invalid
	 */
	public void setCodes(String cardFireCode,String cardBurglaryCode) throws CardException {
		setCardFireCode(cardFireCode);
		setCardBurlaryCode(cardBurglaryCode);
		cardStatus=CardStatus.CARD_OK;
		saveCard();
	}



	public CardStatus getCardStatus() {
		return cardStatus;
	}



	public void setCardStatus(CardStatus cardStatus) {
		this.cardStatus = cardStatus;
	}

	public void incrementBadCodeAttempts() {
		this.badCodeAttempts++;
		if (this.badCodeAttempts >= 3) {
			setCardStatus(CardStatus.CARD_BLOCKED);
		}
		saveCard();
	}

	public void resetBadCodeAttempts() {
		this.badCodeAttempts = 0;
		saveCard();
	}

//	public boolean isCardLocked() {
//		return this.cardStatus == CardStatus.CARD_BLOCKED;
//	}

}
	


