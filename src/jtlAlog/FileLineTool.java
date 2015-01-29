package jtlAlog;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;

public class FileLineTool {

	    // ��ȡ�ļ�ָ���С�  
	    static void readAppointedLineNumber(File sourceFile, int lineNumber)  
	            throws IOException {  
	        FileReader in = new FileReader(sourceFile);  
	        LineNumberReader reader = new LineNumberReader(in);  
	        String s = "";  
	        if (lineNumber <= 0 || lineNumber > getTotalLines(sourceFile)) {  
	            System.out.println("�����ļ���������Χ(1��������)֮�ڡ�");  
	            System.exit(0);  
	        }  
	        int lines = 0;  
	        while (s != null) {  
	            lines++;  
	            s = reader.readLine();  
	            if((lines - lineNumber) == 0) {  
	             System.out.println(s);  
	             System.exit(0);  
	            }  
	        }  
	        reader.close();  
	        in.close();  
	    }  
	    // �ļ����ݵ���������  
	    static int getTotalLines(File file) throws IOException {  
	        FileReader in = new FileReader(file);  
	        LineNumberReader reader = new LineNumberReader(in);  
	        String s = reader.readLine();  
	        int lines = 0;  
	        while (s != null) {  
	            lines++;  
	            s = reader.readLine();  
	        }  
	        reader.close();  
	        in.close();  
	        return lines;  
	    }  
	      	
	public static void main(String[] args) throws IOException {
		long begin = System.currentTimeMillis();
//		// ָ����ȡ���к�  
//        int lineNumber = 2;  
//        // ��ȡ�ļ�  
//        File sourceFile = new File("D:/Jmeter.jtl");  
//        // ��ȡָ������  
////        readAppointedLineNumber(sourceFile, lineNumber);  
//        // ��ȡ�ļ������ݵ�������  
//        System.out.println(getTotalLines(sourceFile));  
		

		File test = new File("D:/Jmeter.jtl");
		long fileLength = test.length();
		LineNumberReader rf = null;
		try {
			rf = new LineNumberReader(new FileReader(test));
			if (rf != null) {
				int lines = 0;
				rf.skip(fileLength);
				lines = rf.getLineNumber();
				System.out.println(lines);
				rf.close();
			}
		} catch (IOException e) {
			if (rf != null) {
				try {
					rf.close();
				} catch (IOException ee) {
				}
			}
		}
		long end = System.currentTimeMillis();
		System.out.println(end - begin);
	}
}