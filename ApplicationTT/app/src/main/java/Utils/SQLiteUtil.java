package Utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by hasee on 2018/5/31.
 */

public class SQLiteUtil extends SQLiteOpenHelper {
    public static final String CREAT_DATABASE = "create table HistoryData(" +
            "id integer primary key autoincrement," +
            "name text," +
            "year text," +
            "raw_material text," +
            "country text," +
            "winery text," +
            "temperature text," +
            "time text," +
            "volume text," +
            "jiujd text," +
            "colour text," +
            "xiangqi text," +
            "kougan text," +
            "type text," +
            "picture1 blob," +
            "picture2 blob," +
            "picture3 blob)";

    private Context mContext;

    public SQLiteUtil(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context, name, factory, version);
        mContext = context;
    }
    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(CREAT_DATABASE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
    }
}
