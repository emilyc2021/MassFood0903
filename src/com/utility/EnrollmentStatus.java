package com.utility;

public enum EnrollmentStatus {

	REJECT("審核不通過", 0), 
//	WAITING_USER_FILL_IN("使用者填寫中", 1), 
	WAITING_ADMIN_REVIEW("待管理員審核", 1), 
	APPROVAL("審核通過", 2);

	EnrollmentStatus(String description, int code) {
		this.Description = description;
		this.Code = code;
	}

	private String Description;
	private int Code;

	public static EnrollmentStatus getEnrollmentStatus(int i) {
		for (EnrollmentStatus enrollmentStatus : values()) {
			if (enrollmentStatus.getCode() == i) {
				return enrollmentStatus;
			}
		}
		return null;
	}

	public String getDescription() {
		return this.Description;
	}

	public int getCode() {
		return this.Code;
	}

}
