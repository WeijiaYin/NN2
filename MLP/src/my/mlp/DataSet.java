package my.mlp;

import java.io.IOException;
import java.util.List;

public class DataSet {

	private List<Data> trainSet;
	private List<Data> testSet;
	private List<String> outputClass;
	private int inputNum;
	private int outputNum;
	
	public List<Data> getTrainSet() {
		return trainSet;
	}

	public List<Data> getTestSet() {
		return testSet;
	}

	public List<String> getOutputClass() {
		return outputClass;
	}

	public int getInputNum() {
		return inputNum;
	}

	public int getOutputNum() {
		return outputNum;
	}

	public DataSet(String dataSet) throws IOException
	{
		switch(dataSet) {
		case "agaricus-lepiota.data":
			MushroomParser mr = new MushroomParser(dataSet);
			trainSet = mr.getTraindata();
			testSet = mr.getTestdata();
			outputClass = mr.getOutput();
			outputNum = outputClass.size();
			inputNum = mr.getInput();
			break;
		case "wine.data":
			WineParser w = new WineParser(dataSet);
			trainSet = w.getTraindata();
			testSet = w.getTestdata();
			outputClass = w.getOutput();
			outputNum = outputClass.size();
			inputNum = w.getInput();
			break;
		case "ionosphere.data":
			IonosphereParser iono = new IonosphereParser(dataSet);
			trainSet = iono.getTraindata();
			testSet = iono.getTestdata();
			outputClass = iono.getOutput();
			outputNum = outputClass.size();
			inputNum = iono.getInput();
			break;
		default:
			System.out.println("Wrong filename");
		}
	}
	
}
