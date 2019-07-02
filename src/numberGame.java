import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class numberGame extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private static int amount = 10;
	private int[] nums;
	private int type; // 1 - for PvP, 2 - for PvPc
	private String stringNums;
	private String stringPick;
	private int markLeft, markRight, scoreOne = 0, scoreTwo = 0, justPicked;
	private JLabel gameDescriptionJLabel;
	private JLabel displayNumsJLabel;
	private JLabel typeOfGame;
	private JLabel whosPickingJLabel;
	private JLabel scoreOneJLabel;
	private JLabel scoreTwoJLabel;
	private JLabel messageJLabel;
	private Boolean turn = false; //false - PC, true - student, false - player 1, true - player 2
	private JButton newGameJButton;
	private JButton leftJButton;
	private JButton rightJButton;
	private JButton PvPJButton;
	private JButton PvPcJButton;
	

	/**
	 * a constructor, it creates all the JLabels with the right text, sets the font to be bigger, and sets some to be visible of not
	 * Creates the JButtons, and sets up actionListener's for each button, each button has a list of commands
	 *  that will happened when they are pressed, sets the layout, and adds all the JLabels and JButtons
	 */
	 public numberGame()
	 {
		 super("numberGame");
		 markLeft = 0;
		 markRight = amount-1;
		 createNums(amount);
		 displayNums();
		 messageJLabel = new JLabel( "" );
		 messageJLabel.setFont(new Font("Serif", Font.PLAIN, 25));
		 messageJLabel.setVisible( false );
		 typeOfGame = new JLabel("what type of game do you want? for player VS. player press 1 for plaer VS. PC press 2");
		 typeOfGame.setFont(new Font("Serif", Font.PLAIN, 25));
		 gameDescriptionJLabel = new JLabel("here is a list of random number, each take turn picking one from the sides to get the highest score");
		 gameDescriptionJLabel.setFont(new Font("Serif", Font.PLAIN, 25));
		 gameDescriptionJLabel.setVisible( false );
		 displayNumsJLabel = new JLabel(stringNums);
		 displayNumsJLabel.setFont(new Font("Serif", Font.PLAIN, 25));
		 displayNumsJLabel.setVisible( false );
		 whosPickingJLabel = new JLabel("I am the boss, so i will pick first, press either button (left or right) for me to go");
		 whosPickingJLabel.setFont(new Font("Serif", Font.PLAIN, 25));
		 whosPickingJLabel.setVisible( false );
		 scoreOneJLabel = new JLabel("player one score is: "+scoreOne);
		 scoreOneJLabel.setFont(new Font("Serif", Font.PLAIN, 25));
		 scoreOneJLabel.setVisible( false );
		 scoreTwoJLabel = new JLabel("player two score is: "+scoreTwo);
		 scoreTwoJLabel.setFont(new Font("Serif", Font.PLAIN, 25));
		 scoreTwoJLabel.setVisible( false );
		 newGameJButton = new JButton("New Game");
		 newGameJButton.setFont(new Font("Serif", Font.PLAIN, 25));
		 newGameJButton.addActionListener(

		         new ActionListener() // anonymous inner class
		         {
		            public void actionPerformed( ActionEvent e )
		            {
		       		 gameDescriptionJLabel.setVisible( false );
		    		 typeOfGame.setVisible( true);
		    		 PvPJButton.setVisible( true );
		    		 PvPcJButton.setVisible( true );
		    		 displayNumsJLabel.setVisible( false);
		    		 whosPickingJLabel.setVisible( false );
		    		 scoreOneJLabel.setVisible( false );
		    		 scoreTwoJLabel.setVisible( false );
		    		 leftJButton.setVisible( false );
		    		 rightJButton.setVisible( false );
		    		 messageJLabel.setVisible( false );
		          
		            }
		         } // end anonymous inner class
		      ); // end call to addActionListener
		 
		 leftJButton = new JButton("left");
		 leftJButton.setFont(new Font("Serif", Font.PLAIN, 25));
		 leftJButton.addActionListener(
				 
				 new ActionListener() // anonymous inner class
				 {
					 public void actionPerformed( ActionEvent e )
		            {
						 if (!(markRight<markLeft)) {
							 if (type==2) {
								 if (!turn) {
									 pcTurn();
								 }
								 else {
									 playerTurn(true);
								 }
							 }
							 else {
								 playerTurn(true);
							 }
						 }
		            } 
				 }
		      ); 
		 leftJButton.setVisible( false );
		 rightJButton = new JButton("right");
		 rightJButton.setFont(new Font("Serif", Font.PLAIN, 25));
		 rightJButton.addActionListener(
				 
				 new ActionListener() // anonymous inner class
				 {
					 public void actionPerformed( ActionEvent e )
		            {
						 if (!(markRight<markLeft)) {
							 if (type==2) {
								 if (!turn) {
									 pcTurn();
								 }
								 else {
									 playerTurn(false);
								 }
							 }
							 else {
								 playerTurn(false);
							 }
						 }
		            }
		         } 
		      ); 
		 rightJButton.setVisible( false );
		 
		 PvPJButton = new JButton("1");
		 PvPJButton.setFont(new Font("Serif", Font.PLAIN, 25));
		 PvPJButton.addActionListener(
				 
				 new ActionListener() // anonymous inner class
				 {
					 public void actionPerformed( ActionEvent e )
		            {
						 type =1;
						 whosPickingJLabel.setText("it is now player 1 turn, please pick from left or right");
						 theGame();
		            }
				 }
				 );
		 PvPcJButton = new JButton("2");
		 PvPcJButton.setFont(new Font("Serif", Font.PLAIN, 25));
		 PvPcJButton.addActionListener(
				 
				 new ActionListener() // anonymous inner class
				 {
					 public void actionPerformed( ActionEvent e )
		            {
						 type=2;
						 whosPickingJLabel.setText("I am the boss, so i will pick first, press either button (left or right) for me to go");
						 theGame();
		            }
				 }
				 );
		 
		 setLayout( new FlowLayout() );
		 add(typeOfGame);
		 add(PvPJButton);
		 add(PvPcJButton);
		 add(gameDescriptionJLabel);
		 add(displayNumsJLabel);
		 add(whosPickingJLabel);
		 add(scoreOneJLabel);
		 add(scoreTwoJLabel);
		 add(newGameJButton);
		 add(leftJButton);
		 add(rightJButton);
		 add(messageJLabel);
		 
	 }
	 
	 /**
	  * after the type of game was picked some JLabels must be turned on and some turned off, markers are reset, and a new list
	  * of numbers is generated
	  */
	 public void theGame() {
		 
		 gameDescriptionJLabel.setVisible( true );
		 typeOfGame.setVisible( false );
		 PvPJButton.setVisible( false );
		 PvPcJButton.setVisible( false );
		 displayNumsJLabel.setVisible( true );
		 whosPickingJLabel.setVisible( true );
		 scoreOneJLabel.setVisible( true );
		 scoreTwoJLabel.setVisible( true );
		 leftJButton.setVisible( true );
		 rightJButton.setVisible( true );
		 markLeft = 0;
		 markRight = amount-1;
		 createNums(amount);
		 displayNums();
		 displayNumsJLabel.setText(stringNums);
		 turn = false;
		scoreOne = 0;
		 scoreTwo = 0;
		 scoreOneJLabel.setText("player one score is: "+scoreOne);
		 scoreTwoJLabel.setText("player two score is: "+scoreTwo);
	
	 }
	 
	 /**
	  * after the left or right button is pressed if its the player's turn, then the playerTurn function is called
	  * according to boolean left it knows which side the player picked from,
	  *  then sets all the corresponding labels and markers to the right setting
	  * @param left
	  */
	 public void playerTurn(boolean left) {
		 if (left) {
			 justPicked = nums[markLeft];
			 if(turn) {
				 scoreTwo = scoreTwo + justPicked;
				 scoreTwoJLabel.setText("player two score is: "+scoreTwo);
			 }
			 else {
				 scoreOne = scoreOne + justPicked;
				 scoreOneJLabel.setText("player one score is: "+scoreOne);
			 }
			 markLeft++;
			 turn = !turn;
			 setPick();
			 whosPickingJLabel.setText(stringPick);
			 displayNums();
			 displayNumsJLabel.setText(stringNums); 
		 }
		 else {
			 justPicked = nums[markRight];
			 if(turn) {
				 scoreTwo = scoreTwo + justPicked;
				 scoreTwoJLabel.setText("player two score is: "+scoreTwo);
			 }
			 else {
				 scoreOne = scoreOne + justPicked;
				 scoreOneJLabel.setText("player one score is: "+scoreOne);
			 }
			 markRight--;
			 turn = !turn;
			 setPick();
			 whosPickingJLabel.setText(stringPick);
			 displayNums();
			 displayNumsJLabel.setText(stringNums);
		 }
		 /**
		  * if there are no more numbers to pick from, the gameOver function is called
		  */
		 if (markRight<markLeft) {
			 gameOver();
		 }  
		 
	 }
	 
	 /**
	  * after the left or right button is pressed if its the pc's turn, then the pcTurn function is called
	  * it chooses which side the pc should pick from, then sets all the corresponding labels and markers to the right setting
	  */
	 public void pcTurn() {
		 boolean pick = numToPick(nums, markLeft, markRight);
		 if (pick) {
			 justPicked = nums[markLeft];
			 scoreOne = scoreOne + justPicked;
			 markLeft++;
			 turn = !turn;
			 setPick();
			 whosPickingJLabel.setText(stringPick);
			 displayNums();
			 displayNumsJLabel.setText(stringNums);
			 scoreOneJLabel.setText("player one score is: "+scoreOne);
			 
		 }
		 else {
			 justPicked = nums[markRight];
			 scoreOne = scoreOne + justPicked;
			 markRight--;
			 turn = !turn;
			 setPick();
			 whosPickingJLabel.setText(stringPick);
			 displayNums();
			 displayNumsJLabel.setText(stringNums);
			 scoreOneJLabel.setText("player one score is: "+scoreOne);
		 }
		 /**
		  * if there are no more numbers to pick from, the gameOver function is called
		  */
		 if (markRight<markLeft) {
			 gameOver();
		 }
		 
	 }

	/**
	 * sets the messageJLabel text to represent the game over state according to the four different options, 
	 * two types of games and in each game two options for who can win, then makes the messageJLabel visible
	 */
	public void gameOver() {
		if (type ==2) {
		if (scoreOne>scoreTwo) {
			messageJLabel.setText("game over! PC has won with: "+scoreOne+" points. player only has: "+scoreTwo+" points. to start again press newGame");
		}
		else {
			messageJLabel.setText("game over! player has won with: "+scoreOne+" points. PC only has: "+scoreTwo+" points.to start again press newGame");
		}
		}
		else {
			if (scoreOne>scoreTwo) {
				messageJLabel.setText("game over! player 1 has won with: "+scoreOne+" points. player 2 only has: "+scoreTwo+" points. to start again press newGame");
			}
			else {
				messageJLabel.setText("game over! player 2 has won with: "+scoreOne+" points. player 1 only has: "+scoreTwo+" points.to start again press newGame");
			}
		}
		messageJLabel.setVisible( true );
	}

	/**
	 * sets the nums array to be a an array of amount length, with random numbers, this will be the list of numbers for the game
	 * @param amount
	 */
	public void createNums(int amount) {
		Random rnd = new Random();
		nums = new int[amount];
		for (int i = 0; i < amount; i++) {
			nums[i] = rnd.nextInt(100);
		}
	}
	
	/**
	 * sets the String stringNums to represent the list of the numbers that are left to pick from
	 * String stringNums will set the displayNumsJLabel for the new turn
	 * 
	 */
	public void displayNums() {
		stringNums = "";
		for (int i = markLeft; i < markRight+1; i++) {
			stringNums = stringNums +", "+nums[i];
		}
	}
	
	/**
	 * sets the string stringPick according to four different cases two different kind of games and two different players turn
	 *  String stringPick will set the whosPickingJLabel for the new turn
	 */
	public void setPick() {
		if (type ==2) {
		if (!turn) {
			stringPick ="student just picked: "+ justPicked+" so its PC's turn, press either button (left or right) for me to go";
		}
		else {
			stringPick = "PC just picked: "+ justPicked+" so its students turn, press left to pick from the left side or right from the right side";
		}
		}
		else {
			if (!turn) {
				stringPick ="player 2 just picked: "+ justPicked+" so its player 1 turn, press left to pick from the left side or right from the right side";
			}
			else {
				stringPick = "player 1 just picked: "+ justPicked+" so its player 2 turn, press left to pick from the left side or right from the right side";
			}
		}
	}
	
	/**
	 * checks the sum of the even numbers and odd numbers from spot left to spot right in the a array
	 * returns true for the left side or false for right side telling the pc from which side of a to pick from
	 * @param a
	 * @param left 
	 * @param right
	 * @return
	 */
	public boolean numToPick(int[] a, int left, int right) { //returns true for left and false for right
		int sumLeft = 0, sumRight = 0;
		for (int i = left; i < right; i++) {
			sumLeft = sumLeft + a[i];
			i++;
			sumRight = sumRight + a[i];
		}
		if (sumLeft>sumRight) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public static void main(String[] args) {
		numberGame ng = new numberGame();
		ng.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		ng.setSize( 1100, 350 ); // set frame size
		ng.setVisible( true ); // display frame
	}

}
