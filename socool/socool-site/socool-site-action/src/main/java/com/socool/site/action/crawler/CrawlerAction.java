/****/
package com.socool.site.action.crawler;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author liuwp
 * @date 2016年12月8日
 */
@Controller
@RequestMapping("crawler")
public class CrawlerAction {

	@ResponseBody
	@RequestMapping(value = "/webCrawler.shtml", method = RequestMethod.POST)
	public Map<String, Object> webCrawler(final String webUrl) {
		return null;
	}
}
