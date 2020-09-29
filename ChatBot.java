import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

import java.awt.Color;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

import java.lang.Math;
import java.lang.String;
import java.lang.System;

import chiragagarwal.Calculate;
import chiragagarwal.TimeDate;
import chiragagarwal.Browse;

public class ChatBot extends JFrame implements KeyListener{
	Calculate cal = new Calculate();
	TimeDate td = new TimeDate();
	JPanel p=new JPanel();
	JTextArea dialog=new JTextArea(20,50);
	JTextArea input=new JTextArea(3,50);
	JScrollPane scroll=new JScrollPane(
		dialog,
		JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
		JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED
	);
	String name = "";
	String[][] chatBot={
		//standard greetings
		{"hi","hello","hola","ola","hey"},
		{"hi","hello","hey","Hi, I am CHATBOT"},
		//question greetings
		{"how are you","how r you","how r u","how are u","how do u do"},
		{"good","doing well"},
		//standard questions
		{"who are you","what is your name"},
		{"My name is CHATBOT. Nice to meet you!"},
		//cheeky answers
		{"how much marks should i get for this project"},
		{"This is an amazing project, you should get full marks!"},

		{"are you alive","do you exist"},
		{"I am Virtual Bot.It says it in my name."},

		{"how old are you","what is your age"},
		{"I was created yesterday in Chirag's room, So im still pretty young "},

		{"Who owns you","who created you"},
		{"I was created by Chirag Agarwal(A full time sleeper and noob programmer)"},

		//help
		{"help","what can you do","list the things you can do for me","how can you help me"},
		{"In addition to have a conversation, I can calculate any equation, can tell the current time and date, and do a web search for you."},
		//time and date
		{"what time is it","time","can you tell me the time","what date it is today","date"},
		{"Current Date and Time:  "},
		//Online searching
		{"what","how","where","when","who","why","whom","by whom","are","search"},
		{"Getting Confirmation!"},
		//name
		{"my name is"},
		{"Hi, "},
		//asking name
		{"what is my name","did i tell you my name"},
		{"Your name is "},
		//yes
		{"yes"},
		{"no","NO","NO!!!!!!!"},
		//calculate
		{"calculate","cal","Calculate","Cal"},
		{"="},
		//default
		{"Can't reach CHATBOT at the moment","I didn't quite get you!",
		"(CHATBOT is unavailable, due to Issues)"}
	};

	public static void main(String[] args){
		new ChatBot();
	}

	public ChatBot(){
		super("Chat Bot");
		setSize(600,420);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		dialog.setEditable(false);
		input.addKeyListener(this);

		p.add(scroll);
		p.add(input);
		p.setBackground(new Color(255,0,0));
		add(p);
		addText("\n-->CHATBOT:\tHow can I help you?\n");
		setVisible(true);
	}

	public void keyPressed(KeyEvent e){
		if(e.getKeyCode()==KeyEvent.VK_ENTER){
			input.setEditable(false);
			//-----Take quote-----------
			String quote=input.getText();
			input.setText("");
			addText("-->You:\t"+quote);
			quote = quote.trim();
			while(
				quote.charAt(quote.length()-1)=='!' ||
				quote.charAt(quote.length()-1)=='.' ||
				quote.charAt(quote.length()-1)=='?'
			){
				quote=quote.substring(0,quote.length()-1);
			}
			quote = quote.trim();
			byte response=0;
			/*
			0:we're searching through chatBot[][] for matches
			1:we didn't find anything
			2:we did find something
			*/
			//-----check for matches----
			int j=0;//which group we're checking
			while(response==0){
				if(inArray(quote.toLowerCase(),chatBot[j*2])){

					response=2;
					if((j*2 != chatBot.length-3) && (j*2 != chatBot.length-9) && (j*2 != chatBot.length-11) && (j*2 != chatBot.length-13))
					{
						int r=(int)Math.floor(Math.random()*chatBot[(j*2)+1].length);
						if(j*2 != chatBot.length-7){
							addText("\n-->CHATBOT:\t"+chatBot[(j*2)+1][r]);
						}
						else{
							addText("\n-->CHATBOT:\t"+chatBot[(j*2)+1][r]+ " "+ name);
						}
					}
					else if(j*2 == chatBot.length-3)
					{
						String expression[] = quote.split(" ");
						int eval = cal.evaluate(expression[1]);
					  addText("\n-->CHATBOT:\t"+chatBot[(j*2)+1][0] + " "+ eval);
					}
					else if(j*2 == chatBot.length-9){
						String expression[] = quote.split(" ");
						try{
							name = expression[3];
						}
						catch(ArrayIndexOutOfBoundsException f)
						{
							addText("\n-->CHATBOT:\tNo name written!");
						}
						addText("\n-->CHATBOT:\t"+chatBot[(j*2)+1][0] + " "+ name);
					}
					else if(j*2 == chatBot.length-13){
          	addText("\n-->CHATBOT:\t"+chatBot[(j*2)+1][0] + td.getCurrentTimeDate());
					}
					else if(j*2 == chatBot.length-11){
						addText("\n-->CHATBOT:\t"+chatBot[(j*2)+1][0]);
						try{
							Thread.sleep(2000);
						}
						catch(InterruptedException Ie)
						{
							System.out.println(Ie);
						}
						Browse br = new Browse();
						quote = quote.replaceAll(" ","%20");
						br.link = br.link + quote;
						}

				}
				j++;
				if(j*2==chatBot.length-1 && response==0){
					response=1;
				}
			}

			//-----default--------------
			if(response==1){
				int r=(int)Math.floor(Math.random()*chatBot[chatBot.length-1].length);
				addText("\n-->CHATBOT:\t"+chatBot[chatBot.length-1][r]);
			}
			addText("\n");
		}
	}

	public void keyReleased(KeyEvent e){
		if(e.getKeyCode()==KeyEvent.VK_ENTER){
			input.setEditable(true);
		}
	}

	public void keyTyped(KeyEvent e){}

	public void addText(String str){
		dialog.setText(dialog.getText()+str);
	}

	public boolean inArray(String in,String[] str){
		boolean match=false;
		for(int i=0;i<str.length;i++){
			if(str[i].matches(in) || in.startsWith(str[i])){
				match=true;
			}
		}
		return match;
	}
}
