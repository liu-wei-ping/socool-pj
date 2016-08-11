/****/
package com.socool.site.biz.javaapi.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
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
import com.socool.site.bo.baiduapi.StockInfoBo;
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
	String stockCodeUrl = "http://apis.baidu.com/apistore/stockservice/stock";
	String weatherUrl = "http://apis.baidu.com/apistore/weatherservice/recentweathers";
	String weatherUrl2 = "http://apis.baidu.com/open189/templatesms/sendtemplatesms";

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
	public Map<String, Object> queryStockInfo(String stockStr,
			final String prefix, final boolean isFristFlag) {
		final Map<String, Object> map = new HashMap<String, Object>();
		if (!StringUtils.isBlank(stockStr)) {
			stockStr = stockStr.toLowerCase();
			String httpArg = null;
			if (stockStr.indexOf(",") != -1) {
				final String[] strArr = stockStr.split(",");
				final StringBuffer buf = new StringBuffer();
				for (int i = 0; i < strArr.length; i++) {
					final String str = strArr[i];
					if (!(str.startsWith("sh") || str.startsWith("sz"))) {
						strArr[i] = "sh".concat(strArr[i]);
					}
					buf.append(strArr[i]);
					buf.append(",");
				}
				buf.deleteCharAt(buf.length() - 1);
				stockStr = buf.toString();
				httpArg = "list=1&stockid=".concat(stockStr);

			} else {
				if ((stockStr.startsWith("sh") || stockStr.startsWith("sz"))
						&& isFristFlag) {
					httpArg = "list=2&stockid=".concat(stockStr);
				} else {
					if (stockStr.startsWith("sh") || stockStr.startsWith("sz")) {
						stockStr = stockStr.substring(2);
					}
					stockStr = prefix.concat(stockStr);
					httpArg = "list=2&stockid=".concat(stockStr);
				}
			}
			final String result = BaiduApi.request(stockCodeUrl, httpArg);
			final JSONObject re = new JSONObject(result);
			if (re.getInt("errNum") == 0) {
				final JSONObject retData = re.getJSONObject("retData");
				final JSONArray jsonArr = retData.getJSONArray("stockinfo");
				final String jsonStr = jsonArr.toString();
				final List<StockInfoBo> list = JsonConvertHelper
						.getListFromJson(jsonStr, StockInfoBo.class);
				for (final Iterator<StockInfoBo> it = list.iterator(); it
						.hasNext();) {
					final StockInfoBo stockInfoBo = it.next();
					stockInfoBo.setIncreaseStr(formatPrice(
							stockInfoBo.getIncrease()).concat("%"));
					if (StringUtils.isBlank(stockInfoBo.getName())
							|| "FAILED".equalsIgnoreCase(stockInfoBo.getName())) {
						final String code = stockInfoBo.getCode();
						String prifixA = "sz";
						if (!code.startsWith("sz") || !isFristFlag) {
							prifixA = "sz";
						} else if (!code.startsWith("sh") || !isFristFlag) {
							prifixA = "sh";
						}
						final Map<String, Object> agMap = queryStockInfo(code,
								prifixA, false);
						if (agMap.containsKey("stock_info")) {
							@SuppressWarnings("unchecked")
							final List<StockInfoBo> agList = (List<StockInfoBo>) agMap
									.get("stock_info");
							final StockInfoBo newStockInfoBo = agList.get(0);
							Collections.replaceAll(list, stockInfoBo,
									newStockInfoBo);
						}

					}
				}
				map.put("errMsg", re.getString("errMsg"));
				map.put("stock_info", list);
			} else {
				map.put("errMsg", re.getString("errMsg"));

			}
		}

		return map;
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

	private String formatPrice(String price) {
		if (StringUtils.isBlank(price)) {
			return "0.00";
		}
		final float p = Float.valueOf(price);
		final DecimalFormat fnum = new DecimalFormat("##0.00");
		price = fnum.format(p);
		return price;
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
