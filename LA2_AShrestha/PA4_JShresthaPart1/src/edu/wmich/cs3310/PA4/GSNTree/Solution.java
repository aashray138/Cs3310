package edu.wmich.cs3310.PA4.GSNTree;

public class Solution extends InformationElement {

	public Solution(String id, String description) {
		super(id, description);
		// TODO Auto-generated constructor stub
	}

	@Override
	void Accept(GSNPrintVisitor visitor) {
		// TODO Auto-generated method stub
		visitor.visitSolution(this);
	}

}
