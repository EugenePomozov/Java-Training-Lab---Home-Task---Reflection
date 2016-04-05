import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class LFUCache {

	private List<LinkedHashSet<String>> cache = new ArrayList<LinkedHashSet<String>>();
	private int size;
	private int count;
	private float evictionFactor;

	public LFUCache(int count, int size, float evictionFactor) {
		super();
		this.size = size;
		this.count = count;
		this.evictionFactor = evictionFactor;
		for (int i = 0; i < count; i++)
			cache.add(new LinkedHashSet<String>());
	}

	public void put(String key) {
		Set<String> set0 = cache.get(0);
		if (set0.size() >= size) {
			set0.remove(set0.toArray()[0]);
		}
		set0.add(key);

	}

	public String get(String key) {
		for (int i = 0; i < count - 1; i++)
			if (cache.get(i).contains(key)) {
				cache.get(i + 1).add(key);
				cache.get(i).remove(key);
				return key;
			}
		if (cache.get(count - 1).contains(key)) {
			cache.get(count - 1).remove(key);
			cache.get(count - 1).add(key);
			return key;
		}
		return "";
	}

	public void eviction() {
		int countEntries = 0;
		for (Set<String> set:cache)
			countEntries += set.size();

		int countEviction = (int) (countEntries * evictionFactor);

		while (countEviction > 0) {
			for (Set<String> set:cache) {
				if (set.isEmpty())
					continue;
				set.remove(set.toArray()[0]);
				countEviction--;
				break;

			}
		}
	}

	public void print() {
		System.out.println("\n-------------------------------------");
		for (int i = 0; i < count; i++) {
			System.out.print("\n[ " + i + "]");
			for (String key : cache.get(i))
				System.out.print("  " + key + "  ");
		}
	}
}
