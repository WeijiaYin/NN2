package my.mlp;

import java.io.IOException;
import java.util.List;

import org.jfree.data.xy.XYSeries;

public class MLP {

	private DataSet ds;
	private List<String> classes;
	private Layer inputLayer;
	private Layer hiddenLayer;
	private Layer inputLayer1;
	private Layer hiddenLayer1;
	private Layer hiddenLayer2;
	private XYSeries mSeriesTrain;
	
	public MLP(String fileName) throws IOException {
		ds = new DataSet(fileName);
		classes = ds.getOutputClass();
		mSeriesTrain = new XYSeries("train");
	}
	
	public XYSeries getmSeriesTrain() {
		return mSeriesTrain;
	}
	
	public double test1H()
	{
		List<Data> test = ds.getTestSet();
		int sum = 0;
		for(int i = 0; i < test.size(); i++)
		{
			sum += test1hdata(test.get(i)); 
		}
		System.out.println("test rate");
		double rate = (double)sum/test.size();
		System.out.println(rate);
		return rate;
	}
	
	public double test2H()
	{
		List<Data> test = ds.getTestSet();
		int sum = 0;
		for(int i = 0; i < test.size(); i++)
		{
			sum += test2hdata(test.get(i)); 
		}
		System.out.println("test rate");
		double rate = (double)sum/test.size();
		System.out.println(rate);
		return rate;
	}
	
	public void train1H(int hiddenNum)
	{
		inputLayer = new Layer(ds.getInputNum(), hiddenNum);
		hiddenLayer = new Layer(hiddenNum, ds.getOutputNum());
		List<Data> train = ds.getTrainSet();
		for(int i = 0; i < train.size(); i++)
		{
			int t = 0;
			while(true) {
				double[][] err = feedForward1(inputLayer, hiddenLayer, train.get(i), i, t);
				t = 1;
				if(err != null)
				{
					backProp1(inputLayer, hiddenLayer, err, i);
				}else {
					break;
				}
			}
		}
	}
	
	public void train2H(int hidden1Num, int hidden2Num) {
		inputLayer1 = new Layer(ds.getInputNum(), hidden1Num);
		hiddenLayer1 = new Layer(hidden1Num, hidden2Num);
		hiddenLayer2 = new Layer(hidden2Num, ds.getOutputNum());
		List<Data> train = ds.getTrainSet();
		for(int i = 0; i < train.size(); i++)
		{
			int t = 0;
			while(true) {
				double[][] err = feedForward2(inputLayer1, hiddenLayer1, hiddenLayer2, train.get(i), i, t);
				t = 1;
				if(err != null)
				{
					backProp2(inputLayer1, hiddenLayer1, hiddenLayer2, err, i);
				}else {
					break;
				}
			}
		}
	}
	
	public double[][] feedForward1(Layer inputLayer, Layer hiddenLayer, Data data, int time, int p)
	{
		inputLayer.setInput(data.getData());
		hiddenLayer.setInput(inputLayer.getOutput());
		double[][] guess = hiddenLayer.getOutput();
		double[][] targets = new double[classes.size()][1];
		int t = 0;
		for(int i = 0; i < classes.size(); i++)
		{
			if(!classes.get(i).equals(data.getTarget()))
				targets[i][0] = 0;
			else {
				targets[i][0] = 1;
				t = i;
			}
		}
		
		double[][] error = Calculate.minus(targets, guess);
		if(p == 0) {
			System.out.println("error");
			System.out.println(error[t][0]);
			System.out.println(time);
			mSeriesTrain.add((double)time, error[t][0]);
		}
		if(error[t][0] > 0.25)
		{
			return error;
		}
		else
			return null;
		
	}
	
	
	public double[][] feedForward2(Layer inputLayer1, Layer hiddenLayer1, Layer hiddenLayer2, Data data, int time, int p)
	{
		inputLayer1.setInput(data.getData());
		hiddenLayer1.setInput(inputLayer1.getOutput());
		hiddenLayer2.setInput(hiddenLayer1.getOutput());
		double[][] guess = hiddenLayer2.getOutput();
		double[][] targets = new double[classes.size()][1];
		int t = 0;
		for(int i = 0; i < classes.size(); i++)
		{
			if(!classes.get(i).equals(data.getTarget()))
				targets[i][0] = 0;
			else {
				targets[i][0] = 1;
				t = i;
			}
		}
		
		double[][] error = Calculate.minus(targets, guess);
		if(p == 0) {
			System.out.println("error");
			System.out.println(error[t][0]);
			System.out.println(time);
			mSeriesTrain.add((double)time, error[t][0]);
		}
		if(error[t][0] > 0.25)
		{
			return error;
		}
		else
			return null;
		
	}
	
