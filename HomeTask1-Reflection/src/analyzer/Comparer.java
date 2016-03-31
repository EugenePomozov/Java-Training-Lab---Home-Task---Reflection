package analyzer;

import java.lang.reflect.Field;

import annotation.Eq;
import annotation.Equal;

public class Comparer {

	public boolean equalObjects(Object obj1,Object obj2) {
		
		Field[] fields1=obj1.getClass().getDeclaredFields();
		Field[] fields2=obj2.getClass().getDeclaredFields();
		
		int count1=0,count2=0;
		for (Field field1:fields1){
			if(field1.isAnnotationPresent(Equal.class)) count1++;
		}
		for (Field field2:fields2){
			if(field2.isAnnotationPresent(Equal.class)) count2++;
		}
		
		if(count1!=count2) 
			return false;
		boolean result = false;
		boolean ok = true;
		try{
		////Check the presence of the array of field "fields1" in the fields array "fields2"
		for (Field field1:fields1){
			if (field1.isAnnotationPresent(Equal.class)){
				for (Field field2:fields2){
					field1.setAccessible(true);
					field2.setAccessible(true);
					if(field1.getName().equals(field2.getName())) 
						if(field1.getAnnotation(Equal.class).compareby()==field2.getAnnotation(Equal.class).compareby()){
							System.out.print("Field \""+field1.getName()+"\" in objects \""+ obj1.getClass().getSimpleName()+ 
									"\" and \""+ obj2.getClass().getSimpleName() +"\" compare by \""+field1.getAnnotation(Equal.class).compareby()+"\" is ");
						
							if(field1.getAnnotation(Equal.class).compareby()==Eq.reference){
								result=(field1.get(obj1)  ==  field2.get(obj2));
								System.out.println(result);
							}else{
								result=(field1.get(obj1).  equals  (field2.get(obj2)));
								System.out.println(result);
								}
							
							
							ok=ok&&result;
					
				}else{
					System.out.println("---> Field \""+field1.getName()+"\" in objects \""+ obj1.getClass().getSimpleName()+ 
							"\" and \""+ obj2.getClass().getSimpleName() +"\" compare by \""+field1.getAnnotation(Equal.class).compareby()+
							"\" and \""+field2.getAnnotation
							(Equal.class).compareby()+"\" respectively");	
				ok=false;
				}
				}
			}
		}
		}catch(IllegalArgumentException|IllegalAccessException e){
			System.out.println("problem in cast");
		}
		return ok;
		
	}

}
