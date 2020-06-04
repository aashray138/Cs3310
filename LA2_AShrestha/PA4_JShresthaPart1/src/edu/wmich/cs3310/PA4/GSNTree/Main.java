package edu.wmich.cs3310.PA4.GSNTree;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//construct Java GSN pattern trees based on input files
		Goal g1= new Goal("G1","Program1 has been plausibly patched",false,false);
		GSNPrintVisitor visitor= new GSNPrintVisitor();
		g1.accept(visitor);
		System.out.println(visitor.visitGoal(g1));
		
		SupportedBy sb= new SupportedBy(null,null,false, false);
		sb.setTarget("G1");
		sb.setSource("G2");
		sb.Accept(visitor);
		System.out.println("Goal "+visitor.visitSupportedBy(sb));
		/*--------------------------------------------------------------------------------------*/
		Goal g2= new Goal("G2","patch{P} for Program1 has passed all test cases",false,false) ;
		g2.accept(visitor);
		System.out.println(visitor.visitGoal(g2));
		Strategy s1= new Strategy("S1","Consider each test case applied to {P}") ;
		InContextOf con= new InContextOf(null, null,false, false);
		con.setTarget("G2");
		con.setSource("C1");
		con.Accept(visitor);
		System.out.println("Goal "+visitor.visitInContextOf(con));
		sb.setTarget("G2");
		sb.setSource("S1");
		/*--------------------------------------------------------------------------------------*/
		Context c1= new Context("C1", "{P} is an applied autorepair patch");
		c1.Accept(visitor);
		System.out.println(visitor.visitContext(c1));
		System.out.println("Goal "+visitor.visitSupportedBy(sb));
		System.out.println(visitor.visitStrategy(s1));
		SupportedBy sb1= new SupportedBy(null,null,true, false);
		sb1.setSource("G3");
		sb1.setTarget("S1");
		System.out.println("Strategy "+visitor.visitSupportedBy(sb1));
		/*--------------------------------------------------------------------------------------*/
		Goal g3= new Goal("G3","test case {t} has been passed ",false,false) ;
		g3.accept(visitor);
		System.out.print(visitor.visitGoal(g3));
		con.setTarget("G3");
		con.setSource("C2");
		System.out.println("Goal "+visitor.visitInContextOf(con));
		sb.setTarget("G3");
		sb.setSource("Sn1");
		/*--------------------------------------------------------------------------------------*/
		Context c2= new Context("C2", "{t} is a test case applied on "
				+ "the program attempted to be repaired");
		System.out.println(visitor.visitContext(c2));
		System.out.println("Goal "+visitor.visitSupportedBy(sb));
		Solution sn1= new Solution("Sn1", "Open project directory to Program1 "
				+ "and find results of test oracle, stating {t} has been passed");
		sn1.Accept(visitor);
		System.out.println(visitor.visitSolution(sn1));
		/*--------------------------------------------------------------------------------------*/
		/*---------------------------------Tree 2-----------------------------------------------*/
		System.out.println("------------------------------------------------------------------------------------------------------");
		System.out.println();
		Goal go1=new Goal("G1", "Software system is well designed.", false, false);
		go1.accept(visitor);
		System.out.println(visitor.visitGoal(go1));
		InContextOf inc1= new InContextOf(null, null, false, false);
		inc1.setSource("J1");
		inc1.setTarget("G1");
		System.out.println("Goal "+visitor.visitInContextOf(inc1));
		/*--------------------------------------------------------------------------------------*/
		SupportedBy sb2= new SupportedBy(null,null,false, false);
		Justification j1= new Justification("J1", "well designed is based on def 1", false, false);
		j1.accept(visitor);
		System.out.println(visitor.visitJustification(j1));
		sb2.setSource("S1");
		sb2.setTarget("G1");
		System.out.println("Goal "+visitor.visitSupportedBy(sb2));
		Strategy sr1 = new Strategy("S1", "based on two important phases");
		sr1.accept(visitor);
		System.out.println(visitor.visitStrategy(sr1));
		sb2.setSource("G2");
		sb2.setTarget("S1");
		System.out.println("Strategy "+visitor.visitSupportedBy(sb2));
		sb2.setSource("G3");
		sb2.setTarget("S1");
		System.out.println("Strategy "+visitor.visitSupportedBy(sb2));
		/*--------------------------------------------------------------------------------------*/
		Goal go2=new Goal("G2", "All hazards are identified", false, false);
		go2.accept(visitor);
		System.out.println(visitor.visitGoal(go2));
		
		Solution so1=new Solution("Sn1", "hazard report");
		so1.Accept(visitor);
		System.out.println(visitor.visitSolution(so1));
		sb2.setSource("Sn1");
		sb2.setTarget("G2");
		System.out.println("Goal "+visitor.visitSupportedBy(sb2));
		/*--------------------------------------------------------------------------------------*/
		Goal go3=new Goal("G3", "All requirements are correctly considered", false, false);
		go3.accept(visitor);
		System.out.println(visitor.visitGoal(go3));
		sb2.setSource("G4");
		sb2.setTarget("G3");
		System.out.println("Goal "+visitor.visitSupportedBy(sb2));
		
		sb2.setSource("G5");
		sb2.setTarget("G3");
		System.out.println("Goal "+visitor.visitSupportedBy(sb2));
		/*--------------------------------------------------------------------------------------*/
		Goal go4=new Goal("G4", "All Hardware requirements are correctly considered", false, false);
		go4.accept(visitor);
		System.out.println(visitor.visitGoal(go4));
		sb2.setSource("Sn2");
		sb2.setTarget("G4");
		System.out.println("Goal "+visitor.visitSupportedBy(sb2));
		/*--------------------------------------------------------------------------------------*/
		Goal go5=new Goal("G5", "All Software requirements are correctly considered", false, false);
		go5.accept(visitor);
		System.out.println(visitor.visitGoal(go5));
		sb2.setSource("Sn3");
		sb2.setTarget("G5");
		System.out.println("Goal "+visitor.visitSupportedBy(sb2));
		
		Solution so2=new Solution("Sn2", "hardware requirements report");
		so2.Accept(visitor);
		System.out.println(visitor.visitSolution(so2));
		Solution so3=new Solution("Sn3", "software requirements report");
		so3.Accept(visitor);
		System.out.println(visitor.visitSolution(so3));
		
		
	}

}
