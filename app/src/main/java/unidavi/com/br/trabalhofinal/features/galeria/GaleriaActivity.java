package unidavi.com.br.trabalhofinal.features.galeria;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.View;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.time.LocalDateTime;

import unidavi.com.br.trabalhofinal.BuildConfig;
import unidavi.com.br.trabalhofinal.MainActivity;
import unidavi.com.br.trabalhofinal.R;

public class GaleriaActivity extends AppCompatActivity {

    FirebaseStorage storage = FirebaseStorage.getInstance("gs://trabalhofinalandroid-92045.appspot.com");
    StorageReference storageRef = storage.getReference();

    FirebaseAuth auth = FirebaseAuth.getInstance();
    FirebaseUser user = auth.getCurrentUser();

    String path = "images/" + user.getEmail() + "/";

    /**
     * Imagem caputarada
     */
    private File file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galeria);

        FloatingActionButton buttonCamera = findViewById(R.id.open_camera);

        buttonCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fileName = "photo-"+System.currentTimeMillis()+".jpg";

                file = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES),
                        fileName);
                Uri outputDir = FileProvider.getUriForFile(GaleriaActivity.this,
                        BuildConfig.APPLICATION_ID, file);

                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, outputDir);

                startActivityForResult(
                        intent,
                        1_000);
        }});
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1_000) {
            if (data != null && data.hasExtra("data")) {
                Bitmap bitmap = data.getParcelableExtra("data");

                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                byte[] baosData = baos.toByteArray();

                StorageReference mountainsRef = storageRef.child(path + "photo-"+System.currentTimeMillis()+".jpg");

                UploadTask uploadTask = mountainsRef.putBytes(baosData);
                uploadTask.addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        String a = "";
                        // Handle unsuccessful uploads
                    }
                }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        String a = "";
                        // taskSnapshot.getMetadata() contains file metadata such as size, content-type, etc.
                        // ...
                    }
                });

            } else {
                Uri fileUri = Uri.fromFile(file);
                StorageReference riversRef = storageRef.child("images/" + user.getEmail() + "/" + fileUri.getLastPathSegment());
                UploadTask uploadTask = riversRef.putFile(fileUri);

                // Register observers to listen for when the download is done or if it fails
                uploadTask.addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        String a = "";
                        // Handle unsuccessful uploads
                    }
                }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        String a = "";
                        // taskSnapshot.getMetadata() contains file metadata such as size, content-type, etc.
                        // ...
                    }
                });
            }
        }
    }
}
