package util;

import android.content.DialogInterface;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.guidi.collegesearch.main.R;

import static android.widget.Toast.makeText;

public final class OnClickAssigner {
    public static void loginHandler(View v, FirebaseAuth mAuth){
        ImageButton loginB = v.findViewById(R.id.login_image_button);
        final View v1 = v;
        final FirebaseAuth userAuthen = mAuth;
        loginB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("HEY MATT WHAT UP", "you got mail");
                String emailText = ((EditText)v1.findViewById(R.id.email_edit_text)).getText().toString();
                String pText = ((EditText)v1.findViewById(R.id.password_edit_text)).getText().toString();
                Log.d("HEY MATT WHAT UP", emailText);
                Log.d("HEY MATT WHAT UP", pText);
                userAuthen.signInWithEmailAndPassword(emailText, pText)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    Log.d("Login Test" ,"signInWithEmail:success");

                                }else {
                                    Log.w("Login Test", "signInWithEmail:failure", task.getException());
                                    Toast.makeText(v1.getContext(), "Authentication failed sucka", Toast.LENGTH_LONG).show();

                                }
                            }
                        });

            }
        });
    }
}

