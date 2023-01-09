package nonUserPackage;

import java.io.Serializable;
/**
 * Attestation has field of score and status of attestation, if it's closed scores can't be put anymore.
 * @see nonUserPackage.Mark
 */
public class Attestaion implements Serializable{

	private static final long serialVersionUID = -90014145549590878L;
	private double point = 0;
	private boolean status = true;
	public void putPoint(double point) {
		this.point += point;
	}
	public void changeStatus() {
		this.status = false;
	}
	public double getPoint() {
		return this.point;
	}
	public boolean getStatus() {
		return this.status;
	}
}
