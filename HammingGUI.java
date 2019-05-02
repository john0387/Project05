import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JComboBox;

public class HammingGUI extends JFrame //implements MouseListener
{
	
	GridBagConstraints layoutConst = null;
	JLabel showHamDist = null;
	JTextField hammingNum = null;
	JTextArea superBigText = null;
	JSlider HamSlide = null;
	JButton showStation = null;
	JTextArea bigText = null;
	JScrollPane scrollPane = null;
	JLabel comp = null;
	JComboBox dropDownBox = null;
	JButton calculations = null;
	JLabel zeroDistLabel = null;
	JTextField zeroHamDist = null;
	JLabel oneDistLabel = null;
	JTextField oneHamDist = null;
	JLabel twoDistLabel = null;
	JTextField twoHamDist = null;
	JLabel threeDistLabel = null;
	JTextField threeHamDist = null;
	JLabel fourDistLabel = null;
	JTextField fourHamDist = null;
	JButton stationButton = null;
	JTextField newStationName = null;
	ArrayList<String> IDlist = new ArrayList<String>();
	String[] IDlistArr = new String[120];
	String[] tempArr;
	String dropDownCode;
	String bigTextOutput="";
	int temp;
	int HamDistZero;
	int HamDistOne;
	int HamDistTwo;
	int HamDistThree;
	int HamDistFour;
	

	
	public HammingGUI()
	{
		setTitle("Hamming Distance");
		
		try
    	{
    		read("Mesonet.txt");
    	}
    	catch(IOException e)
    	{
    		System.out.println("Error reading from file!\n");
    		e.printStackTrace();
    	}
		
		setLayout(new GridBagLayout());	
	
		showHamDist = new JLabel("Enter Hamming Dist:");
		hammingNum = new JTextField(10);
		hammingNum.setEditable(false);
		
		
		superBigText = new JTextArea(20,41);
		superBigText.setText(
		  "Trivia Section\n\n"
		+ "In information theory, the Hamming distance between two strings of equal length\nis the number"
		+ "of positions at which the corresponding symbols are different. \nIn other words, "
		+ "it measures the minimum number of substitutions required to \nchange one string into the other, "
		+ "or the minimum number of errors that could \nhave transformed one string into the other. In a more general context, \n"
		+ "the Hamming distance is one of several string metrics for measuring \nthe edit distance between two sequences.\n"
		+ "It is named after the American mathematician Richard Hamming.\n\n" 
		+ "However, for comparing strings of different lengths, or strings where not just \nsubstitutions but also insertions or "
		+ "deletions have to be expected, a more \nsophisticated metric like the Levenshtein distance is more appropriate.\n\n"
		+ "source: https://en.wikipedia.org/wiki/Hamming_distance");

		superBigText.setEditable(false);
		
		
		HamSlide = new JSlider(1, 4, 2);
		HamSlide.addChangeListener((e) -> {
			ChangeTextToSliderNum();
		}); 
	    HamSlide.setMajorTickSpacing(1);
	    HamSlide.setPaintTicks(true);
	    HamSlide.setPaintLabels(true);
        hammingNum.setText(HamSlide.getValue()+"");
		
		showStation = new JButton("Show Station");
		showStation.addActionListener((e) -> {
			HammedSliderStations(IDlist.get(dropDownBox.getSelectedIndex()));
		});
		
		bigText = new JTextArea(20,25);
		scrollPane = new JScrollPane(bigText);
		bigText.setEditable(false);
	
		comp = new JLabel("Compare with:");
		dropDownBox = new JComboBox(IDlistArr);
		
		calculations = new JButton("Calculate HD");
		calculations.addActionListener((e) -> {
			CalcHD(IDlist.get(dropDownBox.getSelectedIndex()));
		});
		
		zeroDistLabel = new JLabel("Distance 0");
		zeroHamDist = new JTextField(10);
		zeroHamDist.setEditable(false);
		oneDistLabel = new JLabel("Distance 1");
		oneHamDist = new JTextField(10);
		oneHamDist.setEditable(false);
		twoDistLabel = new JLabel("Distance 2");
		twoHamDist = new JTextField(10);
		twoHamDist.setEditable(false);
		threeDistLabel = new JLabel("Distance 3");
		threeHamDist = new JTextField(10);
		threeHamDist.setEditable(false);
		fourDistLabel = new JLabel("Distance 4");
		fourHamDist = new JTextField(10);
		fourHamDist.setEditable(false);
		
		stationButton = new JButton("Add Station");
		stationButton.addActionListener((e) -> {
			expandDropDownList(newStationName.getText().substring(0, 4));
		});
		newStationName = new JTextField(6);
		newStationName.setText("ZERO");
		
		
		
		
		
		//TODO: create all features
		layoutConst = new GridBagConstraints();
		layoutConst.insets = new Insets(10, 0, 5, 0);
	    layoutConst.gridx = 0;
	    layoutConst.gridy = 0;
	    add(showHamDist, layoutConst);
		
	    layoutConst = new GridBagConstraints();
		layoutConst.insets = new Insets(10, 0, 5, 0);
	    layoutConst.gridx = 1;
	    layoutConst.gridy = 0;
	    add(hammingNum, layoutConst);
	    
	    layoutConst = new GridBagConstraints();
	    layoutConst.insets = new Insets(5, 5, 10, 5);
	    layoutConst.gridx = 2;
	    layoutConst.gridy = 3;
	    add(superBigText, layoutConst);
		
	    layoutConst = new GridBagConstraints();
	    layoutConst.insets = new Insets(5, 30, 5, 0);
	    layoutConst.gridx = 0;
	    layoutConst.gridy = 1;
	    add(HamSlide, layoutConst);
	    
	    layoutConst = new GridBagConstraints();
	    layoutConst.insets = new Insets(10, 0, 5, 0);
	    layoutConst.gridx = 0;
	    layoutConst.gridy = 2;
	    add(showStation, layoutConst);
	    
	    //layoutConst = new GridBagConstraints();
	    //layoutConst.insets = new Insets(5, 5, 10, 5);
	    //layoutConst.gridx = 0;
	    //layoutConst.gridy = 3;
	    //add(bigText, layoutConst);
	    
	    layoutConst = new GridBagConstraints();
	    layoutConst.insets = new Insets(5, 5, 0, 5);
	    layoutConst.gridx = 0;
	    layoutConst.gridy = 3;
	    add(scrollPane, layoutConst);
	    
	    layoutConst = new GridBagConstraints();
	    layoutConst.gridy = 4;
	    add(comp, layoutConst);
	    
	    layoutConst = new GridBagConstraints();
	    layoutConst.gridy = 4;
	    add(dropDownBox, layoutConst);
	    
	    layoutConst = new GridBagConstraints();
	    layoutConst.gridy = 5;
	    layoutConst.insets = new Insets(10, 0, 5, 10);
	    add(calculations, layoutConst);
	    
	    
	    layoutConst = new GridBagConstraints();
	    layoutConst.gridy = 6;
	    layoutConst.gridx = 0;
	    layoutConst.insets = new Insets(3, 0, 3, 0);
	    add(zeroDistLabel, layoutConst);
	    
	    layoutConst = new GridBagConstraints();
	    layoutConst.gridy = 6;
	    layoutConst.gridx = 1;
	    layoutConst.insets = new Insets(3, 0, 3, 0);
	    add(zeroHamDist, layoutConst);
	    
	    layoutConst = new GridBagConstraints();
	    layoutConst.gridy = 7;
	    layoutConst.gridx = 0;
	    layoutConst.insets = new Insets(3, 0, 3, 0);
	    add(oneDistLabel, layoutConst);
	    
	    layoutConst = new GridBagConstraints();
	    layoutConst.gridy = 7;
	    layoutConst.gridx = 1;
	    layoutConst.insets = new Insets(3, 0, 3, 0);
	    add(oneHamDist, layoutConst);
	    
	    layoutConst = new GridBagConstraints();
	    layoutConst.gridy = 8;
	    layoutConst.gridx = 0;
	    layoutConst.insets = new Insets(3, 0, 3, 0);
	    add(twoDistLabel, layoutConst);
	    
	    layoutConst = new GridBagConstraints();
	    layoutConst.gridy = 8;
	    layoutConst.gridx = 1;
	    layoutConst.insets = new Insets(3, 0, 3, 0);
	    add(twoHamDist, layoutConst);
	    
	    layoutConst = new GridBagConstraints();
	    layoutConst.gridy = 9;
	    layoutConst.gridx = 0;
	    layoutConst.insets = new Insets(3, 0, 3, 0);
	    add(threeDistLabel, layoutConst);
	    
	    layoutConst = new GridBagConstraints();
	    layoutConst.gridy = 9;
	    layoutConst.gridx = 1;
	    layoutConst.insets = new Insets(3, 0, 3, 0);
	    add(threeHamDist, layoutConst);
	    
	    layoutConst = new GridBagConstraints();
	    layoutConst.gridy = 10;
	    layoutConst.gridx = 0;
	    layoutConst.insets = new Insets(3, 0, 3, 0);
	    add(fourDistLabel, layoutConst);
	    
	    layoutConst = new GridBagConstraints();
	    layoutConst.gridy = 10;
	    layoutConst.gridx = 1;
	    layoutConst.insets = new Insets(3, 0, 3, 0);
	    add(fourHamDist, layoutConst);
	    
	    layoutConst = new GridBagConstraints();
	    layoutConst.gridy = 11;
	    layoutConst.gridx = 0;
	    add(stationButton, layoutConst);
	    
	    layoutConst = new GridBagConstraints();
	    layoutConst.gridy = 11;
	    layoutConst.gridx = 1;
	    add(newStationName, layoutConst);
	}
	
	
	public void read(String filename) throws IOException
    {
    	BufferedReader BR = new BufferedReader(new FileReader(filename));
        String line = BR.readLine();
        while(line!=null) {
        	IDlist.add(line.substring(0,4));
        	line = BR.readLine();        		
        }
        for(int i=0;i<IDlist.size();i++)
        {
        	IDlistArr[i] = IDlist.get(i);
        }
        BR.close();
    }
	
