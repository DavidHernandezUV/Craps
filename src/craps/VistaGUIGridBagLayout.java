package craps;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import misComponentes.Titulos;

public class VistaGUIGridBagLayout extends JFrame {

	//atributos
	private JPanel zonaJuego, zonaResultados;
	private JLabel dado1, dado2, tiro, punto;
	private JTextField valorTiro, valorPunto;
	private JButton salir, lanzar;
	private ImageIcon imagen;
	private JTextArea mensajes;
	private Titulos titulo, resultados;
	private ControlCraps controlCraps;
	private Escucha escucha;
	private JFrame ventanaGrafica;

	
	
	//métodos
	
	public VistaGUIGridBagLayout() //Constructor
	{
		initGUI();
		
		//set default window configuration
		this.setTitle("Juego Craps");
		this.setUndecorated(true); //QUitar los bordes y queda la ventana fija (realizar siempre al final)
		this.setBackground(new Color(255,255,25,200)); //Color de la ventana, 200 -> opacidad del color
		this.pack(); //Determina automáticamente el tamaño de la ventana dependiendo de los componentes que tenga
		//this.setSize(310,180);
		this.setLocationRelativeTo(null); //centrado
		this.setResizable(false);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //terminar ejecución del programa.
		
	}


	private void initGUI() {
		//set up container - layout
		
		this.getContentPane().setLayout(new GridBagLayout()); //acceder al container
		GridBagConstraints constraints = new GridBagConstraints();
		
		//create objects listener, control, others
		escucha = new Escucha();
		controlCraps = new ControlCraps();
		ventanaGrafica = this; //Referencia así mismo
		
		
		//set up GUIComponents add to window
		
		//Title
		titulo = new Titulos("Juego Craps",30,new Color(0,0,0));
		titulo.addMouseListener(escucha); 
		titulo.setCursor(new Cursor(Cursor.MOVE_CURSOR));
		titulo.addMouseMotionListener(escucha);
		//Restricciones del título
		constraints.gridx=0; 
		constraints.gridy=0;
		constraints.gridwidth=2;
		constraints.fill=GridBagConstraints.HORIZONTAL;
		add(titulo,constraints);
		
		//Zona de Juego
		zonaJuego = new JPanel();
		imagen = new ImageIcon("src/imagenes/dado.png");
		dado1 = new JLabel(imagen);
		dado2 = new JLabel(imagen);
		lanzar = new JButton("Lanzar");
		lanzar.addActionListener(escucha);
		zonaJuego.add(dado1);
		zonaJuego.add(dado2);
		zonaJuego.add(lanzar);
		zonaJuego.setPreferredSize(new Dimension(310,180)); //JPanel que tiene como tamaño 310,180
		zonaJuego.setBorder(new TitledBorder("Zona Juego"));
		//restricciones de la zona de juego
		constraints.gridx=0; 
		constraints.gridy=1;
		constraints.gridwidth=1; //Ocupa una columna
		constraints.gridheight=3; //Ocupa tres filas
		constraints.fill=GridBagConstraints.LAST_LINE_END;
		add(zonaJuego,constraints);
		
		//salir
		salir = new JButton("Salir");
		salir.addActionListener(escucha);
		salir.setCursor(new Cursor(Cursor.HAND_CURSOR)); //Cambiar el diseño del cursor
		//Restricciones del button
		constraints.gridx=0; 
		constraints.gridy=4;
		constraints.gridwidth=1;
		constraints.gridheight=1;
		constraints.fill=GridBagConstraints.NONE; //respete el tamaño
		constraints.anchor=GridBagConstraints.LAST_LINE_END;
		add(salir,constraints);
		
		//Titulo resultados
		resultados = new Titulos("Resultado",30,new Color(255,20,155));
		//Restricciones
		constraints.gridx=1; 
		constraints.gridy=1;
		constraints.gridwidth=1;
		constraints.gridheight=1;
		constraints.fill=GridBagConstraints.HORIZONTAL;
		constraints.anchor=GridBagConstraints.CENTER;
		add(resultados,constraints);
		
		//Zona Resultados
		zonaResultados = new JPanel();
		zonaResultados.setLayout(new GridLayout(2,2)); //crea una matriz tipo layout de 2x2
		tiro = new JLabel("Tiro");
		punto = new JLabel("Punto");
		valorTiro = new JTextField(2); //2 caracteres -> tamaño
		valorTiro.setEditable(false); //Bloquea la edición del TextField
		valorPunto = new JTextField(2); //2 caracteres ->
		valorPunto.setEditable(false);//Bloquea la edición del TextField
		zonaResultados.add(tiro); //Organizar en orden
		zonaResultados.add(valorTiro);
		zonaResultados.add(punto);
		zonaResultados.add(valorPunto);
		zonaResultados.setBackground(Color.WHITE);
		//restricciones
		constraints.gridx=1; 
		constraints.gridy=2;
		constraints.gridwidth=1;
		constraints.gridheight=1;
		constraints.fill=GridBagConstraints.NONE; //respete el tamaño
		constraints.anchor=GridBagConstraints.CENTER;
		add(zonaResultados,constraints);
		
		//Area de texto
		mensajes = new JTextArea(10,20);
		mensajes.setText("Lanza los dados para iniciar el juego. \n");
		mensajes.setEditable(false);
		JScrollPane scroll = new JScrollPane(mensajes);
		//Restricciones
		constraints.gridx=1; 
		constraints.gridy=3;
		constraints.gridwidth=1;
		constraints.gridheight=2;
		constraints.fill=GridBagConstraints.VERTICAL;
		constraints.anchor=GridBagConstraints.CENTER;
		add(scroll,constraints);
				
	}
	
