package edu.wmich.cs3310.PA4.GSNTree;

public class Strategy extends ArgumentElement {
	private String describedInferences;
	

	public Strategy(String id, String description) {
		super(id, description);
		// TODO Auto-generated constructor stub
	}

	public void accept(GSNPrintVisitor visitor) {
		visitor.visitStrategy(this);
	}

}
