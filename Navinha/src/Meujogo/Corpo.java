package Meujogo;

import javax.swing.JFrame;

import Model.Fase;

public class Corpo extends JFrame {
	public Corpo() {
		
		add(new Fase());
		setTitle("meu jogo");
		setSize(1024,728);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		this.setResizable(false);
		setVisible(true);
	}
	public static void main (String [] args) {
		new Corpo();
	}
}
