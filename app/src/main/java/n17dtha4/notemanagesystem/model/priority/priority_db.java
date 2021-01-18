package n17dtha4.notemanagesystem.model.priority;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import n17dtha4.notemanagesystem.DBHelper;

import static n17dtha4.notemanagesystem.Login.AccInfo;

public class priority_db extends DBHelper {

    public priority_db(Context context) {
        super(context);
    }

    public boolean addPriority(priorityOJ priorityOJ){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_PRIORITY_NAME_PRIORITY,priorityOJ.getName());
        contentValues.put(COLUMN_PRIORITY_DATE_PRI,priorityOJ.getCreateDate());
        contentValues.put("IDAcc", AccInfo.getId());
        long inserted = db.insert(TABLE_PRIORITY,null,contentValues);
        if(inserted == -1 )
            return false;
        else
            return true;
    }

    public boolean  deletePriority(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        long inserted = db.delete("Priority","id = "+id,null);
        if(inserted == -1 )
            return false;
        else
            return true;
    }

    public boolean  updatePriority(priorityOJ priorityOJ){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_PRIORITY_NAME_PRIORITY,priorityOJ.getName());
        contentValues.put(COLUMN_PRIORITY_DATE_PRI,priorityOJ.getCreateDate());
        contentValues.put("ID",priorityOJ.getId());
        contentValues.put("IDAcc",AccInfo.getId());
        long inserted = db.update("Priority",contentValues,"ID = "+ priorityOJ.getId(),null);
        if(inserted == -1 )
            return false;
        else
            return true;
    }

    public List<priorityOJ> priorityOJList (){
        List<priorityOJ> listPri = new ArrayList<priorityOJ>();
        String queryString = "SELECT * FROM " + TABLE_PRIORITY + " WHERE IDAcc = " + AccInfo.getId();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString,null);
        if(cursor.moveToFirst()){
            do {
                int priorityId = cursor.getInt(0);
                String priorityName = cursor.getString(1);
                String priorityDatetime = cursor.getString(2);
                priorityOJ newPriority = new priorityOJ(priorityId,priorityName,priorityDatetime);
                listPri.add(newPriority);
            }while (cursor.moveToNext());
        }else{}
        cursor.close();
        db.close();
        return listPri;
    }
}
