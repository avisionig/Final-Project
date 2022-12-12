package nonUserPackage;

import java.io.Serializable;

import userPackage.User;

public class Request implements Serializable{

	private static final long serialVersionUID = 2212615291854504554L;
	protected String reqID;
	protected String description;
	protected User reqAuthor;
	protected RequestType requestType;
	private static int reqNum = 0;
	{
		reqNum++;
	}
	
	public Request(String description, User reqAuthor, RequestType requestType) {
		this.description = description;
		this.reqAuthor = reqAuthor;
		this.requestType = requestType;
		this.setReqID();
	}
	
	private void setReqID() {
		this.reqID = "REQ0" + (this.requestType.ordinal() + 1) + reqNum;
	}
	public User getRequestAuthor() {
		return this.reqAuthor;
	}
	public String getDesctiption() {
		return this.description;
	}
	public RequestType getRequestType() {
		return this.requestType;
	}
	public String toString() {
		return this.reqID + " " + this.description + " request author " + this.reqAuthor;
	}
}
