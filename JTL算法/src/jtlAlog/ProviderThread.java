package jtlAlog;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.HashMap;
import java.util.Map;

public class ProviderThread extends Thread{
	private int threadId;					//�߳�Id
	private String threadName;				//�߳�����
	private String source;					//��ȡ���ļ�����
	private long threadStart = 0;			//��ȡ����ʼ��
	private long threadDivision = 0;		//һ���̶߳�ȡ���ܳ��ȣ���һ���εĳ��ȣ�
	private long threadSection = 0;			//һ���߳�ÿ�ζ�ȡ�ĳ��ȣ���һ���ڵĳ��ȣ�
	private int standardColNum = 10;		//��ȷ��ʽ�µ�����
	private long sectionArrayMaxLen = 0;	//һ������ཨ�������鳤�ȣ����������
	
	private long computeCount = 0;			//������
	private long discardCount = 0;			//����������
	private Map<String, ArrayTool> arrayToolMap = new HashMap<String, ArrayTool>();
	
//���������߳�������װ�����Ƿ���С��˴��õ���ArrayTool������

	public ProviderThread() {	}
	
	public int getThreadId() {
		return threadId;
	}

	public void setThreadId(int threadId) {
		this.threadId = threadId;
	}

	public String getThreadName() {
		return threadName;
	}

	public void setThreadName(String threadName) {
		this.threadName = threadName;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public long getThreadStart() {
		return threadStart;
	}

	public void setThreadStart(long threadStart) {
		this.threadStart = threadStart;
	}

	public long getThreadDivision() {
		return threadDivision;
	}

	public void setThreadDivision(long threadDivision) {
		this.threadDivision = threadDivision;
	}

	public long getThreadSection() {
		return threadSection;
	}

	public void setThreadSection(long threadSection) {
		this.threadSection = threadSection;
	}

	public int getStandardColNum() {
		return standardColNum;
	}

	public void setStandardColNum(int standardColNum) {
		this.standardColNum = standardColNum;
	}

	public long getComputeCount() {
		return computeCount;
	}

	public void setComputeCount(long computeCount) {
		this.computeCount = computeCount;
	}

	public long getDiscardCount() {
		return discardCount;
	}

	public void setDiscardCount(long discardCount) {
		this.discardCount = discardCount;
	}

	public long getSectionArrayMaxLen() {
		return sectionArrayMaxLen;
	}

	public void setSectionArrayMaxLen(long sectionArrayMaxLen) {
		this.sectionArrayMaxLen = sectionArrayMaxLen;
	}

	public void run() {
		System.out.println(threadName + " start......");
		try {
			long begin = threadStart;										//���ÿ�ʼ���ĵ�
			long sectionEnd = threadStart + threadSection;					//������ʼ�Ľ�β��
			long end = threadStart + threadDivision;						//����ÿ���̵߳Ľ���λ��
			RandomAccessFile reader = new RandomAccessFile(source, "r"); 	//Ϊÿ���̶߳���һ����ȡ�Ķ���								
			reader.seek(begin); 											//��λ����ʼλ��		
			String timeStemp = "";
			String responseTime = "";
			String interfaceName = "";
			String responseStatus = "";
			
			while (begin < end) {						
				while(begin < sectionEnd){
					String line = reader.readLine();							//��ȡһ��
					computeCount ++;											//��ȡһ��֮�󣬼���������һ
					int lineLen = line.length();								//������г���
					
					//�и�ÿһ�У�Ȼ���ÿһ�н��д���start
					String[] lineItems = line.split("\\,");						//�зִ���
					if(lineItems.length != standardColNum){						//��������ϸ�ʽ���󣬷�������
						discardCount ++;										//����������һ
						continue;
					}			
					timeStemp = lineItems[0];									//��ȡʱ���
					responseTime = lineItems[1];								//��ȡ��Ӧʱ��
					interfaceName = lineItems[2];								//��ȡ�ӿ���
					responseStatus = lineItems[4];								//��Ӧ״̬
					
					
					
					//�и�ÿһ�У�Ȼ���ÿһ�н��д���end
					
					begin += lineLen;		
				}
				//���������һ��Ҫ��װ�õĶ���ӿڵ����ݼ�clone����������һ�������߳�start
				//...
				//���������һ��Ҫ��װ�õĶ���ӿڵ����ݼ�clone����������һ�������߳�end
				begin += threadSection;
				sectionEnd += threadSection;
			}			
		} catch (IOException ex) {
			System.out.println(ex);
		} catch (NullPointerException ne){
			System.out.println(ne);
		} catch (Exception e){
			System.out.println(e);
		}
		System.out.println(threadName + " end...... �� ����������" + computeCount +" ����������" + discardCount + "��");
	}
}
