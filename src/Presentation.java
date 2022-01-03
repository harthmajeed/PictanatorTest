import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.LineBorder;


public class Presentation {
	/*every variable below is initialized as a global variable, including the GUI
	 * objects, array to hold values, integers, etc*/

	private JFrame frame; //the interface with pictures and buttons
	private JFrame frame2; //fixed size interface show only the passwords
	
	private ArrayList<JButton> buttons = new ArrayList<JButton>();
	
	//current pictures and current category being tested
	private BufferedImage[] pictures;
	private ArrayList<String> category;
	
	//arrays taking in all 112 pictures from the Resource folder
	private BufferedImage[] animalsImages = new BufferedImage[16];
	private BufferedImage[] foodsImages = new BufferedImage[16];
	private BufferedImage[] vehiclesImages = new BufferedImage[16];
	private BufferedImage[] placesImages = new BufferedImage[16];
	private BufferedImage[] sportsImages = new BufferedImage[16];
	private BufferedImage[] clothingImages = new BufferedImage[16];
	private BufferedImage[] stuffImages = new BufferedImage[16];
	
	//hold imagei icons that can be overlayed on top of buttons to be displayed to the user
	private ImageIcon[] animalsIcons = new ImageIcon[16];
	private ImageIcon[] foodsIcons = new ImageIcon[16];
	private ImageIcon[] vehiclesIcons = new ImageIcon[16];
	private ImageIcon[] placesIcons = new ImageIcon[16];
	private ImageIcon[] sportsIcons = new ImageIcon[16];
	private ImageIcon[] clothingIcons = new ImageIcon[16];
	private ImageIcon[] stuffIcons = new ImageIcon[16];
	
	//stores the 7 icons above, allows for easy querying
	private ArrayList<ImageIcon[]> categoriesIcons = new ArrayList<ImageIcon[]>(7);
	//strings array the names, for example, gold, kangaroo, etc, see below
	private ArrayList<ArrayList<String>> categoriesStrings = new ArrayList<ArrayList<String>>(7);
	
	//storing all the strings for each category elements
	private ArrayList<String> animals = new ArrayList<String>(16);
	private ArrayList<String> foods = new ArrayList<String>(16);
	private ArrayList<String> vehicles = new ArrayList<String>(16);
	private ArrayList<String> places = new ArrayList<String>(16);
	private ArrayList<String> sports = new ArrayList<String>(16);
	private ArrayList<String> clothing = new ArrayList<String>(16);
	private ArrayList<String> stuff = new ArrayList<String>(16);
	
	//current password string for the each category
	private String animalsPassString = "Not generated";
	private String foodsPassString = "Not generated";
	private String vehiclesPassString = "Not generated";
	private String placesPassString = "Not generated";
	private String sportsPassString = "Not generated";
	private String clothingPassString = "Not generated";
	private String stuffPassString = "Not generated";
	
	//current password index for each category
	private int animalsPassIndex;
	private int foodsPassIndex;
	private int vehiclesPassIndex;
	private int placesPassIndex;
	private int sportsPassIndex;
	private int clothingPassIndex;
	private int stuffPassIndex;
	
	//all the buttons for OptionsPanel
	private JButton GenPassButton;
	private JButton PracticeButton;
	private JButton TestButton;
	private JButton RescaleButton;
	private JButton ShowPassButton;
	private JButton HelpButton;
	private JButton EscapeButton;
	
	//all buttons for selecting elements to enter password
	private JButton button_0;
	private JButton button_1;
	private JButton button_2;
	private JButton button_3;
	private JButton button_4;
	private JButton button_5;
	private JButton button_6;
	private JButton button_7;
	private JButton button_8;
	private JButton button_9;
	private JButton button_10;
	private JButton button_11;
	private JButton button_12;
	private JButton button_13;
	private JButton button_14;
	private JButton button_15;
	
	//all the labels for frame 2
	private JLabel AnimalsLabel;
	private JLabel AnimalsCurrentLabel;
	private JLabel AnimalsCurrentPictureLabel;
	private JLabel FoodsCurrentPictureLabel;
	private JLabel FoodsCurrentLabel;
	private JLabel FoodsLabel;
	private JLabel VehiclesCurrentPictureLabel;
	private JLabel VehiclesCurrentLabel;
	private JLabel VehiclesLabel;
	private JLabel SportsCurrentPictureLabel;
	private JLabel SportsCurrentLabel;
	private JLabel SportsLabel;
	private JLabel ClothingCurrentPictureLabel;
	private JLabel ClothingCurrentLabel;
	private JLabel ClothingLabel;
	private JLabel StuffCurrentPictureLabel;
	private JLabel StuffCurrentLabel;
	private JLabel StuffLabel;
	private JLabel PlacesCurrentPictureLabel;
	private JLabel PlacesCurrentLabel;
	private JLabel PlacesLabel;
	private JLabel SummaryLabel;
	private JTextPane SummaryListTextPane;
	
