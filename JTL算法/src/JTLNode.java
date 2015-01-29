
public class JTLNode {
	private long skipCount = 0;					//跳过去的行数
	private long count = 0l;					//统计的行数
	private long rtSum = 0l;					//统计的响应时间总和
	private long startTimeStamp = 0;			//开始时的时间戳
	private long endTimeStamp = 0;				//结束时的时间戳
	
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
