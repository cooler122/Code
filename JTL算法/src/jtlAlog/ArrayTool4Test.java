package jtlAlog;

//���鹤�ߣ��������ô��ı��ж�������ÿ�е���Ч����

public class ArrayTool4Test {
	private int xLength = 1000;					//������Թ̶�Ϊ2��ֻ��ȡ��2�����õ���(ʱ���_1����Ӧʱ��_2)
	private final int yLength = 4;						//����ĳ��̻����ܿ��Ƽ������ľ��ȣ�����Ҫ�����ٵ������������
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
