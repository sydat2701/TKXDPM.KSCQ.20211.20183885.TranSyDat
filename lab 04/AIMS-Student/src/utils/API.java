package utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.logging.Logger;

import entity.payment.CreditCard;
import entity.payment.PaymentTransaction;

/**
 * Class cung cấp các phương thức giúp gửi request lên server và nhận dữ liệu trả về
 * Date: 11/12/2021
 * @author admin
 * @version 1.0
 */
public class API {
	/**
	 * thuộc tính đưa thời gian về đúng định dạng
	 */
	public static DateFormat DATE_FORMATER = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

	/**
	 * thuộc tính để đưa thông tin ra màn hình console
	 */
	private static Logger LOGGER = Utils.getLogger(Utils.class.getName());


	/**
	 * Phương thức gửi yêu cầu API đến server dựa trên "phương thức GET"
	 * @param url: đường dẫn tới server
	 * @param token: token để định danh người sử dụng
	 * @return : phản hồi trả về từ server, định dạng String
	 * @throws Exception
	 */
	public static String get(String url, String token) throws Exception {
		//phan 1: setup
		HttpURLConnection conn = setupConnection(url, token);

		//phan 2: doc du lieu tra ve tu server
		return readResponse(conn);
	}

	int var;

	/**
	 * Phương thức gửi yêu cầu API đến server dựa trên "phương thức POST"
	 * @param url: đường dẫn tới server
	 * @param data: dữ liệu gửi kèm lên server để xử lí
	 * @return : phản hồi trả về từ server, định dạng String
	 * @throws IOException
	 */
	public static String post(String url, String data
//			, String token
	) throws IOException {
		allowMethods("PATCH");

		//phan 1: setup
		URL line_api_url = new URL(url);
		String payload = data;
		LOGGER.info("Request Info:\nRequest URL: " + url + "\n" + "Payload Data: " + payload + "\n");
		HttpURLConnection conn = (HttpURLConnection) line_api_url.openConnection();
		conn.setDoInput(true);
		conn.setDoOutput(true);
		conn.setRequestMethod("PATCH");
		conn.setRequestProperty("Content-Type", "application/json");

		//gui du lieu
		Writer writer = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
		writer.write(payload);
		writer.close();

		//phan 3: doc du lieu gui ve tu server
		BufferedReader in;
		String inputLine;
		if (conn.getResponseCode() / 100 == 2) {
			in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		} else {
			in = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
		}
		StringBuilder response = new StringBuilder();
		while ((inputLine = in.readLine()) != null)
			response.append(inputLine);
		in.close();
		LOGGER.info("Respone Info: " + response.toString());
		return response.toString();
	}

	/**
	 * Phương thức cho phép gọi các loại API khác nhau như PATCH, PUT,...(chỉ hoạt động với java 11)
	 * @deprecated chỉ hoạt động với java <= 11
	 * @param methods: giao thức cần cấp phép (put, patch,...)
	 */
	private static void allowMethods(String... methods) {
		try {
			Field methodsField = HttpURLConnection.class.getDeclaredField("methods");
			methodsField.setAccessible(true);

			Field modifiersField = Field.class.getDeclaredField("modifiers");
			modifiersField.setAccessible(true);
			modifiersField.setInt(methodsField, methodsField.getModifiers() & ~Modifier.FINAL);

			String[] oldMethods = (String[]) methodsField.get(null);
			Set<String> methodsSet = new LinkedHashSet<>(Arrays.asList(oldMethods));
			methodsSet.addAll(Arrays.asList(methods));
			String[] newMethods = methodsSet.toArray(new String[0]);

			methodsField.set(null/* static field */, newMethods);
		} catch (NoSuchFieldException | IllegalAccessException e) {
			throw new IllegalStateException(e);
		}
	}

	private static String readResponse(HttpURLConnection conn) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		String inputLine;
		StringBuilder respone = new StringBuilder(); // ising StringBuilder for the sake of memory and performance
		while ((inputLine = in.readLine()) != null)
			System.out.println(inputLine);
		respone.append(inputLine + "\n");
		in.close();
		LOGGER.info("Respone Info: " + respone.substring(0, respone.length() - 1).toString());
		return respone.substring(0, respone.length() - 1).toString();
	}

	private static HttpURLConnection setupConnection(String url, String token) throws IOException {
		LOGGER.info("Request URL: " + url + "\n");
		URL line_api_url = new URL(url);
		HttpURLConnection conn = (HttpURLConnection) line_api_url.openConnection();
		conn.setDoInput(true);
		conn.setDoOutput(true);
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-Type", "application/json");
		conn.setRequestProperty("Authorization", "Bearer " + token);
		return conn;
	}



}
