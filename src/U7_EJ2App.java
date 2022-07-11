import java.util.ArrayList;
import java.util.Scanner;

public class U7_EJ2App {
	final static double IVA1 = 0.21;
	final static double IVA2 = 0.04;
	static Scanner LECTOR = new Scanner(System.in); //al ponerla como static la puedo usar en toda la class

	public static void main(String[] args) {
		/**
		 * gestionar flujo de ventas de una caja de super
		 * guardar las cantidades del carrito en una ArrayList
		 * mostrara por pantalla: 
		 * 	iva aplicado (21 o 4 %)
		 * 	precio bruto y precio + iva
		 * 	num articulos comprados en total
		 * 	cantidad pagada
		 * 	cambio a devolver
		 */			
		
		ArrayList<Integer> precios = new ArrayList<>();		
		int sum = 0;
		
		System.out.println("Introduce los precios de la compra del carrito.\nIntroduce -1 para finalizar.");
		addPricesToList(precios);
		
		//System.out.println(precios.toString());
		
		for(int pos=0;pos<precios.size();pos++) {//recorremos list creado para calcular iva de cada producto y generar la suma total
			calculoIVA(precios,pos);
			sum += precios.get(pos);
		}
		
		System.out.println("\nCuanto ha pagado el cliente?");
		int cantidadPagada = LECTOR.nextInt();
		LECTOR.nextLine();	
		
		int change = cantidadPagada-sum;
		
		LECTOR.close();
		
		//mostramos los datos finales de la compra
		System.out.println("\nLa cantidad de productos comprados es: "+precios.size());
		System.out.println("El precio total de la compra ha sido: "+sum);
		System.out.println("La cantidad pagada ha sido: "+cantidadPagada);
		System.out.println("La cantidad a devolver es: "+change);		
		
	}

	private static void addPricesToList(ArrayList<Integer> precios) {
		
		boolean end = false;
		while(!end){
			if(LECTOR.hasNextInt()) {
				int precio = LECTOR.nextInt();			
				if(precio!=-1) {
					precios.add(precio);
				}else {
					end = true;
					LECTOR.nextLine();
				}				
			}			
		}
	}

	private static void calculoIVA(ArrayList<Integer> precios, int posicion) {
		
		double precioSinIVA;
		int valorCompra = precios.get(posicion);
		
		System.out.println("\nIndica el IVA del producto "+(posicion+1)+". Normal o reducido?");
		String iva = LECTOR.nextLine();			
		
		if(iva.equalsIgnoreCase("Normal")) {
			precioSinIVA = valorCompra - (valorCompra*IVA1);
			System.out.println("El IVA aplicado es "+IVA1);
		}else {
			precioSinIVA = valorCompra - (valorCompra*IVA2);
			System.out.println("El IVA aplicado es "+IVA2);
		}
		
		System.out.println("El precio con iva del producto es "+valorCompra+".\nEl precio bruto del producto es "+precioSinIVA);		
	}
	
	
	
}
