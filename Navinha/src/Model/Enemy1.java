package Model;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Enemy1 {

	private Image imagem;
	private int x,y;
	private int largura, altura;
	private boolean isvisivel;
	
	//private static final int Largura= 938;
	private static int Velocidade= 2; //VELOCIDADE DO TIRO
	
	public Enemy1(int x,int y) {
		this.x = x;
		this.y = y;
		isvisivel = true;
	}
	
	public void load(){
		ImageIcon referencia = new ImageIcon("res\\enemy1.png");
		imagem = referencia.getImage();
		
		this.largura = imagem.getWidth(null);
		this.altura = imagem.getHeight(null);
	}
	
	public void update() {
		this.x -= Velocidade;   //VELOCIDADE DO TIRO
	        //if(this.x > Largura) {
	        	//isvisivel = false;
	        //}
	}
	
	public Rectangle getBounds() {
		return new Rectangle (x,y, largura, altura);
	}


	public boolean isIsvisivel() {
		return isvisivel;
	}

	public void setIsvisivel(boolean isvisivel) {
		this.isvisivel = isvisivel;
	}

	public static int getVelocidade() {
		return Velocidade;
	}

	public static void setVelocidade(int velocidade) {
		Velocidade = velocidade;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Image getImagem() {
		return imagem;
	}

	
}
