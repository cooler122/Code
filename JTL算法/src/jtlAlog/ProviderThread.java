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
	private int sectionArrayMaxLen = 0;		//һ������ཨ�������鳤�ȣ���������ģ�
	
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

	public int getSectionArrayMaxLen() {
		return sectionArrayMaxLen;
	}

	public void setSectionArrayMaxLen(int sectionArrayMaxLen) {
		this.sectionArrayMaxLen = sectionArrayMaxLen;
	}

	public void run() {
		System.out.println(threadName + " start......");
		RandomAccessFile reader = null;
		try {
			long begin = threadStart;										//���ÿ�ʼ���ĵ�
			long sectionEnd = threadStart + threadSection;					//������ʼ�Ľ�β��
			long end = threadStart + threadDivision;						//����ÿ���̵߳Ľ���λ��
			reader = new RandomAccessFile(source, "r"); 	//Ϊÿ���̶߳���һ����ȡ�Ķ���								
			reader.seek(begin); 											//��λ����ʼλ��		
			String timeStemp = "";
			String responseTime = "";
			String interfaceName = "";
			String responseStatus = "";
			
//			ArrayTool arrayTool = new ArrayTool();
//			arrayTool.setYLength(sectionArrayMaxLen);
			
//			Map<String, ArrayTool> dataMap = null;	
			Map<String, ArrayTool4Test> dataMap = null;	
			int version = 0;
			while (begin < end) {			
//				dataMap = new HashMap<String, ArrayTool>();
				dataMap = new HashMap<String, ArrayTool4Test>();
				
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
					
//					ArrayTool arrayTool = dataMap.get(interfaceName);
					ArrayTool4Test arrayTool = dataMap.get(interfaceName);
					if(null == arrayTool){
//						arrayTool = new ArrayTool();
						arrayTool = new ArrayTool4Test();
						arrayTool.setXLength(sectionArrayMaxLen);				//�Ƿ�Ҫ��ô�������飬Ҫ˼��.......
						dataMap.put(interfaceName, arrayTool);
					}
					arrayTool.putData(computeCount + "", timeStemp, responseTime, version + "");
					//�и�ÿһ�У�Ȼ���ÿһ�н��д���end
					
					begin += lineLen;		
				}
				//���������һ��Ҫ��װ�õĶ���ӿڵ����ݼ�clone����������һ�������߳�start
				//...
				//���������һ��Ҫ��װ�õĶ���ӿڵ����ݼ�clone����������һ�������߳�end
//				for(ArrayTool at : dataMap.values()){
				for(ArrayTool4Test at : dataMap.values()){

					System.out.println(this.getThreadName() + "		" + at.toString(threadId + "	"));
				}
				sectionEnd = begin + threadSection;		//��ʱbegin�Ѿ��������̸߳����һ���ڵ�ĩβ��sectionEndҪ�ټ�һ���ڳ�
				version ++;
			}			
		} catch (IOException ex) {
			System.out.println(ex);
		} catch (NullPointerException ne){
			System.out.println(ne);
		} catch (Exception e){
			System.out.println(e);
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println(threadName + " end...... �� ����������" + computeCount +" ����������" + discardCount + "��");
	}
}