	private class Escucha implements ActionListener, MouseListener,MouseMotionListener{
		
		private int x,y; //coordenadas
		
		@Override
		public void actionPerformed(ActionEvent eventAction) {
			if(eventAction.getSource()==salir)
			{
				System.exit(0);
			}
			else
			{
				controlCraps.calcularTiro();
				imagen = new ImageIcon("src/imagenes/"+controlCraps.getCaraDado1()+".png");
				dado1.setIcon(imagen);
				imagen = new ImageIcon("src/imagenes/"+controlCraps.getCaraDado2()+".png");
				dado2.setIcon(imagen);
				
				controlCraps.determinarJuego();
				valorTiro.setText(String.valueOf(controlCraps.getTiro()));
				
				switch(controlCraps.getEstado()) 
				{
				case 1://gano

						mensajes.append("Has Ganado!! \n");
						break;
				case 2://perdio
						
						mensajes.append("Has Perdido!! \n");
						break;
				case 3://punto
											valorPunto.setText(String.valueOf(controlCraps.getPunto()));
						String mensaje = "Estás en punto!! \nDebes seguir lanzado \n"+"Ganas si sacas nuevamente " + String.valueOf(controlCraps.getPunto() + "\nPierdes si antes sacas 7 \n");
					
						mensajes.append(mensaje);
						break;
					
				}
				
			
			}
			
		}

		@Override
		public void mouseClicked(MouseEvent arg0) { //presionar y solar click
			// TODO Auto-generated method stub
	
		}

		@Override
		public void mouseEntered(MouseEvent arg0) { //Pasar por encima
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent arg0) { //salir de encima
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(MouseEvent eventMouse) { //presionar click
			// TODO Auto-generated method stub
			x = eventMouse.getX(); //toma la coordenada x
			y = eventMouse.getY(); //toma la coordenada y
			
		}

		@Override
		public void mouseReleased(MouseEvent arg0) { //soltar click
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseDragged(MouseEvent eventMouseMotion) { //presionado y arrastrar con el mouse
			// TODO Auto-generated method stub
			setLocation(ventanaGrafica.getLocation().x+eventMouseMotion.getX()-x,ventanaGrafica.getLocation().y+eventMouseMotion.getY()-y);
		}

		@Override
		public void mouseMoved(MouseEvent arg0) { //solo el arrastre del mouse
			// TODO Auto-generated method stub
			
		}
		
	}
	
}
