package servlets;

import java.util.ArrayList;

public class DealDetailsPOJO {

	long dealid;
	String dealname;
	String dealDesc;
	String dealDetail;
	String dealNumbers;
	String dealEventAttendees;
	String scheduleTime;
	String type;
	Boolean status;
	int subscribedDealCount;
	
	public int getSubscribedDealCount() {
		return subscribedDealCount;
	}
	public void setSubscribedDealCount(int subscribedDealCount) {
		this.subscribedDealCount = subscribedDealCount;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	public String getScheduleTime() {
		return scheduleTime;
	}
	public void setScheduleTime(String scheduleTime) {
		this.scheduleTime = scheduleTime;
	}
	String[] tags;
	ArrayList<String> altags;
	
	
	public ArrayList<String> getAltags() {
		return altags;
	}
	public void setAltags(ArrayList<String> altags) {
		this.altags = altags;
	}
	String tagsString;
	
	
	public String getTagsString() {
		return tagsString;
	}
	public void setTagsString(String tagsString) {
		this.tagsString = tagsString;
	}
	public long getDealid() {
		return dealid;
	}
	public void setDealid(long dealid) {
		this.dealid = dealid;
	}
	public String getDealname() {
		return dealname;
	}
	public void setDealname(String dealname) {
		this.dealname = dealname;
	}
	public String getDealDesc() {
		return dealDesc;
	}
	public void setDealDesc(String dealDesc) {
		this.dealDesc = dealDesc;
	}
	public String getDealDetail() {
		return dealDetail;
	}
	public void setDealDetail(String dealDetail) {
		this.dealDetail = dealDetail;
	}
	public String getDealNumbers() {
		return dealNumbers;
	}
	public void setDealNumbers(String dealNumbers) {
		this.dealNumbers = dealNumbers;
	}
	public String getDealEventAttendees() {
		return dealEventAttendees;
	}
	public void setDealEventAttendees(String dealEventAttendees) {
		this.dealEventAttendees = dealEventAttendees;
	}
	public String[] getTags() {
		return tags;
	}
	public void setTags(String[] tags) {
		this.tags = tags;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
}
