package org.domain.system;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import org.annotation.FunctionEx;
import org.annotation.MenuEx;

@Entity
@DiscriminatorValue(value="Function")
public class Function extends URLLink {
	
	private static final long serialVersionUID = 8691400618766153798L;

	public  Function(){}
	
	public  Function(String id){
		setId(id);
	}
	
	public Function(FunctionEx functionEx){
		this(functionEx,null);
	}
	
	public Function(FunctionEx functionEx,MenuEx menuParentEx){
		setResourceCd(functionEx.code());
		setResourceNm(functionEx.name());
		setResourceLink(functionEx.link());
		setParentCd(functionEx.parentCd());
		setAppCd(functionEx.appCd());
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
}
