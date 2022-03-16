/**
 * @author Harjinder Singh Banga
 * @email hsbanga@yahoo.com
 * @create date 14/03/2022 
 * @modify By:  date:
 * 
 * @desc 
 */

package utilities;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import configuration.TestData;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResources;
import stepDefinations.Hooks;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.output.WriterOutputStream;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter; // Import the FileWriter class

public class Utils {

	public RequestSpecification req = null;
	public HashMap<String, String> queryParameters = new HashMap<String, String>();
	public Map<String, String> headerParameters = new HashMap<String, String>();
	public Map<String, String> pathParameters = new HashMap<String, String>();
	public static Map<String, String> bodyParameters = new HashMap<String, String>();
	Map<String, String> map = null;
	private HashMap<String, String> apiResponse = new HashMap<String, String>();
	PreemptiveBasicAuthScheme auth = new PreemptiveBasicAuthScheme();

	String apiEndpoint = null;
	String parameters = "?";
	JsonPath jsonPath = null;
	static DateFormat dateFormat = new SimpleDateFormat("ddMMYYHHmmss");
	static Date date = new Date();
	TestData data = new TestData();
	public static Object bodyparam;
	RequestSpecification res;
	ResponseSpecification resSpec;
	public Response response;
	public APIResources resource;

	public void setPlace(String place) {
		try {
			switch (place) {
			case "HEADER":
				map = headerParameters;
				break;
			case "QUERY":
				map = queryParameters;
				break;
			case "BODY":
				map = bodyParameters;
				break;
			case "PATH":
				map = pathParameters;
			}
		} catch (Exception e) {

			LogBack.log.error("Place " + place + "Not Found");
			e.printStackTrace();
		}
	}

	public void setParameters(String condition, String parameterName, String place) {

		try {

			setPlace(place);

			if (condition.equalsIgnoreCase("correct")) {
				if (parameterName.equalsIgnoreCase("SANITIZED_EMAIL_VALUE")
						|| (parameterName.equalsIgnoreCase("SOCIAL_EMAILID")
								|| parameterName.equalsIgnoreCase("SECONDARY_EMAIL"))) {
					if (map.equals(bodyParameters) && parameterName.equalsIgnoreCase("SANITIZED_EMAIL_VALUE")) {
						bodyParameters.put("VALUE", data.parameterData().get(parameterName));
					} else {
						map.put("email", data.parameterData().get(parameterName));
					}

				} else
					map.put(parameterName, data.parameterData().get(parameterName));
//				System.out.println("------------ " + data.parameterData().get(parameterName));
//				System.out.println(map);

			} else {
				redefineParameters(condition, parameterName, place);
			}

		} catch (Exception e) {

			LogBack.log.error("There is an error while updating the parameter - " + parameterName);
			e.printStackTrace();
		}

	}

	public void setParametersWithSelfValue(String parameterValue, String parameterName, String place) {

		try {
			setPlace(place);
			map.put(parameterName, parameterValue);
			System.out.println(map);

		} catch (Exception e) {
			e.printStackTrace();
			LogBack.log.error("There is an error while updating the parameter - " + parameterName);

		}

	}

	public void redefineParameters(String condition, String parameterName, String place) {
		setPlace(place);
		if (condition.equalsIgnoreCase("invalid")) {
			if (parameterName.equalsIgnoreCase("Authorization")) {

				map.put(parameterName, "Bearer " + RandomStringUtils.randomAlphabetic(6));

			} else
				map.put(parameterName, RandomStringUtils.randomAlphabetic(6));

		}

		else if (condition.equalsIgnoreCase("incorrect")) {

			map.put(parameterName, data.parameterData().get(parameterName)
					.replaceAll(data.parameterData().get(parameterName).substring(2, 5), "abc"));

		} else if (condition.equalsIgnoreCase("null")) {

			map.put(parameterName, "");

		} else if (condition.equalsIgnoreCase("expired") || condition.equalsIgnoreCase("expire")) {

			String newParameterName = "EXPIRED_" + parameterName;

			map.put(parameterName, data.parameterData().get(newParameterName));

		}
	}

	public String writeHTMLfile(String HTML) {
		FileWriter myWriter = null;
		File file = null;
		try {
			myWriter = new FileWriter("demo.html");
			myWriter.write(HTML);
			myWriter.close();
			file = new File("demo.html"); // Is used to get the absolute path
		} catch (IOException e) {
			e.printStackTrace();
			e.printStackTrace();
		}
		return file.getAbsolutePath();

	}

