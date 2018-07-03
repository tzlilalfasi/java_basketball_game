import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class BasketBallPanel extends JPanel{

	private JButton cmdLeft, cmdRight, cmdPauseResume;
	private JLabel lblPoints;
	private Timer timer; 
	private int counter; //counts the number of times the "PauseResume" button has pressed 
	private Timer t;
	private BasketBallLogic b;
	private boolean isPlaying; // a boolean variable that prevents the "b" object from repainting

	public BasketBallPanel(){
		b=new BasketBallLogic(getWidth(), getHeight());
		cmdLeft=new JButton("Left");
		cmdRight=new JButton("Right");
		cmdPauseResume=new JButton("Pause");
		lblPoints=new JLabel("Points: 0");
		counter=0;
		isPlaying=false;


		JPanel down= new JPanel(); //a flow layout for the buttons
		down.add(cmdLeft);
		down.add(cmdRight);
		down.add(cmdPauseResume);

		setLayout(new BorderLayout()); // a border layout that holds the "down" panel and the label "points" 
		add(down, BorderLayout.SOUTH);
		add(lblPoints, BorderLayout.NORTH);

		MyListener l = new MyListener();
		TL TimerListener = new TL();
		t = new Timer(200, TimerListener);
		cmdLeft.addActionListener(l);
		cmdRight.addActionListener(l);
		cmdPauseResume.addActionListener(l);
		t.start();
	}

	public void paintComponent(Graphics g){
		super.paintComponent(g);
		if(!isPlaying)
			b=new BasketBallLogic(getWidth(), getHeight());
		b.draw(g);
		this.setBackground(Color.WHITE);
	}
	
	private class TL implements ActionListener{  // a timer listener
		public void actionPerformed(ActionEvent arg0) {
			isPlaying=true;
			int num=b.step();
			if(counter %2 ==0)
				repaint();
			if (num == 1)
				lblPoints.setText("Points: " + b.getPoints());
			if (num == 2){
				JOptionPane.showMessageDialog(null, "The game is over!"+"\n "+"Your total number of points: "+b.getPoints(),"Game Over",JOptionPane.INFORMATION_MESSAGE);
				if (JOptionPane.OK_OPTION ==0 ){
					b=new BasketBallLogic(getWidth(), getHeight());
					lblPoints.setText("Points: " + b.getPoints());
				}
			}
		}

	}

	private class MyListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			int num=b.step();
			if(e.getSource()== cmdLeft && counter %2 ==0){
				b.moveLeft();
				repaint();
			}
			if(e.getSource() == cmdRight && counter %2 ==0){
				b.moveRight();
				repaint();
			}
			if(e.getSource() == cmdPauseResume){
				counter++;
				if(counter %2 ==1){
					t.stop();
					cmdPauseResume.setText("Resume");}
				else{
					t.start();
					cmdPauseResume.setText("Pause");
				}
			}
		}
	}
}
