import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Scanner;

public class U7_EJ1App {

	public static void main(String[] args) {
		/**
		 * calcular nota media de los alumnos. una vez calculada 
		 * guardarla en un hashtable, alumno:notaMedia.
		 * datos proporcionados por user
		 */
						
		Scanner lector = new Scanner(System.in);
		
		Hashtable<String,Integer> avgGradeStudent = new Hashtable<String,Integer>(); //inicializamos hastable que vamos a usar
		
		System.out.println("Cuantos alumnos vas a introducir?");
		int students = lector.nextInt();
		lector.nextLine();
		
		for(int i=0;i<students;i++) {
			addStudent(avgGradeStudent,lector); //introduciremos tantos como hemos indicado por teclado
		}
		
		System.out.println(avgGradeStudent.toString()); //imprimimos key y valor de la hastable 
		
		lector.close(); //cerramos el scanner que hemos estado usando
		
	}

	private static void addStudent(Hashtable<String,Integer> avgGradeStudent,Scanner lector) {
			
		System.out.println("Indica nombre del alumno");
		String student = lector.nextLine();
		
		System.out.println("Indica las notas del alumno del curso. Introduce -1 cuando hayas terminado");		
						
		avgGradeStudent.put(student,resultAvgGrade(addGrades(lector)));	//añadimos al hastable, el nombre y la nota media calculada
		
	}

	private static ArrayList<Integer> addGrades(Scanner lector) {
		ArrayList<Integer> grades = new ArrayList<Integer>(); //creamos el arrayList como auxiliar para guardar las notas que sean
																//se reinicia en cada llamada del metodo		
		boolean end = false;
		while(!end){
			if(lector.hasNextInt()) {
				int nota = lector.nextInt();			
				if(nota!=-1) {
					grades.add(nota);
				}else {
					end = true;
					lector.nextLine();
				}				
			}			
		}
		return grades;
	}

	private static int resultAvgGrade(ArrayList<Integer> grades) {  //calculo nota media 
		int sum = 0;
		int avg;
		
		Iterator<Integer> it = grades.iterator();
		while(it.hasNext()) {
			sum += it.next();
		}
		avg = sum/grades.size();
		return avg;
	}

}
