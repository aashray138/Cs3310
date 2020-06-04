package edu.wmich.cs3310.PA4.GSNTree;

public class Goal extends Claim{

	public Goal(String id, String description, boolean assumed, boolean toBeSupported) {
		// TODO Auto-generated constructor stub
		super(id,description,assumed,toBeSupported);
		
	}

	
	@Override
	void accept(GSNPrintVisitor visitor) {
		// TODO Auto-generated method stub
		visitor.visitGoal(this);
	}

}