	public void backProp1(Layer inputLayer, Layer hiddenLayer, double[][] error, int time)
	{
		double lr = 0.01/(1+0.001*time);
		double[][] deltawh = Calculate.multiple(Calculate.multiple(Calculate.multipleLr(error, lr), Calculate.sigmoid_derivative(hiddenLayer.getOutput())), Calculate.transpose(hiddenLayer.getInput()));
		double[][] wh = Calculate.add(hiddenLayer.getWeight(), deltawh);
		hiddenLayer.setWeight(wh);
		
		double[][] deltabh = Calculate.multiple(Calculate.multipleLr(error, lr), Calculate.sigmoid_derivative(hiddenLayer.getOutput()));
		double[][] bh = Calculate.add(hiddenLayer.getB(), deltabh);
		hiddenLayer.setB(bh);
		
		double[][] herror = Calculate.multiple(Calculate.transpose(hiddenLayer.getWeight()), error);
		double[][] deltawi = Calculate.multiple(Calculate.multiple(Calculate.multipleLr(herror, lr), Calculate.sigmoid_derivative(inputLayer.getOutput())), Calculate.transpose(inputLayer.getInput()));
		double[][] wi = Calculate.add(inputLayer.getWeight(), deltawi);
		inputLayer.setWeight(wi);
		
		double[][] deltabi = Calculate.multiple(Calculate.multipleLr(herror, lr), Calculate.sigmoid_derivative(inputLayer.getOutput()));
		double[][] bi = Calculate.add(inputLayer.getB(), deltabi);
		inputLayer.setB(bi);
	}
	
	public void backProp2(Layer inputLayer1, Layer hiddenLayer1, Layer hiddenLayer2, double[][] error, int time)
	{
		double lr = 0.01/(1+0.001*time);
		double[][] deltawh2 = Calculate.multiple(Calculate.multiple(Calculate.multipleLr(error, lr), Calculate.sigmoid_derivative(hiddenLayer2.getOutput())), Calculate.transpose(hiddenLayer2.getInput()));
		double[][] wh2 = Calculate.add(hiddenLayer2.getWeight(), deltawh2);
		hiddenLayer2.setWeight(wh2);
		
		double[][] deltabh2 = Calculate.multiple(Calculate.multipleLr(error, lr), Calculate.sigmoid_derivative(hiddenLayer2.getOutput()));
		double[][] bh2 = Calculate.add(hiddenLayer2.getB(), deltabh2);
		hiddenLayer2.setB(bh2);
		
		double[][] h2error = Calculate.multiple(Calculate.transpose(hiddenLayer2.getWeight()), error);
		double[][] deltawh1 = Calculate.multiple(Calculate.multiple(Calculate.multipleLr(h2error, lr), Calculate.sigmoid_derivative(hiddenLayer1.getOutput())), Calculate.transpose(hiddenLayer1.getInput()));
		double[][] wh1 = Calculate.add(hiddenLayer1.getWeight(), deltawh1);
		hiddenLayer1.setWeight(wh1);
		
		double[][] deltabh1 = Calculate.multiple(Calculate.multipleLr(h2error, lr), Calculate.sigmoid_derivative(hiddenLayer1.getOutput()));
		double[][] bh1 = Calculate.add(hiddenLayer1.getB(), deltabh1);
		hiddenLayer1.setB(bh1);
		
		double[][] h1error = Calculate.multiple(Calculate.transpose(hiddenLayer1.getWeight()), h2error);
		double[][] deltawi = Calculate.multiple(Calculate.multiple(Calculate.multipleLr(h1error, lr), Calculate.sigmoid_derivative(inputLayer1.getOutput())), Calculate.transpose(inputLayer1.getInput()));
		double[][] wi = Calculate.add(inputLayer1.getWeight(), deltawi);
		inputLayer1.setWeight(wi);
		
		double[][] deltabi = Calculate.multiple(Calculate.multipleLr(h1error, lr), Calculate.sigmoid_derivative(inputLayer1.getOutput()));
		double[][] bi = Calculate.add(inputLayer1.getB(), deltabi);
		inputLayer1.setB(bi);
	}
	
	public int test1hdata(Data data)
	{
		inputLayer.setInput(data.getData());
		hiddenLayer.setInput(inputLayer.getOutput());
		double[][] guess = hiddenLayer.getOutput();
		int num = Calculate.getMax(guess);
		if(classes.get(num).equals(data.getTarget())) 
			return 1;
		else 
			return 0;
	}
	
	public int test2hdata(Data data)
	{
		inputLayer1.setInput(data.getData());
		hiddenLayer1.setInput(inputLayer1.getOutput());
		hiddenLayer2.setInput(hiddenLayer1.getOutput());
		double[][] guess = hiddenLayer2.getOutput();
		int num = Calculate.getMax(guess);
		if(classes.get(num).equals(data.getTarget())) 
			return 1;
		else 
			return 0;
	}
}
