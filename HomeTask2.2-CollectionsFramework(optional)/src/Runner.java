
public class Runner {

	public static void main(String[] args) {
		LFUCache lfu = new LFUCache(4,4,0.8f);
		
		lfu.put("a");
		lfu.put("b");
		lfu.put("c");
		lfu.put("d");
		lfu.put("e");
			lfu.print();
			//[0] b, c, d, e
			
		lfu.get("d");
		lfu.get("e");
		lfu.get("a");	//!!!It does not work	
		lfu.put("f");
		lfu.put("g");
			lfu.print();
			//[0] b, c, f, g
			//[1] d, e
			
		lfu.put("h");		
		lfu.get("e");
		lfu.get("f");		
		lfu.put("i");
			lfu.print();
			//[0] c, g, h, i
			//[1] d, f
			//[2] e
			
		lfu.get("e");
		lfu.get("f");
		lfu.get("g");
		lfu.get("d");
		lfu.put("j");
		lfu.put("k");
		lfu.get("f");
		lfu.get("e");
			lfu.print();
			//[0] h, i, j, k
			//[1] g
			//[2] d
			//[3] f, e
		
	lfu.eviction();
		lfu.print();
		//[0] 
		//[1] 
		//[2] 
		//[3] f, e

	}

}
