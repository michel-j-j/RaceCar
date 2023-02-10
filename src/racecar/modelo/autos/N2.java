package racecar.modelo.autos;

public class N2 extends Autos{
	public N2(Integer x, Integer y, Integer h, Integer w) {
		super(x, y, h, w);
	}
	
	public void Avanzar(Integer y)
	{   
		this.getAuto().setLocation(this.getAuto().getLocation().x, this.getAuto().getLocation().y - y);
	}

}
