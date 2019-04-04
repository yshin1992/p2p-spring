package org.background.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 * 导航栏tag
 * @author yshin1992
 *
 */
public class NavigationTag extends SimpleTagSupport {

    private String navigations;

    public void setNavigations(String navigations) {
        this.navigations = navigations;
    }

	@Override
	public void doTag() throws JspException, IOException {
		JspWriter out = this.getJspContext().getOut();
		StringBuilder builder = new StringBuilder(
				"<i class='layui-icon layui-icon-home'></i> <span class=''>");
		String[] navs = navigations.split(",");
		for (int i = 0; i < navs.length; i++) {
			builder.append("<a>");
			if (i == navs.length - 1) {
				builder.append("<cite>");
			}
			builder.append("&nbsp;").append(navs[i]).append("&nbsp;");
			if (i == navs.length - 1) {
				builder.append("</cite>");
			}else{
				builder.append("/");
			}
			builder.append("</a>");
		}
		builder.append("</span><hr/>");
		out.println(builder.toString());
		out.flush();
	}
}
