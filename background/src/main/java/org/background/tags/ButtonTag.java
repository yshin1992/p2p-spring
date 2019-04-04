package org.background.tags;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.domain.system.Resource;

/**
 * 按钮Tag
 * 默认查询是公有的，不在此之列
 * @author yshin1992
 *
 */
public class ButtonTag extends SimpleTagSupport {

	private List<Resource> resources;

	public void setResources(List<Resource> resources) {
		this.resources = resources;
	}

	@Override
	public void doTag() throws JspException, IOException {
		if(null != resources && !resources.isEmpty()){
			JspWriter out = getJspContext().getOut();
			StringBuilder sb = new StringBuilder("<div class='layui-inline'>");
			for(Resource res : resources){
				sb.append("<button class='layui-btn' id='");
				String cd = res.getResourceCd();
				String htmlId = cd.substring(cd.lastIndexOf('.')+1);
				sb.append(htmlId).append("'>").append(res.getResourceNm()).append("</button>");
			}
			sb.append("</div>");
			out.println(sb.toString());
			out.flush();
		}
		
	}
	
	
	
}
