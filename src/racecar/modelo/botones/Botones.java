package racecar.modelo.botones;

import javax.swing.JLabel;

public class Botones {
	
	private JLabel btn;
	private Integer x;
	private Integer y;
	private Integer h;
	private Integer w;
	
	public Botones(Integer x, Integer y, Integer h, Integer w)
	{
		this.x = x;
		this.y = y;
		this.h = h;
		this.w = w;
		this.btn = new JLabel();
		btn.setBounds(x, y, h, w);
	}
	public JLabel getBoton()
	{
		return(this.btn);
	}
	public void pulsar() //Efecto de boton apretado
	{
		
	}
}
