package unidavi.com.br.trabalhofinal;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        Button buttonEntrar = findViewById(R.id.btnEntrar);
        buttonEntrar.setOnClickListener(view -> {

            EditText mail = findViewById(R.id.email);
            EditText password = findViewById(R.id.senha);

            String email = mail.getText().toString();
            String senha = password.getText().toString();

            if (email.equalsIgnoreCase("") ||
                    senha.equalsIgnoreCase("")) {
                Toast.makeText(this, "Email/Senha devem ser preenchidos!",
                        Toast.LENGTH_LONG).show();
            } else {

                mAuth.signInWithEmailAndPassword(email, senha)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    FirebaseUser user = mAuth.getCurrentUser();

                                    //Colocar para ir a lista

                                } else {
                                    Toast.makeText(MainActivity.this, "Usuario nao autenticado, por favor tente novamente!.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }
}
