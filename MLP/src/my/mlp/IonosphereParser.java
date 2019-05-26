package my.mlp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class IonosphereParser {
	private int input;
	private List<String> output;
	private List<String> iono;
	
	public int getInput() {
		String[] in = iono.get(0).split(",");
		input = in.length - 1;
		return input;
	}
	
	public List<String> getOutput()
	{
		return output;
	}

	public IonosphereParser(String file) throws IOException
	{
		iono = new ArrayList<String>();
		String line;
		FileReader fileReader = new FileReader(file);
		BufferedReader br = new BufferedReader(fileReader);

		while ((line = br.readLine()) != null) {
			iono.add(line);
		}

		br.close();
		fileReader.close();
	}
	
	public List<HashMap<String, double[]>> getTraindata(){
		
		output = new ArrayList<String>();
		
		int num = (int) (iono.size() * 0.75);
		
		List<HashMap<String,double[]>> list = new ArrayList<HashMap<String, double[]>>();
		
		for(int i = 0; i < num; i++)
		{
			HashMap<String, double[]> hm = new HashMap<String, double[]>();
			String[] line = iono.get(i).split(",");
			if(!output.contains(line[0]))
				output.add(line[0]);
			double[] par = new double[line.length-1];
			for(int j = 0; j < line.length-1; j++)
			{
				par[j] = Double.valueOf(line[j]);
			}
			hm.put(line[line.length -1], par);
			list.add(hm);
		}
		
		return list;
	}
	
public List<HashMap<String, double[]>> getTestdata(){
		
		int num = (int) (iono.size() * 0.75);
		int start = iono.size() - num;
		
		List<HashMap<String,double[]>> list = new ArrayList<HashMap<String, double[]>>();
		
		for(int i = start; i < iono.size(); i++)
		{
			HashMap<String, double[]> hm = new HashMap<String, double[]>();
			String[] line = iono.get(i).split(",");
			double[] par = new double[line.length-1];
			for(int j = 0; j < line.length - 1; j++)
			{
				par[j] = Double.valueOf(line[j]);
			}
			hm.put(line[line.length - 1], par);
			list.add(hm);
		}
		
		return list;
	}
	
}
