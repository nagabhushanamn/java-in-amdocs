package com.example.container;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Container {

	public void processRequest(String url) {
		try {
			Class<?> clazz = Class.forName("com.example.controller.UserController");
			Object instance = clazz.newInstance(); // new UserController();
			Method[] methods = clazz.getMethods();
			for (Method method : methods) {
				RequestMapping rm = method.getAnnotation(RequestMapping.class);
				if (rm != null) {
					String givenUrl = rm.url();
					if (url.equals(givenUrl)) {
						method.invoke(instance, new Object[] {});
					}
				}
			}

		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			e.printStackTrace();
		} //
	}
}
