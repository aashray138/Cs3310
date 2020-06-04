package edu.wmich.cs3310.PA4.GSNTree;

public interface IVisitor_GSN {
	//method that performs a function when a goal is visited
	public Object visitGoal(Goal goal);
	
	// method that performs a function when an Assumption is visited
	public Object visitAssumption(Assumption assumption);
	
	         // method that performs a function when a Justification is visited
	public Object visitJustification(Justification justification);
	
	 // method that performs a function when a Justification is visited
	public Object visitStrategy(Strategy strategy);
	
	// method that performs a function when a Context is visited
	public Object visitContext(Context context);
	
	// method that performs a function when a Solution is visited
	public Object visitSolution(Solution solution);
	
	// method that performs a function when a SupportedBy is visited
	public Object visitSupportedBy(SupportedBy supportedBy);
	
	// method that performs a function when a InContextOf is visited
	public Object visitInContextOf(InContextOf inContextOf);


}
