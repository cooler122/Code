package jtlAlog;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class JTLManager {
	private String source = "";				//要读的文件（用户自设）
	private int threadNum = 0;				//开启的筛选线程数量（用户自设）
	private int standardColNum = 0;			//标准情况下的列数
	private int totalRequirePointCount = 0;	//需要的总点数（用户自设）
	private int minCharCountPerLine = 40;	//jtl文本最短行中的字符数量（用户自设，用来估算一个ArrayTool的数组长度，此量只可设置小，不可设置大）
	private float arrayLenRate = 1.0f;		//这个量用来乘估算出来的数组长度，进而扩大数组长度（用户自设，不可小于1）
	
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

	public float getArrayLenRate() {
		return arrayLenRate;
	}

	public void setArrayLenRate(float arrayLenRate) {
		this.arrayLenRate = arrayLenRate;
	}

	public void sectionalProcess(){
		RandomAccessFile file = null;
		try {
			file = new RandomAccessFile(source, "r");
			long totalLen = file.length();
			long threadDivision = totalLen / threadNum;								//一个段（线程段）的字符数量，即每个线程读取的字符数量（计算得到）			
			long pointCountPerThread = totalRequirePointCount / threadNum;			//每个线程分担的点数（同时也是每个段需要分的节数）
			long threadSection = threadDivision / pointCountPerThread;				//一个节的字符数量（计算得到）
			int sectionArrayMaxLen = (int)(threadSection / minCharCountPerLine * arrayLenRate);	//一个节获取的行数的估算值（乘上自设的调整倍数）
			
			for(int i = 1; i <= threadNum; i ++){
				ProviderThread st = new ProviderThread();
				st.setThreadId(i);													//为线程设置threadId，此变量准备第i段点集做准备
				st.setThreadName("ProcessThread--" + i);							//线程名称
				st.setSource(source);												//线程读取的数据来源
				st.setThreadStart(threadDivision * (i - 1));						//线程起始点
				st.setThreadDivision(threadDivision);								//线程段（一个线程总共要读的字符数）
				st.setThreadSection(threadSection);									//线程节（一个线程创造一个点要读的一个节的字符数）
				st.setStandardColNum(standardColNum);								//标准列数（判断线程是否采取此行数据的标准）
				st.setSectionArrayMaxLen(sectionArrayMaxLen);						//最大数组长度（用上面用每行最少字符数量估算出来的）
				st.start();				
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				file.close();														//关闭文件流
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public static void main(String args[]){
		JTLManager sm = new JTLManager();
		
		sm.setSource("D:/result0.jtl");												//设置文件来源
		sm.setThreadNum(2);															//设置线程数量
		sm.setStandardColNum(10);													//设置标准行数
		sm.setTotalRequirePointCount(10); 											//需要的总点数
		sm.setMinCharCountPerLine(60);
		//注意，还有很多其他的变量也需要事先设置
		sm.sectionalProcess();
	}
	
}
