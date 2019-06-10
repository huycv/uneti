package edu.uneti.core.student;

/**
 * 
 * The Enum Sex
 *
 */
public enum Sex {

	/** The Male */
	MALE(0, "Male"),
	
	//** The female*/
	FEMALE(1, "Female");
	
	/** The value */
	public int value;
	
	/** The description */
	public String description;
	
	/**
	 * Instantiates a new sex
	 * 
	 * @param value the value
	 * @param description the description
	 */
	private Sex(int value, String description) {
		this.value = value;
		this.description = description;
	}
}
