package my.mlp;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class Main {
	
	public static void main(String args[]) throws IOException
	{
		List<String> wine;
		List<HashMap<String, double[]>> train;
		List<HashMap<String, double[]>> test;
		IonosphereParser wp = new IonosphereParser("ionosphere.data");
		train = wp.getTraindata();
		test = wp.getTestdata();
		int a = wp.getOutput().size();
		List<String> aa = wp.getOutput();
		for(int i = 0; i < 2; i++)
		{
			System.out.println(aa.get(i));
		}
		System.out.println("oooooooooooooooooooooooooout");
		System.out.println(a);
		int b = wp.getInput();
		System.out.println("Innnnnnnnnnnnnnnnnnnnnnnnnnn");
//		System.out.println(b);
//		double[][] a = {{1,2},{2,3},{5,6}};
//		double[][] b = {{1,2,3},{2,3,4}};
//		double[][] c = Calculate.addB(a, 1);
//		for(int i = 0; i < c.length; i++)
//		{
//			for(int j = 0; j < c[0].length; j++)
//			{
//				System.out.println(c[i][j]);
//			}
//			System.out.println();
//		}
	}
}
