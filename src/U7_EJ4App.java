
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Scanner;

public class U7_EJ4App {
	
	static Scanner lector = new Scanner(System.in);
	//final static int MAX_PROD = 10;
	final static int STOCK = 1, VENTA = 2;
	final static double IVA1 = 0.21;
	final static double IVA2 = 0.04;

	public static void main(String[] args) {
		/**
		 * combina los metodos hechos en ej 2 y ej 3
		 * gestionar ventas y control de stock a la vez
		 * 
		 * estructuras de datos mas convenientes
		 * 
		 * podriamos tener un hashtable con los productos y sus precios, otro hashtable con producto y num en stock
		 * al comprar producto se borra 1 del stock !
		 */
		Hashtable<String,String> item_priceVenta = new Hashtable<String,String>();
		Hashtable<String,String> item_priceStock = new Hashtable<String,String>();
		Hashtable<String,Integer> item_numStock = new Hashtable<String,Integer>();
		
		boolean end = false;
		while(!end) { // menu principal
			System.out.println("Elige menu:\n 1-Gestion stock\n 2-Gestion ventas\n Para salir pulsa 0");
			int option = lector.nextInt();
			lector.nextLine();
			
			switch(option) {
			case 1:
				menuStock(item_priceStock,item_numStock);
				break;
			case 2:
				menuVentas(item_priceVenta,item_numStock);
				break;
			default:
				System.out.println("EXIT");
				end = true;
			}
			
		}				
		
	}

	private static void menuVentas(Hashtable<String,String> item_priceVenta,Hashtable<String,Integer> numStock) {
				
		boolean end = false;
		while(!end) {
			System.out.println("\nQue accion quieres realizar?\n "
					+ "1 - añadir producto comprado\n 2 - Calcular el IVA del producto\n 3 - Saber suma total de las ventas\n "
					+ "4 - Saber cantidad de ventas\n 5 - Mostrar todas las compras introducidas\n 6 - Saber el cambio a dar\n Para salir pulsa 0");
			int option = lector.nextInt();
			lector.nextLine();
			
			switch(option){
			case 1:
				addItem(item_priceVenta,numStock,VENTA); 
				break;
			case 2:
				if(!item_priceVenta.isEmpty()){
					calculoSinIVA(item_priceVenta);
				}else{
					System.out.println("Introduce primero algun producto");
				}				
				break;
			case 3:
				if(!item_priceVenta.isEmpty()){
					sumaVentas(item_priceVenta);
				}else{
					System.out.println("Introduce primero algun producto");
				}
				break;
			case 4:
				System.out.println("\nLa cantidad de productos comprados es: "+item_priceVenta.size());
				break;
			case 5:
				if(!item_priceVenta.isEmpty()){
					consultData(item_priceVenta,null);
				}else{
					System.out.println("Introduce primero algun producto");
				}					
				break;
			case 6:
				whichChange(item_priceVenta);
			default:
				System.out.println("EXIT");
				end = true;				
			}
		}
		
	}

	private static void menuStock(Hashtable<String,String> item_priceStock,Hashtable<String,Integer> item_numStock) {		
		
		boolean end = false;
		while(!end){
			System.out.println("\nQue accion quieres realizar?\n "
					+ "1 - Añadir un nuevo producto\n 2 - Consultar la info almacenada de algun producto concreto\n"
					+ " 3 - Listar toda la info almacenada\n Para salir pulsa 0");
			int option = lector.nextInt();
			lector.nextLine();
			
			switch(option) {
			case 1:
				addItem(item_priceStock,item_numStock,STOCK);
				break;
			case 2:
				if(!item_priceStock.isEmpty()){
					consultInfoItem(item_priceStock,item_numStock);
				}else{
					System.out.println("Introduce primero algun producto");
				}
				break;
			case 3:
				if(!item_priceStock.isEmpty()){
					consultData(item_priceStock,item_numStock);
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

	private static void addItem(Hashtable<String,String> data, Hashtable<String,Integer> listStock, int option){
		
		System.out.println("Indica nombre producto");
		String name = lector.nextLine();
		
		System.out.println("Indica precio");
		String price = lector.nextLine();
		
		if(option==STOCK){
			System.out.println("Indica num en stock");
			int stock = lector.nextInt();
			lector.nextLine();
			listStock.put(name,stock);
		}
						
		data.put(name,price);		
		
		if(option==VENTA) {
			if(listStock.containsKey(name)) {
				listStock.put(name,listStock.get(name)-1);
			}
		}
				
	}
	
	private static void consultInfoItem(Hashtable<String,String> data,Hashtable<String,Integer> listStock){
		System.out.println("De que articulo quieres consultar el precio y num unidades en stock?");
		String item = lector.nextLine();
		
		System.out.println("El precio del articulo "+item+" es "+data.get(item)+"\nTiene "+listStock.get(item)+" unidades en stock");
	}
	
	
	private static void consultData(Hashtable<String,String> data,Hashtable<String,Integer> listStock) {
		Enumeration<String> listPrices = data.elements();
		Enumeration<String> listItems = data.keys();
				
		if(listStock!=null && !listStock.isEmpty()) {
			Enumeration<Integer> enumStock = listStock.elements();
			while(listItems.hasMoreElements()) {
				System.out.println("El producto "+listItems.nextElement()+" tiene un precio de "+listPrices.nextElement()+
						" y tiene "+ enumStock.nextElement()+" unidades en stock");			
			}
		}else{
			while(listItems.hasMoreElements()) {
				System.out.println("El producto comprado "+listItems.nextElement()+" tiene un precio de "+listPrices.nextElement());			
			}
		}
		
	}
	
	
	private static void calculoSinIVA(Hashtable<String,String> item_priceVenta) {
		
		double precioSinIVA;
		
		System.out.println("De que producto quieres calcular el precio sin IVA?");
		String item = lector.nextLine();
				
		int precio = Integer.parseInt(item_priceVenta.get(item));
		
		System.out.println("\nIndica que tipo de IVA se aplica. Normal o reducido?");
		String iva = lector.nextLine();			
		
		if(iva.equalsIgnoreCase("Normal")) {
			precioSinIVA = precio - (precio*IVA1);
			System.out.println("El IVA aplicado es "+IVA1);
		}else {
			precioSinIVA = precio - (precio*IVA2);
			System.out.println("El IVA aplicado es "+IVA2);
		}
		
		System.out.println("El precio con iva del producto es "+precio+".\nEl precio bruto del producto es "+precioSinIVA);		
	}
	
	
	private static int sumaVentas(Hashtable<String, String> item_priceVenta) {
		Enumeration<String> ventas = item_priceVenta.elements();
		int sum = 0;
		
		while(ventas.hasMoreElements()) {
			sum += Integer.parseInt(ventas.nextElement());		
		}		
		
		System.out.println("La suma total de las ventas es de "+sum);
		return sum;
	}
	
	
	private static void whichChange(Hashtable<String, String> item_priceVenta) {
		System.out.println("\nCuanto ha pagado el cliente?");
		int cantidadPagada = lector.nextInt();
		lector.nextLine();	
		
		int change = cantidadPagada-sumaVentas(item_priceVenta);
		System.out.println("La cantidad a devolver es: "+change);
	}
		
}