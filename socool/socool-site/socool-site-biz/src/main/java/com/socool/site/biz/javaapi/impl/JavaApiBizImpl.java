/****/
package com.socool.site.biz.javaapi.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.socool.site.biz.BaseBiz;
import com.socool.site.biz.javaapi.IJavaApiBiz;
import com.socool.site.biz.utils.BaiduApi;
import com.socool.site.biz.utils.JsonConvertHelper;
import com.socool.site.biz.utils.RSAUtils;
import com.socool.site.bo.baiduapi.AddressDetailBo;
import com.socool.site.bo.baiduapi.IdentityBo;
import com.socool.site.bo.baiduapi.MessageBo;
import com.socool.site.bo.baiduapi.PointBo;
import com.socool.site.bo.baiduapi.WeatherIndexBo;
import com.socool.site.bo.baiduapi.WeatherInfoBo;
import com.socool.site.bo.baiduapi.WeatherMixBo;
import com.socool.site.condition.baiduapi.LocationCondition;

/**
 * @author liuwp
 * @date 2016年6月12日
 */
@Service
public class JavaApiBizImpl extends BaseBiz implements IJavaApiBiz {
	String identityUrl = "http://apis.baidu.com/apistore/idservice/id";
	String locationUrl = "http://api.map.baidu.com/location/ip";
	String messageUrl = "http://apis.baidu.com/kingtto_media/106sms/106sms";
	String weatherUrl = "http://apis.baidu.com/apistore/weatherservice/recentweathers";
	String weatherUrl2 = " http://apis.baidu.com/open189/templatesms/sendtemplatesms";

	// String httpUrl =
	// "http://apis.baidu.com/apistore/weatherservice/citylist";

	@Override
	public Map<String, Object> getAddressLocation(
			final LocationCondition condition) {
		final Map<String, Object> resultMap = new HashMap<String, Object>();
		final String httpArg = condition.toString();
		// String tempStr = URLEncoder.encode(httpArg, "UTF-8");
		// final String md5Str = "/location/ip?" + httpArg
		// + "kLB4bKksCl4vEov7pG4INRNRo6R9SfUD";
		// final Map paramsMap = new LinkedHashMap();
		// paramsMap.put("ak", "b7t556Hc2iDvllZgNAriyVT3PDTwmQTP");
		// paramsMap.put("coor", "bd09ll");
		try {
			// final String paramsStr =
			// JsonConvertHelper.toQueryString(paramsMap);
			// final String wholeStr = new String("/location/ip?" + paramsStr
			// + "kLB4bKksCl4vEov7pG4INRNRo6R9SfUD");
			final String wholeStr2 = new String("/location/ip?" + httpArg
					+ "kLB4bKksCl4vEov7pG4INRNRo6R9SfUD");
			// final String tempStr = URLEncoder.encode(wholeStr, "UTF-8");
			// final String sn = RSAUtils.MD5(tempStr);
			final String tempStr2 = URLEncoder.encode(wholeStr2, "UTF-8");
			final String sn2 = RSAUtils.MD5(tempStr2);
			condition.setSn(sn2);
			// httpArg = paramsStr + "&sn=" + sn;
			final String httpArg2 = condition.toString();
			final String result = BaiduApi.request(locationUrl, httpArg2);
			final JSONObject re = new JSONObject(result);
			if (re.getInt("status") == 0) {
				final String address = re.getString("address");
				resultMap.put("address", address);
				final JSONObject contentObj = re.getJSONObject("content");
				final String simpAddress = contentObj.getString("address");
				resultMap.put("simpAddress", simpAddress);
				final String pointJson = String
						.valueOf(contentObj.get("point"));
				final String addressDetailJson = String.valueOf(contentObj
						.get("address_detail"));
				final AddressDetailBo contentBo = JsonConvertHelper
						.jsonToObject(addressDetailJson, AddressDetailBo.class);
				resultMap.put("content", contentBo);
				final PointBo pointBo = JsonConvertHelper.jsonToObject(
						pointJson, PointBo.class);
				resultMap.put("point", pointBo);
			} else {
				resultMap.put("err", result);
			}
		} catch (final UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// condition.setSn("kLB4bKksCl4vEov7pG4INRNRo6R9SfUD");

		return resultMap;
	}

	@Override
	public IdentityBo queryIdentityInfo(final String IdentityNo) {
		final String httpArg = "id=" + IdentityNo;
		final String result = BaiduApi.request(identityUrl, httpArg);
		final JSONObject re = new JSONObject(result);
		final int errNum = re.getInt("errNum");
		if (errNum == 0 && re.keySet().contains("retData")) {
			final Object retDataObj = re.get("retData");
			final IdentityBo identityBo = JsonConvertHelper.jsonToObject(
					String.valueOf(retDataObj), IdentityBo.class);
			String sexStr = "";
			if ("M".equalsIgnoreCase(identityBo.getSex())) {
				sexStr = "男";
			} else if ("F".equalsIgnoreCase(identityBo.getSex())) {
				sexStr = "女";
			} else if ("N".equalsIgnoreCase(identityBo.getSex())) {
				sexStr = "未知";
			}
			identityBo.setSexStr(sexStr);
			return identityBo;
		}
		return null;
	}

	@Override
	@SuppressWarnings("unchecked")
	public WeatherMixBo queryWeather(final String cityId) {
		final String result = BaiduApi.request(weatherUrl, "cityid=" + cityId);
		final JSONObject re = new JSONObject(result);
		final JSONObject retData = re.getJSONObject("retData");
		final String city = retData.getString("city");
		final JSONObject todayObj = retData.getJSONObject("today");
		final JSONArray todayIndexArr = todayObj.getJSONArray("index");
		final JSONArray historyArr = retData.getJSONArray("history");
		final JSONArray forecastArr = retData.getJSONArray("forecast");
		final List<WeatherInfoBo> forecastWeather = JsonConvertHelper
				.getObjectsFromJson(forecastArr.toString(), List.class,
						WeatherInfoBo.class);
		final List<WeatherInfoBo> historyWeather = JsonConvertHelper
				.getObjectsFromJson(historyArr.toString(), List.class,
						WeatherInfoBo.class);
		final List<WeatherIndexBo> indexList = JsonConvertHelper
				.getObjectsFromJson(todayIndexArr.toString(), List.class,
						WeatherIndexBo.class);
		final WeatherInfoBo todayWeather = JsonConvertHelper.jsonToObject(
				todayObj.toString(), WeatherInfoBo.class);
		todayWeather.setIndex(indexList);
		final WeatherMixBo weatherMixBo = new WeatherMixBo();
		weatherMixBo.setForecastWeather(forecastWeather);
		weatherMixBo.setHistoryWeather(historyWeather);
		weatherMixBo.setTodayWeather(todayWeather);
		weatherMixBo.setCity(city);
		return weatherMixBo;
	}

	@Override
	public MessageBo sendMessageToPhone(final String phone, final String content) {
		return send106Msg(phone, content);
	}

	private MessageBo send106Msg(final String phone, String content) {
		content = "【SOCOOL】验证码：" + content;
		final String httpArg = "mobile=" + phone + "&content=" + content
				+ "&tag=2";// tag 1 返回xml 2 返回json
		final String result = BaiduApi.request(messageUrl, httpArg);
		final JSONObject re = new JSONObject(result);
		if (re.optBoolean("errNum")) {
			System.out.println(re.get("errNum"));
		}
		final MessageBo messageBo = JsonConvertHelper.jsonToObject(
				re.toString(), MessageBo.class);
		return messageBo;
	}
}
