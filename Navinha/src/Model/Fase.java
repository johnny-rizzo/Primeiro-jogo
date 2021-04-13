package Model;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Fase extends JPanel implements ActionListener {

	private Image fundo;
	private Player player;
	private Timer timer;
    private List <Enemy1> enemy1;
    private boolean emJogo;
    
    
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
		
		inicializainimigos();
		emJogo = true;
	}
	
	public void inicializainimigos() {
		int cordenadas [] = new int [40];
		enemy1 = new ArrayList<Enemy1>();
		
		for (int i = 0; i < cordenadas.length; i++) {
			int x = (int) (Math.random() * 8000+1024);
			int y = (int) (Math.random() * 650+30);
			enemy1.add(new Enemy1 (x,y));
		}
	}

	public void paint(Graphics g) {
		Graphics2D graficos = (Graphics2D) g;
		if (emJogo == true) {
		graficos.drawImage(fundo, 0, 0, null);
		graficos.drawImage(player.getImagem(), player.getX(), player.getY(), this);

		List<Tiro> tiros = player.getTiros();
		for (int i = 0; i < tiros.size(); i++) {
			Tiro m = tiros.get(i);
			m.load();
			graficos.drawImage(m.getImagem(), m.getX(), m.getY(), this);
		}
		
		for (int o = 0; o < enemy1.size(); o++) {
			Enemy1 in = enemy1.get(o);
			in.load();
			graficos.drawImage(in.getImagem(), in.getX(), in.getY(),this);
		}
		}
		else {
			ImageIcon fimjogo = new ImageIcon ("ress\\png-clipart-es-game-over-text-thumbnail.png");
			graficos.drawImage(fimjogo.getImage(), 0, 0, null);
		}
		

		g.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
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

		for (int o = 0; o < enemy1.size(); o++) {
			Enemy1 in = enemy1.get(o);
			if (in.isIsvisivel()) {
				in.update();
			}else {
				enemy1.remove(o);
			}
			
		}
		checarColisoes();
		
		repaint();
	}
	
	public void checarColisoes() {
		Rectangle formanave= player.getBounds();
		Rectangle formaenemy1;
		Rectangle formatiro;
		
		for (int i = 0; i < enemy1.size(); i++) {
			 Enemy1 tempEnemy1 = enemy1.get(i);
			 formaenemy1 = tempEnemy1.getBounds();
			 if (formanave.intersects(formaenemy1)) {
				 player.setVisivel(false);
				 tempEnemy1.setIsvisivel(false);
				 emJogo = false;
		}	 
	}
		
		List<Tiro> tiros = player.getTiros();
		for (int j = 0; j < tiros.size(); j++) {
		Tiro tempTiro = tiros.get(j);
		formatiro = tempTiro.getBounds();
		for (int o = 0; o < enemy1.size(); o++) {
		Enemy1 tempenemy1 = enemy1.get(o);
		 formaenemy1 = tempenemy1.getBounds();
		 if (formatiro.intersects(formaenemy1)){
			 tempenemy1.setIsvisivel(false);
			 tempTiro.setIsvisivel(false);
		    }
		  }
	   }
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

