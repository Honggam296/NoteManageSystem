package n17dtha4.notemanagesystem.model.note;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import n17dtha4.notemanagesystem.DBHelper;
import n17dtha4.notemanagesystem.model.category.categoryOJ;
import n17dtha4.notemanagesystem.model.priority.priorityOJ;
import n17dtha4.notemanagesystem.model.status.StatusViewModel;

import static n17dtha4.notemanagesystem.Login.AccInfo;

public class note_db extends DBHelper {
    public note_db(Context context) {
        super(context);
    }

    public boolean  insetCategory(noteOJ noteOJ){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Content",noteOJ.getName());
        contentValues.put("Category",noteOJ.getCategory());
        contentValues.put("Priority",noteOJ.getPriority());
        contentValues.put("Status",noteOJ.getStatus());
        contentValues.put("PlanDate",noteOJ.getPlanDate());
        contentValues.put("DateCreate",noteOJ.getCreateDate());
        contentValues.put("IDAcc",AccInfo.getId());
        long inserted = db.insert(TABLE_NOTE,null,contentValues);
        if(inserted == -1 )
            return false;
        else
            return true;
    }

    public List<noteOJ> getNote (){
        List<noteOJ> list = new ArrayList<noteOJ>();
        String queryString = "SELECT * FROM  Note Where IDAcc = "+ AccInfo.getId()+ " ORDER BY id DESC";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString,null);
        if(cursor.moveToFirst()){
            do {
                noteOJ  noteOJ = new noteOJ(cursor.getString(4),cursor.getString(2),cursor.getString(1),cursor.getString(3),cursor.getString(6),cursor.getString(5),cursor.getInt(0));
                list.add(noteOJ);
            }while (cursor.moveToNext());
        }else{}
        cursor.close();
        db.close();
        return list;
    }


    public List<categoryOJ> getSpinnerCategory (){
        List<categoryOJ> list = new ArrayList<categoryOJ>();
        String queryString = "SELECT * FROM  Category Where IDAcc = "+ AccInfo.getId()+" ORDER BY id DESC";
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


    public List<priorityOJ> getSpinnerPriority (){
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


    public List<StatusViewModel> getSpinnerStatus (){
        List<StatusViewModel> lstStatus = new ArrayList<StatusViewModel>();
        String queryString = "SELECT * FROM " + TABLE_STATUS +" Where IDAcc = "+ AccInfo.getId();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString,null);
        if(cursor.moveToFirst()){
            do {
                int Id = cursor.getInt(0);
                String StatusName = cursor.getString(1);
                String DateSta = cursor.getString(2);
                StatusViewModel n = new StatusViewModel(Id,StatusName,DateSta);
                lstStatus.add(n);
            }while (cursor.moveToNext());
        }else{}
        cursor.close();
        db.close();
        return lstStatus;
    }

    public boolean  deleteNote(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        long inserted = db.delete("Note","id = "+id,null);
        if(inserted == -1 )
            return false;
        else
            return true;
    }



    public boolean  updateNote(noteOJ noteOJ){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Status",noteOJ.getStatus());
        contentValues.put("Category",noteOJ.getCategory());
        contentValues.put("IDAcc",AccInfo.getId());
        contentValues.put("ID",noteOJ.getId());
        contentValues.put("Priority",noteOJ.getPriority());
        contentValues.put("PlanDate",noteOJ.getPlanDate());
        contentValues.put("DateCreate",noteOJ.getCreateDate());
        contentValues.put("Content",noteOJ.getName());
        long inserted = db.update("Note",contentValues,"ID = "+ noteOJ.getId(),null);
        if(inserted == -1 )
            return false;
        else
            return true;
    }


}
