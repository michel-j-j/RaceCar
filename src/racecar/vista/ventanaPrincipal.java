package racecar.vista;
import java.awt.BorderLayout;
import racecar.modelo.autos.*;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;

import racecar.modelo.Ranking;
import racecar.modelo.RotatedIcon;
import racecar.modelo.RotatedIcon.Rotate;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

//import racecar.modelo.Administrador;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ventanaPrincipal extends JFrame implements Runnable, MouseListener, Cloneable
{
	private JPanel contentPane;
	public  JLabel lblFondo;// Inicializar(0, 0, 634, 604,"/src/imagenes/Proyecto_base.png");
	private JLabel lblStart;
	private JLabel lblReset;
	private JLabel lblStop;
	private JLabel lblCronometro;
	private JLabel lblMetrometro;
	private JTextField tfEntrada;
	
	//Para los Start, Reset y Stop
	public static int onoff = 0;
	boolean cronometroActivo;
	boolean pausar;
	boolean iniciado = true;
	Thread hilo;
	
	private LogN lblLogN;
	private N lblN;
	private NLogN lblNLogN;
	private N2 lblN2;
	private N3 lblN3;
	private eN lbleN;
	
	private ArrayList<Autos> misAutos = new ArrayList<Autos>();
	private ArrayList<JLabel> ranking = new ArrayList<JLabel>();
	
	public static void main(String[] args)
	{   
		ventanaPrincipal desafio =new ventanaPrincipal();
		desafio.setTitle("Race Car");
		desafio.setVisible(true);
//		Administrador miAdmin = new Administrador(desafio);
//		
//		miAdmin.run(true);
	}
	
	public ventanaPrincipal()
	{   
		/****************************************Configuracion de Frame******************************************************/
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 648, 641);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		Image a = Toolkit.getDefaultToolkit().getImage("src\\imagenes\\logo.png");
		a.getScaledInstance(10000, 10000, Image.SCALE_SMOOTH);
		repaint();
		setIconImage(a);
		setResizable(false);
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		
	    /****************************************Objetos de la pantalla******************************************************/	
		lblReset = new JLabel("Reset");
		lblReset.addMouseListener(this);
		lblReset.setBounds(0, 408, 128, 49);
		setImageLabel(lblReset,"src/imagenes/Reset.png");
		contentPane.add(lblReset);
		
		
		lblStart = new JLabel("Start");
		lblStart.addMouseListener(this);
		lblStart.setBounds(0, 290, 128, 49);
		setImageLabel(lblStart,"src/imagenes/Start.png");
		contentPane.add(lblStart);
		
		lblStop = new JLabel("Stop");
		lblStop.addMouseListener(this);
		lblStop.setBounds(0, 349, 128, 49);
		setImageLabel(lblStop,"src/imagenes/Stopt.png");
		contentPane.add(lblStop);
		
		//Todo esto es el cronometro
		lblCronometro = new JLabel("00:00:000");
		lblCronometro.setBounds(0, 0, 128, 81);
		lblCronometro.setFont( new Font("Consolas", Font.ITALIC, 25) );
		lblCronometro.setHorizontalAlignment( JLabel.CENTER );
		lblCronometro.setForeground( Color.WHITE );
		lblCronometro.setBackground( Color.BLACK );
		lblCronometro.setOpaque( true );
		getContentPane().add( lblCronometro, BorderLayout.CENTER );
		
//		setImageLabel(lblCronometro,"src/imagenes/TableroCrono.png");
//		contentPane.add(lblCronometro);
		
		lblMetrometro = new JLabel();
		lblMetrometro.setBounds(0, 91, 128, 81);
		setImageLabel(lblMetrometro,"src/imagenes/TableroMts.png");
		contentPane.add(lblMetrometro);
		
		tfEntrada = new JTextField();
		tfEntrada.setBackground(new Color(121, 121, 121));
		tfEntrada.setHorizontalAlignment(SwingConstants.CENTER);
		tfEntrada.setForeground( Color.BLACK );
		tfEntrada.setFont(new Font("Consolas", Font.ITALIC, 40));
		tfEntrada.setBounds(10, 520, 107, 71);
		contentPane.add(tfEntrada);
		tfEntrada.setColumns(10);
		/***********************************AUTOS***********************************/
		
		lblLogN = new LogN(134, 534, 45, 60);
		lblLogN.getAuto().setName("autoAzul");
		setImageLabel(lblLogN.getAuto(),"src/imagenes/autoAzul.png");
		contentPane.add(lblLogN.getAuto());
		
		lblN = new N(265, 534, 45, 60);
		lblN.getAuto().setName("autoVerdeOscuro");
		setImageLabel(lblN.getAuto(),"src/imagenes/autoVerdeOscuro.png");
		contentPane.add(lblN.getAuto());
		
		lblNLogN = new NLogN(205, 534, 45, 60);
		lblNLogN.getAuto().setName("autoVerde");
		setImageLabel(lblNLogN.getAuto(),"src/imagenes/autoVerde.png");
		contentPane.add(lblNLogN.getAuto());
		
		lblN2 = new N2(328, 534, 45, 60);
		lblN2.getAuto().setName("autoRosa");
		setImageLabel(lblN2.getAuto(),"src/imagenes/autoRosa.png");
		contentPane.add(lblN2.getAuto());
		
		lblN3 = new N3(393, 534, 45, 60);
		lblN3.getAuto().setName("autoCeleste");
		setImageLabel(lblN3.getAuto(),"src/imagenes/autoCeleste.png");
		contentPane.add(lblN3.getAuto());
		
		lbleN = new eN(456, 534, 45, 60);
		lbleN.getAuto().setName("autoNaranja");
		setImageLabel(lbleN.getAuto(),"src/imagenes/autoNaranja.png");
		contentPane.add(lbleN.getAuto());
		
		lblFondo= new JLabel();
		lblFondo.setBounds(0, 0, 634, 604);
		setImageLabel(lblFondo,"src/imagenes/Proyecto_base_1.png");
		contentPane.add(lblFondo);		
		
		this.agregar(lblLogN);
		this.agregar(lblN);
		this.agregar(lblNLogN);
		this.agregar(lblN2);
		this.agregar(lblN3);
		this.agregar(lbleN);
		
	}
	
	private void setImageRotate(JLabel labelName, String root)
	{
		ImageIcon image = new ImageIcon(root);
		image = new ImageIcon(image.getImage().getScaledInstance(labelName.getWidth(), labelName.getHeight(), Image.SCALE_DEFAULT));
		Icon icon = new RotatedIcon(image,-90);
		labelName.setIcon(icon);
		this.repaint();
	}
	private void setImageLabel(JLabel labelName, String root)
	{
		ImageIcon image = new ImageIcon(root);
		Icon icon = new ImageIcon(image.getImage().getScaledInstance(labelName.getWidth(), labelName.getHeight(), Image.SCALE_DEFAULT));
		labelName.setIcon(icon);
		this.repaint();
	}
	
	public Autos getAuto(int i)
	{
		return(this.misAutos.get(i));
	}
	
	public ArrayList<Autos> getAutos()
	{
		return(this.misAutos);
	}
	
	private void agregar(Autos unAuto)
	{
		this.misAutos.add(unAuto);
	}
	/*
	private void actualizar(){
	}
	private void mostrar(){

	}
	private synchronized void iniciar()
	{   Empezar = true;
		hilo = new Thread(this, "Graficos");
		hilo.start();
	}
	private synchronized void detener()
	{
		Empezar = false;
		try {
			hilo.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public void run() {
		final int NS_POR_SEGUNDO = 1000000000;
		final byte APS_OBJETIVO = 60;
		final double NS_POR_ACTUALIZACION = NS_POR_SEGUNDO / APS_OBJETIVO;
		
		long referenciaACTUALIZACION = NS_POR_SEGUNDO / APS_OBJETIVO;
		long referenciaContador = System.nanoTime();

		double tiempoTranscurrido;
		double delta = 0;
		int i = 1 ;
		while(Empezar)
		{   final long incioBucle =System.nanoTime();
		    tiempoTranscurrido = incioBucle - referenciaACTUALIZACION;
		    referenciaACTUALIZACION = incioBucle;
		    delta += tiempoTranscurrido / NS_POR_ACTUALIZACION;
	
		    while(delta >=1)
		    {
		    	actualizar();
		    	
		    	delta--;
		    }
			if(System.nanoTime() - referenciaContador > NS_POR_SEGUNDO)
			{
				referenciaContador = System.nanoTime();
			}
		}
	}
	*/


	@Override
	public void run() {
		Integer minutos = 0 , segundos = 0, milesimas = 0;
		String min="", seg="", mil="";
		int i = 0;
		//int corte=Integer.parseInt(tf)
		try {
			//Mientras cronometroActivo sea verdadero entonces seguira aumentando el tiempo
			while( cronometroActivo ) {	
				if (!pausar) {
					Thread.sleep(1);
					
					//Incrementamos 4 milesimas de segundo
					milesimas += 4;
					
					//Cuando llega a 1000, osea 1 segundo, aumenta 1 segundo y las milesimas vuelven a 0
					if( milesimas == 1000 ) {
						milesimas = 0;
						segundos += 1;
					}
						//Si los segundos llegan a 60 entonces aumenta 1 los minutos y los segundos vuelven a 0
						else if( segundos == 60 )
					{
						segundos = 0;
						minutos++;
					}
					

					//Para que siempre este en formato 00:00:000
					if( minutos < 10 ) {
						min = "0" + minutos;
					}
					else {
						min = minutos.toString();
						}
					if( segundos < 10 ) {
						seg = "0" + segundos;
					}
					else {
						seg = segundos.toString();
					}

					if( milesimas < 10 ) {
						mil = "00" + milesimas;
					}
					else if( milesimas < 100 ) {
						mil = "0" + milesimas;
					}
					else {
						mil = milesimas.toString();
					}

					lblCronometro.setText( min + ":" + seg + ":" + mil );
					
					//Lo que estaba en el Administrador que hizo michelle
//					sleep(7 * 100);
					
					//ventana.getAuto(0).Avanzar(6);
					//ventana.lblN.setLocation(ventana.lblN.getLocation().x, ventana.lblN.getLocation().y - 5);
					iniciarCarrera(i);
					i++;

		
					repaint();
	
					System.out.println(getAuto(0).getY());
				
					
				}
			}
			lblCronometro.setText( min + ":" + seg + ":" + mil );

		}catch(Exception e){
			System.out.println(e);
			}
		
		lblCronometro.setText( "00:00:000" );
		
	}

	private void comprobarLlegada(){
		/*
        Ranking miRanking = new Ranking();
		for(Autos misAutos:getAutos())
		{   
			if(misAutos.llego==true)
			{   System.out.println("hola");
				miRanking.inc();
				misAutos.getAuto().setBounds(561, Ranking.posicion().getY(), 35, 60);
				contentPane.add(misAutos.getAuto());
			}
		}
	
		*/
	}
	private void iniciarCarrera(int i)
	{ Ranking miRanking = new Ranking();
		for(Autos misAutos:getAutos())
		{   

			if((misAutos.getY()<=0)&&(misAutos.verEstado()==false)) //ACA ADENTRO CRONOMETRO
			{
				misAutos.cambiarEstado();
				misAutos.parar();
				
				JLabel nuevo = new JLabel(); // = misAutos.getAuto();//Clonar
				System.out.println(misAutos.getAuto().getName()+" "+ misAutos.verEstado());
				Ranking.inc();
				
				nuevo.setBounds(561, Ranking.posicion().getY(), 35, 60);
				setImageRotate(nuevo,"src/imagenes/"+misAutos.getAuto().getName()+".png");
				contentPane.add(nuevo, 1);
				ranking.add(nuevo);
				
				JLabel nuevoCrono = new JLabel();
				nuevoCrono.setBounds(555, Ranking.poscionCrono().getY()+10, 70, 36);
				nuevoCrono.setText(lblCronometro.getText());
				nuevoCrono.setFont(new Font("Tahoma", Font.PLAIN, 15));
				nuevoCrono.setForeground(Color.WHITE);
				nuevoCrono.setHorizontalAlignment(SwingConstants.CENTER);
				contentPane.add(nuevoCrono, 1);
				ranking.add(nuevoCrono);
				
				
			}
			else
			{ 
				if(!(misAutos.getY()<=0))
				{ 
					identificarAutos(misAutos, i);
				}
				
			}
			if((misAutos.verEstado()==true)&&(Autos.llegaron==6))
			{   Ranking.contador = 0;
				parar();
			}
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		Object o = e.getSource();
		if( o instanceof JLabel ) {
			JLabel lbl = (JLabel)o;
			if( lbl.getText().equals("Start") ){
				if(isNumeric(tfEntrada.getText()))
				 iniciar();
				else
				 JOptionPane.showMessageDialog(null, "Ingrese un numero valido.");
			}
			if( lbl.getText().equals("Reset") ) {
				reiniciar();
			}
			if( lbl.getText().equals("Stop") ) {
				parar();
			}
		}
	}
    public ArrayList<Integer> reverseArrayList(ArrayList<Integer> alist)
    {
        // Arraylist for storing reversed elements
        ArrayList<Integer> revArrayList = new ArrayList<Integer>();
        for (int i = alist.size() - 1; i >= 0; i--) {
 
            // Append the elements in reverse order
            revArrayList.add(alist.get(i));
        }
 
        // Return the reversed arraylist
        return revArrayList;
    }
	public void identificarAutos(Autos auto, int i)
	{    ArrayList<Integer> misMentiras = new ArrayList<Integer>();
	     
	      int mentira = 0;
	     try
	     {
	 		 mentira = Integer.parseInt(tfEntrada.getText());
	     }
	     catch(NumberFormatException e)
	     {
	 		 mentira = Integer.parseInt(tfEntrada.getText());
	     }
	     int a = (int)mentira/10;
	     if(a <= 0)
	     {
	    	 a = 2;
	     }
		if(mentira<50)
		{
			for(Integer j = 1; j<=6; j++)
			{
				misMentiras.add(j*a);
			}
			misMentiras = reverseArrayList(misMentiras);
		}
		else if((mentira >=50)&&(mentira <100))
		{
			for(Integer j = 1; j<=6; j++)
			{
				misMentiras.add(a + j);
			}
			misMentiras = reverseArrayList(misMentiras);
		}
		else if((mentira >=100)&&(mentira <=200))
		{
			for(Integer j = 1; j<=6; j++)
			{
				misMentiras.add(j*a);
			}
		//	System.out.println(misMentiras.get(0));
		}
		else if(mentira >=200)
		{
			for(Integer j = 1; j<=6; j++)
			{  
				misMentiras.add(j*a -(a + j));
			}
		}
		switch(auto.getAuto().getName())
		{ 
		  case "autoAzul":
			  if(i % misMentiras.get(0)==0)
			  {
				  auto.Avanzar(1); 
			  }
			
		  break;
		  case "autoVerde":
			  if(i % misMentiras.get(1)==0)
			  {
				  auto.Avanzar(1); 
			  }
			  break;
		  case "autoVerdeOscuro":
			  if(i % misMentiras.get(2)==0)
			  {
				  auto.Avanzar(1); 
			  }
			 break;
		  case "autoRosa":
			  if(i % misMentiras.get(3)==0)
			  {
				  auto.Avanzar(1); 
			  }
			 break;
		  case "autoCeleste":
			  if(i % misMentiras.get(4)==0)
			  {
				  auto.Avanzar(1); 
			  }
			 break;
		  case"autoNaranja":
			  if(i % misMentiras.get(5)==0)
			  {  
				  auto.Avanzar(1); 
			  }
		  break;

			  
		}
	}
	private void iniciar() {
		//Para que cuando se vuelva oprimir iniciar no llame a otro hilo y que se pisen
		if (iniciado) {
			hilo = new Thread( this ); 	
			cronometroActivo = true;
			pausar = false;       
			hilo.start();
			iniciado = false;
			largarOPararAutos();
		}else {
		}        
	}

	//Para detener el cronometro
	private void parar(){
		pausar = true ;
		largarOPararAutos();
		//cada vez que el cronometro sea detenido se habilita varaible iniciado para que se pueda crear una nueva instancia de hilo
		iniciado = true;
	}

	//Para reiniciar el cronometro
	private void reiniciar(){
		cronometroActivo = false;
		iniciado = true;
		Ranking.contador = 0;
		for(Autos unAuto: misAutos) {
			unAuto.reacomodar(534);
			unAuto.cambiarEstado();
		}
		//Para que dejear el label en formato "00:00:00" 
		lblCronometro.setText( "00:00:000" );
		for(JLabel misLabels: ranking)
		{
			misLabels.setVisible(false);
			//dispose();
		}
	}

	
	private void largarOPararAutos() {
		for(Autos unAuto: misAutos) {
			unAuto.largar();
		}
	}

    public static boolean isNumeric(String s)
    {
        if (s == null || s.equals("")) {
            return false;
        }
 
        for (int i = 0; i < s.length(); i++)
        {
            char c = s.charAt(i);
            if (c < '0' || c > '9') {
                return false;
            }
        }
        return true;
    }
	
	//Obligado por implementar mouseListener
	@Override
	public void mousePressed(MouseEvent e){}
	@Override
	public void mouseReleased(MouseEvent e){}
	@Override
	public void mouseEntered(MouseEvent e){}
	@Override
	public void mouseExited(MouseEvent e){}
}

