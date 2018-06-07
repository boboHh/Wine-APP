package Utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import Object.*;

public final class JSONUtils {


	//myJSONmannager
	public static JSONBean parseListJSON(String jsonStr){
		Gson gson = new Gson();
		JSONBean js = gson.fromJson(jsonStr, JSONBean.class);
		return js;
	}

	public static <T> T parseJSON(String jsonStr, Class<T> t) {
		Gson gson = new Gson();
		T bean = gson.fromJson(jsonStr, t);
		return bean;
	}

	/**
	 * 
	 * @param response
	 * @param type
	 *           Type type = new TypeToken<ltArrayList<ltAnimeInfo>>() {
	//							 *            }.getType();
	 * @return
	 */
	public static <T> T parseJSONArray(String response, Type type) {
		Gson gson = new Gson();
		T data = gson.fromJson(response, type);
		return data;
	}
	public static <T> String mapToJson(Map<String, T> map) {
		Gson gson = new Gson();
		String jsonStr = gson.toJson(map);
		return jsonStr;
	}
	public static String listToJson(List<?> list) {

		Gson gson = new Gson();
		String json = gson.toJson(list);
		return json;
	}
}
