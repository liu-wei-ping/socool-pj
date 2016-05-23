/****/
package com.socool.site.action.ios;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.socool.site.action.BaseAction;
import com.socool.site.biz.utils.Constants;

/**
 * @author liuwp
 * @date 2016年5月20日
 */
@Controller
@RequestMapping("ios-info")
public class IosBaseAction extends BaseAction {
	/**
	 * PHP 基本知识
	 *
	 * @return
	 */
	@RequestMapping(value = "/base.html")
	public ModelAndView iosBaseInfo() {
		final ModelAndView model = new ModelAndView();
		model.setViewName(getViewUrl("base"));
		return model;
	}

	@Override
	protected String getType() {
		return Constants.TYPE_IOS;
	}

}
