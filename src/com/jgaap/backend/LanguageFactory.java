package com.jgaap.backend;

import java.util.HashMap;
import java.util.Map;

import com.jgaap.generics.Language;

public class LanguageFactory {

	private Map<String, Language> languages;
	
	public LanguageFactory() {
		// Load the classifiers dynamically
		languages = new HashMap<String, Language>();
		for(Language language : AutoPopulate.getLanguages()){
			languages.put(language.getName().toLowerCase(), language);
		}
	}
	
	public Language getLanguage(String action) throws Exception{
		Language language;
		action = action.toLowerCase();
		if(languages.containsKey(action)){
			language = languages.get(action).getClass().newInstance();
		}else {
			throw new Exception("Language "+action+" was not found!");
		}
		return language;
	}
}
