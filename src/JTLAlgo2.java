import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.SortedMap;
import java.util.TreeMap;


public class JTLAlgo2 {
	private File jtlFile = null;								//文件（自设）
	private int byteCountOfALine = 0;							//每一行所占字节数，一般是80-90（自设）
	private int timePointCount = 0;								//时间点个数（自设）
	
	private long countOfLines = 0l;								//文件大致行数（计算得到）
	private long countOfAPoint = 0l; 							//每个点需要的行数（计算得到）
	
	private int timeStampColNum = 0;							//时间戳列号
	private int rtColNum = 0;									//响应时间列号
	private int requestColNum = 0;								//请求名称列号
	private int responseCodeColNum = 0;							//回应码列号
	
	private SortedMap<String, String> resultMap = new TreeMap<String, String>();
	
	public File getJtlFile() {
		return jtlFile;
	}
	public void setJtlFile(File jtlFile) {
		this.jtlFile = jtlFile;
	}

	public int getByteCountOfALine() {
		return byteCountOfALine;
	}
	public void setByteCountOfALine(int byteCountOfALine) {
		this.byteCountOfALine = byteCountOfALine;
	}

	public int getTimePointCount() {
		return timePointCount;
	}
	public void setTimePointCount(int timePointCount) {
		this.timePointCount = timePointCount;
	}

	
	public void setCountOfLines() {
		long fileLength = jtlFile.length();
		this.countOfLines = fileLength / byteCountOfALine;
	}	

	public void setCountOfAPoint() {
		this.countOfAPoint = countOfLines / timePointCount;
	}
	
	public void jtlCalculate(){
		System.out.println("大致总行数：" + countOfLines);
		System.out.println("每点需要行数：" + countOfAPoint);
		
		File jtlFile = this.getJtlFile();
		try {
			FileReader filereader = new FileReader(jtlFile);
			BufferedReader br = new BufferedReader(filereader);
			String line = br.readLine();
			String[] titles = line.split(",");							//读取第一行，设置各个列号
			for(int i = 0; i < titles.length; i ++){
				if(titles[i].equals("timeStamp")){
					timeStampColNum = i;
				}else if(titles[i].equals("elapsed")){
					rtColNum = i;
				}else if(titles[i].equals("label")){
					requestColNum = i;
				}else if(titles[i].equals("responseCode")){
					responseCodeColNum = i;
				}else{}
			}
			
			String firstLine = br.readLine();										//读取第一行
			String[] firstNums = firstLine.split(",");
			long startTimeStampNode = Long.parseLong(firstNums[timeStampColNum]);	//确定开始时间点
			long countOfAItem = 0l;													//单位行段内记录的行数（每次加1）
			long rtSumOfAItem = 0l;													//单位行段内记录的响应时间总和（每次加rt）
			
			
			
			while((line = br.readLine()) != null){
				String[] items = line.split(",");				
				long timeStampNode = Long.parseLong(items[0]);			//时间戳
				int rtNode = Integer.parseInt(items[1]);				//响应时间
				String requestNode = items[2];							//请求名称
				int httpCode = Integer.parseInt(items[3]);				//响应码
				
				if(httpCode == 200){
					if(countOfAItem == countOfAPoint){
						JTLNode jn = new JTLNode();
						jn.setCount(countOfAItem);
					}
				}else{
					
				}
			}
			br.close();
			filereader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	
	public static void main(String args[]){			
		JTLAlgo2 ja = new JTLAlgo2();
		
		ja.setJtlFile(new File("D:\\result.jtl"));			//设置文件
		ja.setByteCountOfALine(80);							//设置文件每行大致字符数
		ja.setTimePointCount(100);							//设置所需要的时间点数
		
		ja.setCountOfLines();								//设置好大致总行数
		ja.setCountOfAPoint();								//设置好每个点所要的行数
		
		ja.jtlCalculate();
	}
	
}


