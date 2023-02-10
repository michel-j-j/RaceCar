package racecar.modelo;

import javax.swing.*;

import racecar.modelo.autos.Autos;
import racecar.vista.ventanaPrincipal;

public class Administrador extends Thread{
	private ventanaPrincipal ventana;
	private Boolean Empezar;
	
	public Administrador()
	{
		this.Empezar = true;
		
	}
	public Administrador(ventanaPrincipal ventana)
	{
		this.ventana = ventana;
		
	}
	public ventanaPrincipal getAdmin()
	{
		return(this.ventana);
	}
	public void run(Boolean estado) 
	{
		while(estado)
		{
			try
			{
				sleep(7 * 100);
				
				//ventana.getAuto(0).Avanzar(6);
				//ventana.lblN.setLocation(ventana.lblN.getLocation().x, ventana.lblN.getLocation().y - 5);
				iniciarCarrera();
				
				ventana.repaint();
				System.out.println(ventana.getAuto(0).getY());
			}
			catch(InterruptedException e)
			{
				System.out.println(e);
			}
		}
		
	}
	public void iniciarCarrera()
	{
		for(Autos misAutos:ventana.getAutos())
		{
			misAutos.Avanzar(5);
		}
	}
	public void setVentana(ventanaPrincipal mivetana)
	{
		this.ventana = mivetana;
	}
}
