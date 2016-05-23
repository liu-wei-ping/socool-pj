/****/
package com.socool.site.action.index;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author liuwp
 * @date 2016年5月11日
 */
@Controller
@RequestMapping("main")
public class IndexAction {
	@RequestMapping("/{type}/center.html")
	public ModelAndView center(@PathVariable(value = "type") final String type) {
		final ModelAndView model = new ModelAndView();
		model.addObject("type", type);
		model.setViewName(type + "/" + type + "_center");
		return model;
	}

	@RequestMapping("/{type}/footer.html")
	public ModelAndView footer(@PathVariable(value = "type") final String type) {
		final ModelAndView model = new ModelAndView();
		model.addObject("type", type);
		model.setViewName("footer");
		return model;
	}

	@RequestMapping("/{type}/header.html")
	public ModelAndView hearder(@PathVariable(value = "type") final String type) {
		final ModelAndView model = new ModelAndView();
		model.addObject("type", type);
		model.setViewName(type + "/" + type + "_header");
		return model;
	}

	@RequestMapping("/index.html")
	public ModelAndView index() {
		return new ModelAndView("main");
	}

	@RequestMapping("/{type}/index.html")
	public ModelAndView layout(@PathVariable(value = "type") final String type) {
		final ModelAndView model = new ModelAndView();
		model.addObject("type", type);
		model.setViewName("layout");
		return model;
	}
}
