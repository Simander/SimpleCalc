import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
public class GUI extends JFrame implements ActionListener
{
	private static final long serialVersionUID = 1L;
	float[] val;
	String[] operator;
	Funksjoner func= new Funksjoner();
	//operatortaster
	JButton pluss;
	JButton minus;
	JButton multi;
	JButton div;
	//systemtaster
	JButton resultat;
	JButton clear;
	JButton comma;
	
	//talltaster
	JButton[] talltast;
	
	//display
	JTextField display;
	JTextArea hist;
	//display panel
	JPanel top;
	//bottom panel
	JPanel bottom;
	//tall panel
	JPanel left;
	
	//system panel
	JPanel right;
	
	GUI()
	{
		super("SimpleCalc");
		top = new JPanel();
		bottom = new JPanel();
		left = new JPanel();
		right = new JPanel();
		talltast = new JButton[10];
		display = new JTextField(16);
		display.addActionListener(this);
		//display.set
		display.setPreferredSize(new Dimension(100,40));
		top.add(display);
		//initialiserer talltaster
		int i = 9;
		while(i >= 0)
		{
			
			talltast[i] = new JButton(i+"");
			left.add(talltast[i]);
			talltast[i].addActionListener(this);
			talltast[i].setPreferredSize(new Dimension(60,40));
			talltast[i].setRequestFocusEnabled(false);
			//prevents buttons from stealing focus from display
			i--;
		}
		val = new float[8];
		for(int z = 0; z < val.length; z ++)
		{
			val[z] = 0;
		}
		hist = new JTextArea(4,17);
		hist.setEditable(false);
		hist.setRequestFocusEnabled(false);
		operator = new String[val.length/2];
		comma = new JButton(" , ");
		comma.addActionListener(this);
		comma.setRequestFocusEnabled(false);
		comma.setPreferredSize(new Dimension(60, 40));
		left.add(comma);
		talltast[0].setPreferredSize(new Dimension(120,40));
		pluss = new JButton(" + ");
		pluss.setRequestFocusEnabled(false);
		pluss.addActionListener(this);
		minus = new JButton(" - ");
		minus.setRequestFocusEnabled(false);
		minus.addActionListener(this);
		div = new JButton(" / ");
		div.setRequestFocusEnabled(false);
		div.addActionListener(this);
		multi = new JButton(" * ");
		multi.setRequestFocusEnabled(false);
		multi.addActionListener(this);
		clear = new JButton(" C ");
		clear.setRequestFocusEnabled(false);
		clear.addActionListener(this);
		resultat = new JButton(" = ");
		resultat.setRequestFocusEnabled(false);
		resultat.addActionListener(this);
		top.add(clear);
		right.add(pluss);
		right.add(minus);
		right.add(div);
		right.add(multi);
		right.add(resultat);
		clear.setPreferredSize(new Dimension(60,35));
		pluss.setPreferredSize(new Dimension(60,35));
		minus.setPreferredSize(new Dimension(60,35));
		div.setPreferredSize(new Dimension(60,35));
		multi.setPreferredSize(new Dimension(60,35));
		resultat.setPreferredSize(new Dimension(60,75));
		left.setPreferredSize(new Dimension(200,101));
		left.setLayout(new FlowLayout());
		left.setBackground(Color.LIGHT_GRAY);
		right.setPreferredSize(new Dimension(56,250));
		bottom.setLayout(new BorderLayout(5,5));
		bottom.add(left, BorderLayout.LINE_START);
		bottom.add(right, BorderLayout.LINE_END);
		Container c = getContentPane();
		c.setLayout(new FlowLayout());
		c.add(top);
		c.add(bottom);
		c.add(new JLabel("By A. Simonsen."));
		left.add(new JScrollPane(hist));
		
		
		
	}
	public void calcFunc()
	{
		String cache = display.getText();
			String[] values = cache.split("(\\+)|(\\-)|(\\*)|(\\/)");
			for(int l = 0; l < values.length; l++)
			{
				if(values[l].contains(","))
				{
					String temp = values[l].replace(",", ".");
					values[l] = temp;
							
				}
			}
			String[] operators = cache.split("[0-9]");
			String toHist;
			String temp;
			int v = 0;
			float x = 0;
			float y = 0;
			for(int o = 0; o < operators.length; o++)
			{
				if(operators[o].equals("+"))
				{
					x = Float.parseFloat(values[v]);
					v++;
					y = Float.parseFloat(values[v]);
					float res = func.addisjon(x, y);
					toHist = display.getText();
					temp = commaCheck(res +"");
					display.setText(temp);
					hist.append(" " + toHist+"\n");
				}
				else if(operators[o].equals("-"))
				{
					x = Float.parseFloat(values[v]);
					v++;
					y = Float.parseFloat(values[v]);
					float res = func.subtraksjon(x, y);
					toHist = display.getText();
					temp = commaCheck(res +"");
					display.setText(temp);
					hist.append(" " + toHist+"\n");
				}
				else if(operators[o].equals("/"))
				{
					x = Float.parseFloat(values[v]);
					v++;
					y = Float.parseFloat(values[v]);
					float res = func.divisjon(x, y);
					toHist = display.getText();
					temp = commaCheck(res +"");
					display.setText(temp);
					hist.append(" " + toHist+"\n");
				}
				if(operators[o].equals("*"))
				{
					x = Float.parseFloat(values[v]);
					v++;
					y = Float.parseFloat(values[v]);
					float res = func.multiplikasjon(x, y);
					toHist = display.getText();
					temp = commaCheck(res +"");
					display.setText(temp);
					hist.append(" " + toHist+"\n");
				}
			}
	
	
	}
	
	
	public boolean commaCheck()
	{
		String dispText = display.getText();
		char temp = dispText.charAt(dispText.length()-1);
		if(temp =='i' || temp == '+' || temp =='-' || temp =='/' || temp=='*' || temp =='.')
			return false;
		else 
			return true;
	}
	public String commaCheck(String b)
	{
		String temp ="";
		if(b.contains("."))
		{
			temp = b.replace(".",",");
		}
		return temp;
	}
	public void clickNum(int a)
	{
		String res = display.getText() + a;
		display.setText(res);
	}
	
