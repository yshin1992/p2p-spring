package org.vo.sms;

public class VerifyCode {
	/**
	 * 序号
	 */
	private String seqCode;
	/**
	 * 验证码
	 */
	private String verifyCode;

	public String getSeqCode() {
		return seqCode;
	}

	public void setSeqCode(String seqCode) {
		this.seqCode = seqCode;
	}

	public String getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}

	@Override
	public String toString() {
		return "VerifyCode [seqCode=" + seqCode + ", verifyCode=" + verifyCode + "]";
	}

}
