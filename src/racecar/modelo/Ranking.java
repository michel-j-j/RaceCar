package racecar.modelo;

import java.util.ArrayList;

import javax.swing.JLabel;

import racecar.modelo.autos.Autos;
import racecar.modelo.autos.N;

public class Ranking {
	private static Autos primer = new N(561, 70, 35, 60);
	private static Autos segundo = new N(561, 150, 35, 60);
	private static Autos tercero = new N(561, 228, 35, 60);
	private static Autos cuarto =  new N(561, 308, 35, 60);
	private static Autos quinto = new N(561, 380, 35, 60);
	private static Autos sexto = new N(561, 457, 35, 60);
	
    private static Autos primerCrono = new N(561, 110, 35, 60);
    private static Autos segundoCrono = new N(561, 190, 35, 60);
    private static Autos tercerCrono = new N(561, 266, 35, 60);
    private static Autos cuartoCrono = new N(561, 340, 35, 60);
    private static Autos quintoCrono = new N(561, 417, 35, 60);
    private static Autos sextoCrono = new N(561, 496, 35, 60);
    
	private static ArrayList<Autos> posiciones = new ArrayList<Autos>();
	private static ArrayList<Autos> posicionesCrono = new ArrayList<Autos>();
	
	public static Integer contador = 0;

	public Ranking()
	{
		posiciones.add(primer);
		posiciones.add(segundo);
		posiciones.add(tercero);
		posiciones.add(cuarto);
		posiciones.add(quinto);
		posiciones.add(sexto);
		
		posicionesCrono.add(primerCrono);
		posicionesCrono.add(segundoCrono);
		posicionesCrono.add(tercerCrono);
		posicionesCrono.add(cuartoCrono);
		posicionesCrono.add(quintoCrono);
		posicionesCrono.add(sextoCrono);
		

	}
	public static void inc()
	{
		Ranking.contador++;
	}
	public static JLabel dibujarRanking()
	{
		JLabel unAuto = new JLabel();
		Autos miPosicion = posicion();
		unAuto.setBounds(561, posicion().getY(),35, 60);
		return(unAuto);
	}
	public static JLabel dibujarCrono()
	{
		JLabel unAuto = new JLabel();
		Autos miPosicion = posicion();
		unAuto.setBounds(561, posicion().getY(),35, 60);
		return(unAuto);
	}
	public static Autos posicion()
	{   
		return(posiciones.get(contador - 1));
	}
	public static Autos poscionCrono()
	{
		return(posicionesCrono.get(contador-1));
	}
	public static int x()
	{
		return(posiciones.get(contador - 1).getX());
	}
}