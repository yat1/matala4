/**
 * a class that extends thread, it gets two numbers and sets max to be the bigger number
 * @author yehud
 *
 */
public class checkMax extends Thread{
	
	private int a,b,max;
	
	public checkMax ( int A, int B) {
		super();
		a=A;
		b=B;
	}
	
	public void run(){
		if (a>b) {
			max = a;
		}
		else {
			max = b;
		}
	}

	public int getMax() {
		return max;
	}
}
