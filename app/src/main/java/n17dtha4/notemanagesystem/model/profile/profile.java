package n17dtha4.notemanagesystem.model.profile;


import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import n17dtha4.notemanagesystem.Login;
import n17dtha4.notemanagesystem.MainActivity;
import n17dtha4.notemanagesystem.R;
import n17dtha4.notemanagesystem.SignUp;
import n17dtha4.notemanagesystem.model.category.categoryOJ;
import n17dtha4.notemanagesystem.model.category.category_dialog;
import n17dtha4.notemanagesystem.model.category.category_DB;
import n17dtha4.notemanagesystem.model.priority.priority;
import n17dtha4.notemanagesystem.model.priority.priority_db;

import java.util.Date;

import static n17dtha4.notemanagesystem.Login.AccInfo;

public class profile extends Fragment {

    private ProfileViewModel mViewModel;
    Button btnHome, btnConfirm;
    EditText etFirstName, etLastName, etEmail;

    public static profile newInstance() {
        return new profile();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profile_fragment, container, false);
        btnHome = (Button)view.findViewById(R.id.btnReturnHome);
        btnConfirm = (Button)view.findViewById(R.id.btnConfirmEditProfile);
        etFirstName = (EditText)view.findViewById(R.id.etEditFirstName);
        etLastName = (EditText)view.findViewById(R.id.etEditLastName);
        etEmail = (EditText)view.findViewById(R.id.etEditEmail);

        etFirstName.setText(AccInfo.getFirstName());
        etLastName.setText(AccInfo.getLastName());
        etEmail.setText(AccInfo.getEmail());
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(profile.this.getContext(),"Home",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(profile.this.getContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                profile_db profile_db = new profile_db(profile.this.getContext());
                String firstName = etFirstName.getText().toString();
                String lastName = etLastName.getText().toString();
                String email = etEmail.getText().toString();

                profileOJ profileOJ = new profileOJ(AccInfo.getId(),firstName,lastName,email,AccInfo.getPassWord());
                boolean update =profile_db.updateProfile(profileOJ);
                if(update == true)
                    Toast.makeText(profile.this.getContext(),"update profile ",Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(profile.this.getContext(),"update error profile ",Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ProfileViewModel.class);
        // TODO: Use the ViewModel
    }

}