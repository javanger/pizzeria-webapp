package fr.pizzeria.utils;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target(FIELD)
public @interface ToString {

	/** Annotation pour mettre en upperCase ou non */
	boolean upperCase() default false;
	/** Annotation pour définir la séparation*/
	String separateur() default "";
	/** Annotation pour mettre en upperCase ou non */
	boolean notNull() default true;
}
