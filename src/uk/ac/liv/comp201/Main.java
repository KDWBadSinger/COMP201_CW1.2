package uk.ac.liv.comp201;


public class Main {

	public static void main(String[] args) {
		String cardName="coopesabc";
		Card card;
		try {
			Card.createNewCard(cardName);
			card = Card.loadCard(cardName);
			card.setCodes("12345", "56789");

		} catch (CardException e) {
			e.printStackTrace();
		}

		try {
			card = Card.loadCard(cardName);
			System.out.println("burglary code is "+card.getCardBurlaryCode());
			System.out.println("fire code is "+card.getCardFireCode());
			System.out.println("status "+card.getCardStatus());

		} catch (CardException e) {
			e.printStackTrace();
		}

	}

}

//import java.io.File;
//
//public class Main {
//
//	public static void main(String[] args) {
//
//		String cardName="CardNameB";
//		Card card;
//		try {
////			Card.createNewCard(cardName);
////			card = Card.loadCard(cardName);
//
//			File cardFile = new File(cardName);
//
//			if (!cardFile.exists()) {
//				Card.createNewCard(cardName);
//			}
//			card = Card.loadCard(cardName);
//
//			// set code
//		    card.setCodes("123456789ABC", "567891234");
//
//			// ---------------------------------------------------------------------------------
//			Authenticator authenticator = new Authenticator(card);
//
//			ResponseCode fireCodeResponse = authenticator.checkFireCode("123456789ABD");
//			ResponseCode burglaryCodeResponse = authenticator.checkBurglaryCode("567891235");
//
//			System.out.println("Fire code response: " + fireCodeResponse);
//			System.out.println("Burglary code response: " + burglaryCodeResponse);
//			// ----------------------------------------------------------------------------------
//
//		} catch (CardException e) {
//		e.printStackTrace();
//		}
//
//		try {
//
//			card = Card.loadCard(cardName);
//			System.out.println("burglary code is "+card.getCardBurlaryCode());
//			System.out.println("fire code is "+card.getCardFireCode());
//			System.out.println("status "+card.getCardStatus());
//
//
//		} catch (CardException e) {
//			e.printStackTrace();
//		}
//
//
//	}
//
//}


