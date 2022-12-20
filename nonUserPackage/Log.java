package nonUserPackage;

import java.io.Serializable;

public class Log implements Serializable{

	private static final long serialVersionUID = -5704811149581133639L;
	protected LogAction act;
	protected String description;
	
	public Log(LogAction act, String description) {
		this.act = act;
		this.description = description;
	}
	
	public LogAction getAct() {
		return act;
	}
	
	public String getDescription() {
		return description;
	}
	
	public String toString() {
		return act.name() + " " + description;
	}
	
}