package fr.pizzeria.utils;

import java.lang.reflect.Field;

/**
 * 
 * @author Valentin Lefebvre
 *
 */
public class StringUtils {
	
	public static String afficherPizza(Object obj){
		
		String pizza = "";
		
		try{
			Field[] fields = obj.getClass().getDeclaredFields();
			for(Field f:fields){
				f.setAccessible(true);
				if(f.isAnnotationPresent(ToString.class)){
					ToString annotation = f.getAnnotation(ToString.class);
					Object value = f.get(obj);
					if(annotation.notNull()){
						if(value == null)
							value = "La valeur " + f.getName() + " est null" + annotation.separateur();
						else{
							//Cas d'annotation upperCase
							if(annotation.upperCase())
								value = value.toString().toUpperCase();
							//Cas d'annotation séparateur
							if(!annotation.separateur().equals(""))
								value += annotation.separateur();
							
						}
					}
					
					pizza += value;
				}
			}
			return pizza;
		} catch(Exception e){
			return null;
		}
	}

}