	public void clickOperator(String a)
	{
		String res = display.getText() + a;
		display.setText(res);
	}
	
	public void actionPerformed( ActionEvent event )
	{
		//number clicks
		if (event.getSource() == talltast[0])
		{
			clickNum(0);
		}
		else if(event.getSource() == talltast[1])
		{
			clickNum(1);
		}
		else if(event.getSource() == talltast[2])
		{
			clickNum(2);
		}
		else if(event.getSource() == talltast[3])
		{
			clickNum(3);
		}
		else if(event.getSource() == talltast[4])
		{
			clickNum(4);
		}
		else if(event.getSource() == talltast[5])
		{
			clickNum(5);
		}
		else if(event.getSource() == talltast[6])
		{
			clickNum(6);
		}
		else if(event.getSource() == talltast[7])
		{
			clickNum(7);
		}
		else if(event.getSource() == talltast[8])
		{
			clickNum(8);
		}
		else if(event.getSource() == talltast[9])
		{
			clickNum(9);
		}
		
		//system
		else if(event.getSource() == clear)
		{
			display.setText("");
		}
		else if(event.getSource() == pluss)
		{
			clickOperator("+");
		}
		else if(event.getSource() == minus)
		{
			clickOperator("-");
		}
		else if(event.getSource() == div)
		{
			clickOperator("/");
		}
		else if(event.getSource() == multi)
		{
			clickOperator("*");
		}
		else if(event.getSource() == comma)
		{
			if(!display.getText().equals("") && commaCheck() == true)
				clickOperator(",");
		}
		else if(event.getSource() == resultat)
			calcFunc();
		else if(event.getSource() == display)
			calcFunc();
	}
	
}
