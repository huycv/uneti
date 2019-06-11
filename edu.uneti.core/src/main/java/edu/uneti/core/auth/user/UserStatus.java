package edu.uneti.core.auth.user;

public enum UserStatus {

	/** The inactive */
	INACTIVE(0, "INACTIVE"),
	
	/** The active */
	ACTIVE(1, "ACTICE");
	
	/** The value */
	public int value;
	
	/** The description */
	public String description;
	
	/**
	 * Instantiates a new transaction status
	 * 
	 * @param value
	 *            the value
	 * @param description
	 *            the description
	 */
	private UserStatus(int value, String description) {
		this.value = value;
		this.description = description;
	}
}
