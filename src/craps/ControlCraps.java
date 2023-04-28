package craps;

public class ControlCraps {

	private Dado dado1,dado2;
	private int tiro, punto, estado;
	private boolean lanzamiento;
	private int[] carasDados; //Crea un arreglo est�tico de enteros
	
	public ControlCraps() { //Constructor al crear un nuevo objeto de tipo controlCraps se llama a el constructor
		
		dado1 = new Dado();
		dado2 = new Dado();
		lanzamiento = true; //ronda de tiro
		carasDados = new int[2]; // un arreglo con dos elementos
	}
	
	public void calcularTiro() { //da el valor de los dados
		
		carasDados[0] = dado1.getCaraVisible(); //generea el n�mero aleatorio y lo guarda en la pos 1 del arreglo
		carasDados[1] = dado2.getCaraVisible(); //generea el n�mero aleatorio y lo guarda en la pos 2 del arreglo
		
		tiro = carasDados[0] + carasDados[1];
	}
	
	public void determinarJuego() {
		if(lanzamiento == true)//ronda de tiro
		{ 
			if(tiro == 7 || tiro == 11)
			{
				estado = 1; // Gan�
			}
			if(tiro == 2 || tiro == 3 || tiro == 12)
			{
				estado = 2; // Perdi�
			}
			else 
			{
				estado = 3; //entra a ronda de punto punto
				punto = tiro;
				lanzamiento = false;
			}
		}else
		{
			rondaPunto();
		}
	}
	
	private void rondaPunto() //Pueden crearse m�todos privados en una clase, se usan cuando no se van a llamar m�todos de una clase externa
		{
			if(tiro==punto)
			{
				estado = 1; //Ganar
				lanzamiento = true; //nueva ronda de juego
			}
			if(tiro == 7)
			{
				estado = 2; //Perdi�
				lanzamiento = true; //Nueva ronda de juego
			}
		}

	public void setAbandono() {
		estado = 2;
		lanzamiento = true;
	}
	public int getTiro() {
		return tiro;
	}

	public int getPunto() {
		return punto;
	}

	public int getEstado() {
		return estado;
	}

	public int[] getCarasDados() {
		return carasDados;
	}	
	
	public int getCaraDado1() {
		return carasDados[0];
	}
	public int getCaraDado2() {
		return carasDados[1];
	}
}
