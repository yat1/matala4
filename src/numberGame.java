import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class numberGame extends JFrame {
	
	private static int amount = 10;
	private int[] nums;
	private String stringNums;
	private String stringPick;
	private int markLeft, markRight, scoreOne = 0, scoreTwo = 0, justPicked;
	private JLabel gameDescriptionJLabel;
	private JLabel displayNumsJLabel;
	private JLabel offerToPickJLabel;
	private JLabel whosPickingJLabel;
	private JLabel scoreOneJLabel;
	private JLabel scoreTwoJLabel;
	private Boolean turn = false; //false - PC, true - student
	private Color background;
	private JButton newGameJButton;
	private JButton leftJButton;
	private JButton rightJButton;
	

	 public numberGame()
	 {
		 super("numberGame");
		 background = Color.LIGHT_GRAY;
		 markLeft = 0;
		 markRight = amount-1;
		 createNums(amount);
		 displayNums();
		 gameDescriptionJLabel = new JLabel("here is a list of random number, each take turn picking one from the sides to get the highest score");
		 displayNumsJLabel = new JLabel(stringNums);
		 whosPickingJLabel = new JLabel("I am the boss, so i will pick first, press either button (left or right) for me to go");
		 scoreOneJLabel = new JLabel("player one score is: "+scoreOne);
		 scoreTwoJLabel = new JLabel("player two score is: "+scoreTwo);
		 newGameJButton = new JButton("New Game");
		 newGameJButton.addActionListener(

		         new ActionListener() // anonymous inner class
		         {
		            public void actionPerformed( ActionEvent e )
		            {
		            	theGame(); 
		            	/* Write code that resets the application to an appropriate state
		                  to start a new game. Reset the background color to light gray,
		                  set the JTextFields to their initial text, call method
		                  theGame and repaint the GuessGame JFrame */
		            }
		         } // end anonymous inner class
		      ); // end call to addActionListener
		 
		 leftJButton = new JButton("left");
		 leftJButton.addActionListener(
				 
				 new ActionListener() // anonymous inner class
				 {
					 public void actionPerformed( ActionEvent e )
		            {
		                 
		            }
		         } 
		      ); 
		 rightJButton = new JButton("right");
		 rightJButton.addActionListener(
				 
				 new ActionListener() // anonymous inner class
				 {
					 public void actionPerformed( ActionEvent e )
		            {
						 
		            }
		         } 
		      ); 
		 
		 setLayout( new FlowLayout() );
		 add(gameDescriptionJLabel);
		 add(displayNumsJLabel);
		 add(whosPickingJLabel);
		 add(scoreOneJLabel);
		 add(scoreTwoJLabel);
		 add(newGameJButton);
		 add(leftJButton);
		 add(rightJButton);
		 
	 }

	 public void theGame() {
		 markLeft = 0;
		 markRight = amount-1;
		 createNums(amount);
		 displayNums();
		 displayNumsJLabel.setText(stringNums);
		 turn = false;
		 whosPickingJLabel.setText("I am the boss, so i will pick first, press either button (left or right) for me to go");
		 scoreOne = 0;
		 scoreTwo = 0;
		 scoreOneJLabel.setText("player one score is: "+scoreOne);
		 scoreTwoJLabel.setText("player two score is: "+scoreTwo);
		 
		 
		 
	 }

	
	public void createNums(int amount) {
		Random rnd = new Random();
		nums = new int[amount];
		for (int i = 0; i < amount; i++) {
			nums[i] = rnd.nextInt(100);
		}
	}
	
	public void displayNums() {
		stringNums = "";
		for (int i = 0; i < nums.length; i++) {
			stringNums = stringNums +", "+nums[i];
		}
	}
	
	public void setPick() {
		if (!turn) {
			stringPick ="student just picked: "+ justPicked+" so its PC's turn, press either button (left or right) for me to go";
		}
		else {
			stringPick = "PC just picked: "+ justPicked+" so its students turn, press left to pick from the left side or right from the right side";
		}
		turn = !turn;
	}
	
	public int numToPick(int[] a, int left, int right) {
		int sumLeft = 0, sumRight = 0;
		for (int i = left; i < right; i++) {
			sumLeft = sumLeft + a[i];
			i++;
			sumRight = sumRight + a[i];
		}
		System.out.println("sumleft: "+sumLeft);
		System.out.println("sumright: "+sumRight);
		if (sumLeft>sumRight) {
			return left;
		}
		else {
			return right;
		}
	}
	
	public static void main(String[] args) {
		numberGame ng = new numberGame();
		ng.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		ng.setSize( 600, 150 ); // set frame size
		ng.setVisible( true ); // display frame
	}

}
