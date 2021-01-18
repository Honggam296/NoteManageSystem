package n17dtha4.notemanagesystem.model.category;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import n17dtha4.notemanagesystem.DBHelper;
import n17dtha4.notemanagesystem.model.priority.priorityOJ;

import java.util.ArrayList;
import java.util.List;

import static n17dtha4.notemanagesystem.Login.AccInfo;

public class category_DB extends DBHelper {
    public category_DB(Context context) {
        super(context);
    }

    public boolean  insetCategory(categoryOJ categoryOJ){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("NameCategory",categoryOJ.getName());
        contentValues.put("DateCate",categoryOJ.getCreatedate());
        contentValues.put("IDAcc",AccInfo.getId());
        long inserted = db.insert("Category",null,contentValues);
        if(inserted == -1 )
            return false;
        else
            return true;
    }

    public boolean  deleteCategory(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        long inserted = db.delete("Category","id = "+id,null);
        if(inserted == -1 )
            return false;
        else
            return true;
    }

    public boolean  updateCategory(categoryOJ categoryOJ){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("NameCategory",categoryOJ.getName());
        contentValues.put("DateCate",categoryOJ.getCreatedate());
        contentValues.put("ID",categoryOJ.getId());
        contentValues.put("IDAcc",AccInfo.getId());
        long inserted = db.update("Category",contentValues,"ID = "+ categoryOJ.getId(),null);
        if(inserted == -1 )
            return false;
        else
            return true;
    }

    public List<categoryOJ> getListCategory (){
        List<categoryOJ> list = new ArrayList<categoryOJ>();
        String queryString = "SELECT * FROM  Category Where IDAcc = " + AccInfo.getId()+" ORDER BY id DESC";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString,null);
        if(cursor.moveToFirst()){
            do {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String Datetime = cursor.getString(2);
                categoryOJ  categoryOJ = new categoryOJ(id,name,Datetime);
                list.add(categoryOJ);
            }while (cursor.moveToNext());
        }else{}
        cursor.close();
        db.close();
        return list;
    }
}
