import java.util.HashMap;
import java.util.Map;

public class WordsFrequency {

	private Map<String,Integer> frequency = new HashMap<>();
		
	
	public WordsFrequency(String text) {
		super();
		final String DELIMITER = "[,;:.!?\\s]+";
		String[] words = text.split(DELIMITER);
		for (String word:words){
			if(frequency.get(word.toUpperCase())!=null)
				frequency.put(word.toUpperCase(),1+frequency.get(word.toUpperCase()));
			else 
				frequency.put(word.toUpperCase(),1);
		}
	}

	public WordsFrequency() {
		super();
	}

	public String numberOfWords(String word){
		final String message = "This word is not" ;
		if (frequency.get(word.toUpperCase())!=null)
			return word+" - " + frequency.get(word.toUpperCase());
		else 
			return word+" - " +message;
	}
	
	
}
