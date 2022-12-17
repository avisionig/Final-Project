package nonUserPackage;

import java.io.Serializable;


public class Mark implements Serializable{

	private static final long serialVersionUID = 2041843948330859866L;
	
	
	private Attestaion first;
	private Attestaion second;
	private FinalAttestaion finalAtt;
	{
		first = new Attestaion();
		second = new Attestaion();
		finalAtt = new FinalAttestaion();
	}
	public Mark() {
		
	}
	public void setMark(double point) {
		if(this.first.getStatus()) {
			this.first.putPoint(point);
		}
		else if(this.second.getStatus()) {
			this.second.putPoint(point);
		}
		else if(this.finalAtt.getStatus()){
			this.finalAtt.putPoint(point);
		}
	}
	public void closeAttestaion() {
		if(this.first.getStatus()) {
			this.first.changeStatus();
		}
		else if (this.second.getStatus()) {
			this.second.changeStatus();
		}
		else if(this.finalAtt.getStatus()) {
			this.finalAtt.changeStatus();
		}
	}
	public boolean equals(Object o) {
		if (o == null) return false;
		if (!(o instanceof Mark)) return false;
		Mark m = (Mark)o;
		return this.first.getPoint() == m.first.getPoint() && this.second.getPoint() == m.second.getPoint() && this.finalAtt.getPoint() == m.finalAtt.getPoint(); 
	}
	public String toString() {
		return this.first.getPoint() + " | " + this.second.getPoint() + " | " + this.finalAtt.getPoint();
	}

}
