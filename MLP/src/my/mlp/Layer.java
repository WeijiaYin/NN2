package my.mlp;

import java.util.Random;

public class Layer {
	
	private double[][] b;
	private double[][] input;
	private double[][] weight;
	
	public Layer(int inputNum, int outputNum)
	{
		b = new double[outputNum][1];
		for(int i = 0; i < outputNum; i++)
		{
			b[i][0] = 1;
		}
		Random rd = new Random();
		double min = (-4)*Math.sqrt(6)/Math.sqrt(inputNum+outputNum);
		double max = 4*Math.sqrt(6)/Math.sqrt(inputNum+outputNum);
		weight = new double[outputNum][inputNum];
		for(int i = 0; i < outputNum; i++)
		{
			for(int j = 0; j < inputNum; j++)
			{
				weight[i][j] = min+rd.nextDouble()*(max-min);
			}
		}
	}
	
	public double[][] getB() {
		return b;
	}
	public void setB(double[][] b) {
		this.b = b;
	}
	public double[][] getInput() {
		return input;
	}
	public void setInput(double[][] input) {
		this.input = input;
	}
	public double[][] getWeight() {
		return weight;
	}
	public void setWeight(double[][] weight) {
		this.weight = weight;
	}
	public double[][] getOutput() {
		return Calculate.sigmoidMatrix(Calculate.add(Calculate.multiple(weight, input), b));
	}

}
