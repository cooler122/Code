package jtlAlog;

//数组工具，用来放置从文本中读出来的每行的有效数据

public class ArrayTool4Test {
	private int xLength = 1000;					//这个可以固定为2，只用取出2个有用的列(时间戳_1、响应时间_2)
	private final int yLength = 4;						//这个的长短或许能控制计算结果的精度（靠需要画多少点来计算出来）
	public String[][] dataArray = null;
	public int index = 0;
		
	public void setXLength(int xLen){
		xLength = xLen;
		dataArray = new String[xLength][yLength];
	}
	
	public void putData(String lineNum, String timeStemp, String responseTime, String version){
		if(index >= 0 && index < xLength){
			dataArray[index][0] = lineNum;
			dataArray[index][1] = timeStemp;
			dataArray[index][2] = responseTime;
			dataArray[index][3] = version;
			index ++;
		}else if(index >= xLength){
			throw new IllegalArgumentException("index out!");
		}
	}	
	public String toString(String midStr){
		StringBuffer sb = new StringBuffer("{ " + index + " :  \n");
		for(int i = 0; i < dataArray.length; i ++){
			
			sb.append(midStr).append(" [ ").append(dataArray[i][0]).append(" , ").append(dataArray[i][1]).append(" , ").append(dataArray[i][2]).append(" ],\n ");
		}
		sb.append(" } \n");
		return sb.toString();
	}
	public String toString(){
		StringBuffer sb = new StringBuffer("{ " + index + " :  \n");
		for(int i = 0; i < dataArray.length; i ++){
			
			sb.append(" [ ").append(dataArray[i][0]).append(" , ").append(dataArray[i][1]).append(" , ").append(dataArray[i][2]).append(" , ").append(dataArray[i][3]).append(" ],\n ");
		}
		sb.append(" } \n");
		return sb.toString();
	}
}
