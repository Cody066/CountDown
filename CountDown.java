import java.awt.Color;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.awt.Font;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;





/* Created By Cody MacLean
 * Date : Oct 20 2018
 * Purpose : Countdown timer, stops when correct password entered
 */


	public class CountDown extends JFrame implements KeyListener, ActionListener, Runnable{
		

		private JButton btnSet;
		private JLabel lblCountdownSec;
		private JLabel lblBreak;
		private JLabel lblPassSet;
		private JLabel lblWin;
		private Container content;
		private int sec;
		private int min;
		private Thread t1;
		private JPasswordField txtPass;
		private Minutes DisplayMins;
		private Minutes DisplaySecs;
		private String password;
		private boolean found;
		private JLabel lblLose;
		

		
		public boolean isFound() {
			return found;
		}

		public void setFound(boolean found) {
			this.found = found;
		}



		
		public CountDown() {
		
			min = 60;
			sec = 60;
			
			Font font = new Font(Font.MONOSPACED, Font.BOLD, 50);
			Font fontbig = new Font(Font.MONOSPACED, Font.BOLD, 200);
			Font fontsm = new Font(Font.MONOSPACED, Font.BOLD, 20);
			txtPass = new JPasswordField(200);
			DisplayMins = new Minutes();
			DisplaySecs = new Minutes();
			lblCountdownSec = new JLabel();
			lblBreak = new JLabel(" : ");
			lblPassSet = new JLabel();
			lblWin = new JLabel();
			lblLose = new JLabel ();
			
	
			
			btnSet = new JButton("START");
			password = new String("PLaceHolder");
		
			
			
			btnSet.setFont(fontsm);
			btnSet.setLocation(470,300);
			btnSet.setSize(250,40);
			btnSet.setForeground(Color.BLACK);
			btnSet.setBackground(Color.RED);
			

			lblCountdownSec.setLocation(270, 30); //location of clock	

			lblCountdownSec.setFont(fontbig);
			lblCountdownSec.setForeground(Color.RED);
			lblBreak.setForeground(Color.RED);
			lblBreak.setLocation(220,200);
			lblBreak.setSize(10,10);
			

			lblCountdownSec.setSize(1000,1000); // size counter fits in 
			
			lblPassSet.setText("Enter Password");
			lblPassSet.setForeground(Color.RED);
			lblPassSet.setSize(600, 200); //enter password width then height 
			lblPassSet.setLocation(470,50);
			lblPassSet.setFont(font);
			txtPass.setLocation(470, 200);
			txtPass.setSize(420,70); //text box for password
			txtPass.setForeground(Color.RED);
			txtPass.setBackground(Color.GRAY);
			txtPass.setFont(font);
			
			lblWin.setText("<html><center>The antidote has successfully <br> been broadcasted.</html>");
			lblWin.setForeground(Color.WHITE);
			lblWin.setSize(2000, 400); 
			lblWin.setLocation(250,150);
			lblWin.setFont(font);
			
			
			lblLose.setText("<html><center>The antidote file has been erased.</html>");
			lblLose.setForeground(Color.WHITE);
			lblLose.setSize(2000, 300); 
			lblLose.setLocation(250,150);
			lblLose.setFont(font);
		
			
		btnSet.addActionListener(this);	


		add(lblCountdownSec);
		add(lblPassSet);
		add(txtPass);
		add(btnSet);
		

		content = getContentPane();
		setSize(500,500);
		setLayout(null);
		content.setBackground(Color.BLACK);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		this.setUndecorated(true);
		this.setVisible(true);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	
		
		}
	
		public static void main(String[] args) {
			// TODO Auto-generated method stub
			
			
			
			
			
		CountDown TimeDown = new CountDown();
		TimeDown.setVisible(true);
		TimeDown.setResizable(false);
			
		}
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				
				
				if (e.getSource() == btnSet) {
					password = txtPass.getText();
					txtPass.setText(null);
					btnSet.setEnabled(false);
					btnSet.setVisible(false);
					System.out.println(password);
					t1 = new Thread(this, "Timer");
					t1.start();
				}
			}
	
	
	
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
	
	
	
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
	
	
	
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void run() {
				found = false;

			
				while (!found) {
		
					for(min = 59; min >= 0; min --) {
						for(sec = 59; sec >= 0; sec --) {
							DisplaySecs.setDisplay(sec);
							DisplayMins.setDisplay(min);
							
						if (sec <10 && (!found) ) {
							lblCountdownSec.setText(String.valueOf(DisplayMins.getDisplay()) +" : 0" + String.valueOf((DisplaySecs.getDisplay())));
						
						} else if (!found) {
							lblCountdownSec.setText(String.valueOf(DisplayMins.getDisplay()) +" : " + String.valueOf((DisplaySecs.getDisplay())));
						}
						
						System.out.println("Txt" + txtPass.getText());
						System.out.println("Pass word " + password);
							
						if (min ==0 && sec == 0) {
							add(lblLose);
							remove(lblPassSet);
							remove(txtPass);
							this.setFound(true);
							System.out.println("Found");
							
					
							//insert try catch loop for sound here 
							
						    try {
						         // Open an audio input stream.           
						          File soundFile = new File("C:/Windows/media/air_raid.wav"); //you could also get the sound file with an URL
						          AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);              
						         // Get a sound clip resource.
						         Clip clip = AudioSystem.getClip();
						         // Open audio clip and load samples from the audio input stream.
						         clip.open(audioIn);
						         clip.start();
						      } catch (UnsupportedAudioFileException e) {
						         e.printStackTrace();
						      } catch (IOException e) {
						         e.printStackTrace();
						      } catch (LineUnavailableException e) {
						         e.printStackTrace();
						
						}
						}
						  
				
					   if (password.trim().equals((txtPass.getText().trim()))) {
								this.setFound(true);
								System.out.println("Found");
								add(lblWin);
								remove(lblPassSet);
								remove(txtPass);
								
							    try {
							         // Open an audio input stream.           
							          File soundFile = new File("C:/Windows/media/Alarm05.wav"); //you could also get the sound file with an URL
							          AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);              
							         // Get a sound clip resource.
							         Clip clip = AudioSystem.getClip();
							         // Open audio clip and load samples from the audio input stream.
							         clip.open(audioIn);
							         clip.start();
							      } catch (UnsupportedAudioFileException e) {
							         e.printStackTrace();
							      } catch (IOException e) {
							         e.printStackTrace();
							      } catch (LineUnavailableException e) {
							         e.printStackTrace();
							      }
								break;
			
					    }
								try
								{
								    Thread.sleep(1000);
								}
								catch(InterruptedException ex)
								{
								    Thread.currentThread().interrupt();
								}
							}
					 
					
					
					
							DisplayMins.setDisplay(min); 
							if (password.trim().equals((txtPass.getText().trim()))) {
							this.setFound(true);
							System.out.println("Found");
							break;
							
							}	
						}
					}
				}
			}
		


	

