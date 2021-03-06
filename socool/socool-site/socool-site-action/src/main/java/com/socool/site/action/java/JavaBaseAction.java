/****/
package com.socool.site.action.java;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.socool.site.action.BaseAction;
import com.socool.site.biz.answerresult.ITestAnswerResultBiz;
import com.socool.site.biz.testinfo.IInterviewTestBiz;
import com.socool.site.biz.utils.Constants;
import com.socool.site.bo.answerresult.TestAnswerResultBo;
import com.socool.site.bo.testinfo.TestInfoBo;
import com.socool.site.bo.userinfo.UserInfo;
import com.socool.site.condition.PageCondition;
import com.socool.site.condition.TestInfoCondition;

/**
 * @author liuwp
 * @date 2016年5月20日
 */
@Controller
@RequestMapping("java-info")
public class JavaBaseAction extends BaseAction {
	@Autowired
	private IInterviewTestBiz iInterviewTestBiz;
	@Autowired
	private ITestAnswerResultBiz iTestAnswerResultBiz;

	@ResponseBody
	@RequestMapping(value = "/base.shtml")
	public Map<String, Object> ajaxJavaBaseInfo(final int page) {

		final TestInfoCondition condition = new TestInfoCondition();
		final PageCondition pageCondition = new PageCondition(page,
				Constants.MAX_JAVA_PAGE);
		condition.setRePage(pageCondition);
		final Map<String, Object> map = new HashMap<String, Object>();
		final int count = iInterviewTestBiz.queryInterviewCount(condition);
		List<TestInfoBo> list = null;
		if (count > 0) {
			list = iInterviewTestBiz.queryInterview(condition);
		}
		map.put("list", list);
		map.put("totalPage", pageCondition.getTotalPage(count));
		return map;
	}

	public List<TestAnswerResultBo> assemblingResult(
			final List<TestAnswerResultBo> list) {
		return list;
	}

	/**
	 * JAVA 基本知识
	 *
	 * @return
	 */
	@RequestMapping(value = "/base.html")
	public ModelAndView javaBaseInfo() {
		final ModelAndView model = new ModelAndView();
		final TestInfoCondition condition = new TestInfoCondition();
		final PageCondition pageCondition = new PageCondition(1,
				Constants.MAX_JAVA_PAGE);
		condition.setRePage(pageCondition);
		final int count = iInterviewTestBiz.queryInterviewCount(condition);

		List<TestInfoBo> list = null;
		if (count > 0) {
			list = iInterviewTestBiz.queryInterview(condition);
		}

		model.addObject("totalPage", pageCondition.getTotalPage(count));
		model.addObject("list", list);
		model.setViewName(getViewUrl("base"));
		return model;
	}

	/**
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/result.shtml", method = RequestMethod.POST)
	public ModelAndView resultTestInfo(
			@RequestBody List<TestAnswerResultBo> result,
			final HttpSession session) {
		final ModelAndView model = new ModelAndView();
		final UserInfo userinfo = (UserInfo) session
				.getAttribute(Constants.SESSION_USER);
		for (final TestAnswerResultBo testAnswerResultBo : result) {
			testAnswerResultBo.setUserId(userinfo.getUid());
			testAnswerResultBo.setStatus(2);
			testAnswerResultBo.setCategoryType(Constants.TYPE_JAVA);
		}
		final boolean f = iTestAnswerResultBiz.saveTestAnswerResult(result);
		if (f) {
			result = iTestAnswerResultBiz.queryTestAnswerResult(
					userinfo.getUid(), Constants.TYPE_JAVA);
			result = assemblingResult(result);
		}
		model.addObject("result", result);
		model.setViewName(getViewUrl("result"));
		return model;
	}

	@Override
	protected String getType() {
		return Constants.TYPE_JAVA;
	}

}
