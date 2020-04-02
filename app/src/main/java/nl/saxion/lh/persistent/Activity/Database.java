package nl.saxion.lh.persistent.Activity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {
    public  static  final String DATABASE_NAME = "codeChallenge.db";
    public  static  final String TABLE_NAME = "challenge";
    public  static  final String COL_1 = "question";
    public  static  final String COL_2  = "image";
    public  static  final String COL_3 = "answer";

    public Database(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE challenge (question TEXT, image TEXT, answer TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public long addChallenge(String question, String image, String answer){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("question", question);
        contentValues.put("image", image);
        contentValues.put("answer", answer);
        long res = db.insert("challenge",null, contentValues);
        return res;
    }

    public Cursor getAllRecords(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery(query,null);
    }

    public void deleteAll(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from "+ TABLE_NAME);
    }
}
