package jtlAlog;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.HashMap;
import java.util.Map;

public class ProviderThread extends Thread{
	private int threadId;					//线程Id
	private String threadName;				//线程名称
	private String source;					//读取的文件名称
	private long threadStart = 0;			//读取的起始点
	private long threadDivision = 0;		//一个线程读取的总长度（即一个段的长度）
	private long threadSection = 0;			//一个线程每次读取的长度（即一个节的长度）
	private int standardColNum = 10;		//正确格式下的列数
	private int sectionArrayMaxLen = 0;		//一个节最多建立的数组长度（估算出来的）
	
	private long computeCount = 0;			//总行数
	private long discardCount = 0;			//丢弃的行数
	private Map<String, ArrayTool> arrayToolMap = new HashMap<String, ArrayTool>();
	
//看看各个线程用数组装数据是否可行。此处用的是ArrayTool工具类

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
			long begin = threadStart;										//设置开始读的点
			long sectionEnd = threadStart + threadSection;					//设置起始的节尾点
			long end = threadStart + threadDivision;						//计算每个线程的结束位置
			reader = new RandomAccessFile(source, "r"); 	//为每个线程定义一个读取的对象								
			reader.seek(begin); 											//定位到开始位置		
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
					String line = reader.readLine();							//读取一行
					computeCount ++;											//读取一行之后，计算行数加一
					int lineLen = line.length();								//计算此行长度					
					
					//切割每一行，然后对每一列进行处理start
					String[] lineItems = line.split("\\,");						//切分此行
					if(lineItems.length != standardColNum){						//如果不符合格式需求，放弃此行
						discardCount ++;										//放弃行数加一
						continue;
					}			
					timeStemp = lineItems[0];									//获取时间戳
					responseTime = lineItems[1];								//获取响应时间
					interfaceName = lineItems[2];								//获取接口名
					responseStatus = lineItems[4];								//响应状态
					
//					ArrayTool arrayTool = dataMap.get(interfaceName);
					ArrayTool4Test arrayTool = dataMap.get(interfaceName);
					if(null == arrayTool){
//						arrayTool = new ArrayTool();
						arrayTool = new ArrayTool4Test();
						arrayTool.setXLength(sectionArrayMaxLen);				//是否要这么长的数组，要思考.......
						dataMap.put(interfaceName, arrayTool);
					}
					arrayTool.putData(computeCount + "", timeStemp, responseTime, version + "");
					//切割每一行，然后对每一列进行处理end
					
					begin += lineLen;		
				}
				//如果读完了一节要将装好的多个接口的数据集clone并交付给另一个计算线程start
				//...
				//如果读完了一节要将装好的多个接口的数据集clone并交付给另一个计算线程end
//				for(ArrayTool at : dataMap.values()){
				for(ArrayTool4Test at : dataMap.values()){

					System.out.println(this.getThreadName() + "		" + at.toString(threadId + "	"));
				}
				sectionEnd = begin + threadSection;		//此时begin已经读到此线程负责的一个节的末尾，sectionEnd要再加一个节长
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
		System.out.println(threadName + " end...... （ 计算行数：" + computeCount +" 丢弃行数：" + discardCount + "）");
	}
}
