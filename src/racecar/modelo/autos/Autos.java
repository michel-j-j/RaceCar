package racecar.modelo.autos;
import javax.swing.JLabel;

public abstract class Autos {
	
	private JLabel auto;
	private Integer x;
	private Integer y;
	private Integer h;
	private Integer w;
	public Boolean avanza = false;
	public Boolean llego = false;
	
	public static int llegaron = 0;
	public Autos(Integer x, Integer y, Integer h, Integer w) {
		this.x = x;
		this.y = y;
		this.h = h;
		this.w = w;
		this.auto = new JLabel();
		auto.setBounds(x, y, h, w);
	}
	
	public JLabel getAuto() {
		return(auto);
	}
	public void cambiarEstado()
	{
		if(this.llego==true)
		{
			this.llego = false;
			llegaron--;
		}
		else
		{
			this.llego = true;
			llegaron++;
		}
	}
	public Boolean verEstado()
	{
		return(this.llego);
	}
	public Integer getY() {
		return(this.auto.getLocation().y);
	}
	public Integer getX() {
		return(this.auto.getLocation().x);
	}
	
	public void largar() {
		this.avanza = true;
	}
	public void parar()
	{
		this.avanza = false;
	}
	public void reacomodar(Integer y) {
		auto.setBounds(x, y, h, w);
	}
	
	public abstract void Avanzar(Integer y);
}

