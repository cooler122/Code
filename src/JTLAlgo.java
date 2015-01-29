import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class JTLAlgo {
	
	private static int step = 1000;								//10s，单位时间段
	private static long dropCount = 0l;
	private static long errorCount = 0l;
	private static ArrayList<NumBean> beanList = new ArrayList<NumBean>();
	
	public static void main(String args[]){
				
		JTLAlgo.jtlCalculate(new File("D:\\result.jtl"));
		for(NumBean nb : beanList){
			System.out.println("tps：" + nb.getTps() + "  ,rt:" + nb.getRt());
		}
		System.out.println("丢弃行数" + dropCount + ", 错误行：" + errorCount);
	}
	
	public static void jtlCalculate(File file){
		File jtlFile = new File("D:\\result.jtl");
		try {
			FileReader filereader = new FileReader(jtlFile);
			BufferedReader br = new BufferedReader(filereader);
			String line = br.readLine();
			String[] strs = line.split(",");
			long startNode = Long.parseLong(strs[0]);				//开始时间点
			long count = 0l;											//单位时间段内记录的行数
			long sum = 0l;											//单位时间段内记录的响应时间总和
			
			while((line = br.readLine()) != null){
				String[] line42Nums = line.split(",");
				long timeStempNode = Long.parseLong(line42Nums[0]);
				int rtNode = Integer.parseInt(line42Nums[1]);
				if(timeStempNode < startNode){
					dropCount ++ ;
					continue;
				}else if(timeStempNode >= startNode && timeStempNode <= startNode + step){
					count ++ ;
					sum += rtNode;
				} else if(timeStempNode > startNode + step){
					System.out.println("-----------------------------------------" + count);
					NumBean nb = new NumBean();
					nb.setStartTimeStemp(startNode);
					nb.setStep(step);
					nb.setCount(count);
					nb.setSum(sum);
					beanList.add(nb);
					startNode = timeStempNode;
					count = 0l;
					sum = 0l;
				} else{
					errorCount ++;
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
	

	
}


