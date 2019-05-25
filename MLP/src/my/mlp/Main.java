package my.mlp;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class Main {
	
	public static void main(String args[]) throws IOException
	{
		List<String> wine;
		List<HashMap<String, float[]>> train;
		List<HashMap<String, float[]>> test;
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
		System.out.println(b);
	}
}
