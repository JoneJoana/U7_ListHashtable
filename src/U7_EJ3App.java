import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Scanner;

public class U7_EJ3App {
	
	static Scanner lector = new Scanner(System.in);
	final static int MAX_PROD = 4;

	public static void main(String[] args) {
		/**
		 * crear bbdd de 10 articulos para controlar stock productos 
		 * de una tienda con un hashtable.
		 * user podra añadir articulos nuevos y cantidades
		 * user podra consultar la info almacenada en el hastable
		 * referente a un articulo concreto. tamb listar toda la info 
		 * por terminal
		 */
		
		Hashtable<String,String> item_price = new Hashtable<String,String>();		
		boolean end = false;
	
		while(!end){
			System.out.println("\nQue accion quieres realizar?\n "
					+ "1 - Añadir un nuevo producto?\n 2 - Consultar la info almacenada de algun producto concreto?\n"
					+ " 3 - Listar toda la info almacenada?\n Para salir pulsa 0");
			int option = lector.nextInt();
			lector.nextLine();
			
			switch(option) {
			case 1:
				if(item_price.size() == MAX_PROD) {
					break;
				}
				addItem(item_price);
				break;
			case 2:
				if(!item_price.isEmpty()){
					consultInfoItem(item_price);
				}else{
					System.out.println("Introduce primero algun producto");
				}
				break;
			case 3:
				if(!item_price.isEmpty()){
					consultData(item_price);
				}else{
					System.out.println("Introduce primero algun producto");
				}			
				break;	
			default:
				System.out.println("EXIT");
				end = true;
			}			
		}		
		
	}	

	private static void addItem(Hashtable<String,String> data){
		System.out.println("Indica nombre producto");
		String name = lector.nextLine();
		
		System.out.println("Indica precio");
		String price = lector.nextLine();
		
		data.put(name,price);
	}
	
	private static void consultInfoItem(Hashtable<String,String> data){
		System.out.println("De que articulo quieres consultar el precio?");
		String item = lector.nextLine();
		
		System.out.println("El precio del articulo: "+item+" es "+data.get(item));
	}
	
	private static void consultData(Hashtable<String,String> data) {
		Enumeration<String> listPrices = data.elements();
		Enumeration<String> listItems = data.keys();
		while(listItems.hasMoreElements()) {
			System.out.println("El producto "+listItems.nextElement()+" tiene un precio de "+listPrices.nextElement());
		}
		
	}

}
