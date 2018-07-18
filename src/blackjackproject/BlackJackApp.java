
package blackjackproject;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class BlackJackApp extends JFrame {
	
    public JPanel contentPanel;
    public  Random myRandom = new Random();
    String heart = "\u2764";
    String spade = "\u2660";
    String diamond = "\u2666";
    String club = "\u2663";
    public int cash = 10;
	private static JTextArea txtrPlayerCards = new JTextArea(); 
	private static JTextArea txtrCardsCPU = new JTextArea();
	private JLabel lblYouWonlost = new JLabel("");
	private JLabel cashlabel = new JLabel("CASH : $" + cash);
    public static  ArrayList<Deck>  myDeck = new ArrayList <Deck>();
    private JLabel lblCardstotalPlayer = new JLabel("cardstotal");
    private JLabel lblCardstotalCPU = new JLabel(" cardstotal");
    private  int totalplayer = 0;
    private  int totalcpu = 0;
    boolean gameon = false;
    boolean gotCash = true;
    boolean aceDetectedcpu = false;
    boolean aceDetectedp1 = false;
    
    public static void main(String[] args) {
    	setCards();
        BlackJackApp myApp = new BlackJackApp();
		myApp.setSize(570, 400);
		myApp.setDefaultCloseOperation(EXIT_ON_CLOSE);
		myApp.setTitle("Black Jack");
		myApp.setVisible(true);
		myApp.setResizable(false);
    }
    
    public BlackJackApp(){
    	JPanel myPanel = new JPanel();
    	myPanel.setLayout(null);
    	JLabel titleLabel = new JLabel("Black Jack\u2764\u2660\u2666\u2663");
    	titleLabel.setFont(new Font("Dialog", Font.BOLD, 16));
    	titleLabel.setLocation(215, 11);
    	titleLabel.setSize(152, 20);
    	JButton standButton = new JButton("Stand");
    	standButton.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    		if(gameon == true){	
    		 	if (aceDetectedcpu == true){
    		 		if(totalcpu<11){
    		 			totalcpu = totalcpu + 10;
    		 			lblCardstotalCPU.setText(""+totalcpu);
    		 			
    		 		}aceDetectedcpu = false;
    		 	}
    			if (gotCash == true){

            	if (totalcpu < 15  ){
            		dealcpu();
            	}
            	if (totalcpu < 15  ){
            		dealcpu();
            	}
            	
            	gameon=false;
            	lblYouWonlost.setText(evaluate(totalcpu,totalplayer));
            	if(lblYouWonlost.getText() == "YOU WON"){
            		cash = cash + 2 ;
            		cashlabel.setText("CASH : $" + cash);
            	}
    		}
    	} else lblYouWonlost.setText("Please hit first");
    		}	
    	});
       JButton dealbutton = new JButton("HIT");
       dealbutton.addActionListener(new ActionListener() {
       	public void actionPerformed(ActionEvent e) {
       	if (gotCash == true){
       		if(myDeck.size()< 1 ){
       			setCards();
       		}
       		if (gameon == false){
       			txtrPlayerCards.setText("");
       			txtrCardsCPU.setText("");
       			totalplayer = 0 ;
       			totalcpu = 0 ;
       			dealcpu();
       		}

       		if (cash == 0 ){
       			lblYouWonlost.setText("YOU LOST RESET THE GAME");
       			gotCash = false;
       		}
       		if(cash > 0 && gameon == false){
   				cash = cash - 1 ;
           		cashlabel.setText("CASH : $" + cash);
           		gameon=true;
       		}
       		if (gameon = true)	{
               	   	dealplayer();
       		}
       		if(aceDetectedp1 == true){
       			if(totalplayer < 11){
       				totalplayer = totalplayer +10 ;
       				lblCardstotalPlayer.setText(""+totalplayer);
       				
       			}
       			aceDetectedp1 = false;
       		}
 
           	if (totalplayer > 21 ){
           			lblYouWonlost.setText("BURST !! ");
           			gameon=false;
           		}
       	}
       	}	
       });
       dealbutton.setLocation(464, 340);
       dealbutton.setSize(90, 20);
       standButton.setLocation(10, 340);
       standButton.setSize(90, 20);
       
       JLabel betlabel = new JLabel("BET: $1");
       betlabel.setLocation(340,340);
       betlabel.setSize(90,20);
       cashlabel.setLocation(170,340);
       cashlabel.setSize(90,20);
       JLabel dealer_Name = new JLabel("Dealer");
       dealer_Name.setLocation(30, 50);
       dealer_Name.setSize(90, 20);
       dealer_Name.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
       JLabel player_Name = new JLabel("YOU");
       player_Name.setLocation(371, 50);
       player_Name.setSize(90, 20);
       player_Name.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
       	txtrCardsCPU.setText("");
       	txtrCardsCPU.setBounds(10, 81, 284, 124);
       	txtrCardsCPU.setEditable(false);
   		myPanel.add(txtrCardsCPU);
        myPanel.add(player_Name);
        myPanel.add(dealer_Name);
        myPanel.add(betlabel);
        myPanel.add(cashlabel);
        myPanel.add(dealbutton);
        myPanel.add(standButton);
    	myPanel.add(titleLabel);
    	getContentPane().add(myPanel);
    	
    	
    	txtrPlayerCards.setText("");
    	txtrPlayerCards.setBounds(304, 81, 240, 124);
    	txtrPlayerCards.setEditable(false);
    	myPanel.add(txtrPlayerCards);
    	
    	JLabel lblTotalCPU = new JLabel("TOTAL");
    	lblTotalCPU.setBounds(10, 216, 46, 14);
    	myPanel.add(lblTotalCPU);
    	
    	JLabel lblTotalplayer = new JLabel("TOTAL");
    	lblTotalplayer.setBounds(401, 216, 46, 14);
    	myPanel.add(lblTotalplayer);
    	
    	
    	lblCardstotalCPU.setBounds(66, 216, 46, 14);
    	myPanel.add(lblCardstotalCPU);
    	

    	lblCardstotalPlayer.setBounds(476, 216, 46, 14);
    	myPanel.add(lblCardstotalPlayer);
    	
    	
    	lblYouWonlost.setFont(new Font("Buxton Sketch", Font.PLAIN, 28));
    	lblYouWonlost.setBounds(130, 244, 329, 79);
    	myPanel.add(lblYouWonlost);
