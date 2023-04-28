package craps;

import java.util.Scanner; //Permite leer datos en la consola provinientes del teclado, base de datos, archivos, impresoras, etc.

public class VistaConsola {

		private ControlCraps controlCraps;
		private String pregunta;
		private Scanner lecturaDatos;
		
		public VistaConsola() //Constructor
		{
			controlCraps = new ControlCraps();
			lecturaDatos = new Scanner(System.in); //System.in lee entradas por consola desde el teclado
		}
		
		public void iniciarJuego() 
		{
			System.out.println("¿Desea lanzar los dados? escribe y/n"); //Permite escribir una linea de texto en la pantalla y da un salto de linea con println
			pregunta = lecturaDatos.nextLine(); //NextLine lee la entrada como si fuera un string
			
			if(pregunta.equalsIgnoreCase("y")) //ignora mayúsculas, método de los strings
			{
				//Inicia Juego
				controlCraps.calcularTiro();
				System.out.printf("Dado 1 = %d Dado2 = %d Tiro = %d \n", controlCraps.getCaraDado1(), //permite crear un formato para la salida de información, "%d" -> permite asignar una variable que va a alistar, en orden de asignación separadas por comas
																		 controlCraps.getCaraDado2(), 
																		 controlCraps.getTiro()); 
				controlCraps.determinarJuego();
				
				switch(controlCraps.getEstado())
				{
				case 1:
						System.out.println("HAS GANADO");
						break;
				case 2:
						System.out.println("HAS PERDIDO");
						break;
				case 3:	
						System.out.printf("Has establecido punto = %d debes lanzar nuevamente \n ", controlCraps.getPunto());
						while(controlCraps.getEstado()==3)
						{
							System.out.println("¿Desea lanzar? escriba y/n");
							pregunta = lecturaDatos.nextLine();
							
							if(pregunta.equalsIgnoreCase("y"))
							{
								controlCraps.calcularTiro();
								
								controlCraps.calcularTiro();
								System.out.printf("Dado 1 = %d Dado2 = %d Tiro = %d \n", controlCraps.getCaraDado1(), //permite crear un formato para la salida de información, "%d" -> permite asignar una variable que va a alistar, en orden de asignación separadas por comas
																						 controlCraps.getCaraDado2(), 
																						 controlCraps.getTiro()); 
								controlCraps.determinarJuego();
							}
							else
							{
								System.out.println("Perdiste por abandonar el juego");
								controlCraps.setAbandono();
							}
						}
						if(controlCraps.getEstado()==1)
						{
							System.out.println("Lograste el punto y ganaste");
						}else 
						{
							System.out.println("Perdiste!!");
						}
						break;
					}
					seguirJuego();
			}
			else
			{
				System.out.println("Ok, Bye!");
			}
		}
		
		private void seguirJuego()
		{
			System.out.println("¿Quieres volver a jugar otra ronda?, Escribe y/n");
			pregunta = lecturaDatos.nextLine();
			
			if(pregunta.equalsIgnoreCase("y"))
			{
				iniciarJuego();
			}else
			{
				System.out.println("Bye!!");
			}
		}
			
}
