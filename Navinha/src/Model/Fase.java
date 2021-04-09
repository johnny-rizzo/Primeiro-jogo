package Model;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

import Meujogo.Player;

public class Fase extends JPanel implements ActionListener {

	private Image fundo;
	private Player player;
	private Timer timer;

	public Fase() {

		setFocusable(true);
		setDoubleBuffered(true);
		ImageIcon referencia = new ImageIcon("res\\background-preto.jpg");
		fundo = referencia.getImage();

		player = new Player();
		player.load();

		addKeyListener(new TecladoAdapter());
		timer = new Timer(5, this); // velocidade do jogo, aumenta o 5 e aumenta a velocidade
		timer.start();
	}

	public void paint(Graphics g) {
		Graphics2D graficos = (Graphics2D) g;
		graficos.drawImage(fundo, 0, 0, null);
		graficos.drawImage(player.getImagem(), player.getX(), player.getY(), this);

		List<Tiro> tiros = player.getTiros();
		for (int i = 0; i < tiros.size(); i++) {
			Tiro m = tiros.get(i);
			m.load();
			graficos.drawImage(m.getImagem(), m.getX(), m.getY(), this);
		}

		g.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		player.update();

		List<Tiro> tiros = player.getTiros();
		for (int i = 0; i < tiros.size(); i++) {
			Tiro m = tiros.get(i);
			if (m.isIsvisivel()) {
				m.update();
			} else {
				tiros.remove(i);
			}
		}

		repaint();
	}

	private class TecladoAdapter extends KeyAdapter {

		@Override

//   instancia keyReleased "que pode esta escrito errado" tem que esta como está na classe play!!!!!
		public void keyPressed(KeyEvent e) {
			player.keyPressed(e);
		}

		@Override

//////  o nome  keyReleased confira se estar correto!!

		public void keyReleased(KeyEvent e) {
			player.keyRealease(e);
		}

	}

}
