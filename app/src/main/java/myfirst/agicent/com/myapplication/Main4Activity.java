package myfirst.agicent.com.myapplication;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

public class Main4Activity extends AppCompatActivity implements View.OnClickListener {
    private static final int REQUEST_ENABLE_BT = 0;
    private static final int REQUEST_DISCOVERABLE_BT = 0;
    private ImageView imageViewpicker;
    private Uri fileuri;
    private String imagepath;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        final TextView out=(TextView)findViewById(R.id.out);
        final Button button1 = (Button) findViewById(R.id.button1);
        final Button button2 = (Button) findViewById(R.id.button2);
        final Button button3 = (Button) findViewById(R.id.button3);
        imageViewpicker = findViewById(R.id.imagepicker);
        imageViewpicker.setOnClickListener(this);
        final BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter == null) {
            out.append("device not supported");
        }
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (!mBluetoothAdapter.isEnabled()) {
                    Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                    startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
                }
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                if (!mBluetoothAdapter.isDiscovering()) {
                    //out.append("MAKING YOUR DEVICE DISCOVERABLE");
                    Toast.makeText(getApplicationContext(), "MAKING YOUR DEVICE DISCOVERABLE",
                            Toast.LENGTH_LONG);

                    Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
                    startActivityForResult(enableBtIntent, REQUEST_DISCOVERABLE_BT);

                }
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                mBluetoothAdapter.disable();
                //out.append("TURN_OFF BLUETOOTH");
                Toast.makeText(getApplicationContext(), "TURNING_OFF BLUETOOTH", Toast.LENGTH_LONG);

            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.imagepicker:
                CropImage.activity()
                        .setGuidelines(CropImageView.Guidelines.ON)
                        .setAllowRotation(false)
                        .setAllowFlipping(false)
                        .start(this);
                break;

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {

            case CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE:
                CropImage.ActivityResult result = CropImage.getActivityResult(data);
                if (resultCode == RESULT_OK) {
                    Uri resultUri = result.getUri();
                    if (resultUri != null && resultUri.getPath() != null) {
                        imagepath = resultUri.getPath();
                        imageViewpicker.setImageBitmap(BitmapFactory.decodeFile(resultUri.getPath()));
                        /* textViewupload.setBackgroundResource(R.drawable.upload_btn);*/
                        /* textViewupload.setEnabled(true);*/
                    }
                } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                    Exception error = result.getError();
                }
                break;
        }

    }
}
