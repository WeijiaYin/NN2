package my.mlp;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeriesCollection;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;

public class Frame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private String filePath;
	private MLP mlp;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frame frame = new Frame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Frame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 848, 652);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JRadioButton rdbtnWine = new JRadioButton("wine");
		rdbtnWine.setBounds(42, 32, 121, 23);
		panel.add(rdbtnWine);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("mushroom");
		rdbtnNewRadioButton.setBounds(42, 76, 121, 23);
		panel.add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnIonosphere = new JRadioButton("ionosphere");
		rdbtnIonosphere.setBounds(42, 121, 121, 23);
		panel.add(rdbtnIonosphere);
		
		ButtonGroup bg = new ButtonGroup();
		bg.add(rdbtnWine);
		bg.add(rdbtnNewRadioButton);
		bg.add(rdbtnIonosphere);
		
		rdbtnWine.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent arg0) {
				// TODO Auto-generated method stub
				
				if(rdbtnWine.isSelected()) {
					filePath = rdbtnWine.getText()+".data";
				}
			}
			
		});
		
		rdbtnNewRadioButton.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent arg0) {
				// TODO Auto-generated method stub
				
				if(rdbtnNewRadioButton.isSelected()) {
					filePath = "agaricus-lepiota.data";
				}
			}
			
		});
		
		rdbtnIonosphere.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent arg0) {
				// TODO Auto-generated method stub
				
				if(rdbtnIonosphere.isSelected()) {
					filePath = rdbtnIonosphere.getText()+".data";
				}
			}
			
		});
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(42, 160, 752, 433);
		panel.add(panel_1);
		
		JLabel lblHiddenlayer = new JLabel("hiddenLayer1");
		lblHiddenlayer.setBounds(169, 36, 81, 15);
		panel.add(lblHiddenlayer);
		
		textField = new JTextField();
		textField.setBounds(273, 33, 66, 21);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblHiddenlayer_1 = new JLabel("hiddenLayer2");
		lblHiddenlayer_1.setBounds(169, 80, 81, 15);
		panel.add(lblHiddenlayer_1);
		
		textField_1 = new JTextField();
		textField_1.setBounds(273, 77, 66, 21);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblCorrectRateOn = new JLabel("correct rate on test:");
		lblCorrectRateOn.setBounds(273, 125, 133, 15);
		panel.add(lblCorrectRateOn);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(416, 129, 94, 15);
		panel.add(lblNewLabel);
		
		JButton btnOk = new JButton("ok");
		btnOk.setBounds(169, 121, 81, 23);
		panel.add(btnOk);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(553, 10, 241, 134);
		panel.add(textArea);
		
		textArea.setLineWrap(true);
		textArea.setEditable(false);
		
		btnOk.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				textArea.setText("");
				panel_1.removeAll();
				if(textField_1.getText().equals("0")) {
					try {
						mlp = new MLP(filePath);
						mlp.train1H(Integer.valueOf(textField.getText()));
						double rate = mlp.test1H();
						lblNewLabel.setText(String.valueOf(rate));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				else {
					try {
						mlp = new MLP(filePath);
						mlp.train2H(Integer.valueOf(textField.getText()), Integer.valueOf(textField_1.getText()));
						double rate = mlp.test2H();
						lblNewLabel.setText(String.valueOf(rate));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				XYSeriesCollection mCollection = new XYSeriesCollection();
				mCollection.addSeries(mlp.getmSeriesTrain());
				mCollection.addSeries(mlp.getmSeriesValue());
				
		        JFreeChart chart = ChartFactory.createXYLineChart("learning curve", "X", "Y", mCollection, PlotOrientation.VERTICAL,  
		                true, true, false);  
				
		        
				ChartPanel cp = new ChartPanel(chart);
				panel_1.add(cp);
				
				textArea.removeAll();
				
				List<String> classes = mlp.getClasses();
				for(int i = 0; i < classes.size(); i++)
				{
					textArea.append("   " + classes.get(i)+" ");
				}
				textArea.append("\r\n");
				int[][] confuseMatrix = mlp.getConfuseMatrix();
				for(int i = 0; i < confuseMatrix.length; i++)
				{
					textArea.append(classes.get(i) + " ");
					for(int j = 0; j < confuseMatrix[i].length; j++)
					{
						textArea.append(confuseMatrix[i][j]+" ");
					}
					textArea.append("\r\n");
				}
			}
			
		});



	}
}
