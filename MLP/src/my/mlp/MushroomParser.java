package my.mlp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MushroomParser {

	private int input;
	private List<String> output;
	private List<String> mushroom;
	
	public int getInput() {
		String[] in = mushroom.get(0).split(",");
		input = in.length - 1;
		return input;
	}
	
	public List<String> getOutput()
	{
		return output;
	}

	public MushroomParser(String file) throws IOException
	{
		mushroom = new ArrayList<String>();
		String line;
		FileReader fileReader = new FileReader(file);
		BufferedReader br = new BufferedReader(fileReader);

		while ((line = br.readLine()) != null) {
			mushroom.add(line);
		}

		br.close();
		fileReader.close();
	}
	
	public List<Data> getTraindata(){
		
		output = new ArrayList<String>();
		
		int num = (int) (mushroom.size() * 0.75);
		
		List<Data> list = new ArrayList<Data>();
		
		for(int i = 0; i < num; i++)
		{
			Data d = new Data();
			String[] line = mushroom.get(i).split(",");
			if(!output.contains(line[0]))
				output.add(line[0]);
			double[] par = new double[line.length-1];
			for(int j = 1; j < line.length; j++)
			{
				if(line[j].equals("?"))
					par[j-1] = 0;
				else
					par[j-1] = Double.valueOf(line[j].getBytes()[0]-'a') + 1.0;
			}
			d.setData(par);
			d.setTarget(line[0]);
			list.add(d);
		}
		
		return list;
	}
	
public List<Data> getTestdata(){
		
		int num = (int) (mushroom.size() * 0.75);
		int start = num + 1;
		
		List<Data> list = new ArrayList<Data>();
		
		for(int i = start; i < mushroom.size(); i++)
		{
			Data d = new Data();
			String[] line = mushroom.get(i).split(",");
			double[] par = new double[line.length-1];
			for(int j = 1; j < line.length; j++)
			{
				par[j-1] = Double.valueOf(line[j].getBytes()[0]-'a');
			}
			d.setTarget(line[0]);
			d.setData(par);
			list.add(d);
		}
		
		return list;
	}
	
}
