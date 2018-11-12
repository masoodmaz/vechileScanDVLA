package uk.co.dvla.model;

public class VehicleBean {

	/**
	 * POJO for Vehicle Object containing information on
	 * REG NUM, MAKE, COLOUR
	 */
	private String reg;
	private String make;
	private String colour;
	
	public String getReg() {
		return reg;
	}
	public void setReg(String reg) {
		this.reg = reg;
	}
	public String getMake() {
		return make;
	}
	public void setMake(String make) {
		this.make = make;
	}
	public String getColour() {
		return colour;
	}
	public void setColour(String colour) {
		this.colour = colour;
	}

}
