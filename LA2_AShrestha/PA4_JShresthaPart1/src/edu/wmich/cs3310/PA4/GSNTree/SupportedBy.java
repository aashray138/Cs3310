package edu.wmich.cs3310.PA4.GSNTree;

public class SupportedBy extends AssertedRelationship{

	public SupportedBy(String id, String description,boolean mul, boolean opt) {
		super(id, description,mul,opt);
		// TODO Auto-generated constructor stub
	}

	@Override
	void Accept(GSNPrintVisitor visitor) {
		// TODO Auto-generated method stub
		visitor.visitSupportedBy(this);
	}

}
