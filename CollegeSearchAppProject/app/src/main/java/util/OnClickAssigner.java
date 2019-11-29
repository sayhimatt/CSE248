package util;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.guidi.collegesearch.main.R;

import java.util.ArrayList;
import java.util.Collection;

import static android.widget.Toast.makeText;

public final class OnClickAssigner {
    private static View rootV;
    private static FirebaseAuth mAuth;
    public static void setOnClickAssigner(View overView, FirebaseAuth firebaseAuthentication){
        rootV = overView;
        mAuth = firebaseAuthentication;
    }
    public static void loginHandler(){
        ImageButton loginB = rootV.findViewById(R.id.login_image_button);
        loginB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailText = ((EditText)rootV.findViewById(R.id.email_edit_text)).getText().toString();
                String pText = ((EditText)rootV.findViewById(R.id.password_edit_text)).getText().toString();
                try{
                    mAuth.signInWithEmailAndPassword(emailText, pText)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        Log.d("Login Test", "signInWithEmail:success");
                                        ToastyMatt.makeMToast(rootV, "Login successful", true).show();
                                    } else {
                                        Log.w("Login Test", "signInWithEmail:failure", task.getException());
                                        ToastyMatt.makeMToast(rootV, "Sorry mate wrong login info", true).show();

                                    }
                                }
                            });
                }catch(IllegalArgumentException e){
                    ToastyMatt.makeMToast(rootV,
                            "Sorry mate wrong login info\nAre you missing something?", true).show();
                }

            }
        });
    }
    public static void registrationHandler(View v, Button rButton) {
        rButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = ((EditText)(rootV.findViewById(R.id.email_address_editText))).getText().toString();
                String password = ((EditText)(rootV.findViewById(R.id.password_one_editText))).getText().toString();
                String password2 = ((EditText)(rootV.findViewById(R.id.password_two_editText))).getText().toString();
                String firstName = ((EditText)(rootV.findViewById(R.id.first_name_editText))).getText().toString();
                String lastName = ((EditText)(rootV.findViewById(R.id.last_name_editText))).getText().toString();
                
                int satScore = Integer.parseInt(((EditText)(rootV.findViewById(R.id.sat_score_editText))).getText().toString().trim());
                int actScore = Integer.parseInt(((EditText)(rootV.findViewById(R.id.act_score_editText))).getText().toString().trim());
                if(password.equals(password2)) {
                    mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("Test", "hello");
                            ToastyMatt.makeMToast(rootV, "It worked", true).show();
                            FirebaseUser user = mAuth.getCurrentUser();




                        } else {
                            // If sign in fails, display a message to the user.
                            Log.d("Failure", "hello");
                            ToastyMatt.makeMToast(rootV, "It failed", true).show();
                        }

                        // ...
                        }
                    });
                }

                /*FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("users");
                String id = cUser.getUid();
                Account myAccount = new Account(new Username("sayhimatt@gmail.com"), "Tester122", "Guidi" );
                myRef.child(id).setValue(myAccount);*/
            }
        });
    }

}

