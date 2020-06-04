package edu.wmich.cs3310.PA4.GSNTree;

public class Context extends InformationElement {

	public Context(String id, String description) {
		super(id, description);
		// TODO Auto-generated constructor stub
	}

	
	@Override
	void Accept(GSNPrintVisitor visitor) {
		// TODO Auto-generated method stub
		visitor.visitContext(this);
		
	}



	

}
