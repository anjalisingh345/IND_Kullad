package com.example.indiankullad;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import de.hdodenhof.circleimageview.CircleImageView;

public class Edit_Profile extends Fragment {

    View v;
    CircleImageView logout,pro_img;
    EditText name,contact,address;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

       v = inflater.inflate(R.layout.edit_profile,container,false);
        logout = v.findViewById(R.id.logout);
        pro_img = v.findViewById(R.id.profile);
        name =v.findViewById(R.id.edt_name);
        contact = v.findViewById(R.id.edt_mobile);
        address = v.findViewById(R.id.edt_address);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Logout");
                builder.setMessage("Are you sure");
                builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        final SharedPreferences sharedPreferences = getContext()
                                .getSharedPreferences("check", Context.MODE_PRIVATE);
                        sharedPreferences.edit().remove("con").apply();
                        sharedPreferences.edit().remove("mobile").apply();
                        Intent intent = new Intent(getContext(),Login.class);
                        startActivity(intent);
                        getActivity().finish();
                    }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();

                        return;

                    }
                });
                AlertDialog dialog =builder.create();
                dialog.show();
            }
        });

        return v;
    }
}