	//other variables needed for proper function
	//also known as state variables that dictate how the program proceeds
	private Random random = new Random();
	private int state = 0;
	private int practiceSessionState = -1;
	private int testSessionState = -1;
	private int[] practiceAnswers = new int[7];
	private int[] testAnswers = new int[7];
	private int[] currSolutionsIndex;
	private int[] solutionsIndex1 = new int[7];
	private int[] solutionsIndex2 = new int[7];
	private int[] solutionsIndex3 = new int[7];
	private ArrayList<Integer> listOfSols = new ArrayList<Integer>(3);
	private int currentPassword;
	private int testPassNum = 0;
	private int phaseNum = 1;
	private int chances = 2;
	private int userid = 1000;
	private int showLastlySelected = 0;
	private int practiceLastlySelected = 0;
	private boolean firstTimeTest = true;
	private boolean passGenerated = false;
	private long timeStart;
	private long timeStart2;
	private long timeEnd;
	private long timeEnd2;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Presentation window = new Presentation();
					window.frame.setVisible(true);
					JOptionPane.showMessageDialog(null, 
							  "Hello and welcome to Pictanator,\n"
							+ "please click on the Help button\n"
							+ "to learn everything you need to\n"
							+ "know.", "Welcome!", 1);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws IOException 
	 */
	//Constructor
	public Presentation() throws IOException {
		initialize(); //the main frame
		initialize2(); //the show password frame
		automate(); //last bit of things to call up
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws IOException 
	 */
	
	//frame2 intitalizations forlabels, panels, text, etc
	private void initialize2() {
		frame2 = new JFrame();
		frame2.setBounds(100, 100, 505, 366);
		frame2.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame2.getContentPane().setLayout(null);
		frame2.setTitle("Your Password");
		frame2.setResizable(false);
		
		AnimalsLabel = new JLabel("Animals");
		AnimalsLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		AnimalsLabel.setBounds(10, 11, 73, 14);
		frame2.getContentPane().add(AnimalsLabel);
		
		AnimalsCurrentLabel = new JLabel("");
		AnimalsCurrentLabel.setBounds(10, 26, 110, 14);
		frame2.getContentPane().add(AnimalsCurrentLabel);
		
		AnimalsCurrentPictureLabel = new JLabel();
		AnimalsCurrentPictureLabel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		AnimalsCurrentPictureLabel.setBounds(10, 51, 110, 110);
		frame2.getContentPane().add(AnimalsCurrentPictureLabel);
		
		FoodsCurrentPictureLabel = new JLabel();
		FoodsCurrentPictureLabel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		FoodsCurrentPictureLabel.setBounds(130, 51, 110, 110);
		frame2.getContentPane().add(FoodsCurrentPictureLabel);
		
		FoodsCurrentLabel = new JLabel("");
		FoodsCurrentLabel.setBounds(130, 26, 110, 14);
		frame2.getContentPane().add(FoodsCurrentLabel);
		
		FoodsLabel = new JLabel("Foods");
		FoodsLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		FoodsLabel.setBounds(130, 11, 73, 14);
		frame2.getContentPane().add(FoodsLabel);
		
		VehiclesCurrentPictureLabel = new JLabel();
		VehiclesCurrentPictureLabel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		VehiclesCurrentPictureLabel.setBounds(250, 51, 110, 110);
		frame2.getContentPane().add(VehiclesCurrentPictureLabel);
		
		VehiclesCurrentLabel = new JLabel("");
		VehiclesCurrentLabel.setBounds(250, 26, 110, 14);
		frame2.getContentPane().add(VehiclesCurrentLabel);
		
		VehiclesLabel = new JLabel("Vehicles");
		VehiclesLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		VehiclesLabel.setBounds(250, 11, 73, 14);
		frame2.getContentPane().add(VehiclesLabel);
		
		SportsCurrentPictureLabel = new JLabel();
		SportsCurrentPictureLabel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		SportsCurrentPictureLabel.setBounds(10, 212, 110, 110);
		frame2.getContentPane().add(SportsCurrentPictureLabel);
		
		SportsCurrentLabel = new JLabel("");
		SportsCurrentLabel.setBounds(10, 187, 110, 14);
		frame2.getContentPane().add(SportsCurrentLabel);
		
		SportsLabel = new JLabel("Sports");
		SportsLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		SportsLabel.setBounds(10, 172, 73, 14);
		frame2.getContentPane().add(SportsLabel);
		
		ClothingCurrentPictureLabel = new JLabel();
		ClothingCurrentPictureLabel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		ClothingCurrentPictureLabel.setBounds(130, 212, 110, 110);
		frame2.getContentPane().add(ClothingCurrentPictureLabel);
		
		ClothingCurrentLabel = new JLabel("");
		ClothingCurrentLabel.setBounds(130, 187, 110, 14);
		frame2.getContentPane().add(ClothingCurrentLabel);
		
		ClothingLabel = new JLabel("Clothing");
		ClothingLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		ClothingLabel.setBounds(130, 172, 73, 14);
		frame2.getContentPane().add(ClothingLabel);
		
		StuffCurrentPictureLabel = new JLabel();
		StuffCurrentPictureLabel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		StuffCurrentPictureLabel.setBounds(250, 212, 110, 110);
		frame2.getContentPane().add(StuffCurrentPictureLabel);
		
		StuffCurrentLabel = new JLabel("");
		StuffCurrentLabel.setBounds(250, 187, 110, 14);
		frame2.getContentPane().add(StuffCurrentLabel);
		
		StuffLabel = new JLabel("Stuff");
		StuffLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		StuffLabel.setBounds(250, 172, 73, 14);
		frame2.getContentPane().add(StuffLabel);
		
		PlacesCurrentPictureLabel = new JLabel();
		PlacesCurrentPictureLabel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		PlacesCurrentPictureLabel.setBounds(370, 51, 110, 110);
		frame2.getContentPane().add(PlacesCurrentPictureLabel);
		
		PlacesCurrentLabel = new JLabel("");
		PlacesCurrentLabel.setBounds(370, 26, 110, 14);
		frame2.getContentPane().add(PlacesCurrentLabel);
		
		PlacesLabel = new JLabel("Places");
		PlacesLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		PlacesLabel.setBounds(370, 11, 73, 14);
		frame2.getContentPane().add(PlacesLabel);
		
		SummaryLabel = new JLabel("Summary");
		SummaryLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		SummaryLabel.setBounds(380, 172, 73, 14);
		frame2.getContentPane().add(SummaryLabel);
		
		SummaryListTextPane = new JTextPane();
		SummaryListTextPane.setEditable(false);
		SummaryListTextPane.setBounds(370, 204, 109, 118);
		frame2.getContentPane().add(SummaryListTextPane);
	}
	
	//strictly initializes all the panels, buttons, action listeners only
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 656, 625);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(2, 2));
		
		JPanel ButtonsPanel = new JPanel();
		frame.getContentPane().add(ButtonsPanel, BorderLayout.CENTER);
		ButtonsPanel.setLayout(new GridLayout(4, 4, 1, 1));
		
		JPanel OptionsPanel = new JPanel();
		frame.getContentPane().add(OptionsPanel, BorderLayout.SOUTH);
		OptionsPanel.setLayout(new GridLayout(1, 1, 1, 1));
		
		GenPassButton = new JButton("Gen Pass");
		GenPassButton.setBackground(new Color(255, 215, 0));
		GenPassButton.setForeground(new Color(105, 105, 105));
		GenPassButton.setFont(new Font("Rockwell", Font.BOLD, 14));
		OptionsPanel.add(GenPassButton);
		GenPassButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int option = JOptionPane.showConfirmDialog(null, 
						 "Are you sure you wish to generate\n"
						+"new passwords?\n"
						+"Doing so will delete current ones.");	
				System.out.println(option);
				if(option == 0)
					generatePasswords();
			}
		});
		
		ShowPassButton = new JButton("Show Pass");
		ShowPassButton.setBackground(new Color(127, 255, 0));
		ShowPassButton.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 14));
		OptionsPanel.add(ShowPassButton);
		ShowPassButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(passGenerated == false)
				{
					JOptionPane.showMessageDialog(null, "Please generate passwords and practice them.");
					return;
				}
				
				String[] showPasswords = {"Password 1","Password 2","Password 3"};
				
				String selectedPassword = (String) JOptionPane.showInputDialog(null, 
						"Please choose a password to see.", 
						"Show Password Selection", 
						JOptionPane.INFORMATION_MESSAGE, null, 
						showPasswords, showPasswords[showLastlySelected]);
				
				if(selectedPassword.equals(showPasswords[0]))
				{
					showLastlySelected = 0;
					setUpAllShowFunctions(solutionsIndex1);
					frame2.setVisible(true);
				}
				else if(selectedPassword.equals(showPasswords[1]))
				{
					showLastlySelected = 1;
					setUpAllShowFunctions(solutionsIndex2);
					frame2.setVisible(true);
				}
				else if(selectedPassword.equals(showPasswords[2]))
				{
					showLastlySelected = 2;
					setUpAllShowFunctions(solutionsIndex3);
					frame2.setVisible(true);
				}
			}
		});
		
		PracticeButton = new JButton("Practice");
		PracticeButton.setBackground(new Color(221, 160, 221));
		PracticeButton.setForeground(new Color(153, 51, 0));
		PracticeButton.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 13));
		OptionsPanel.add(PracticeButton);
		PracticeButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(passGenerated == false)
				{
					JOptionPane.showMessageDialog(null, "Please generate passwords and practice them.");
					return;
				}
				
				String[] possiblePasswords = {"Password 1","Password 2","Password 3"};
				
				String selectedPassword = (String) JOptionPane.showInputDialog(null, 
						"Please choose a password to practice.", 
						"Practice Password Selection", 
						JOptionPane.INFORMATION_MESSAGE, null, 
						possiblePasswords, possiblePasswords[practiceLastlySelected]);
				
				if(selectedPassword.equals(possiblePasswords[0]))
				{
					practiceLastlySelected = 0;
					currentPassword = 1;
					setUpAllShowFunctions(solutionsIndex1);
				}
				else if(selectedPassword.equals(possiblePasswords[1]))
				{
					practiceLastlySelected = 1;
					currentPassword = 2;
					setUpAllShowFunctions(solutionsIndex2);
				}
				else if(selectedPassword.equals(possiblePasswords[2]))
				{
					practiceLastlySelected = 2;
					currentPassword = 3;
					setUpAllShowFunctions(solutionsIndex3);
				}
				state = 1;
				practice(-1);
			}
		});
		
		TestButton = new JButton("TEST");
		TestButton.setBackground(new Color(255, 0, 0));
		TestButton.setForeground(new Color(230, 230, 250));
		TestButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		OptionsPanel.add(TestButton);
		TestButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(passGenerated == false)
				{
					JOptionPane.showMessageDialog(null, "Please generate passwords and practice them.");
					return;
				}
				int test = JOptionPane.showConfirmDialog(null, "Are you ready to take the test?");
				if(test == 0)
				{
					state = 2;
					test(-1);
				}		
				else if(test == 1 || test == 2)
					return;
			}
		});
		
		RescaleButton = new JButton("Rescale Pictures");
		RescaleButton.setBackground(new Color(30, 144, 255));
		RescaleButton.setFont(new Font("Times New Roman", Font.BOLD, 12));
		OptionsPanel.add(RescaleButton);
		RescaleButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				rescalePictures(pictures);
			}
		});
		
		HelpButton = new JButton("HELP");
		HelpButton.setBackground(new Color(255, 255, 0));
		HelpButton.setFont(new Font("Times New Roman", Font.BOLD, 14));
		OptionsPanel.add(HelpButton);
		HelpButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JOptionPane.showMessageDialog(null,
			  "GEN PASS - This will produce 3 randomly generated passwords for you, you will be tested on these passwords.\n"
			+ "SHOW PASS - Show one of the 3 passwords of your choosing, please note this button is disabled during a test.\n"
			+ "PRACTISE - This button will put you in a session where you can safely practise remembering your password, also \n"
			+ "note that if you forget the password for the current category it is display on the title of the window as well\n"
			+ "including the position number. Please note that 'Gen Pass' and 'TEST' and 'Practice' buttons will be disabled \n"
			+ "in this session.\n"
			+ "TEST - This button will put you in a test session. During the test you will be presented with 1 of 3 passwords.\n"
			+ "The password chosen to be tested is random. Please do look at the header of the window to see what password\n"
			+ "you're currently being tested on. During the test you will be preseted with one category at a time, please \n"
			+ "click on the picture that corresponds to your password.\n"
			+ "*PLEASE NOTE: THERE IS NO BACK BUTTON, SO ONCE YOU CLICK ON THE PICTURE IT'S FINAL !!!*\n"
			+ "Once you've finished entering your 3 passwords the program will automatically repeat the whole process again with a\n"
			+ "differently randomly generated order of the same passwords.\n"
			+ "RESCALE PICTURE - If you resize the window and the pictures are too small for you to see you can click on this\n"
			+ "button. Please do note that this change does take several seconds to appear depending on your machine, if it takes\n"
			+ "longer then please be patient, also note that ONLY current category images will be scaled and not the next ones.\n"
			+ "HELP - This is the button opens up what you're currently reading. :^) \n"
			+ "ESCAPE - This button will escape from the current Practice session and Test session, note all data will be SAVED.\n"
			+ "NOTE - This program outputs a CSV file, it there is none it will create it, if one exists it will append to it.", "HELP", 1);
			}
		});
		
		EscapeButton = new JButton("ESCAPE");
		EscapeButton.setBackground(new Color(0,0,0));
		EscapeButton.setForeground(new Color(255,255,255));
		OptionsPanel.add(EscapeButton);
		EscapeButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				state = 0;
				testSessionState = -1;
				firstTimeTest = true;
				chances = 2;
				phaseNum = 0;
				
				for(int i = 0; i < buttons.size(); i++)
					buttons.get(i).setIcon(new ImageIcon());
				
				frame.setTitle("Welcome to Pictanator");
				GenPassButton.setVisible(true);
				PracticeButton.setVisible(true);
				TestButton.setVisible(true);
				ShowPassButton.setVisible(true);
			}
		});
		
		button_0 = new JButton();
		ButtonsPanel.add(button_0);
		buttons.add(button_0);
		button_0.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("[0]-"+category.get(0));
				buttonPressed(0);
			}
		});
		
		button_1 = new JButton();
		ButtonsPanel.add(button_1);
		buttons.add(button_1);
		button_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("[1]-"+category.get(1));
				buttonPressed(1);
			}
		});
		
		button_2 = new JButton();
		ButtonsPanel.add(button_2);
		buttons.add(button_2);
		button_2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("[2]-"+category.get(2));
				buttonPressed(2);
			}
		});
		
		button_3 = new JButton();
		ButtonsPanel.add(button_3);
		buttons.add(button_3);
		button_3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("[3]-"+category.get(3));
				buttonPressed(3);
			}
		});
		
		button_4 = new JButton();
		ButtonsPanel.add(button_4);
		buttons.add(button_4);
		button_4.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("[4]-"+category.get(4));
				buttonPressed(4);
			}
		});
		
		button_5 = new JButton();
		ButtonsPanel.add(button_5);
		buttons.add(button_5);
		button_5.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("[5]-"+category.get(5));
				buttonPressed(5);
			}
		});
		
		button_6 = new JButton();
		ButtonsPanel.add(button_6);
		buttons.add(button_6);
		button_6.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("[6]-"+category.get(6));
				buttonPressed(6);
			}
		});
		
		button_7 = new JButton();
		ButtonsPanel.add(button_7);
		buttons.add(button_7);
		button_7.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("[7]-"+category.get(7));
				buttonPressed(7);
			}
		});
		
		button_8 = new JButton();
		ButtonsPanel.add(button_8);
		buttons.add(button_8);
		button_8.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("[8]-"+category.get(8));
				buttonPressed(8);
			}
		});
		
		button_9 = new JButton();
		ButtonsPanel.add(button_9);
		buttons.add(button_9);
		button_9.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("[9]-"+category.get(9));
				buttonPressed(9);
			}
		});
		
		button_10 = new JButton();
		ButtonsPanel.add(button_10);
		buttons.add(button_10);
		button_10.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("[10]-"+category.get(10));
				buttonPressed(10);
			}
		});
		
		button_11 = new JButton();
		ButtonsPanel.add(button_11);
		buttons.add(button_11);
		button_11.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("[11]-"+category.get(11));
				buttonPressed(11);
			}
		});
		
		button_12 = new JButton();
		ButtonsPanel.add(button_12);
		buttons.add(button_12);
		button_12.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("[12]-"+category.get(12));
				buttonPressed(12);
			}
		});
		
		button_13 = new JButton();
		ButtonsPanel.add(button_13);
		buttons.add(button_13);
		button_13.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("[13]-"+category.get(13));
				buttonPressed(13);
			}
		});
		
		button_14 = new JButton();
		ButtonsPanel.add(button_14);
		buttons.add(button_14);
		button_14.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("[14]-"+category.get(14));
				buttonPressed(14);
			}
		});
		
		button_15 = new JButton();
		ButtonsPanel.add(button_15);
		buttons.add(button_15);
		button_15.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("[15]-"+category.get(15));
				buttonPressed(15);
			}
		});
	}
	
	//calls a function that loads pictures, here that functions is 
	//called 7 times here to load everything, one of the most time consuming step
	private void loadAllPictures() throws IOException
	{
		loadPictures(animalsImages, "animals", animalsIcons);
		loadPictures(foodsImages, "foods", foodsIcons);
		loadPictures(vehiclesImages, "vehicles", vehiclesIcons);
		loadPictures(placesImages, "places", placesIcons);
		loadPictures(sportsImages, "sports", sportsIcons);
		loadPictures(clothingImages, "clothing", clothingIcons);
		loadPictures(stuffImages, "stuff", stuffIcons);
	}
	
	//loads all the element names into their respective categories
	private void loadCategoryElementNames()
	{
		animals.add("Kangaroo");
		animals.add("Frog");
		animals.add("Flamingo");
		animals.add("Octopus");
		animals.add("Lizard");
		animals.add("Beaver");
		animals.add("Penguin");
		animals.add("Elephant");
		animals.add("Bear");
		animals.add("Eagle");
		animals.add("Lion");
		animals.add("Wolf");
		animals.add("Giraffe");
		animals.add("Rhinoceros");
		animals.add("Shark");
		animals.add("Dolphin");
		
		foods.add("Sushi");
		foods.add("Pizza");
		foods.add("Fries");
		foods.add("Tacos");
		foods.add("Soup");
		foods.add("Burger");
		foods.add("Salad");
		foods.add("Ice Cream");
		foods.add("Hot Dogs");
		foods.add("Lasagna");
		foods.add("Rice");
		foods.add("Waffles");
		foods.add("Shawarma");
		foods.add("Chicken Nuggets");
		foods.add("Omelette");
		foods.add("Pie");
		
		vehicles.add("Submarine");
		vehicles.add("Bicycle");
		vehicles.add("Car");
		vehicles.add("Airplane");
		vehicles.add("Segway");
		vehicles.add("Flying Saucer");
		vehicles.add("Boat");
		vehicles.add("Helicopter");
		vehicles.add("Motorcycle");
		vehicles.add("Hot Air Balloon");
		vehicles.add("Golf-cart");
		vehicles.add("Train");
		vehicles.add("Truck");
		vehicles.add("Canoe");
		vehicles.add("Skateboard");
		vehicles.add("Snowmobile");

		places.add("Niagara Falls");
		places.add("Ocean Reef");
		places.add("Pyramid");
		places.add("Jungle");
		places.add("Glacier");
		places.add("Moon");
		places.add("Great Wall");
		places.add("Volcano");
		places.add("Island");
		places.add("Tower");
		places.add("Beach");
		places.add("Cave");
		places.add("Castle");
		places.add("Savannah");
		places.add("Space Station");
		places.add("Mountain Top");

		sports.add("Soccer");
		sports.add("Hockey");
		sports.add("Basketball");
		sports.add("Baseball");
		sports.add("Tennis");
		sports.add("Football");
		sports.add("Cricket");
		sports.add("Bowling");
		sports.add("Curling");
		sports.add("Ultimate Frisbee");
		sports.add("Golf");
		sports.add("Boxing");
		sports.add("Archery");
		sports.add("Volleyball");
		sports.add("Rowing");
		sports.add("Water Polo");

		clothing.add("Hat");
		clothing.add("Pants");
		clothing.add("Shoes");
		clothing.add("Shirt");
		clothing.add("Backpack");
		clothing.add("Sun Glasses");
		clothing.add("Gloves");
		clothing.add("Socks");
		clothing.add("Jacket");
		clothing.add("Scarf");
		clothing.add("Space Suit");
		clothing.add("Cape");
		clothing.add("Dress");
		clothing.add("Fire suit");
		clothing.add("Swimsuit");
		clothing.add("Tuxedo");
		
		stuff.add("Gold");
		stuff.add("Television");
		stuff.add("Computers");
		stuff.add("Meteorite");
		stuff.add("Crown");
		stuff.add("Fossil");
		stuff.add("Sword");
		stuff.add("Telescope");
		stuff.add("Jet-pack");
		stuff.add("Lantern");
		stuff.add("Talisman");
		stuff.add("Gyroscope");
		stuff.add("Pottery");
		stuff.add("Totem Pole");
		stuff.add("Gong");
		stuff.add("Treasure Map");
	}
	
	//this functions adds all the Image Icon arrays references to a single ArrayList
	//allowing for easy access during the program, more specifically dynamic access
	//the above is also applied to category strings
	private void loadIndexes()
	{
		categoriesIcons.add(animalsIcons);
		categoriesIcons.add(foodsIcons);
		categoriesIcons.add(vehiclesIcons);
		categoriesIcons.add(placesIcons);
		categoriesIcons.add(sportsIcons);
		categoriesIcons.add(clothingIcons);
		categoriesIcons.add(stuffIcons);
		
		categoriesStrings.add(animals);
		categoriesStrings.add(foods);
		categoriesStrings.add(vehicles);
		categoriesStrings.add(places);
		categoriesStrings.add(sports);
		categoriesStrings.add(clothing);
		categoriesStrings.add(stuff);
	}
	
	/*
	 * loads all the pictures of a specific category, category is specified by a string "category"
	 * BufferedImage[] is where the current pictures of the sspecified category are stored
	 * At the same time the category Icons is also set so that loading of icons onto buttons is instant
	 * the for loop's length is 16, to load 16 pictures of the selected category into the program
	 */
	private void loadPictures(BufferedImage[] categoryImages, String category, ImageIcon[] categoryIcons) throws IOException
	{
		for(int i = 0; i < categoryImages.length; i++)
		{
			categoryImages[i] = ImageIO.read(getClass().getResource(category+"-"+i+".png"));
			categoryIcons[i] = new ImageIcon(categoryImages[i].getScaledInstance(150, 150, Image.SCALE_SMOOTH));
		}
	}
	
	/*
	 * goes through a for loop and changes all the buttons icons on display to the select category
	 * for example if categoryIcons was the animals Array of icons, you will see all 16 buttons 
	 * now containing 16 different animals
	 */
	private void setIcons(ImageIcon[] categoryIcons)
	{
		for(int i = 0; i < categoryIcons.length; i++)
			buttons.get(i).setIcon(categoryIcons[i]);
	}
	
	/*
	 * The rescale function will go through the currently loaded pictures and take in the newly resized buttons'
	 * width and hieght, and set the current images to those dimensions
	 * the way this happens however is actually a scaled version of the original picture is loaded as the
	 * new image icon rather than use the already set Image Icon
	 */
	private void rescalePictures(BufferedImage[] pictures)
	{
		for(int i = 0; i < buttons.size(); i++)
			buttons.get(i).setIcon(new ImageIcon(pictures[i].getScaledInstance(buttons.get(i).getWidth(), buttons.get(i).getHeight(), Image.SCALE_SMOOTH)));
	}
	
	/*
	 * This function is pretty important, it handles changing the state of the program, so SolutionIndex
	 * is the list of all the answers that is passed into this function, using that all the indexes are
	 * now set to meet that solutionIndexes information, including setting the strings for CSV output,
	 * setting frame2's window objects to this passwords, and even setting the right pictures to match 
	 * this password
	 * **this password means the passed on paramter in this function, which is always the new password to be
	 * displayed and interacted with
	 */
	private void setUpAllShowFunctions(int[] solutionIndex)
	{
		currSolutionsIndex = solutionIndex;
		setPassIndexes(solutionIndex);
		setPassStrings(solutionIndex);
		setShowPass();
		setShowPassPictures();
	}
	
	/*
	 * Sets all the variables for frame2's labels texts and strings
	 */
	private void setShowPass()
	{
		AnimalsCurrentLabel.setText(animalsPassString);
		FoodsCurrentLabel.setText(foodsPassString);
		VehiclesCurrentLabel.setText(vehiclesPassString);
		PlacesCurrentLabel.setText(placesPassString);
		SportsCurrentLabel.setText(sportsPassString);
		ClothingCurrentLabel.setText(clothingPassString);
		StuffCurrentLabel.setText(stuffPassString);
		SummaryListTextPane.setText(
				animalsPassString+"\n"
				+foodsPassString+"\n"
				+vehiclesPassString+"\n"
				+placesPassString+"\n"
				+sportsPassString+"\n"
				+clothingPassString+"\n"
				+stuffPassString+"\n");
	}
	
	/*
	 * sets all of frame2's pictures with the currently selected password
	 */
	private void setShowPassPictures()
	{
		  AnimalsCurrentPictureLabel.setIcon(animalsIcons[animalsPassIndex]);
		  FoodsCurrentPictureLabel.setIcon(foodsIcons[foodsPassIndex]);
		  VehiclesCurrentPictureLabel.setIcon(vehiclesIcons[vehiclesPassIndex]);
		  PlacesCurrentPictureLabel.setIcon(placesIcons[placesPassIndex]);
		  SportsCurrentPictureLabel.setIcon(sportsIcons[sportsPassIndex]);
		  ClothingCurrentPictureLabel.setIcon(clothingIcons[clothingPassIndex]);
		  StuffCurrentPictureLabel.setIcon(stuffIcons[stuffPassIndex]);
	}
	
	//meant for debugging and testing purposes, shows each array for all 3 randomly generated passwords
	private void printSolutionIndexes()
	{
		System.out.print("Passwords Solutions 1: [");
		for(int i=0; i < 7; i++)
		{
			System.out.print(solutionsIndex1[i]);
			if(i == 6)
				continue;
			System.out.print(",");
		}
		System.out.print("]"); System.out.println();
		
		System.out.print("Passwords Solutions 2: [");
		for(int i=0; i < 7; i++)
		{
			System.out.print(solutionsIndex2[i]);
			if(i == 6)
				continue;
			System.out.print(",");
		}
		System.out.print("]"); System.out.println();
		
		System.out.print("Passwords Solutions 3: [");
		for(int i=0; i < 7; i++)
		{
			System.out.print(solutionsIndex3[i]);
			if(i == 6)
				continue;
			System.out.print(",");
		}
		System.out.print("]"); System.out.println();
	}
	
	/*
	 * generate 3 random passwords, each saved in their respective array
	 * genPass is called 3 times here, also sets the boolean passGenerated to true
	 * to allow the program to progress, also shows a friendly message
	 */
	private void generatePasswords()
	{
		genPass(solutionsIndex1);
		genPass(solutionsIndex2);
		genPass(solutionsIndex3);
		passGenerated = true;
		printSolutionIndexes();
		JOptionPane.showMessageDialog(null, "PLEASE PRACTICE YOUR NEW PASSWORDS!", 
				"Generation Successful", 1);
	}
	
	//set the current password's solution String values
	//when passwords are switched this gets updated since both passwrods have different
	//elements contributed to them
	private void setPassStrings(int[] solutionIndex)
	{
		animalsPassString = animals.get(solutionIndex[0]);
		foodsPassString = foods.get(solutionIndex[1]);
		vehiclesPassString = vehicles.get(solutionIndex[2]);
		placesPassString = places.get(solutionIndex[3]);
		sportsPassString = sports.get(solutionIndex[4]);
		clothingPassString = clothing.get(solutionIndex[5]);
		stuffPassString = stuff.get(solutionIndex[6]);
	}
	
	//similar as the above function, however this only sets the integer index values
	private void setPassIndexes(int[] solutionsIndex)
	{
		animalsPassIndex = solutionsIndex[0];
		foodsPassIndex = solutionsIndex[1];
		vehiclesPassIndex = solutionsIndex[2];
		placesPassIndex = solutionsIndex[3];
		sportsPassIndex = solutionsIndex[4];
		clothingPassIndex = solutionsIndex[5];
		stuffPassIndex = solutionsIndex[6];
	}
	
	/*
	 * This function first randomly generates a password, and then
	 * sets the password to the passed in parameter, commonly
	 * this would be one of the 3 Password # arrays that's passed
	 * in here
	 */
	private void genPass(int[] solutionsIndex)
	{
		//a->b,b->c, consider a->c
		animalsPassIndex = random.nextInt(16);
		foodsPassIndex = random.nextInt(16);
		vehiclesPassIndex = random.nextInt(16);
		placesPassIndex = random.nextInt(16);
		sportsPassIndex = random.nextInt(16);
		clothingPassIndex = random.nextInt(16);
		stuffPassIndex = random.nextInt(16);
		
		solutionsIndex[0] = animalsPassIndex;
		solutionsIndex[1] = foodsPassIndex;
		solutionsIndex[2] = vehiclesPassIndex;
		solutionsIndex[3] = placesPassIndex;
		solutionsIndex[4] = sportsPassIndex;
		solutionsIndex[5] = clothingPassIndex;
		solutionsIndex[6] = stuffPassIndex;
	}
	
	/*
	 * A simple but powerful function, it's the driving force of this application, acts
	 * more like a router actually, so when a button is pressed it calls this function
	 * and passes in an integer, that integer represents the button's own number or rather
	 * more like index actually since they are from button_0 to button_15.
	 * This function also changes the state of the program and proceeds, so if it's state 1
	 * for practice and a button is pressed simply pass that button's number into the practice
	 * function to continue practicing, remember this is a state-based/asynchronous program.
	 * If state is 2, then test, if state is 0 then simply dont do anything.
	 */
	private void buttonPressed(int buttonNum)
	{
		if(state == 0) //0 is nothing
			return;
		else if(state == 1) //1 is practice
			practice(buttonNum);
		else if(state == 2) //2 is test
			test(buttonNum);
	}
	
	/*
	 * The practice function is a bit long because its more user interactive however it's still
	 * fairly simple. First time called it initializes the setting of the window, like disabling buttons,
	 * changing window name, etc. The every time a button is clicked the number is stored, and the state
	 * is incremented and will match a new if-else conditional statements below. So for example if our state
	 * is currently 0, the program will go down the code and see there is an if statement that matches
	 * "state == 0", it will then run that code which will change the icons to the next category which is 
	 * Animals, then it will set pictures and categories so other functions have a proper reference
	 * since the state is changing and finally the header name is changed on that window.
	 * 
	 * Now this will continue on throughout 0 to 6, once 7 is reached it will go through the array of
	 * answers and check which is right or wrong, finally the program will output a detailed information
	 * window showing you for every category what you answered, the correct answer, and a little X beside
	 * each line indicating what you got right or wrong as a quick way of looking right at your mistakes
	 * 
	 * After that the code will reset all the variables and all the states back to normal, restore the
	 * buttons and everything else.
	 */
	private void practice(int buttonClicked)
	{
		//practiceSessionState must be -1 and 7
		if(practiceSessionState == -1)
		{
			TestButton.setVisible(false);
			GenPassButton.setVisible(false);
			PracticeButton.setVisible(false);
			frame2.setTitle("[Practice Session] - Password "+currentPassword);
			frame2.setVisible(true);
		}
		
		if(buttonClicked != -1)
			practiceAnswers[practiceSessionState] = buttonClicked;
		
		practiceSessionState++;
		
		if(practiceSessionState == 7)
		{
			String[] tempArr = new String[7];
			for(int i=0; i < tempArr.length; i++)
			{
				if(practiceAnswers[i] != currSolutionsIndex[i])
					tempArr[i] = "X";
				else if(practiceAnswers[i] == currSolutionsIndex[i])
					tempArr[i] = "";
			}
			JOptionPane.showMessageDialog(null, 
					 "[Animals] Answer: "+animals.get(animalsPassIndex)+", You Chose: "+animals.get(practiceAnswers[0])+" ["+tempArr[0]+"]"+"\n"
					+"[Foods] Answer: "+foods.get(foodsPassIndex)+", You Chose: "+foods.get(practiceAnswers[1])+" ["+tempArr[1]+"]"+"\n"
					+"[Vehicles] Answer: "+vehicles.get(vehiclesPassIndex)+", You Chose: "+vehicles.get(practiceAnswers[2])+" ["+tempArr[2]+"]"+"\n"
					+"[Places] Answer: "+places.get(placesPassIndex)+", You Chose: "+places.get(practiceAnswers[3])+" ["+tempArr[3]+"]"+"\n"
					+"[Sports] Answer: "+sports.get(sportsPassIndex)+", You Chose: "+sports.get(practiceAnswers[4])+" ["+tempArr[4]+"]"+"\n"
					+"[Clothing] Answer: "+clothing.get(clothingPassIndex)+", You Chose: "+clothing.get(practiceAnswers[5])+" ["+tempArr[5]+"]"+"\n"
					+"[Stuff] Answer: "+stuff.get(stuffPassIndex)+", You Chose: "+stuff.get(practiceAnswers[6])+" ["+tempArr[6]+"]"+"\n",
					"RESULTS - [X] indicates incorrect", 1);
			for(int i=0; i < 7; i++)
				practiceAnswers[i] = -1;
			TestButton.setVisible(true);
			GenPassButton.setVisible(true);
			PracticeButton.setVisible(true);
			practiceSessionState = -1;
			state = 0;
			frame.setTitle("Welcome to Pictanator");
			frame2.setTitle("Your Password");
			return;
		}
		
		System.out.println("Solution: "+currSolutionsIndex[practiceSessionState]);
		
		if(practiceSessionState == 0)
		{
			setIcons(animalsIcons); 
			pictures = animalsImages; 
			category = animals;
			frame.setTitle("[Practice Session] - Answer: "+animals.get(animalsPassIndex));
		}
		else if(practiceSessionState == 1)
		{
			setIcons(foodsIcons); 
			pictures = foodsImages; 
			category = foods;
			frame.setTitle("[Practice Session] - Answer: "+foods.get(foodsPassIndex));
		}
		else if(practiceSessionState == 2)
		{
			setIcons(vehiclesIcons); 
			pictures = vehiclesImages; 
			category = vehicles;
			frame.setTitle("[Practice Session] - Answer: "+vehicles.get(vehiclesPassIndex));
		}
		else if(practiceSessionState == 3)
		{
			setIcons(placesIcons); 
			pictures = placesImages; 
			category = places;
			frame.setTitle("[Practice Session] - Answer: "+places.get(placesPassIndex));
		}
		else if(practiceSessionState == 4)
		{
			setIcons(sportsIcons); 
			pictures = sportsImages; 
			category = sports;
			frame.setTitle("[Practice Session] - Answer: "+sports.get(sportsPassIndex));
		}
		else if(practiceSessionState == 5)
		{
			setIcons(clothingIcons); 
			pictures = clothingImages; 
			category = clothing;
			frame.setTitle("[Practice Session] - Answer: "+clothing.get(clothingPassIndex));
		}
		else if(practiceSessionState == 6)
		{
			setIcons(stuffIcons); 
			pictures = stuffImages; 
			category = stuff;
			frame.setTitle("[Practice Session] - Answer: "+stuff.get(stuffPassIndex));
		}
	}
	
	/*
	 * This next piece of code was a little complicated to write mainly because the
	 * order of the logic must be perfect in order to ensure nothing gets broken in 
	 * the process during run-time while taking into account all the possible changes
	 * that may happen during run-time. The way this function works is that it will take
	 * currently random ordered list of passwords. 
	 * 
	 * When (inside Test function) the current
	 * Password being tested has been fully submitted and the program wants to move on to
	 * the next password it calls this function below. The function will check what's the
	 * next password based on the state variables and setUp that password to the program
	 * accordingly.
	 * 
	 * This function also takes into account the phase, so after doing the first set of
	 * testing 3 passwords in random order, the code will detect that we need another round
	 * of the same passwords but in again in a random order different from the first order.
	 */
	private void setNextTest()
	{
		if(listOfSols.get(testPassNum) == 1)
		{
			currentPassword = 1;
			setUpAllShowFunctions(solutionsIndex1);
		}
		else if(listOfSols.get(testPassNum) == 2)
		{
			currentPassword = 2;
			setUpAllShowFunctions(solutionsIndex2);
		}
		else if(listOfSols.get(testPassNum) == 3)
		{
			currentPassword = 3;
			setUpAllShowFunctions(solutionsIndex3);	
		}
		
		if(testPassNum == 2)
		{
			testPassNum = 0;
			phaseNum++;
			Collections.shuffle(listOfSols);
			System.out.println(listOfSols);
			if(phaseNum == 2)
				JOptionPane.showMessageDialog(null, "Round 1 of 2 complete, last round.");
			setNextTest();
		}
		else if(testPassNum < 3)
			testPassNum++;
	}
	
	/*
	 * The function takes the time different between when the user gets to see the images
	 * of the current category until they press on one of the images. So basically time 
	 * per category, in seconds.
	 */
	private double timeSpentCategory()
	{
		timeEnd = System.currentTimeMillis();
		Long result = timeEnd - timeStart;
		double intResult = result.doubleValue();
		timeStart = System.currentTimeMillis();
		return (intResult/1000);
	}
	
	/*
	 * The same function as above, also in seconds, however this time start at when the user
	 * started the password, and ends when the user answered all 7 categories, so basically
	 * time per password.
	 */
	private double timeSpentTest()
	{
		timeEnd2 = System.currentTimeMillis();
		Long result = timeEnd2 - timeStart2;
		double intResult = result.doubleValue();
		timeStart2 = System.currentTimeMillis();
		return (intResult/1000);
	}
	
	/*
	 * This is where the fun happens, perhaps the most complicated function written here due
	 * to meet the requirements of the project. So the function will first shuffle the password
	 * order and change the states to start showing the categories and await input, just like practice.
	 * 
	 * However there are several differences here, once the user has inputted the password the function 
	 * will check to see if its correct, if it is then move on to the next password, if not then try again,
	 * if the user fails 3 times thee program will move on to the next password. This above is repeated for
	 * each password. Once that's done the whole process will be repeated again but with a different order
	 * for the passwords. Now this is tricky because there are so many things to update and keep track of
	 * it's still not perfected (look at the document, Part 2, Queston 3). Despite that however it's still
	 * currently very stable and it's best to keep it that way for now.
	 */
	private void test(int buttonClicked)
	{	
		String result = null;
		if(firstTimeTest)
		{
			firstTimeTest = false;
			Collections.shuffle(listOfSols);
			System.out.println(listOfSols);
			setNextTest();
		}
		
		if(testSessionState == -1)
		{
			frame2.setVisible(false);
			GenPassButton.setVisible(false);
			ShowPassButton.setVisible(false);
			PracticeButton.setVisible(false);
			TestButton.setVisible(false);
			frame2.setTitle("[Testing Session Password "+currentPassword+"] - Your Password");
		}
		
		if(buttonClicked != -1)
		{
			testAnswers[testSessionState] = buttonClicked;
			
			if(currSolutionsIndex[testSessionState] != testAnswers[testSessionState])
			result = "Failure";
			else if (currSolutionsIndex[testSessionState] == testAnswers[testSessionState])
			result = "Success";
		}
		
		testSessionState++;
		
		if(testSessionState == 7)
		{
			writeTo(userid,"Password "+currentPassword,"Stuff",result,stuff.get(testAnswers[6]),
					stuff.get(stuffPassIndex),timeSpentCategory());
			
			int wrongValues = 0;
			for(int i=0; i < 7; i++) //save testAnswers before clearing here
			{
				if(currSolutionsIndex[i] != testAnswers[i])
					wrongValues++;
			}
			
			if(wrongValues > 0)
			{
				writeTo(userid,"Password "+currentPassword,"Total/Overall","Failure","N/A","N/A",timeSpentTest());
				
				if(chances <= 0)
				{
					testSessionState = 0;
					chances = 2;
					setNextTest();
					JOptionPane.showMessageDialog(null, "Sorry you didn't do well, moving on to next test.");
				}
				else if(chances > 0)
				{
					chances--;
					testSessionState = 0;
					JOptionPane.showMessageDialog(null, "Incorrect, please try again.\n"
						+ "Chances Left: "+(chances+1));
				}
			}
			else if (wrongValues == 0)
			{
				writeTo(userid,"Password "+currentPassword,"Total/Overall","Success","N/A","N/A",timeSpentTest());
				
				testSessionState = 0;
				chances = 2;
				setNextTest();
				JOptionPane.showMessageDialog(null, "Great job! Moving on to next password.");
			}
			
			if(phaseNum > 2)
			{
				JOptionPane.showMessageDialog(null, 
						 "Thank you so much for participating,\n"
						+ "your data is invaluable to us!", "TEST COMPLETE", 1);
				GenPassButton.setVisible(true);
				ShowPassButton.setVisible(true);
				PracticeButton.setVisible(true);
				TestButton.setVisible(true);			
				testSessionState = -1;
				firstTimeTest = true;
				chances = 2;
				state = 0;
				phaseNum = 0;
				userid++;
				frame.setTitle("Welcome to Pictanator");
				return;
			}
			testSessionState = 0;//consider removing if error
			frame2.setTitle("[Testing Session Password "+currentPassword+"] - Your Password");
		}
		
		if(testSessionState == 0)
		{
			timeStart = System.currentTimeMillis();
			timeStart2 = System.currentTimeMillis();
			setIcons(animalsIcons); 
			pictures = animalsImages; 
			category = animals;
			frame.setTitle("[Testing Session Password "+currentPassword+"] - Category: Animals");
			System.out.println("Solution: "+currSolutionsIndex[testSessionState]+" "+animals.get(animalsPassIndex));
		}
		else if(testSessionState == 1)
		{
			writeTo(userid,"Password "+currentPassword,"Animals",result,animals.get(testAnswers[0]),
					animals.get(animalsPassIndex),timeSpentCategory());
			setIcons(foodsIcons); 
			pictures = foodsImages; 
			category = foods;
			frame.setTitle("[Testing Session Password "+currentPassword+"] - Category: Foods");
			System.out.println("Solution: "+currSolutionsIndex[testSessionState]+" "+foods.get(foodsPassIndex));
		}
		else if(testSessionState == 2)
		{
			writeTo(userid,"Password "+currentPassword,"Foods",result,foods.get(testAnswers[1]),
					foods.get(foodsPassIndex),timeSpentCategory());
			setIcons(vehiclesIcons); 
			pictures = vehiclesImages; 
			category = vehicles;
			frame.setTitle("[Testing Session Password "+currentPassword+"] - Category: Vehicles");
			System.out.println("Solution: "+currSolutionsIndex[testSessionState]+" "+vehicles.get(vehiclesPassIndex));
		}
		else if(testSessionState == 3)
		{
			writeTo(userid,"Password "+currentPassword,"Vehicles",result,vehicles.get(testAnswers[2]),
					vehicles.get(vehiclesPassIndex),timeSpentCategory());
			setIcons(placesIcons); 
			pictures = placesImages; 
			category = places;
			frame.setTitle("[Testing Session Password "+currentPassword+"] - Category: Places");
			System.out.println("Solution: "+currSolutionsIndex[testSessionState]+" "+places.get(placesPassIndex));
		}
		else if(testSessionState == 4)
		{
			writeTo(userid,"Password "+currentPassword,"Places",result,places.get(testAnswers[3]),
					places.get(placesPassIndex),timeSpentCategory());
			setIcons(sportsIcons); 
			pictures = sportsImages; 
			category = sports;
			frame.setTitle("[Testing Session Password "+currentPassword+"] - Category: Sports");
			System.out.println("Solution: "+currSolutionsIndex[testSessionState]+" "+sports.get(sportsPassIndex));
		}
		else if(testSessionState == 5)
		{
			writeTo(userid,"Password "+currentPassword,"Sports",result,sports.get(testAnswers[4]),
					sports.get(sportsPassIndex),timeSpentCategory());
			setIcons(clothingIcons); 
			pictures = clothingImages; 
			category = clothing;
			frame.setTitle("[Testing Session Password "+currentPassword+"] - Category: Clothing");
			System.out.println("Solution: "+currSolutionsIndex[testSessionState]+" "+clothing.get(clothingPassIndex));
		}
		else if(testSessionState == 6)
		{
			writeTo(userid,"Password "+currentPassword,"Clothing",result,clothing.get(testAnswers[5]),
					clothing.get(clothingPassIndex),timeSpentCategory());
			setIcons(stuffIcons); 
			pictures = stuffImages; 
			category = stuff;
			frame.setTitle("[Testing Session Password "+currentPassword+"] - Category: Stuff");
			System.out.println("Solution: "+currSolutionsIndex[testSessionState]+" "+stuff.get(stuffPassIndex));
		}
	}
	
	/*
	 * This function is simple, has 2 parts to it. First it reads in a csv file names "dataOutput.csv"
	 * if the file doesn't exist it will simply return with an IOException but the program will keep
	 * running, if the file does exist then it will go to the last line and read the first four characters, 
	 * those character are the digits of the user id. 
	 * If the user id exists then simply set the program to that user id and increment it,
	 * this way no data is damaged and writing to the file will simply append to it.
	 */
	private void setUserID()
	{
		BufferedReader br = null;
		try {
			String sCurrentLine;
		    String lastLine = "";
		    
		    br = new BufferedReader(new FileReader("dataOutput.csv"));

		    while ((sCurrentLine = br.readLine()) != null) 
		    {
		        lastLine = sCurrentLine;
		    }
		    System.out.println("Last Line Here: "+lastLine.substring(0, 4));
		    System.out.println("Before: "+userid);
		    userid = Integer.parseInt(lastLine.substring(0, 4));
		    userid++;
		    System.out.println("After: "+userid);
		} catch (IOException e) {
		    e.printStackTrace();
		    System.out.println("*** File Not Found! Creating a new one. ***");
		}
		 catch (NumberFormatException n) {
			 System.out.println("Existing ID not found, seems like only headers are found or its empty.");
			 return;
		 }
		 finally {
		    try {
		        if (br != null)br.close();
		    } catch (IOException ex) {
		        ex.printStackTrace();
		    }
		}
	}
	
	/*
	 * This function will simply put all the paramters into one string and write it to the
	 * named file below. If it doesn't exists then it will create one, if it does exist
	 * then it will append to it on the next line.
	 */
	private void writeTo(int userid, String currPass, String cat, String succ_fail,
			String answer, String corrAnswer, double time)
	{
	String text = userid+","+currPass+","+cat+","+succ_fail+","+answer+","+corrAnswer+","+time;
		try(FileWriter fw = new FileWriter("dataOutput.csv", true);
			    BufferedWriter bw = new BufferedWriter(fw);
			    PrintWriter out = new PrintWriter(bw))
			{
			    out.println(text);
			    out.close();
			} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * Same as above but this function takes in just a string, overloaded function.
	 */
	private void writeTo(String text)
	{
		try(FileWriter fw = new FileWriter("dataOutput.csv", true);
			    BufferedWriter bw = new BufferedWriter(fw);
			    PrintWriter out = new PrintWriter(bw))
			{
			    out.println(text);
			    out.close();
			} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * The automate functions, basically the only function called in the
	 * constructor after initialize() and initialize2() are called. This
	 * will initialize a few more things, load in all the category element
	 * names, load all the pictures, the indexes, calls setUserID to set
	 * the user ID appropiately and if no file UserId was set then it
	 * will call the writeTo function above to print the headers of the 
	 * CSV file, and setting the window title.
	 * This wraps up everything that needs to be taken into account.
	 */
	private void automate() throws IOException
	{
		listOfSols.add(1);
		listOfSols.add(2);
		listOfSols.add(3);
		loadCategoryElementNames();
		loadAllPictures();
		loadIndexes();
		setUserID();
		if(userid == 1000)
			writeTo("UserID,CurrentPassword,Category,Success/Failure,Answer,CorrectAnswer,TimeSpent");
		frame.setTitle("Welcome to Pictanator");
	}
}
