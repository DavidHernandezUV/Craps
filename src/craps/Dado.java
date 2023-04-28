package craps;

import java.util.Random; 


// TODO: Auto-generated Javadoc
/**
 * The Class Dado. Esta clase simula un dado y permite ver el valor de la cara visible
 */
public class Dado {
	
	/** The cara visible. Es el valor (1 a 6) obtenido por el usuario en ese dado*/
	private int caraVisible;

	/**
	 * Gets the cara visible. Determina el valor de la cara visible del dado
	 *
	 * @return the cara visible. Es un valor entre 1 y 6
	 */
	public int getCaraVisible() {
		Random aleatorio = new Random();
		caraVisible = aleatorio.nextInt(6) + 1; //Genera un n�mero aleatorio entre 1 y 6
		return caraVisible;
	}

}