//    	dealplayer();
//    	dealcpu();
    }
    
    public void dealplayer(){
    	int x = rand();
		txtrPlayerCards.setText(txtrPlayerCards.getText() + " " + name(x)+ " " + value (x) + "\n");
		totalplayer =  totalplayer + value (x);
		lblCardstotalPlayer.setText( " " + totalplayer);
		if(myDeck.get(x).getName()=="Ace of Hearts" ||myDeck.get(x).getName()=="Ace of Diamonds" || myDeck.get(x).getName()=="Ace of Clubs"  ||myDeck.get(x).getName()=="Ace of Spades") aceDetectedp1 = true;
    	myDeck.remove(x);
    }
    public void dealcpu(){
    	int x = rand();

    	txtrCardsCPU.setText(txtrCardsCPU.getText()+ " "  + name(x)+ " " + value (x) 
    			+ "\n" );
     	totalcpu = totalcpu +  value (x);
     	lblCardstotalCPU.setText(" " + totalcpu);
     	if(myDeck.get(x).getName()=="Ace of Hearts" ||myDeck.get(x).getName()=="Ace of Diamonds" || myDeck.get(x).getName()=="Ace of Clubs"  ||myDeck.get(x).getName()=="Ace of Spades") aceDetectedcpu = true;
    	myDeck.remove(x);
    }
    public static void  setCards()
    {
    Deck n1 = new Deck(1 , "Ace of Hearts"); //aces need to be assigned 11 as well!!
    Deck n2 = new Deck(2 , "Two of Hearts");
    Deck n3 = new Deck(3 , "Three of Hearts");
    Deck n4 = new Deck(4 , "Four of Hearts");
    Deck n5 = new Deck(5 , "Five of Hearts");
    Deck n6 = new Deck(6 , "Six of Hearts");
    Deck n7 = new Deck(7 , "Seven of Hearts");
    Deck n8 = new Deck(8 , "Eight of Hearts");
    Deck n9 = new Deck(9 , "Nine of Hearts");
    Deck n10 = new Deck(10 , "Ten of Hearts");
    Deck n11 = new Deck(10 , "Jack of Hearts");
    Deck n12 = new Deck(10 , "Queen of Hearts");
    Deck n13 = new Deck(10 , "King of Hearts");
    Deck n14 = new Deck(1 , "Ace of Diamonds");//ace
    Deck n15 = new Deck(2 , "Two of Diamonds");
    Deck n16 = new Deck(3 , "Three of Diamonds");
    Deck n17 = new Deck(4 , "Four of Diamonds");
    Deck n18 = new Deck(5 , "Five of Diamonds");
    Deck n19 = new Deck(6 , "Six of Diamonds");
    Deck n20 = new Deck(7 , "Seven of Diamonds");
    Deck n21 = new Deck(8 , "Eight of Diamonds");
    Deck n22 = new Deck(9 , "Nine of Diamonds");
    Deck n23 = new Deck(10 , "Ten of Diamonds");
    Deck n24 = new Deck(10 , "Jack of Diamonds");
    Deck n25 = new Deck(10 , "Queen of Diamonds");
    Deck n26 = new Deck(10 , "King of Diamonds");
    Deck n27 = new Deck(1 , "Ace of Clubs");//ace
    Deck n28 = new Deck(2 , "Two of Clubs");
    Deck n29 = new Deck(3 , "Three of Clubs");
    Deck n30 = new Deck(4 , "Four of Clubs");
    Deck n31 = new Deck(5 , "Five of Clubs");
    Deck n32 = new Deck(6 , "Six of Clubs");
    Deck n33 = new Deck(7 , "Seven of Clubs");
    Deck n34 = new Deck(8 , "Eight of Clubs");
    Deck n35 = new Deck(9 , "Nine of Clubs");
    Deck n36 = new Deck(10 , "Ten of Clubs");
    Deck n37 = new Deck(10 , "Jack of Clubs");
    Deck n38 = new Deck(10 , "Queen of Clubs");
    Deck n39 = new Deck(10 , "King of Clubs");
    Deck n40 = new Deck(1 , "Ace of Spades");//ace
    Deck n41 = new Deck(2 , "Two of Spades");
    Deck n42 = new Deck(3 , "Three of Spades");
    Deck n43 = new Deck(4 , "Four of Spades");
    Deck n44 = new Deck(5 , "Five of Spades");
    Deck n45 = new Deck(6 , "Six of Spades");
    Deck n46 = new Deck(7 , "Seven of Spades");
    Deck n47 = new Deck(8 , "Eight of Spades");
    Deck n48 = new Deck(9 , "Nine of Spades");
    Deck n49 = new Deck(10 , "Ten of Spades");
    Deck n50 = new Deck(10 , "Jack of Spades");
    Deck n51 = new Deck(10 , "Queen of Spades");
    Deck n52 = new Deck(10 , "King of Spades");
    
    myDeck.add(n1);
    myDeck.add(n2);
    myDeck.add(n3);
    myDeck.add(n4);
    myDeck.add(n5);
    myDeck.add(n6);
    myDeck.add(n7);
    myDeck.add(n8);
    myDeck.add(n9);
    myDeck.add(n10);
    myDeck.add(n11);
    myDeck.add(n12);
    myDeck.add(n13);
    myDeck.add(n14);
    myDeck.add(n15);
    myDeck.add(n16);
    myDeck.add(n17);
    myDeck.add(n18);
    myDeck.add(n19);
    myDeck.add(n20);
    myDeck.add(n21);
    myDeck.add(n22);
    myDeck.add(n23);
    myDeck.add(n24);
    myDeck.add(n25);
    myDeck.add(n26);
    myDeck.add(n27);
    myDeck.add(n28);
    myDeck.add(n29);
    myDeck.add(n30);
    myDeck.add(n31);
    myDeck.add(n32);
    myDeck.add(n33);
    myDeck.add(n34);
    myDeck.add(n35);
    myDeck.add(n36);
    myDeck.add(n37);
    myDeck.add(n38);
    myDeck.add(n39);
    myDeck.add(n40);
    myDeck.add(n41);
    myDeck.add(n42);
    myDeck.add(n43);
    myDeck.add(n44);
    myDeck.add(n45);
    myDeck.add(n46);
    myDeck.add(n47);
    myDeck.add(n48);
    myDeck.add(n49);
    myDeck.add(n50);
    myDeck.add(n51);
    myDeck.add(n52);
    }
    public static String name (int i ){
        return myDeck.get(i).getName();
    }
    public static int value(int i){
        return myDeck.get(i).getValue();
    }
    public static int rand(){
      Random rand = new Random();
      if(myDeck.size() < 1 ) setCards();
        int x = rand.nextInt(myDeck.size());
        return x ;
    }
    
    public String evaluate(int cpu , int p1){
    	if(cpu > 21){
    		return "YOU WON";
    	}
    	if(p1 > 21){
    		return "YOU LOST";
    	}
    	if(p1 > cpu){
    		return "YOU WON";
    		
    	}
    	if(p1 == cpu){
    		return "DRAW"; 
    	}
    	
    	return "YOU LOST";
    	
    }
}


