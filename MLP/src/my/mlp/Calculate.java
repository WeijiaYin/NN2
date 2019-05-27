package my.mlp;

public class Calculate {
	public static double[][] multiple(double[][] matrix1, double[][] matrix2)
	{
		double[][] re = new double[matrix1.length][matrix2[0].length];
		for(int i = 0; i < matrix1.length; i++) {
			for(int j = 0; j < matrix2[0].length; j++) {
				re[i][j] = 0;
				for(int k = 0; k < matrix1[0].length; k++)
				{
					re[i][j] += matrix1[i][k]*matrix2[k][j]; 
				}
			}
		}
			return re;
	}
	
	public static double[][] multipleLr(double[][] matrix, double lr)
	{
		double[][] re = new double[matrix.length][matrix[0].length];
		for(int i = 0; i < re.length; i++)
		{
			for(int j = 0; j < re[0].length; j++)
			{
				re[i][j] = matrix[i][j] * lr;
			}
		}
		return re;
	}
	
	public static double[][] oneMinus(double[][] matrix)
	{
		double[][] re = new double[matrix.length][matrix[0].length];
		for(int i = 0; i < re.length; i++)
		{
			for(int j = 0; j < re[0].length; j++)
			{
				re[i][j] = 1 - matrix[i][j];
			}
		}
		return re;
	}
	
	public static double[][] add(double[][] matrix1, double[][] matrix2)
	{
		double[][] re = new double[matrix1.length][matrix1[0].length];
		for(int i = 0; i < re.length; i++)
		{
			for(int j = 0; j < re[0].length; j++)
			{
				re[i][j] = matrix1[i][j] + matrix2[i][j];
			}
		}
		return re;
	}
	
	public static double[][] minus(double[][] matrix1, double[][] matrix2)
	{
		double[][] re = new double[matrix1.length][matrix1[0].length];
		for(int i = 0; i < re.length; i++)
		{
			for(int j = 0; j < re[0].length; j++)
			{
				re[i][j] = matrix1[i][j] - matrix2[i][j];
			}
		}
		return re;
	}
	
	public static double[][] transpose(double[][] matrix)
	{
		double[][] re = new double[matrix[0].length][matrix.length];
		for(int i = 0; i < re.length; i++) {
			for(int j = 0; j < re[0].length; j++)
			{
				re[i][j] = matrix[j][i];
			}
		}
		return re;
	}
	
	public static double sigmoid(double a)
	{
		double re = 1/(1+Math.exp(-a));
		return re;
	}
	
	public static double[][] sigmoidMatrix(double[][] matrix)
	{
		double[][] re = new double[matrix.length][matrix[0].length];
		for(int i = 0; i < matrix.length; i++)
		{
			for(int j = 0; j < matrix[0].length; j++)
			{
				re[i][j] = sigmoid(matrix[i][j]);
			}
		}
		return re;
	}
	
	public static double[][] sigmoid_derivative(double[][] matrix)
	{
		return multiple(matrix, oneMinus(matrix));
	}
	
	public static int getMax(double[][] guess)
	{
		double max = guess[0][0];
		int num = 0;
		for(int i = 1; i < guess.length; i++)
		{
			if(guess[i][0] > max) {
				max = guess[i][0];
				num = i;
			}
		}
		return num;
	}
	
}
