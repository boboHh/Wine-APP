package Utils;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.hardware.Camera.Size;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION_CODES;
import android.support.annotation.DrawableRes;
import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Comparator;
import java.util.List;

public class ChageTypeUtils {

	private ChageTypeUtils() {
	}

	public static String getResourcesUri(Context context, @DrawableRes int id) {
		Resources resources = context.getResources();
		String uriPath = ContentResolver.SCHEME_ANDROID_RESOURCE + "://" +
				resources.getResourcePackageName(id) + "/" +
				resources.getResourceTypeName(id) + "/" +
				resources.getResourceEntryName(id);
		return uriPath;
	}


	public static Uri bitmap2uri(Context c, Bitmap b) {
		File path = new File(c.getCacheDir() + File.separator + System.currentTimeMillis() + ".jpg");
		try {
			OutputStream os = new FileOutputStream(path);
			b.compress(Bitmap.CompressFormat.JPEG, 10, os);
			os.close();
			return Uri.fromFile(path);
		} catch (Exception ignored) {
		}
		return null;
	}

	public static Bitmap byteToBitmap(byte[] b){
		Bitmap bitmap = BitmapFactory.decodeByteArray(b, 0, b.length);
		return bitmap;
	}


	// 将bitmap转成string类型通过Base64
	public static String BitmapToString(Bitmap bitmap) {

		ByteArrayOutputStream bao = new ByteArrayOutputStream();
		// 将bitmap压缩成30%
		bitmap.compress(Bitmap.CompressFormat.JPEG, 30, bao);
		// 将bitmap转化为一个byte数组
		byte[] bs = bao.toByteArray();
		// 将byte数组用BASE64加密
		String photoStr = Base64.encodeToString(bs, Base64.DEFAULT);

		// 返回String
		return photoStr;
	}


	public static boolean hasFroyo() {
		return Build.VERSION.SDK_INT >= VERSION_CODES.FROYO;

	}

	public static boolean hasGingerbread() {
		return Build.VERSION.SDK_INT >= VERSION_CODES.GINGERBREAD;
	}

	public static boolean hasHoneycomb() {
		return Build.VERSION.SDK_INT >= VERSION_CODES.HONEYCOMB;
	}

	public static boolean hasHoneycombMR1() {
		return Build.VERSION.SDK_INT >= VERSION_CODES.HONEYCOMB_MR1;
	}

	public static boolean hasJellyBean() {
		return Build.VERSION.SDK_INT >= VERSION_CODES.JELLY_BEAN;
	}

	public static boolean hasKitKat() {
		return Build.VERSION.SDK_INT >= 19;
	}

	public static List<Size> getResolutionList(Camera camera)
	{
		Parameters parameters = camera.getParameters();
		return parameters.getSupportedPreviewSizes();
	}

	public static class ResolutionComparator implements Comparator<Size> {

		@Override
		public int compare(Size lhs, Size rhs) {
			if(lhs.height!=rhs.height)
			return lhs.height-rhs.height;
			else
			return lhs.width-rhs.width;
		}
		 
	}

	@SuppressWarnings("rawtypes")
	public static boolean isEmpty(Object obj)
	{
		if (obj == null)
		{
			return true;
		}
		if ((obj instanceof List))
		{
			return ((List) obj).size() == 0;
		}
		if ((obj instanceof String))
		{
			return ((String) obj).trim().equals("");
		}
		return false;
	}


}
