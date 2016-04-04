
public class Rynner {

	public static void main(String[] args) {
		WordsFrequency wf = new WordsFrequency("To be or not to be. That is the question. dfg! dfdg dfg dfg gdf gdf dfg? rt re rt");
		
		System.out.println(wf.numberOfWords("be"));
		System.out.println(wf.numberOfWords("to"));
		System.out.println(wf.numberOfWords("dfg"));
		System.out.println(wf.numberOfWords("hello"));
		

	}

}
