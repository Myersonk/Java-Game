package ri.game2;

public class Time {
	
	//HI KYLE
	
	private static double delta;
	public static final long SECOND = 1000000000L;
	
	
	public static long getTimeNano(){
		return System.nanoTime();
		
	}
	
	public static double getDelta(){
		return delta;
	}
	
	public static void setDelta(double d){
		delta = d;
		
	}
	public static double getTimeSec(){
		return ((double)Time.getTimeNano())/((double)Time.SECOND);
	
	}


}
