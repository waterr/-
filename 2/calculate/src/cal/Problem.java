package cal;

import java.util.Random;

public class Problem {

	public int getVar1() {
		return var1;
	}

	public void setVar1(int var1) {
		this.var1 = var1;
	}

	public int getVar2() {
		return var2;
	}

	public void setVar2(int var2) {
		this.var2 = var2;
	}

	public int getVar3() {
		return var3;
	}

	public void setVar3(int var3) {
		this.var3 = var3;
	}

	public char getOp1() {
		return op1;
	}

	public void setOp1(char op1) {
		this.op1 = op1;
	}

	public char getOp2() {
		return op2;
	}

	public void setOp2(char op2) {
		this.op2 = op2;
	}

 
 
	
	private int var1;
	private int var2;
	private int var3;
	private int vart;
	
	private char op1;
	private char op2;
	
	private int result;
	private String answer;
	
	public Problem() {
		 
		var1 = geneVar();
		var2 = geneVar();
		var3 = geneVar();
		op1 = geneOp();
		op2 = geneOp();
		setResult(calResult());
		setAnswer(toStr());
	}
	
	public String toStr() {
		StringBuilder sb =new StringBuilder();
		sb.append(String.valueOf(var1));
		sb.append(op1+"");
		sb.append(String.valueOf(var2));
		sb.append(op2+"");
		sb.append(String.valueOf(var3));
		sb.append("=");
		return sb.toString();		
	}

	public char geneOp() {
		char op = '+';
		Random rand = new Random();
		int tOp = rand.nextInt(4);
		switch(tOp) {
		case 0:
			op='+';
			break;
		case 1:
			op='-';
			break;
		case 2:
			op='*';
			break;
		case 3:
			op='/';
			break;
		}
		
		return op;
	}
	
	private int geneVar() {
		Random rand = new Random();
		int t = rand.nextInt(100);
		return t;
	}
	

	int calResult() {
	 
		if(op1=='+') {
			if(op2=='+') {
				while(var1+var2>100)
					var2 = geneVar();
				vart = var1 + var2;
				while(vart+var3>100) 
					var3 = geneVar();
				setResult(vart+var3);
			}
			if(op2=='-') {
				while(var1+var2>100)
					var2 = geneVar();
				vart = var1 + var2;
				while(vart-var3<0) 
					var3 = geneVar();
				setResult(vart-var3);
			}
			if(op2=='*') {
				while(var2*var3>100)
					var2 = geneVar()/10;
				vart = var2*var3;
				while(var1+vart>100)
					var1 = geneVar();
				setResult(var1+vart);
			}
			if(op2=='/') {
				while(var3==0) var3=geneVar();
				while(var2%var3!=0)  
					var3--;
			 
				vart = var2/var3;
				while(var1+vart>100) var1 = geneVar();
				setResult(var1+vart);
			}
			
		}
		if(op1=='-') {
			if(op2=='+') {
				while(var1-var2<0)
					var2 = geneVar();
				vart = var1 - var2;
				while(vart+var3>100) 
					var3 = geneVar();
				setResult(vart+var3);
			}
			if(op2=='-') {
				while(var1-var2<0)
					var2 = geneVar();
				vart = var1 - var2;
				while(vart-var3<0) 
					var3 = geneVar();
				setResult(vart-var3);
			}
			if(op2=='*') {
				while(var2*var3>100)
					var2 = geneVar()/10;
				vart = var2*var3;
				while(var1-vart<0)
					var1 = geneVar();
				setResult(var1-vart);
			}
			if(op2=='/') {
				while(var3==0) var3=geneVar();
				while(var2%var3!=0)  
					var3--;
				 
				vart = var2/var3;
				while(var1-vart<0) var1 = geneVar();
				setResult(var1-vart);
			}
			
		}
		if(op1=='*') {
			if(op2=='+') {
				while(var1*var2>100)
					var2 = geneVar()/10;
				vart = var1 * var2;
				while(vart+var3>100) 
					var3 = geneVar();
				setResult(vart+var3);
			}
			if(op2=='-') {
				while(var1*var2>100)
					var2 = geneVar()/10;
				vart = var1 * var2;
				while(vart-var3<0) 
					var3 = geneVar();
				setResult(vart-var3);
			}
			if(op2=='*') {
				while(var1*var2>100)
					var2 = geneVar()/10;
				vart = var1*var2;
				while(vart*var3>100)
					var3 = geneVar()/10;
				setResult(vart*var3);
			}
			if(op2=='/') {
				while(var1*var2>100) var2 = geneVar()/10;
				vart = var1*var2;
				while(var3==0) var3=geneVar();
				while(vart%var3!=0)  
					var3--;
				 
				setResult(vart/var3);
			}
			
		}
		if(op1=='/') {
			if(op2=='+') {
				while(var2==0) var2=geneVar();
				while(var1%var2!=0)
					var2--;
				vart = var1 / var2;
				while(vart+var3>100) 
					var3 = geneVar();
				setResult(vart+var3);
			}
			if(op2=='-') {
				while(var2==0) var2=geneVar();
				while(var1%var2!=0)
					var2--;
				vart = var1 / var2;
				while(vart-var3<0) 
					var3 = geneVar();
				setResult(vart-var3);
			}
			if(op2=='*') {
				while(var2==0) var2=geneVar();
				while(var1%var2!=0)
					var2--;
				vart = var1 / var2;
				while(vart*var3>100) 
					var3 = geneVar()/10;
				setResult(vart*var3);
			}
			if(op2=='/') {
				while(var2==0) var2=geneVar();
				while(var1%var2!=0)
					var2--;
				vart = var1 / var2;
				while(var3==0) var2=geneVar();
				while(vart%var3!=0) 
					var3--;
				setResult(vart/var3);
			}
		}
		return getResult();
	}
	 
	
	public void printP() {
		System.out.print(var1);
		System.out.print(op1);
		System.out.print(var2);
		System.out.print(op2);
		System.out.print(var3);
	//	System.out.println("="+result);
	
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

 
 
	
}
