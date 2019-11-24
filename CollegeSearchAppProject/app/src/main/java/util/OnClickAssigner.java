package util;

import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
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
                String emailText = ((EditText)v1.findViewById(R.id.email_edit_text)).getText().toString();
                String pText = ((EditText)v1.findViewById(R.id.password_edit_text)).getText().toString();
                try{
                    userAuthen.signInWithEmailAndPassword(emailText, pText)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        Log.d("Login Test", "signInWithEmail:success");
                                        ToastyMatt.makeMToast(v1, "Login successful", true).show();
                                    } else {
                                        Log.w("Login Test", "signInWithEmail:failure", task.getException());
                                        ToastyMatt.makeMToast(v1, "Sorry Mate wrong login info", true).show();

                                    }
                                }
                            });
                }catch(IllegalArgumentException e){
                    ToastyMatt.makeMToast(v1,
                            "Sorry Mate wrong login info\nAre you missing something?", true).show();
                }

            }
        });
    }
}

