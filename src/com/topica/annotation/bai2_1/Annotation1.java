package com.topica.annotation.bai2_1;

import java.lang.annotation.Annotation;
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

public class Annotation1 {

	public static void setValueStaticFinal(Object obj) throws Exception {
		Class<?> myClass = obj.getClass();
		Field[] field = myClass.getDeclaredFields();
		for (Field fid : field) {
			setValueStaticFinal check = fid.getAnnotation(setValueStaticFinal.class);

			if (check != null) {
				fid.setAccessible(true);
				int modifier = fid.getModifiers();
				if (Modifier.isStatic(modifier) && Modifier.isFinal(modifier)) {

					Annotation an = fid.getAnnotation(setValueStaticFinal.class);
					setValueStaticFinal ann = (setValueStaticFinal) an;
					process(ann, fid);
				}
			}
		}

	}

	public static void process(setValueStaticFinal ann, Field field)
			throws NoSuchFieldException, IllegalAccessException, Exception {
		if (!ann.updateValueString().equals("")) {
			setFinalStatic(field, ann.updateValueString());
		} else if (ann.updateValueInteger() != 0) {
			setFinalStatic(field, ann.updateValueInteger());
		} else if (ann.updateValueFloat() != 0) {
			setFinalStatic(field, ann.updateValueFloat());
		} else if (ann.updateValueDouble() != 0) {
			setFinalStatic(field, ann.updateValueDouble());
		}
	}

	static void setFinalStatic(Field field, Object newValue)
			throws Exception, NoSuchFieldException, IllegalAccessException {
		field.setAccessible(true);

		Field modifiersField = Field.class.getDeclaredField("modifiers");
		modifiersField.setAccessible(true);
		modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);

		field.set(null, newValue);

	}
}
