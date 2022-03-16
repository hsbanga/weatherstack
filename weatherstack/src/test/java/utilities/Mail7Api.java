/**
 * @author Harjinder Singh Banga
 * @email hsbanga@yahoo.com
 * @create date 14/03/2022 
 * @modify By:  date:
 * 
 * @desc 
 */

package utilities;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import configuration.TestData;
import io.restassured.RestAssured;

public class Mail7Api {
	RandomFunctions random = new RandomFunctions();
	TestData data = new TestData();

	public static String URL;

	public String getUrlFromEmail() throws IOException, JSONException, InterruptedException {

		String username = RandomFunctions.secondaryEmail.replaceAll("@mail7.io", "");
		System.out.println(username);
		Thread.sleep(5000);

		RestAssured.baseURI = "https://api.mail7.io";
		System.out.println(data.parameterData().get("MAIL7_APIKEY"));
		System.out.println(data.parameterData().get("MAIL7_APISECRET"));
		System.out.println("till API");

		String response = given().log().all()
				.queryParams("apikey", data.parameterData().get("MAIL7_APIKEY"), "apisecret",
						data.parameterData().get("MAIL7_APISECRET"), "to", username)
				.header("Content-Type", "application/json").when().log().all().get("inbox").then().assertThat()
				.statusCode(200).extract().response().asString();

		System.out.println("mail7 response" + response);

		JSONObject jsonObject = new JSONObject(response);
		Object data = jsonObject.get("data");

		JSONArray arrObj = jsonObject.getJSONArray("data");
		Object mailsource = arrObj.getJSONObject(0).get("mail_source");

		JSONObject jsonObject1 = (JSONObject) mailsource;
		String html = (String) jsonObject1.get("html");
		System.out.println("html" +html);

		ArrayList extractedURL = (ArrayList) extractUrls(html);
		String newURL = extractedURL.toString();

		URL = replace(newURL);

		System.out.println("mail7 URL " + URL);
		return URL;

	}

	public static String replace(String str) {

		String rs = str.replace("[", "");
		rs = rs.replace("]", "");
		rs = rs.replace("<br/", "");
		return rs;
	}

	public static List<String> extractUrls(String text) {
		List<String> containedUrls = new ArrayList<String>();
		String urlRegex = "((https?|ftp|gopher|telnet|file):((//)|(\\\\))+[\\w\\d:#@%/;$()~_?\\+-=\\\\\\.&]*)";
		Pattern pattern = Pattern.compile(urlRegex, Pattern.CASE_INSENSITIVE);
		Matcher urlMatcher = pattern.matcher(text);

		while (urlMatcher.find()) {
			containedUrls.add(text.substring(urlMatcher.start(0), urlMatcher.end(0)));

		}

		return containedUrls;
	}

	public String getQueryParameterValue(String attribute) {

		URL url = null;
		Map<String, String> map = new HashMap<String, String>();
		try {
			url = new URL(getUrlFromEmail());
			String query = url.getQuery();
			String[] params = query.split("&");

			for (String param : params) {
				String name = param.split("=")[0];
				String value = param.split("=")[1];
				map.put(name, value);
				System.out.println(map);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		String attributeValue = map.get(attribute);
		System.out.println(attributeValue);
		return attributeValue;

	}

}
