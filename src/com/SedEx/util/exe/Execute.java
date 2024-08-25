package com.SedEx.util.exe;

import java.util.Arrays;

import com.SedEx.main.service.Service;

public class Execute {
	public static Object execute(Service service, Object obj) throws Exception {
		//실행로그 출력
		//데이터 처리(전달 데이터, )
		System.out.println("#실행로그 기록 시작#");
		
		long start=System.nanoTime();
		
		System.out.println("전달 데이터: "
				+((obj instanceof Object[])?Arrays.toString((Object[])obj):obj));
		
		Object result=service.service(obj);
		System.out.println("결과 데이터: "+result);

		long end=System.nanoTime();
		
		System.out.println("로그 기록: "+(end-start));
		System.out.println("#실행로그 기록 종료#");

		return result;
	}
}