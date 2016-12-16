/****/
package com.socool.site.action.train;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author liuwp
 * @date 2016年12月15日
 */
@RequestMapping("train")
public class TrainAction {
	@RequestMapping("/index.html")
	public ModelAndView index() {
		final ModelAndView model = new ModelAndView();
		model.setViewName("train/12306_login");
		return model;
	}
}
