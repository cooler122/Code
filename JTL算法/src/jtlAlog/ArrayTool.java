package jtlAlog;

public class ArrayTool {
	private final int xLength = 2;					//������Թ̶�Ϊ2��ֻ��ȡ��2�����õ���(ʱ���_1����Ӧʱ��_2)
	private int yLength = 1000;						//����ĳ��̻����ܿ��Ƽ������ľ��ȣ�����Ҫ�����ٵ������������
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
