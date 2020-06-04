package edu.wmich.cs3310.PA4.GSNTree;

public class GSNPrintVisitor implements IVisitor_GSN {
	
	@Override
	public Object visitGoal(Goal goal) {
		// TODO Auto-generated method stub
		String id=goal.getId();
		String description=goal.getDescription();
		boolean assumed=goal.isAssumed();
		boolean supported=goal.isToBeSupported();
		String string=String.format("Goal %s: \n \ncontent: %s \ntoBeSupported: "
				+ "%s\nAssumed: %s \n", id,description,supported,assumed);
		return string;
		
	}

	@Override
	public Object visitAssumption(Assumption assumption) {
		String id=assumption.getId();
		String description=assumption.getDescription();
		boolean assumed=assumption.isAssumed();
		boolean supported=assumption.isToBeSupported();
		String string=String.format("Assumption %s: \n \ncontent: %s \ntoBeSupported: "
				+ "%s\nAssumed: %s \n", id,description,supported,assumed);
		return string;
		
	}

	@Override
	public Object visitJustification(Justification justification) {
		String id=justification.getId();
		String description=justification.getDescription();
		boolean assumed=justification.isAssumed();
		boolean supported=justification.isToBeSupported();
		String string=String.format("Justification %s: \n \ncontent: %s \ntoBeSupported: "
				+ "%s\nAssumed: %s \n", id,description,supported,assumed);
		return string;
	}

	@Override
	public Object visitStrategy(Strategy strategy) {
		String id=strategy.getId();
		String description=strategy.getDescription();
		String string=String.format("Strategy %s: \n \ncontent: %s \n", id,description);
		return string;
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object visitContext(Context context) {
		String id=context.getId();
		String description=context.getDescription();
		String string=String.format("Context %s: \n \ncontent: %s \n", id,description);
		return string;
		
	}

	@Override
	public Object visitSolution(Solution solution) {
		String id=solution.getId();
		String description=solution.getDescription();
		String str=String.format("Solution %s: \n \ncontent: %s \n ", id,description);
		return str;
		
	}

	@Override
	public Object visitSupportedBy(SupportedBy supportedBy)
	{
		// TODO Auto-generated method stub
		String id=supportedBy.getId();
		String description=supportedBy.getDescription();
		String source=supportedBy.getSource();
		String target=supportedBy.getTarget();
		boolean mul=supportedBy.isMultiplicity();
		boolean opt=supportedBy.isOptionality();
		return String.format("%s is supported by %s \nSupportedBy link between "
				+ "source %s and target %s \nMultiplicity: %s \nOptionality: %s \n ",target,source, source,target,mul,opt);
		
	}

	@Override
	public Object visitInContextOf(InContextOf inContextOf) {
		// TODO Auto-generated method stub
		String id=inContextOf.getId();
		String description=inContextOf.getDescription();
		String source=inContextOf.getSource();
		String target=inContextOf.getTarget();
		boolean mul=inContextOf.isMultiplicity();
		boolean opt=inContextOf.isOptionality();
		String context=String.format("%s is in context of %s \n \nInContextOf link between "
				+ "source %s and target %s \nMultiplicity: %s \nOptionality: %s \n", target,source, source,target,mul,opt);
		return context;
		
		
	}

}
