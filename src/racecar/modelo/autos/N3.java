package racecar.modelo.autos;

public class N3 extends Autos{
	
	public N3(Integer x, Integer y, Integer h, Integer w) {
		super(x, y, h, w);
	}
	
	public void Avanzar(Integer y)
	{   int valor = 1;
	    valor =+ (y)/(y*y*y);
		this.getAuto().setLocation(this.getAuto().getLocation().x, this.getAuto().getLocation().y - valor);
	}

}