	public void ChangeTextToSliderNum()
	{
		hammingNum.setText(HamSlide.getValue()+"");
	}
	
	public void expandDropDownList(String str)
	{
		IDlist.add(str);
		tempArr = new String[IDlist.size()];
		for(int i=0;i<IDlist.size();i++)
        {
        	tempArr[i] = IDlist.get(i);
        }
		IDlistArr = tempArr;
		dropDownBox = new JComboBox(IDlistArr);
	}
	
	public void HammedSliderStations(String str)
	{
		for(int x=0;x<IDlist.size();x++) {
			for(int i=0;i<4;i++) {
				if(str.substring(i,i+1).compareToIgnoreCase(IDlist.get(x).substring(i,i+1))!=0) {
					temp++;
				}	
			}
			if(temp==HamSlide.getValue())
			{
				bigTextOutput += (IDlist.get(x)+"\n");
			}
			temp=0;
		}
		bigText.setText(bigTextOutput);
		bigTextOutput="";
	}
	
	public void CalcHD(String str) {
		for(int x=0;x<IDlist.size();x++) {
			for(int i=0;i<4;i++) {
				if(str.substring(i,i+1).compareToIgnoreCase(IDlist.get(x).substring(i,i+1))!=0) {
					temp++;
				}	
			}
			if(temp==0)
				HamDistZero++;
			
			if(temp==1)
				HamDistOne++;
			
			else if(temp==2) 
				HamDistTwo++;
			
			else if(temp==3) 
				HamDistThree++;
			
			else if(temp==4) 
				HamDistFour++;
			
			temp=0;
		}
		zeroHamDist.setText(HamDistZero+"");
		oneHamDist.setText(HamDistOne+"");
		twoHamDist.setText(HamDistTwo+"");
		threeHamDist.setText(HamDistThree+"");
		fourHamDist.setText(HamDistFour+"");
		HamDistZero=0;
		HamDistOne=0;
		HamDistTwo=0;
		HamDistThree=0;
		HamDistFour=0;
	}
	
	public void actionPerformed(ActionEvent e) {
        JComboBox cb = (JComboBox)e.getSource();
        dropDownCode = (String)cb.getSelectedItem();
	}
	
	public static void main(String[] args)
	{
		HammingGUI myFrame = new HammingGUI();
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    myFrame.pack();
	    myFrame.setVisible(true);
	}

}
