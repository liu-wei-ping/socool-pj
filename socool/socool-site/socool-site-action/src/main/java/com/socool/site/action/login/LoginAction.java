/****/
package com.socool.site.action.login;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.socool.site.biz.utils.Constants;
import com.socool.site.biz.utils.CookieUtil;
import com.socool.site.biz.utils.IdentifyingCodeUtil;
import com.socool.site.biz.utils.RSAUtils;
import com.socool.site.bo.userinfo.UserInfo;
import com.socool.site.bo.utils.PublicKeyPo;

/**
 * @author mr.lwp
 * @see 2016年5月15日
 */
@Controller
@RequestMapping("login")
public class LoginAction {

	/**
	 * @param req
	 * @param resp
	 */
	@RequestMapping(value = "/code.shtml")
	public void identifyingCode(final HttpServletRequest req,
			final HttpServletResponse resp) {
		try {
			IdentifyingCodeUtil.getCode(req, resp);
		} catch (final IOException var4) {
			var4.printStackTrace();
		}

	}

	/**
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/login.html")
	public ModelAndView login(final HttpSession session,
			final HttpServletResponse response) {
		final ModelAndView model = new ModelAndView();
		final PublicKeyPo publicKeyMap = RSAUtils.getPublicKeyMap(true);
		final Cookie cookie = CookieUtil.createCookie(
				Constants.LOGIN_MAX_FAIL_KEY, Constants.LOGIN_MAX_FAIL, false);
		response.addCookie(cookie);
		final Object code = session.getAttribute(Constants.LOGIN_CODE);
		model.addObject("key", publicKeyMap);
		model.addObject("code", code);
		model.setViewName("login");
		return model;
	}

	@RequestMapping(value = "/quit.html")
	public String quit(final HttpSession session, final RedirectAttributes attr) {
		session.invalidate();
		return "redirect:/login.html";
	}

	/**
	 * @param userInfo
	 * @param session
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/sign.shtml", method = RequestMethod.POST)
	public Map<String, Object> sign(@RequestBody final UserInfo userInfo,
			final HttpServletRequest request, final HttpServletResponse response) {
		final HttpSession session = request.getSession();
		final String sessionId = session.getId();
		final Object code = session.getAttribute(Constants.LOGIN_CODE);
		boolean f = false;
		final Map<String, Object> map = new HashMap<String, Object>();
		// if (code != null &&
		// code.toString().equalsIgnoreCase(userInfo.getCode())) {
		final String pwd = RSAUtils.decryptStringByJs(userInfo.getPassword());
		userInfo.setUid(1);
		userInfo.setPassword("");
		session.setAttribute(Constants.SESSION_USER, userInfo);
		f = true;
		// }

		if (!f) {
			// final String failCount = CookieUtil.getCookieValue(request,
			// Constants.LOGIN_COOKIE_FAIL);
			@SuppressWarnings("deprecation")
			final String cookieKey = URLEncoder.encode(userInfo.getUsername());
			final String failCount = CookieUtil.getCookieValue(request,
					cookieKey, false);
			final Cookie ck = CookieUtil.createCookie(cookieKey,
					loginFailCount(failCount, true), Constants.LOGIN_FAIL_TIME,
					false);
			response.addCookie(ck);

		}
		map.put(Constants.SUCCESS, f);

		return map;
	}

	private String loginFailCount(String failCount, final boolean flag) {
		if (!flag) {
			return "1";
		}
		if (StringUtils.isNoneBlank(failCount)) {
			int count = Integer.valueOf(failCount);
			count++;
			failCount = String.valueOf(count);
		} else {
			failCount = "1";
		}
		return failCount;
	}
}
