// Guess the number
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;

public class GuessGameFrame extends JFrame 
{
   private static Random generator = new Random();
   private int number; // number chosen by application
   private int guessCount; // number of guesses
   private int lastDistance; // distance between last guess and number
   private JTextField guessInputJTextField; // for guessing
   private JLabel guessesJLabel;
   private JLabel answerJLabel;
   private JLabel prompt1JLabel; // first prompt to user
   private JLabel prompt2JLabel; // second prompt to user
   private JLabel messageJLabel; // displays message of game status
   private JButton newGameJButton; // creates new game
   private Color background; // background color of application

   // set up GUI and initialize values
   public GuessGameFrame()
   {
      super("Guessing Game");
	  
      guessCount = 0; // initialize number of guesses to 0
      background = Color.LIGHT_GRAY; // set background to light gray
	  	  
	  answerJLabel = new JLabel( "" );
	  answerJLabel.setVisible( false );
	  
      prompt1JLabel = new JLabel( 
         "I have a number between 1 and 1000." ); // describe game
      prompt2JLabel = new JLabel( 
         "Can you guess my number? Enter your Guess:" ); // prompt user

      guessInputJTextField = new JTextField( 5 ); // to enter guesses
      guessInputJTextField.addActionListener( new GuessHandler( ) );
      messageJLabel = new JLabel( "Guess result appears here." );

      /* Write a statement that creates the "New Game" button */
      newGameJButton = new JButton("New Game");
	  newGameJButton.addActionListener(

         new ActionListener() // anonymous inner class
         {
            public void actionPerformed( ActionEvent e )
            {
                guessInputJTextField.setText( "" );
			    background = Color.LIGHT_GRAY;
				theGame();
				repaint();
			   /* Write code that resets the application to an appropriate state
                  to start a new game. Reset the background color to light gray,
                  set the JTextFields to their initial text, call method
                  theGame and repaint the GuessGame JFrame */
            } // end method actionPerformed
         } // end anonymous inner class
      ); // end call to addActionListener
	
	guessesJLabel = new JLabel( "" );
	
	setLayout( new FlowLayout() );
	add(prompt1JLabel);
    add(prompt2JLabel);
	add(messageJLabel);
    add(guessInputJTextField);
	add(answerJLabel);
    add(newGameJButton);
	add(guessesJLabel);
      /* Write code that will set the layout of the container to a Flowlayout,
         then add all the GUI components to the container */
     theGame(); // start new game
   } // end GuessGameFrame constructor

   // choose a new random number
   public void theGame()
   {
      number = 1+ generator.nextInt( 1000 );
	  String answer = "The Answer is " + Integer.toString(number);
	  answerJLabel.setText(answer);
	  /* Write a statement that sets instance variable number to a random number
         between 1 and 1000 */
   } // end method theGame

   // change background color
   public void paint( Graphics g )
   {
      super.paint( g );
      getContentPane().setBackground( background ); // set background
   } // end method paint

   // react to new guess
   public void react( int guess ) 
   {
      guessCount++; // increment guesses
	  String guessString = "You have guessed " + Integer.toString(guessCount) + " Times.";
	  guessesJLabel.setText(guessString);
	  
	  int currentDistance = 1000;
      /* Write code that sets instance variable currentDistance to 1000. This
         variable’s value will be used to determine if th ebackground color
         should be set to red or blue to indicate that the last guess was getting
         closer to or further from the actual number. */

      // first guess
      if ( guessCount == 1 ) 
      {
	  
	  lastDistance = Math.abs(guess - number);
	     /* Write code to set instance variable lastDistance to the absolute value
            of the difference between variables guess and number. This value will
           be used with subsequent guesses to help set the background color. */
				
         if ( guess > number )
            messageJLabel.setText( "Too High. Try a lower number." );
         else
            messageJLabel.setText( "Too Low. Try a higher number." );
      } // end if
      else 
      {
		currentDistance = Math.abs(guess - number);
         /* Write code that sets instance variable currentDistance to the absolute
            value of the difference between variables guess and number. This
            variable’s value will be compared with lastDistance to determine the
            background color. */
         // guess is too high
         if ( guess > number ) 
         {
            messageJLabel.setText( "Too High. Try a lower number." );
			background = ( currentDistance <= lastDistance ) ?
							Color.RED : Color.BLUE;
            lastDistance = currentDistance;
            /* Write code that sets Color variable background to red if the
               currentDistance is less than or equal to lastDistance; otherwise,
               set background to blue. Then assign currentDistance to lastDistance. */
         } // end if
         else if ( guess < number ) // guess is too low
         {
            messageJLabel.setText( "Too Low. Try a higher number." );
            background = ( currentDistance <= lastDistance ) ?
               Color.RED : Color.BLUE;
            lastDistance = currentDistance;
         } // end else if
         else // guess is correct
         {
            messageJLabel.setText( "Correct!" );
			answerJLabel.setVisible( true );
			background = Color.LIGHT_GRAY;
         } // end else

         repaint();
      } // end else
   } // end method react

   // inner class acts on user input
   class GuessHandler implements ActionListener
   {
      public void actionPerformed( ActionEvent e )
      {
			
			String text = guessInputJTextField.getText();
			int num = Integer.parseInt(text);
			react(num);
         /* Write code that will obtain the guess, convert it to an int and
            pass that value to the react method */
      } // end method actionPerformed
   } // end inner class GuessHandler
   
   public static void main( String args[] )
   {
      GuessGameFrame guessGameFrame = new GuessGameFrame(); 
      guessGameFrame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
      guessGameFrame.setSize( 300, 150 ); // set frame size
      guessGameFrame.setVisible( true ); // display frame
   } // end main
   
} // end class GuessGameFrame