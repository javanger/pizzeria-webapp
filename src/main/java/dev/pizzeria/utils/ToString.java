/**
 * 
 */
package dev.pizzeria.utils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**Annotation ToString
 * @author Alexis Darcy
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ToString {
	
	/**separateur : String*/
	String separateur() default "";
	/**upperCase : boolean*/
	boolean upperCase() default false;
}
