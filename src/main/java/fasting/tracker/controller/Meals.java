package fasting.tracker.controller;

public class Meals {
	private final String Name;
	private final String definition;

	public Meals(String string, String string2) {
		this.Name = string;
		this.definition = string2;
	}

	public String getMeals() {
		return Name;
	}

	public String getDefinition() {
		return definition;
	}
}
