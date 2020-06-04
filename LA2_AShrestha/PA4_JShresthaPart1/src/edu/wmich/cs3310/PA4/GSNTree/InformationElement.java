package edu.wmich.cs3310.PA4.GSNTree;

public abstract class InformationElement extends ArgumentElement {

	public InformationElement(String id, String description) {
		super(id, description);
		// TODO Auto-generated constructor stub
	}
	abstract void Accept(GSNPrintVisitor visitor);

}
