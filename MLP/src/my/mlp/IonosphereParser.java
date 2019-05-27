package my.mlp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
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
	
	public List<Data> getTraindata(){
		
		output = new ArrayList<String>();
		
		int num = (int) (iono.size() * 0.75);
		
		List<Data> list = new ArrayList<Data>();
		
		for(int i = 0; i < num; i++)
		{
			Data d = new Data();
			String[] line = iono.get(i).split(",");
			if(!output.contains(line[line.length-1]))
				output.add(line[line.length-1]);
			double[] par = new double[line.length-1];
			for(int j = 0; j < line.length-1; j++)
			{
				par[j] = Double.valueOf(line[j]);
			}
			d.setData(par);
			d.setTarget(line[line.length-1]);
			list.add(d);
		}
		
		return list;
	}
	
public List<Data> getTestdata(){
		
		int num = (int) (iono.size() * 0.75);
		int start = num + 1;
		
		List<Data> list = new ArrayList<Data>();
		
		for(int i = start; i < iono.size(); i++)
		{
			Data d = new Data();
			String[] line = iono.get(i).split(",");
			double[] par = new double[line.length-1];
			for(int j = 0; j < line.length - 1; j++)
			{
				par[j] = Double.valueOf(line[j]);
			}
			d.setData(par);
			d.setTarget(line[line.length - 1]);
			list.add(d);
		}
		
		return list;
	}
	
}
