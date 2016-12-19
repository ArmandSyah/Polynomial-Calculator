package polynomialCalculator;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;

public class UserInterface extends JFrame {

	private JFrame frame;
	private JTextField Deg;
	private JTextField xvalue;
	private JButton btnConfirm;
	private JButton help;
	private JButton reset;
	private JButton save;
	private JButton read;
	private JRadioButton chckbxEvaluateAtX;
	private JRadioButton chckbxFactor;
	private JRadioButton chckbxDerivePolynomial;
	private JRadioButton chckbxOriginal;
	private ButtonGroup group;
	private JTextArea output;
	private String x;
	private String y;
	private int degree;
	private double coeffs;
	private BigDecimal Big;
	private List <BigDecimal> Temp = new ArrayList<BigDecimal>();
	private long eval;
	private BigDecimal total;
	private int count = 0;
	private String helpText = new String ("Enter max degree on the box above this help tip. Message Dialogue should then prompt user to enter coefficients starting from the lowest degree. Save button saves equation string on a text file. Read button outputs it onto console on Java IDE");


	public UserInterface() {
		super ("Polynomial Calculator");
		setLayout(null);
		
		Equation E = new Equation(degree);
		
		chckbxEvaluateAtX = new JRadioButton("Evaluate At X = ");
		chckbxEvaluateAtX.setBounds(257, 130, 124, 33);
		add(chckbxEvaluateAtX);
		
		chckbxFactor = new JRadioButton("Factor");
		chckbxFactor.setBounds(257, 160, 121, 33);
		add(chckbxFactor);
		
		chckbxDerivePolynomial = new JRadioButton("Derive Polynomial");
		chckbxDerivePolynomial.setBounds(257, 190, 156, 33);
		add(chckbxDerivePolynomial);
		
		chckbxOriginal = new JRadioButton("Show original");
		chckbxOriginal.setBounds(257, 220, 156, 33);
		add(chckbxOriginal);
		
		group = new ButtonGroup();
		group.add(chckbxFactor);
		group.add(chckbxDerivePolynomial);
		group.add(chckbxEvaluateAtX);
		group.add(chckbxOriginal);
		
		Deg = new JTextField();
		Deg.setBounds(36, 141, 161, 20);
		add(Deg);
		
		xvalue = new JTextField("1");
		xvalue.setBounds(390, 135, 40, 20);
		add(xvalue);
		
		output = new JTextArea("Please Enter Max Degree of polynomial you wish to input. Please press reset button after finishing equation");
		output.setFont(new Font("Time New Roman", Font.PLAIN, 12));
		output.setBounds(5, 5, 700, 125);
		output.setEditable(false);
		add(output);
		
		btnConfirm = new JButton("Confirm");
		btnConfirm.setBounds(32, 180, 121, 23);
		add(btnConfirm);
		btnConfirm.addActionListener(new ActionListener()
		{
			public void actionPerformed	(ActionEvent event){
				try{
					degree = Integer.parseInt(Deg.getText());
					Deg.setText("");
					while (count <= degree){
						try{
							coeffs = Double.parseDouble(JOptionPane.showInputDialog("Enter coefficient for x^" + count + ":")); 
							Big = BigDecimal.valueOf(coeffs);
							Temp.add(Big);
							count ++;
						}
						catch(Exception e){
							JOptionPane.showMessageDialog(null, "Please Enter Valid Number");
						}
					}
					x = E.createPolynomialEquation(Temp, degree);
					output.setText(x);
				}
				catch(Exception e){
					JOptionPane.showMessageDialog(null, "Please Enter Valid Number");
				}
				
			}
		}
		);
		
		reset = new JButton("Reset");
		reset.setBounds(32, 220, 121, 23);
		add(reset);
		reset.addActionListener(new ActionListener()
		{
			public void actionPerformed	(ActionEvent event){
				Deg.setText("");
				x = new String("Please Enter Max Degree of polynomial you wish to input. Please press reset button after finishing equation");
				output.setText(x);
				Temp.clear();
				count = 0;
			}
		}
		);
		
		help = new JButton("Help");
		help.setBounds(163, 180, 80, 23);
		add(help);
		help.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event){
				JOptionPane.showMessageDialog(null, helpText);
			}
		}
		);
		
		save = new JButton("Save Equation");
		save.setBounds(163, 220, 80, 23);
		add(save);
		save.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event){
				Save s = new Save();
				s.openFile();
				s.addRecords(x);
				s.closeFile();
			}
		}
				);
		
		read = new JButton("Read Text on Console");
		read.setBounds(425, 220, 100, 23);
		add(read);
		read.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event){
				Read r = new Read();
				r.openFile();
				r.readFile();
				r.closeFile();
				
			}
		}
				);
		
		
		thehandler handler = new thehandler();
		chckbxFactor.addItemListener(handler);
		chckbxDerivePolynomial.addItemListener(handler);
		chckbxEvaluateAtX.addItemListener(handler);
	}	
		private class thehandler implements ItemListener{
			
			public void itemStateChanged(ItemEvent Event){
				Equation E = new Equation(degree);
				if (chckbxDerivePolynomial.isSelected()){
					y = E.deriveEquation(Temp, degree);
					output.setText(y);
				}
				else if(chckbxEvaluateAtX.isSelected()){
					eval = Long.parseLong(xvalue.getText());
					total = E.evaluateEquation(Temp, eval);
					y = total.toString();
					output.setText(y);
				}
				else if(chckbxFactor.isSelected()){
					y = E.roots(Temp);
					output.setText(y);
				}
				else{
					y = E.createPolynomialEquation(Temp, degree);
					output.setText(y);
				}
			}
			
		}
	}



