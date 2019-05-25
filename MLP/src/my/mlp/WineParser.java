package my.mlp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class WineParser {
	
	private int input;
	private List<String> output;
	private List<String> wine;
	
	public int getInput() {
		String[] in = wine.get(0).split(",");
		input = in.length - 1;
		return input;
	}
	
	public List<String> getOutput()
	{
		return output;
	}

	public WineParser(String file) throws IOException
	{
		wine = new ArrayList<String>();
		String line;
		FileReader fileReader = new FileReader(file);
		BufferedReader br = new BufferedReader(fileReader);

		while ((line = br.readLine()) != null) {
			wine.add(line);
		}
		Collections.shuffle(wine);


		br.close();
		fileReader.close();
	}
	
	public List<HashMap<String, float[]>> getTraindata(){
		
		output = new ArrayList<String>();
		
		int num = (int) (wine.size() * 0.75);
		
		List<HashMap<String,float[]>> list = new ArrayList<HashMap<String, float[]>>();
		
		for(int i = 0; i < num; i++)
		{
			HashMap<String, float[]> hm = new HashMap<String, float[]>();
			String[] line = wine.get(i).split(",");
			if(!output.contains(line[0]))
				output.add(line[0]);
			float[] par = new float[line.length-1];
			for(int j = 1; j < line.length; j++)
			{
				par[j-1] = Float.valueOf(line[j]);
			}
			hm.put(line[0], par);
			list.add(hm);
		}
		
		return list;
	}
	
public List<HashMap<String, float[]>> getTestdata(){
		
		int num = (int) (wine.size() * 0.75);
		int start = wine.size() - num;
		
		List<HashMap<String,float[]>> list = new ArrayList<HashMap<String, float[]>>();
		
		for(int i = start; i < wine.size(); i++)
		{
			HashMap<String, float[]> hm = new HashMap<String, float[]>();
			String[] line = wine.get(i).split(",");
			float[] par = new float[line.length-1];
			for(int j = 1; j < line.length; j++)
			{
				par[j-1] = Float.valueOf(line[j]);
			}
			hm.put(line[0], par);
			list.add(hm);
		}
		
		return list;
	}
}
