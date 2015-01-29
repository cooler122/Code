package jtlAlog;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class JTLManager {
	private String source = "";				//Ҫ�����ļ����û����裩
	private int threadNum = 0;				//������ɸѡ�߳��������û����裩
	private int standardColNum = 0;			//��׼����µ�����
	private int totalRequirePointCount = 0;	//��Ҫ���ܵ������û����裩
	private int minCharCountPerLine = 40;	//jtl�ı�������е��ַ��������û����裬��������һ��ArrayTool�����鳤�ȣ�����ֻ������С���������ô�

	
	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public int getThreadNum() {
		return threadNum;
	}

	public void setThreadNum(int threadNum) {
		this.threadNum = threadNum;
	}

	public int getStandardColNum() {
		return standardColNum;
	}

	public void setStandardColNum(int standardColNum) {
		this.standardColNum = standardColNum;
	}

	public int getTotalRequirePointCount() {
		return totalRequirePointCount;
	}

	public void setTotalRequirePointCount(int totalRequirePointCount) {
		this.totalRequirePointCount = totalRequirePointCount;
	}

	public int getMinCharCountPerLine() {
		return minCharCountPerLine;
	}

	public void setMinCharCountPerLine(int minCharCountPerLine) {
		this.minCharCountPerLine = minCharCountPerLine;
	}

	public void sectionalProcess(){
		try {
			RandomAccessFile file = new RandomAccessFile(source, "r");
			long totalLen = file.length();
			long threadDivision = totalLen / threadNum;								//һ���Σ��̶߳Σ����ַ���������ÿ���̶߳�ȡ���ַ�����������õ���			
			long pointCountPerThread = totalRequirePointCount / threadNum;			//ÿ���̷ֵ߳��ĵ�����ͬʱҲ��ÿ������Ҫ�ֵĽ�����
			long threadSection = threadDivision / pointCountPerThread;				//һ���ڵ��ַ�����������õ���
			
			for(int i = 1; i <= threadNum; i ++){
				ProviderThread st = new ProviderThread();
				st.setThreadId(i);													//Ϊ�߳�����threadId���˱���׼����i�ε㼯��׼��
				st.setThreadName("ProcessThread--" + i);							//�߳�����
				st.setSource(source);												//�̶߳�ȡ��������Դ
				st.setThreadStart(threadDivision * (i - 1));						//�߳���ʼ��
				st.setThreadDivision(threadDivision);								//�̶߳Σ�һ���߳��ܹ�Ҫ�����ַ�����
				st.setThreadSection(threadSection);									//�߳̽ڣ�һ���̴߳���һ����Ҫ����һ���ڵ��ַ�����
				st.setStandardColNum(standardColNum);								//��׼�������ж��߳��Ƿ��ȡ�������ݵı�׼��
				st.start();				
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String args[]){
		JTLManager sm = new JTLManager();
		
		sm.setSource("D:/Jmeter.jtl");
		sm.setThreadNum(5);
		sm.setStandardColNum(10);
		
		sm.sectionalProcess();
	}
	
}
