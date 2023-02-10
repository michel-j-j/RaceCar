package racecar.modelo.autos;

public class LogN extends Autos{
	
	public LogN(Integer x, Integer y, Integer h, Integer w) {
		super(x, y, h, w);
	}
	public void Avanzar(Integer y)
	{   
		this.getAuto().setLocation(this.getAuto().getLocation().x, this.getAuto().getLocation().y - y);
	}

}
