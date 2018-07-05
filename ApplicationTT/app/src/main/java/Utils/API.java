package Utils;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by 他的猫 on 2017/5/10.
 */

public class API {

    public static final String Base = "https://aip.baidubce.com/rest/2.0/image-classify/v1/realtime_search/similar/search";
    public static final String key = "24.52de4aecba19cb5e5918620c03688b6e.2592000.1529733324.282335-11267556";


    public static void TJ(
            String imgStr,

            Okhttp.Objectcallback handler) {

        Map<String, String> map = new HashMap<>();
        map.put("access_token", key);
        map.put("image", imgStr);

        Okhttp.post(Base, map, handler);

    }


}
