package com.st.rental.ui;

import org.eclipse.jface.viewers.IColorProvider;

public class Palette {
	private String name,id;
	private IColorProvider provider;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public IColorProvider getProvider() {
		return provider;
	}
	public void setProvider(IColorProvider provider) {
		this.provider = provider;
	}

}
