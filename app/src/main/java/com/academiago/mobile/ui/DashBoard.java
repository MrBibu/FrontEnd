package com.academiago.mobile.ui;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import com.academiago.mobile.R;
import com.academiago.mobile.ui.fragments.AdminDashboardFragment;
import com.academiago.mobile.ui.fragments.StudentDashboardFragment;
import com.academiago.mobile.ui.fragments.TeacherDashboardFragment;
import com.academiago.mobile.utils.TokenManager;

public class DashBoard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        TokenManager.init(this);
        String role = TokenManager.getRole();

        if (role != null) {
            loadDashboardFragment(role);
        }
    }

    private void loadDashboardFragment(String role) {
        Fragment fragment;
        switch (role) {
            case "ADMIN":
                fragment = new AdminDashboardFragment();
                break;
            case "TEACHER":
                fragment = new TeacherDashboardFragment();
                break;
            case "STUDENT":
                fragment = new StudentDashboardFragment();
                break;
            default:
                // Handle unknown role or show a default screen
                return;
        }

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.commit();
    }
}
