package myfirst.agicent.com.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.play.core.splitinstall.SplitInstallManager;
import com.google.android.play.core.splitinstall.SplitInstallManagerFactory;
import com.google.android.play.core.splitinstall.SplitInstallRequest;
import com.google.android.play.core.tasks.OnFailureListener;
import com.google.android.play.core.tasks.OnSuccessListener;

public class Main2Activity extends AppCompatActivity {
    private SplitInstallManager splitInstallManager = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        splitInstallManager =
                SplitInstallManagerFactory.create(getApplicationContext());
    }
    public void loadFeatureOne() {
        // Builds a request to install the feature1 module
        SplitInstallRequest request =
                SplitInstallRequest
                        .newBuilder()
                        // You can download multiple on demand modules per
                        // request by invoking the following method for each
                        // module you want to install.
                        .addModule("feature1")
                        .build();

        // Begin the installation of the feature1 module and handle success/failure
        splitInstallManager
                .startInstall(request)
                .addOnSuccessListener(new OnSuccessListener<Integer>() {
                    @Override
                    public void onSuccess(Integer integer) {

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(Exception e) {

                    }
                });
    }
}
