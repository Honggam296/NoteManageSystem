package n17dtha4.notemanagesystem.model.status;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import n17dtha4.notemanagesystem.R;

public class status extends Fragment implements status_dialog.dialog_Add_Status_Listener,PopupMenu.OnMenuItemClickListener {

    private StatusViewModel mViewModel;
    private List<StatusViewModel> listStatus = new ArrayList<StatusViewModel>();
    private int index = 0;

    private ListView listView;
    public static status newInstance() {
        return new status();
    }
    ArrayList<StatusViewModel> arrayStatus = new ArrayList<StatusViewModel>();
    private status.StatusAdapter adapter = null;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.status_fragment,container,false);
        FloatingActionButton myFab = view.findViewById(R.id.btnAddStatus);
        listView = view.findViewById(R.id.lstStatus);
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {  // press and hold
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                index = position;
                showPopup(view);
                return false;
            }
        });

        myFab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                openDialog();
            }
        });
        refreshData();
        return view;
    }
    public  void openDialog(){
        status_dialog dialogAddStatus = new status_dialog();
        dialogAddStatus.show(getChildFragmentManager(),"example dialog");
    }
    public  void openDialogEdit(String name , int key){
        status_dialog dialogAddStatus = new status_dialog(name,key);
        dialogAddStatus.show(getChildFragmentManager(),"example dialog");
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch(item.getItemId()){
            case R.id.menu_edit_Status:
                openDialogEdit(listStatus.get(index).getName(),listStatus.get(index).getID());
                return  true;
            case R.id.menu_delete_Status:
                status_db status_db = new status_db(status.this.getContext());
                status_db.deleteStatus(listStatus.get(index).getID());
                getData();
                return  true;
            default:
                return false;
        }

    }



    @Override
    public void getData() {
        refreshData();
    }



    public void refreshData(){
        status_db dbHelper = new status_db(status.this.getContext());
        listStatus = dbHelper.GetListStatus();
        status.StatusAdapter Adapter = new status.StatusAdapter();
        listView.setAdapter(Adapter);


    }

    public class StatusAdapter extends ArrayAdapter<StatusViewModel>{
        public StatusAdapter(Context context ,int textViewResourceId){
            super(context,textViewResourceId);
        }
        public StatusAdapter(){
            super(status.this.getContext(), android.R.layout.simple_list_item_1,listStatus);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            View row = convertView;
            if( row == null){
                LayoutInflater inflater = getLayoutInflater();
                row = inflater.inflate(R.layout.row_status,null);
            }
            StatusViewModel c = listStatus.get(position);
            ((TextView)row.findViewById(R.id.name)).setText(c.getName());
            ((TextView)row.findViewById(R.id.date)).setText(c.getDatetime());
            return row;
        }
    }
    public  void  showPopup(View v){
        PopupMenu popupMenu = new PopupMenu(getContext(),v);
        popupMenu.setOnMenuItemClickListener(this);
        popupMenu.inflate(R.menu.menu_status);
        popupMenu.show();
    }



    @Override
    public void addStatus(String category, String date) {
        StatusViewModel s = new StatusViewModel();
        s.setName(category);
        s.setDatetime(date);
        listStatus.add(s);
        adapter = new StatusAdapter() ;
        listView.setAdapter(adapter);

    }




}

