package com.neocool.pgmanagement.utils;

import com.neocool.pgmanagement.model.Allo;
import com.neocool.pgmanagement.model.Flats;
import com.neocool.pgmanagement.model.Person;
import com.neocool.pgmanagement.model.Status;

public class AppContext {

	private static AppContext appContext = null;

	private AppContext() {
	}

	public static AppContext getInstance() {
		if (appContext == null) {
			appContext = new AppContext();
		}

		return appContext;
	}

	@SuppressWarnings("unchecked")
	public <T> T getBean(String beanName) {

		if (beanName.equalsIgnoreCase("Person")) {
			return (T) new Person();	
		} 
		else if (beanName.equalsIgnoreCase("Allo")) {
			return (T) new Allo();
		}
		else if (beanName.equalsIgnoreCase("Status")) {
			return (T) new Status();
		}
		else if (beanName.equalsIgnoreCase("flats")) {
			return (T) new Flats();
		}
		return null;
	}


}