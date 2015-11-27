/**
 * 
 */
package sinhvien;

import java.awt.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

/**
 * @author Nhut
 *
 */
public class sinhvien implements Comparable {
	private String ten;
	private double diem;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ArrayList<sinhvien> danhsach_sv = new ArrayList();
		for (int i = 1; i <= 3; i++) {
			Scanner sc = new Scanner(System.in);
			sinhvien sv = new sinhvien();
			System.out.println("Nhap ten sv " + i + " ");
			sv.setTen(sc.nextLine());
			System.out.println("Nhap diem sv " + i + " ");
			sv.setDiem(sc.nextDouble());			
			danhsach_sv.add(sv);
			
		}		
//		danhsach_sv.sort(danhsach_sv);
		for (int i = 0; i < 3; i++) {
			sinhvien sv = danhsach_sv.get(i);
			System.out.println("Sinh vien " + sv.getTen() + " diem " + sv.getDiem());
		}
	}
	
	public String getTen() {
		return ten;
	}

	public void setTen(String ten) {
		this.ten = ten;
	}

	public double getDiem() {
		return diem;
	}

	public void setDiem(double diem) {
		this.diem = diem;
	}

	@Override
	public int compareTo(Object sv) {
		// TODO Auto-generated method stub
		double compare = ((sinhvien) sv).getDiem() - this.getDiem();
		int result = (compare > 0) ? 1 : (compare == 0) ? 0 : -1; 
		return result;
	}
}
