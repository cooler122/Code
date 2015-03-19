package study.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.concurrent.ConcurrentHashMap;

public class Test {
	private ArrayList al;
	public HashMap hm;
	public Hashtable ht;
	public ConcurrentHashMap chm;
	public LinkedHashMap lhm;
	public static void main(String args[]){
//		System.out.println(1 << 30);
//		System.out.println(Integer.MAX_VALUE);
//		System.out.println(1 & 2);
//		System.out.println(3323 & 15);
//		System.out.println(Integer.toBinaryString(3323));
//		System.out.println(Integer.toBinaryString(15));
//		
//		String a = "0100";
//		int d = Integer.parseInt(a, 2); // 2进制
//		int o = Integer.parseInt(a, 8); // 8进制
//		System.out.println(d);
//		System.out.println(o);
		
		LinkedHashMap<String, String> hm = new LinkedHashMap<String,String>(16,  1.0f);
		for(int i = 0; i < 1000; i ++){
			hm.put(i + "", i + "");
		}
		System.out.println(hm.toString());
//		System.out.println(hm.size());
		
//		int oldCapacity = 14;
//		int newCapacity = (oldCapacity << 1) + 1;
//		System.out.println(newCapacity);
		
//		System.out.println(0x7FFFFFFF);
		
	}
}
