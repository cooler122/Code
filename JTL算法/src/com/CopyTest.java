package com;

import java.io.*;

class Download implements Runnable {
	private String source;
	private String dest;
	private int buflen = 1024 * 1024;
	private int threadnum = 5; // 定义线程个数
	private Thread thread[]; // 定义线程数组

	public Download(String source, String dest) {
		this.source = source;
		this.dest = dest;
	}

	public Download(String source, String dest, int threadnum) {
		this.source = source;
		this.dest = dest;
		this.threadnum = threadnum;
	}

	public void getThread() {
		thread = new Thread[threadnum];
		for (int i = 0; i < thread.length; i++) {
			thread[i] = new Thread(this);
			thread[i].setName(i + ""); // 设置线程的名字,用于区分其他线程
		}
	}

	public void run() {
		int id = Integer.valueOf(Thread.currentThread().getName());
		byte buffer[] = new byte[buflen];
		try {
			RandomAccessFile reader = new RandomAccessFile(source, "r"); // 为每个线程定义一个读取的对象
			RandomAccessFile writer = new RandomAccessFile(dest, "rw");
			long alone = reader.length() % threadnum == 0 ? reader.length() // 计算每个线程应该读取的长度
					/ threadnum : ((int) (Math.ceil((double) reader.length()
					/ threadnum)));
			long begin = id * alone; // 第个线程读取文件的开始位置
			reader.seek(begin); // 定位到开始位置
			writer.seek(begin);
			long end = (begin + alone) > reader.length() ? reader.length() : (begin + alone); // 计算每个线程的结束位置
			while (begin < end) {
				int len = 0;
				if (begin + buflen < end) { 							// 如果可以装满一个缓冲区
					len = reader.read(buffer);
				} else {
					len = reader.read(buffer, 0, (int) (end - begin));
				}
				writer.write(buffer, 0, len);
				begin += len;
//				System.out.println("Thread-" + id + "get " + len);
			}
		} catch (IOException ex) {
			System.out.println(ex);
		}
	}

	public int getThreadNumber() {
		return this.thread.length;
	}

	public void start() {
		for (int i = 0; i < thread.length; i++)
			thread[i].start();
	}
}

public class CopyTest {

	public static String source = "/export/servers/jmeter/script/jtl/JmeterTestReport201501260426.jtl";
	public static String dest = "/export/servers/jmeter/script/jtl/JmeterTestReport201501260426a.jtl";
	
	public static void main(String args[]) throws Exception {

		mutilDownload();	//多线程
//		download();			//单线程
	}

	public static void mutilDownload() throws Exception{
		long begin = System.currentTimeMillis();
		Download load = new Download(source, dest, 5);
		load.getThread();
		load.start();
		while (true) {
			if (Thread.activeCount() == 1) {
				long end = System.currentTimeMillis();
				System.out.println("Download successfully..." + (end - begin) + "ms");
				break;
			}
		}
	}
	
	// 写一个单纯种的下载方法与多纯种的进行一下比较
	public static void download() throws IOException {
		long begin = System.currentTimeMillis();
		BufferedInputStream filein = new BufferedInputStream(new FileInputStream(source));
		BufferedOutputStream fileout = new BufferedOutputStream(new FileOutputStream(dest));
		byte buffer[] = new byte[1024 * 1024];
		int length = 0;
		while ((length = filein.read(buffer)) != -1) {
			fileout.write(buffer, 0, length);
//			System.out.println("Download " + length);
		}
		fileout.close();
		filein.close();
		long end = System.currentTimeMillis();
		System.out.println("Download successfully..." + (end - begin) + "ms");
	}

}
