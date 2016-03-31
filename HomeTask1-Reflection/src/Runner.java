import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


import analyzer.Comparer;
import model.FirstObject;
import model.SecondObject;


public class Runner {

	public static void main(String[] args) {
		FirstObject first1 = new FirstObject("Alex",23);
		FirstObject first2 = new FirstObject("Eugene",23);
		SecondObject second1 = new SecondObject(25,"Alex","Gomel");
		SecondObject second2 = new SecondObject(23,"Eugene","Minsk");
		SecondObject second3 = new SecondObject(22,"Ivan","Gomel");
		SecondObject second4 = new SecondObject(23,"Alex","Gomel");
		
		Object[] objects ={first1,first2,second1,second2,second3,second4};
		int count = objects.length;
		
		Comparer analyzer = new Comparer();
		Method[] methods = analyzer.getClass().getDeclaredMethods();
		
		 for (int i = 0; i < count-1; i++) {
	            for (int j = i + 1; j < count; j++) {
	                String object1 = objects[i].getClass().getSimpleName();
	                String object2 = objects[j].getClass().getSimpleName();
	                for (Method method : methods) {
	                    method.setAccessible(true);
	                    try {
							System.out.println(object1 +" and "+ object2 + " equals is "+ method.invoke(analyzer, objects[i], objects[j])+"\n");
						} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
							System.out.println(e.getMessage());
						}
	                    
	                }
	            }

	}

	}}
