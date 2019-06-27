package com.topica.annotation.bai2_1;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

@Target(value = { ElementType.FIELD, ElementType.METHOD })
@Retention(value = RetentionPolicy.RUNTIME)
@Documented
@Inherited
@interface setValueStaticFinal {
	String updateValueString() default "";

	int updateValueInteger() default 0;

	float updateValueFloat() default 0;

	double updateValueDouble() default 0;
}

public class Annotation {
	public static final String MODIFIER = "modifiers";

	public static void setValueStaticFinal(Object obj) throws Exception {
		Class<?> myClass = obj.getClass();
		Field[] fields = myClass.getDeclaredFields();
		for (Field field : fields) {
			setValueStaticFinal check = field.getAnnotation(setValueStaticFinal.class);
			if (check != null) {
				field.setAccessible(true);
				int modifier = field.getModifiers();
				if (Modifier.isStatic(modifier) && Modifier.isFinal(modifier)) {
					setValueStaticFinal anno = (setValueStaticFinal) check;
					process(anno, field);
				}
			}
		}

	}

	private static void process(setValueStaticFinal anno, Field field)
			throws NoSuchFieldException, IllegalAccessException, Exception {
		if (!anno.updateValueString().equals("")) {
			setFinalStatic(field, anno.updateValueString());
		} else if (anno.updateValueInteger() != 0) {
			setFinalStatic(field, anno.updateValueInteger());
		} else if (anno.updateValueFloat() != 0) {
			setFinalStatic(field, anno.updateValueFloat());
		} else if (anno.updateValueDouble() != 0) {
			setFinalStatic(field, anno.updateValueDouble());
		}
	}

	private static void setFinalStatic(Field field, Object newValue)
			throws Exception, NoSuchFieldException, IllegalAccessException {
		field.setAccessible(true);

		Field modifiersField = Field.class.getDeclaredField(MODIFIER);
		modifiersField.setAccessible(true);
		modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);

		field.set(null, newValue);

	}
}
