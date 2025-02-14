package com.projects.teashop.tea.domain.status;

public enum GRAMS {
		FOUR(4),
		FIVE(5),
		SIX(6),
		SEVEN(7),
		EIGHT(8), 
		NINE(9),
		
		TEN(10),
		TWELVE(12),
		FOURTEEN(14),
		FIFTEEN(15),
		SIXTEEN(16),
		
		TWENTY(20),
		TWENTY_FIVE(25),
		FIFTY(50),
		
		ONE_HUNDRET(100),
		ONE_HUNDRED_FIFTY(150),
		
		TWO_HUNDRET(200),
		TWO_HUNDRET_FIFTY(250),
		
		THREE_HUNDRET_FIFTY_SEVEN(357);
	
		private final int weightInGrams;
		
	
		GRAMS(int weightInGrams) {
			this.weightInGrams = weightInGrams;
		}
		public int getWeightInGrams() {
	        return weightInGrams;
	    }
	}
