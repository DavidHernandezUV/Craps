package craps;

import java.awt.EventQueue;




public class PrincipalCraps {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		 * VistaConsola consola = new VistaConsola(); consola.iniciarJuego();
		 */
		//Ejecutar la interfaz gráfica
		EventQueue.invokeLater(new Runnable() 
		{

			@Override
			public void run() {
				// TODO Auto-generated method stub
				//VistaGUICraps myWindow = new VistaGUICraps();
				VistaGUIGridBagLayout myVista = new VistaGUIGridBagLayout();
			}	
		});
	}

}
