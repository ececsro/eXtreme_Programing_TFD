package equationSystem;

public class X {
	private int[] t;
	private int k;
	public X(int[] t, int k){
		this.t = t;
		this.k = k;
	}
	public void mal(){
		boolean enc = false;
		int i=0;
		while (!enc && i<t.length){
			if (t[i]==k){
				System.out.println("Está en "+ i);
				enc = true;
			}
			i++;
		}
		if (!enc){
			System.out.println("No está");
		}
	}
	public void bien(){
		int i=0;
		while (t[i]!=k && i<t.length-1){
			i++;
		}
		if (t[i]==k){
			System.out.println("Está en "+ i);
		} else {
			System.out.println("No está");
		}
	}
	
	public static void main(String[] arg){
		new X(new int[]{3,5,2,6,1}, 9).mal();
		new X(new int[]{3,5,2,6,1}, 9).bien();
	}
}
