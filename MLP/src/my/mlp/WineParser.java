package my.mlp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
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
	
	public List<Data> getTraindata(){
		
		output = new ArrayList<String>();
		
		int num = (int) (wine.size() * 0.75);
		
		List<Data> list = new ArrayList<Data>();
		
		for(int i = 0; i < num; i++)
		{
			Data d = new Data();
			String[] line = wine.get(i).split(",");
			if(!output.contains(line[0]))
				output.add(line[0]);
			double[] par = new double[line.length-1];
			for(int j = 1; j < line.length; j++)
			{
				par[j-1] = Double.valueOf(line[j]);
			}
			
			d.setData(par);
			d.setTarget(line[0]);
			list.add(d);
		}
		
		return list;
	}
	
public List<Data> getTestdata(){
		
		int num = (int) (wine.size() * 0.5);
		int start = num + 1;
		int end = (int) (wine.size() * 0.75);
		
		List<Data> list = new ArrayList<Data>();
		
		for(int i = start; i < end; i++)
		{
			Data d = new Data();
			String[] line = wine.get(i).split(",");
			double[] par = new double[line.length-1];
			for(int j = 1; j < line.length; j++)
			{
				par[j-1] = Double.valueOf(line[j]);
			}
			d.setData(par);
			d.setTarget(line[0]);
			list.add(d);
		}
		
		return list;
	}

public List<Data> getValuedata(){
	
	int num = (int) (wine.size() * 0.75);
	int start = num + 1;
	
	List<Data> list = new ArrayList<Data>();
	
	for(int i = start; i < wine.size(); i++)
	{
		Data d = new Data();
		String[] line = wine.get(i).split(",");
		double[] par = new double[line.length-1];
		for(int j = 1; j < line.length; j++)
		{
			par[j-1] = Double.valueOf(line[j]);
		}
		d.setData(par);
		d.setTarget(line[0]);
		list.add(d);
	}
	
	return list;
}
}
