package org.p2p.form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.p2p.form.group.ForgotPasswordCheck;
import org.p2p.form.group.LoginCheck;
import org.p2p.form.group.RegistCheck;
/**
 * 注册表单（用于校验)
 * https://blog.csdn.net/u013815546/article/details/77248003
 * @author yanshuai
 *
 */
public class RegisterForm {

	@NotNull(message="用户名不能为空",groups={RegistCheck.class})
	@Size(max=16,message="用户名最大长度为16个字符",groups={RegistCheck.class})
	private String nickName;// 用户呢称
	
	@NotNull(message="手机号不能为空",groups={RegistCheck.class,LoginCheck.class,ForgotPasswordCheck.class})
	@Size(max=11,min=11,message="手机号非法",groups={RegistCheck.class,LoginCheck.class,ForgotPasswordCheck.class})
	private String phone;
	
	@NotNull(message="密码不能为空",groups={RegistCheck.class,LoginCheck.class,ForgotPasswordCheck.class})
	@Size(min=6,message="密码最短为6个字符",groups={RegistCheck.class,LoginCheck.class,ForgotPasswordCheck.class})
	private String password;
	
	@NotNull(message="密码不能为空",groups={RegistCheck.class,LoginCheck.class,ForgotPasswordCheck.class})
	@Size(min=6,message="密码最短为6个字符",groups={RegistCheck.class,LoginCheck.class,ForgotPasswordCheck.class})
	private String confirm;
	
	@NotNull(message="住址不能为空",groups={RegistCheck.class})
	private String address;
	
	@NotNull(message="电子邮件地址不能为空",groups={RegistCheck.class})
	private String email;
	
	@NotNull(message="图形验证码不能为空",groups={RegistCheck.class,LoginCheck.class,ForgotPasswordCheck.class})
	private String captcha;//图形验证码
	
	@NotNull(message="手机验证码不能为空",groups={RegistCheck.class,ForgotPasswordCheck.class})
	private String vfCode;//手机验证码

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirm() {
		return confirm;
	}

	public void setConfirm(String confirm) {
		this.confirm = confirm;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCaptcha() {
		return captcha;
	}

	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}

	public String getVfCode() {
		return vfCode;
	}

	public void setVfCode(String vfCode) {
		this.vfCode = vfCode;
	}

	@Override
	public String toString() {
		return "RegisterForm [nickName=" + nickName + ", phone=" + phone
				+ ", password=" + password + ", confirm=" + confirm
				+ ", address=" + address + ", email=" + email + ", captcha="
				+ captcha + ", vfCode=" + vfCode + "]";
	}
	
}
