package craps;

import java.awt.Container; //Contenedor
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton; //Botones
import javax.swing.JFrame; //ventanas
import javax.swing.JLabel; //Etiquetas
import javax.swing.JOptionPane;

public class VistaGUICraps extends JFrame{
	//atributos
		private JLabel dado1,dado2;
		private JButton lanzar;
		private ImageIcon imagen; //permite cargar imágenes
		private Escucha escucha;
		private ControlCraps controlCraps;
	
		
	//métodos
	
	public VistaGUICraps()
	{	//window container y layout
		
		Container contenedor = this.getContentPane(); //Devuelve un objeto conteiner
		contenedor.setLayout(new FlowLayout()); //Asignarle un layout al contenedor,FlowLayout -> importa el orden en que se agregan los gráficos
		
		//crear objeto escucha y el objeto control
		
		escucha = new Escucha();
		controlCraps = new ControlCraps();
		
		//crear los componentes gráficos
		imagen = new ImageIcon("src/imagenes/dado.png");
		dado1 = new JLabel(imagen);
		dado2 = new JLabel(imagen);
		lanzar = new JButton("Lanzar"); //necesita incorporarse una escucha "Listener"
		lanzar.addActionListener(escucha); //le agrega la escucha al botón
		
		//Agregar los componentes gráficos
		contenedor.add(dado1);
		contenedor.add(dado2);
		contenedor.add(lanzar);
		
		
		this.setTitle("Juego Craps");
		this.setSize(350,210);
		this.setLocationRelativeTo(null); //null -> ventana centrada
		this.setResizable(false); //Permitir Redimensionar
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //al cerrar la ventana se termina la ejecución del programa
		this.setVisible(true);
	}

		//Clase interna, que es de uso privado en esta misma clase de VistaGUICraps, clases Auxiliares
	private class Escucha implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent event) {
				// TODO Auto-generated method stub
				if(event.getSource()==lanzar) //método que devuelve una referencia al objeto que disparó el evento
				{
					visualizarCaras();
					controlCraps.determinarJuego();
					String tiro = "El tiro fue " + controlCraps.getTiro()+"\n";
					
					Icon icon;
					
					switch(controlCraps.getEstado())
					{
					case 1: icon = new ImageIcon("src/imagenes/ganaste.png"); 
						
							JOptionPane.showMessageDialog(lanzar, tiro + " Has ganado!!", "Resultado", JOptionPane.DEFAULT_OPTION, icon);
						
							break;
						
					case 2: icon = new ImageIcon("src/imagenes/perdiste.png"); 
					
							JOptionPane.showMessageDialog(lanzar, tiro + " Has perdido!!", "Resultado", JOptionPane.DEFAULT_OPTION, icon);
				
							break;
						
					case 3: icon = new ImageIcon("src/imagenes/punto.png"); 
							String punto = "Has establecido punto en: " + controlCraps.getPunto() + " , debes volver a sacar el valor del punto para ganar"+ "\n" + "pero si sacas antes 7, perderás";
							
							JOptionPane.showMessageDialog(lanzar, tiro + punto, "Resultado", JOptionPane.DEFAULT_OPTION, icon);						
							
							break;
					}
				}
			}
			
		
	}
	
	private void visualizarCaras()
	{
		controlCraps.calcularTiro();
		imagen = new ImageIcon("src/imagenes/"+controlCraps.getCaraDado1()+".png");
		dado1.setIcon(imagen);
		imagen = new ImageIcon("src/imagenes/"+controlCraps.getCaraDado2()+".png");
		dado2.setIcon(imagen);	
	}

}
