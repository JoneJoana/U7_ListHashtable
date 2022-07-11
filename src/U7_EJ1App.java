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
		
		Scanner sc = new Scanner(System.in);
		
		String student;
				
		ArrayList<Integer> grades = new ArrayList<Integer>();
		Hashtable<String,Integer> avgGradeStudent = new Hashtable<String,Integer>();
				
		int sum = 0;
		int avg;
		
		System.out.println("Indica nombre del alumno");
		student = sc.nextLine();
		
		System.out.println("Indica las notas del alumno del curso. Introduce -1 cuando hayas terminado");	
		boolean end = false;

		while(!end){
			if(sc.hasNextInt()) {
				int nota = sc.nextInt();			
				if(nota!=-1) {
					grades.add(nota);
				}else {
					end = true;
					sc.nextLine();
				}				
			}			
		}
		
		sc.close();
		
		Iterator<Integer> it = grades.iterator();
		while(it.hasNext()) {
			sum += it.next();
		}
		avg = sum/grades.size();
		
		System.out.println(avg);
		
		avgGradeStudent.put(student,avg);
		
	}

}
