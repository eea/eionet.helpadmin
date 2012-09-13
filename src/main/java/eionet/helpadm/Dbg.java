package eionet.helpadm;

public class Dbg {
	
	private static boolean enable = true;
	private static boolean initCalled = false;
	
	/**
	 * 
	 */
	public static void dbg(Object obj){
		if (enable){
			System.out.println("=============================================>");
			System.out.println(obj);
			System.out.println("=============================================>");
		}
	}
	
	/**
	 * 
	 */
	public static void dbg(Throwable t){
		if (enable){
			System.out.println("=============================================>");
			t.printStackTrace(System.out);
			System.out.println("=============================================>");
		}
	}
	
	private static void init(){
		if (initCalled) return;
		initCalled = true;
		String dbg = Props.getProperty(Props.DBG);
		if (dbg!=null && dbg.equals("true"))
			enable = true;
	}
}
