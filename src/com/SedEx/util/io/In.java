package com.SedEx.util.io;

import java.util.Scanner;

public class In {
	//키보드 입력
	private static Scanner sc=new Scanner(System.in);
	
	public static String getStr() {
		return sc.nextLine();
	}
	public static String getStr(String str) {
		System.out.print(str+" :: ");
		return sc.nextLine();
	}
	
	public static long getLong(String msg) {
		while(true) { //숫자 입력시 종료
			try {
				return Long.parseLong(getStr(msg));
				//getStr으로 입력한 문자열을 long형으로 변경 가능하면 정상 실행됨.
			}catch(Exception e){
				System.out.println("  [ 숫자만 입력 가능합니다. ]");
			}
		}//end of while
	}//end of getLong
}