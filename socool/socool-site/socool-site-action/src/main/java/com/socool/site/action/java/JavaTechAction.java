/****/
package com.socool.site.action.java;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.socool.site.action.BaseAction;
import com.socool.site.biz.utils.Constants;

/**
 * @author liuwp
 * @date 2016年6月12日
 */
@Controller
@RequestMapping("java-tech")
public class JavaTechAction extends BaseAction {
	@RequestMapping(value = "/crawler.html")
	public ModelAndView crawler() {
		final ModelAndView model = new ModelAndView();
		model.setViewName(getViewUrl("crawler"));
		return model;
	}

	@Override
	protected String getType() {
		return Constants.TYPE_JAVA;
	}
}
