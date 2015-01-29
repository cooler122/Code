
public class JTLNode {
	private long skipCount = 0;					//����ȥ������
	private long count = 0l;					//ͳ�Ƶ�����
	private long rtSum = 0l;					//ͳ�Ƶ���Ӧʱ���ܺ�
	private long startTimeStamp = 0;			//��ʼʱ��ʱ���
	private long endTimeStamp = 0;				//����ʱ��ʱ���
	
	private double tps = 0d;
	private double rt = 0d;
	
	
	public long getSkipCount() {
		return skipCount;
	}
	public void setSkipCount(long skipCount) {
		this.skipCount = skipCount;
	}
	public long getCount() {
		return count;
	}
	public void setCount(long count) {
		this.count = count;
	}
	public long getRtSum() {
		return rtSum;
	}
	public void setRtSum(long rtSum) {
		this.rtSum = rtSum;
	}
	public long getStartTimeStamp() {
		return startTimeStamp;
	}
	public void setStartTimeStamp(long startTimeStamp) {
		this.startTimeStamp = startTimeStamp;
	}
	public long getEndTimeStamp() {
		return endTimeStamp;
	}
	public void setEndTimeStamp(long endTimeStamp) {
		this.endTimeStamp = endTimeStamp;
	}
	public double getTps() {
		tps = ((double)(count + skipCount) / ((double)(endTimeStamp - startTimeStamp) / 1000d));
		return tps;
	}
	
	public double getRt() {
		rt = ((double)rtSum / (double)count);
		return rt;
	}
	
	
	
}
