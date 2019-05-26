package my.mlp;

public class Data {

	private String target;
	private double[][] data;
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public double[][] getData() {
		return data;
	}
	public void setData(double[] data) {
		double[][] d = new double[data.length][1];
		for(int i = 0; i < data.length; i++)
		{
			d[i][0] = data[i];
		}
		this.data = d;
	}
	
	
}
