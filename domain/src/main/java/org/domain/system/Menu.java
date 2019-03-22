package org.domain.system;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import org.annotation.MenuEx;

@Entity
@DiscriminatorValue(value="Menu")
public class Menu extends URLLink {

	private static final long serialVersionUID = 6264996585971216670L;

	@Override
	public List<Resource> getChilds() {
		List<Resource> resources=super.getChilds();
		List<Resource> menus=new ArrayList<Resource>();
		if(resources!=null){
			for (Resource resource : resources) {
				if(resource instanceof Menu && resource.isEnabled()){
					menus.add(resource);
				}
			}
		}
		return menus;
	}
	
	/**
	 * 资源小图标
	 */
	@Column(name="smallIcon")
	private String smallIcon;
	/**
	 * 资源大图标
	 */
	@Column(name="bigIcon")
	private String bigIcon;
	

	public Menu(){
	}
	
	public Menu(String id){
		setId(id);
	}
	public Menu(MenuEx menuEx){
		this(menuEx, null);
	}
	/**把注解的取值复制到对象*/
	public Menu(MenuEx menuEx,MenuEx menuParentEx){
		setResourceCd(menuEx.code());
		setResourceNm(menuEx.name());
		setResourceLink(menuEx.link());
		setSmallIcon(menuEx.smallIco());
		setBigIcon(menuEx.bigIco());
		setParentCd(menuEx.parentCd());
		setAppCd(menuEx.appCd());
		setListSort(menuEx.listSort());
		if(menuParentEx!=null){
			/**如果没有指定上级菜单*/
			if(getParentCd()==null||getParentCd().trim().length()<=0){
				setParentCd(menuParentEx.parentCd());
			}
			/**如果没有指定应用程序*/
			if(getAppCd()==null||getAppCd().trim().length()<=0){
				setAppCd(menuParentEx.appCd());
			}
		}
	}
	
	/**资源小图标*/
	public String getSmallIcon() {
		return smallIcon;
	}
	/**资源小图标*/
	public void setSmallIcon(String smallIcon) {
		this.smallIcon = smallIcon;
	}
	/**资源大图标*/
	public String getBigIcon() {
		return bigIcon;
	}
	/**资源大图标*/
	public void setBigIcon(String bigIcon) {
		this.bigIcon = bigIcon;
	}

	@Override
	public String toString() {
		return "Menu [smallIcon=" + smallIcon + ", bigIcon=" + bigIcon + ", toString()=" + super.toString() + "]";
	}
}
