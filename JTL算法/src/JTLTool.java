
import java.io.*;

class Download implements Runnable {
	private String source;
	private String dest;
	private int buflen = 1024 * 1024;
	private int threadnum = 5; // �����̸߳���
	private Thread thread[]; // �����߳�����

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
			thread[i].setName(i + ""); // �����̵߳�����,�������������߳�
		}
	}

	public void run() {
		int id = Integer.valueOf(Thread.currentThread().getName());
		byte buffer[] = new byte[buflen];
		try {
			RandomAccessFile reader = new RandomAccessFile(source, "r"); // Ϊÿ���̶߳���һ����ȡ�Ķ���
			RandomAccessFile writer = new RandomAccessFile(dest, "rw");
			long alone = reader.length() % threadnum == 0 ? reader.length() // ����ÿ���߳�Ӧ�ö�ȡ�ĳ���
					/ threadnum : ((int) (Math.ceil((double) reader.length()
					/ threadnum)));
			long begin = id * alone; // �ڸ��̶߳�ȡ�ļ��Ŀ�ʼλ��
			reader.seek(begin); // ��λ����ʼλ��
			writer.seek(begin);
			long end = (begin + alone) > reader.length() ? reader.length() : (begin + alone); // ����ÿ���̵߳Ľ���λ��
			while (begin < end) {
				
				String line = reader.readLine();
				int lineLen = line.length();
				//1.�з�line
				//2.���ݽӿ����ƣ���line�Ĳ������ݷ���ָ��Map
				//
				if(begin + lineLen < end){
					begin += lineLen;
					continue;
				}else{
					break;
				}
				
//				int len = 0;
//				if (begin + buflen < end) { // �������װ��һ��������
//					len = reader.read(buffer);
//				} else {
//					len = reader.read(buffer, 0, (int) (end - begin));
//				}
//				writer.write(buffer, 0, len);
//				begin += len;
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

public class JTLTool {

	public static String source = "/export/servers/jmeter/script/jtl/JmeterTestReport201501260426.jtl";
	public static String dest = "/export/servers/jmeter/script/jtl/JmeterTestReport201501260426a.jtl";
	
	public static void main(String args[]) throws Exception {

		mutilDownload();	//���߳�
//		download();			//���߳�
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
	
	// дһ�������ֵ����ط�����ി�ֵĽ���һ�±Ƚ�
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

/*
 * 
 * ���߳��ļ��ĸ���
 * 
 * ԭ������RandomAccessFile ��Ϊÿ���߳̿�һ����ȡ��д��Ķ���
 * Ϊÿ���̼߳����Ҫ��ȡ�ļ��Ŀ�ʼλ�ú�д��λ�ã�Ȼ��������Ӧ��д�뷽��д�뵽�ļ���
 * 
 * ������ĵ��̸߳��ƺͶ��̱߳Ƚ�,ò�Ƶ��̵߳ı����߳�Ҫ��Ķ�......... 2011/11/3 20:25:54
 */