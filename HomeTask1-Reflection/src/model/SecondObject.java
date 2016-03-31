package model;

import annotation.Eq;
import annotation.Equal;
/**
 * field age	 compareby value <br>
 * field name	 compareby reference <br>
 * field address
 * 
 * @author Eugene Pomozov
 */
public class SecondObject {
	
	@Equal(compareby=Eq.value)
	private int age = 23;
	
	@Equal(compareby=Eq.reference)
	private String name = "Alex";
	
	private String address = "Gomel";

	public SecondObject(int age, String name, String address) {
		super();
		this.age = age;
		this.name = name;
		this.address = address;
	}	

}
