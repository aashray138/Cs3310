package edu.wmich.cs3310.PA4.GSNTree;

public abstract class AssertedRelationship extends ArgumentElement{
	private boolean multiplicity;
	private boolean optionality;
	private String source;
	private String target;
	
	public AssertedRelationship(String id, String description,boolean mul, boolean opt) {
		super(id, description);
		setOptionality(opt);
		setMultiplicity(mul);
		
	}
	
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public boolean isMultiplicity() {
		return multiplicity;
	}
	public void setMultiplicity(boolean multiplicity) {
		this.multiplicity = multiplicity;
	}
	public boolean isOptionality() {
		return optionality;
	}
	public void setOptionality(boolean optionality) {
		this.optionality = optionality;
	}
	abstract void Accept(GSNPrintVisitor visitor);

}
