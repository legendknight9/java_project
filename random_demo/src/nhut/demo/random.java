/**
 * 
 */
package nhut.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Nhut
 *
 */
public class random {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Integer> dayso = new ArrayList<Integer>();
		dayso.add(1);
		dayso.add(2);
		dayso.add(3);
		dayso.add(4);
		dayso.add(5);
		for (int i = 0; i < 5; i++) {
			System.out.println("Times " + i + " : " + dayso.remove((int)(Math.random() * (4 - i)))); 
		}
		Random rand = new Random();
		rand.nextInt(5);
	}

}
