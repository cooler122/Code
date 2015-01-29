package jtlAlog;

public class NumBean {
	private long startTimeStemp = 0l;
	private long step = 0;
	
	private long count = 0l;
	private long sum = 0l;
	private double tps = 0l;
	private double rt = 0d;
	
	public long getStartTimeStemp() {
		return startTimeStemp;
	}
	public void setStartTimeStemp(long startTimeStemp) {
		this.startTimeStemp = startTimeStemp;
	}

	public long getStep() {
		return step;
	}
	public void setStep(long step) {
		this.step = step;
	}
	public long getCount() {
		return count;
	}
	public void setCount(long count) {
		this.count = count;
	}
	public long getSum() {
		return sum;
	}
	public void setSum(long sum) {
		this.sum = sum;
	}
	public double getTps() {
		return (double)count / ((double)step / 1000);
	}

	public void setTps(double tps) {
		this.tps = tps;
	}

	public void setRt(double rt) {
		this.rt = rt;
	}

	public double getRt() {
		return (double)sum / (double)count;
	}

	
	
}
