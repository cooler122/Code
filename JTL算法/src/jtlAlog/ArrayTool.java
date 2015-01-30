package jtlAlog;

public class ArrayTool {
	private final int xLength = 2;					//这个可以固定为2，只用取出2个有用的列(时间戳_1、响应时间_2)
	private int yLength = 1000;						//这个的长短或许能控制计算结果的精度（靠需要画多少点来计算出来）
	public String[][] dataArray = null;
	public int index = 0;
	
	public void setYLength(int yLen){
		yLength = yLen;
		dataArray = new String[xLength][yLength];
	}
	
	public void putData(String timeStemp, String responseTime){
		if(index >= 0 && index < yLength){
			dataArray[index][0] = timeStemp;
			dataArray[index][1] = responseTime;
			index ++;
		}else if(index >= yLength){
			throw new IllegalArgumentException("index out!");
		}
	}	
}
