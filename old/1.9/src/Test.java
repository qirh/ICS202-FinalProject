public class Test {
	
	public static void main(String[] args){
		try{
			Tree t = new Tree("C:\\Users\\sal7\\Desktop\\test1");
//			prn(t.printBFT().trim());
		}
		catch(Exception m){
			prn("Catch " + m.getMessage());
			error(m);
		}
	}
	
	public static void pr(String s){		//w/o \n
		System.out.print(s);
	}
	public static void prn(String s){
		System.out.println(s);
	}
	
	public static void error(Exception e){
		prn("Test. error");
		if(e.getClass().getName().equals("GreaterThanMaxException"))
			prn("Greater Error, " + e.getMessage() + ". Program will Exit");
		
		else if(e.getClass().getName().equals("FileNotFoundException"))
			prn("Not Found Error, " + e.getMessage() + ". Program will Exit");
		
		else if(e.getClass().getName().equals("Exception"))
			prn("Unknown Error, Message: " + e.getMessage() + ". Program will Exit");
		
		else{
			prn("Unreachable " + e.getMessage());
			e.printStackTrace();
		}
	}
}
