package edu.wmich.cs3310.PA4.GSNTree;

public abstract class Claim extends ArgumentElement{
	private boolean assumed;
	private boolean toBeSupported;
	public Claim(String id, String description, boolean assumed, boolean toBeSupported) {
		super(id,description);
	}
	public boolean isAssumed() {
		return assumed;
	}
	public void setAssumed(boolean assumed) {
		this.assumed = assumed;
	}
	public boolean isToBeSupported() {
		return toBeSupported;
	}
	public void setToBeSupported(boolean toBeSupported) {
		this.toBeSupported = toBeSupported;
	}
	abstract void accept(GSNPrintVisitor visitor);

}
