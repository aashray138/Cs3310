package edu.wmich.cs3310.PA4.GSNTree;

public class Assumption extends Claim{

	public Assumption(String id, String description, boolean assumed, boolean toBeSupported) {
		super(id, description, assumed, toBeSupported);
		// TODO Auto-generated constructor stub
	}

	@Override
	void accept(GSNPrintVisitor visitor) {
		// TODO Auto-generated method stub
		visitor.visitAssumption(this);
	}

}