	public RequestSpecification requestSpecification(String apiType) {

		PrintStream log = null;

		try {

			log = new PrintStream(new FileOutputStream("./Logs/" + dateFormat.format(date) + ".txt", true));

		} catch (IOException e) {
			e.printStackTrace();
			LogBack.log.error(e);
		} finally {

			auth.setUserName(data.parameterData().get("AUTH_USERNAME"));
			auth.setPassword(data.parameterData().get("AUTH_PASSWORD"));
			System.out.print("APIType" + data.parameterData().get(apiType));
			System.out.println("pathParameters" + pathParameters);
			System.out.println("queryParameters" + queryParameters);
			System.out.println("headerParameters" + headerParameters);

			req = new RequestSpecBuilder().setUrlEncodingEnabled(false).setBaseUri(data.parameterData().get(apiType))
					.setAuth(auth).addPathParams(pathParameters).addQueryParams(queryParameters)
					.addHeaders(headerParameters).addFilter(RequestLoggingFilter.logRequestTo(log))
					.addFilter(ResponseLoggingFilter.logResponseTo(log)).setContentType(ContentType.JSON).build();
		}

		return req;

	}

	public Boolean isStatus(String apiName, String apiType, String methodType) {

		if (methodType.equalsIgnoreCase("Post") || methodType.equalsIgnoreCase("Put")
				|| methodType.equalsIgnoreCase("Delete")) {

			try {
				res = given().spec(requestSpecification(apiType)).body(bodyparam);

			} catch (Exception e) {
				e.printStackTrace();
				LogBack.log.error(e);
			}
		} else {
			try {
				res = given().spec(requestSpecification(apiType));
			} catch (Exception e) {
				e.printStackTrace();
				LogBack.log.error(e);
			}
		}

		resource = APIResources.valueOf(apiName);

		if (methodType.equalsIgnoreCase("POST")) {
			response = res.when().post(resource.getResource());
		} else if (methodType.equalsIgnoreCase("GET")) {
			response = res.when().get(resource.getResource());
		} else if (methodType.equalsIgnoreCase("PUT")) {
			response = res.when().put(resource.getResource());
		} else if (methodType.equalsIgnoreCase("DELETE")) {
			System.out.println("under delete methodtype");
			response = res.when().delete(resource.getResource());

		}

		if (response == null || response.asString().isEmpty() || response.getStatusCode() == 404) {
			return false;
		} else if (response.getStatusCode() == 429) {
			headerParameters.put("Retry-After", "10");
			return false;
		} else {
			return true;
		}

	}

	public String callApiFromBrowser(String basePath, String apiType) {
		try {
			queryParameters.entrySet().forEach(entry -> {
				parameters = parameters + entry.getKey() + "=" + entry.getValue() + "&";
			});
			apiEndpoint = data.parameterData().get(apiType) + "/" + basePath + "/" + parameters;
		} catch (Exception e) {
			e.printStackTrace();
			LogBack.log.error(e);
		} finally {
			parameters = "?";
		}
		LogBack.log.info("API EndPoint:	" + apiEndpoint);
		return apiEndpoint;

	}

	public boolean isValidURL(String urlStr) {

		try {
			@SuppressWarnings("unused")
			URL url = new URL(urlStr);
			return true;
		} catch (MalformedURLException e) {
			return false;
		}
	}

	public HashMap<String, String> getapiResponse() {

		return apiResponse;
	}

	public String getJsonPath(Response response, String key) {

		JsonPath path = new JsonPath(data.getStringBetweenTwoChars(response.asString()));
		return path.get(key).toString();
	}

	public static void captureScreenshots(WebDriver driver) throws IOException {

		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		FileUtils.copyFile(src, new File("./Screenshots/" + "./" + Hooks.splName[0] + "./" + Hooks.splName[1] + "_"
				+ dateFormat.format(date) + ".png"), true);

	}

	public String getattributevalue(String apiName, String parameter) {
		jsonPath = new JsonPath(apiResponse.get(apiName));
		if (parameter.equalsIgnoreCase("PhoneId")) {
			String number = jsonPath.get(parameter).toString();
			number = number.replaceAll("[\\D]", "");
			return number;
		} else {
			return jsonPath.get(parameter).toString();
		}
	}

	public Boolean getJsonFromMap(String apiName) {

		return true;

	}

	public static void deleteFilesOlderThanNdays(int daysBack, String dirWay) {

		File directory = new File(dirWay);
		try {
			if (directory.exists()) {

				File[] listFiles = directory.listFiles();
				long purgeTime = System.currentTimeMillis() - (daysBack * 24 * 60 * 60 * 1000);
				for (File listFile : listFiles) {
					if (listFile.lastModified() < purgeTime) {
						if (!listFile.delete()) {
							System.err.println("Unable to delete file: " + listFile);
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			LogBack.log.error(e);

		}
	}

}
