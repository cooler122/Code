import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.SortedMap;
import java.util.TreeMap;


public class JTLAlgo2 {
	private File jtlFile = null;								//�ļ������裩
	private int byteCountOfALine = 0;							//ÿһ����ռ�ֽ�����һ����80-90�����裩
	private int timePointCount = 0;								//ʱ�����������裩
	
	private long countOfLines = 0l;								//�ļ���������������õ���
	private long countOfAPoint = 0l; 							//ÿ������Ҫ������������õ���
	
	private int timeStampColNum = 0;							//ʱ����к�
	private int rtColNum = 0;									//��Ӧʱ���к�
	private int requestColNum = 0;								//���������к�
	private int responseCodeColNum = 0;							//��Ӧ���к�
	
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
		System.out.println("������������" + countOfLines);
		System.out.println("ÿ����Ҫ������" + countOfAPoint);
		
		File jtlFile = this.getJtlFile();
		try {
			FileReader filereader = new FileReader(jtlFile);
			BufferedReader br = new BufferedReader(filereader);
			String line = br.readLine();
			String[] titles = line.split(",");							//��ȡ��һ�У����ø����к�
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
			
			String firstLine = br.readLine();										//��ȡ��һ��
			String[] firstNums = firstLine.split(",");
			long startTimeStampNode = Long.parseLong(firstNums[timeStampColNum]);	//ȷ����ʼʱ���
			long countOfAItem = 0l;													//��λ�ж��ڼ�¼��������ÿ�μ�1��
			long rtSumOfAItem = 0l;													//��λ�ж��ڼ�¼����Ӧʱ���ܺͣ�ÿ�μ�rt��
			
			
			
			while((line = br.readLine()) != null){
				String[] items = line.split(",");				
				long timeStampNode = Long.parseLong(items[0]);			//ʱ���
				int rtNode = Integer.parseInt(items[1]);				//��Ӧʱ��
				String requestNode = items[2];							//��������
				int httpCode = Integer.parseInt(items[3]);				//��Ӧ��
				
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
		
		ja.setJtlFile(new File("D:\\result.jtl"));			//�����ļ�
		ja.setByteCountOfALine(80);							//�����ļ�ÿ�д����ַ���
		ja.setTimePointCount(100);							//��������Ҫ��ʱ�����
		
		ja.setCountOfLines();								//���úô���������
		ja.setCountOfAPoint();								//���ú�ÿ������Ҫ������
		
		ja.jtlCalculate();
	}
	
}


