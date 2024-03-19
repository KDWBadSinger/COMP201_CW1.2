package uk.ac.liv.comp201;

import static uk.ac.liv.comp201.ResponseCode.*;

public class Authenticator {
	private Card card;	// this is the Card this is being checked
	private String passcodeFire;
	private String passcodeBurglary;
	
	public Authenticator(Card card) {
		this.card=card;
	}

//	public ResponseCode checkFireCode(String passCodeFire) throws CardException {
//		boolean fireCodeCorrect = passCodeFire.equals(card.getCardFireCode());
//		boolean burglaryCodeCorrect = this.passcodeBurglary.equals(card.getCardBurlaryCode());
//
//		if (fireCodeCorrect && burglaryCodeCorrect) {
//			card.badCodeAttempts = 0;
//			return ResponseCode.OK;
//		} else if (fireCodeCorrect == true && burglaryCodeCorrect == false) {
//			return ResponseCode.OK;
//		} else {
//			if (passCodeFire == null || passCodeFire.length() < 10
//					|| passCodeFire.length() > 14
//					|| !passCodeFire.matches("[a-zA-Z0-9]+")){
//				return ResponseCode.INVALID_FIRE_CODE;
//			}
//			else {
//				card.incrementBadCodeAttempts();
//				if (card.badCodeAttempts >= 3) {
//					return ResponseCode.CARD_LOCKED;
//				}
//				if (!fireCodeCorrect) {
//					return ResponseCode.BAD_FIRE_CODE;
//				}
//			}
//		}
//		card.saveCard();
//        return null;
//    }
//
//	public ResponseCode checkBurglaryCode(String passCodeBurglary) throws CardException {
//		boolean fireCodeCorrect = this.passcodeFire.equals(card.getCardFireCode());
//		boolean burglaryCodeCorrect = passCodeBurglary.equals(card.getCardBurlaryCode());
//
//		if (fireCodeCorrect && burglaryCodeCorrect) {
//			card.badCodeAttempts = 0;
//			return ResponseCode.OK;
//		} else if (fireCodeCorrect == false && burglaryCodeCorrect == true){
//			return ResponseCode.OK;
//		} else {
//			if (passCodeBurglary == null
//					|| passCodeBurglary.length() < 8
//					|| passCodeBurglary.length() > 10
//					|| !passCodeBurglary.matches("\\d+")){
//				return ResponseCode.INVALID_BURGLARY_CODE;
//			}
//			else {
//				card.incrementBadCodeAttempts();
//				if (card.badCodeAttempts >= 3) {
//					return ResponseCode.CARD_LOCKED;
//				}
//				if (!burglaryCodeCorrect) {
//					return ResponseCode.BAD_BURGLARY_CODE;
//				}
//			}
//		}
//		card.saveCard();
//		return null;
//	}

	public ResponseCode checkFireCode(String passCodeFire) throws CardException {
		if (card.getCardStatus() == CardStatus.CARD_NEW) {
			return ResponseCode.INVALID_CARD;
		}

		if (passCodeFire.equals(card.getCardFireCode())){
			card.resetBadCodeAttempts();
			return ResponseCode.OK;
		} else {
			if (passCodeFire == null || passCodeFire.length() < 10
					|| passCodeFire.length() > 14
					|| !passCodeFire.matches("[a-zA-Z0-9]+")) {
				return ResponseCode.INVALID_FIRE_CODE;
			} else {
				card.incrementBadCodeAttempts();
				if (card.getCardStatus() == CardStatus.CARD_BLOCKED) {
					return ResponseCode.CARD_LOCKED;
				}
				return ResponseCode.BAD_FIRE_CODE;
			}
		}
	}


	public ResponseCode checkBurglaryCode(String passCodeFire) throws CardException {
		if (card.getCardStatus() == CardStatus.CARD_NEW) {
			return ResponseCode.INVALID_CARD;
		}

		if (passCodeFire.equals(card.getCardBurlaryCode())){
			card.resetBadCodeAttempts();
			return ResponseCode.OK;
		} else {
			if (passCodeFire == null
					|| passCodeFire.length() < 8
					|| passCodeFire.length() > 10
					|| !passCodeFire.matches("\\d+")){
				return ResponseCode.INVALID_BURGLARY_CODE;
			} else {
				card.incrementBadCodeAttempts();
				if (card.getCardStatus() == CardStatus.CARD_BLOCKED) {
					return ResponseCode.CARD_LOCKED;
				}
				return ResponseCode.BAD_BURGLARY_CODE;
			}
		}

	}

}
