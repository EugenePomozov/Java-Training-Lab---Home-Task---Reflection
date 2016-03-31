package model;

import annotation.Eq;
import annotation.Equal;
/**
 * field name	 compareby reference <br>
 * field age	 compareby value <br>
 * 
 * @author Eugene Pomozov
 */
public class FirstObject {
	
	
	@Equal(compareby=Eq.reference)
	private String name = "Alex";
	
	@Equal(compareby=Eq.value)
	private int age = 23;

	public FirstObject(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}

}
