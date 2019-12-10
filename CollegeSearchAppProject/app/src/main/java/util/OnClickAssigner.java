package util;

import android.app.Activity;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.guidi.collegesearch.main.R;

import custom_views.mToast;
import model.Account;
import model.Username;

import static android.widget.Toast.makeText;

public final class OnClickAssigner {
    private static View rootV;
    private static FirebaseAuth mAuth;
    private static Activity mainActivity;
    public static void setOnClickAssigner(View overView, FirebaseAuth firebaseAuthentication, Activity mActivity){
        rootV = overView;
        mAuth = firebaseAuthentication;
        mainActivity = mActivity;
    }
    public static void loginHandler(){
        ImageButton loginB = mainActivity.findViewById(R.id.login_image_button);
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
                                        mToast.mT(rootV, "Login successful", true);
                                        mainActivity.setContentView(R.layout.activity_general);
                                    } else {
                                        Log.w("Login Test", "signInWithEmail:failure", task.getException());
                                        mToast.mT(rootV, "Sorry mate wrong login info", true);

                                    }
                                }
                            });
                }catch(IllegalArgumentException e){
                    mToast.mT(rootV,
                            "Sorry mate wrong login info\nAre you missing something?", true);
                }

            }
        });
    }
    public static void registrationHandler() {
        Button rB = mainActivity.findViewById(R.id.register_account_button);
        rB.setOnClickListener(new View.OnClickListener() {
            boolean listener = false;
            @Override
            public void onClick(View v) {
                String email = ((EditText)(rootV.findViewById(R.id.email_address_editText))).getText().toString();
                String password = ((EditText)(rootV.findViewById(R.id.password_one_editText))).getText().toString();
                String password2 = ((EditText)(rootV.findViewById(R.id.password_two_editText))).getText().toString();
                String firstName = ((EditText)(rootV.findViewById(R.id.first_name_editText))).getText().toString();
                String lastName = ((EditText)(rootV.findViewById(R.id.last_name_editText))).getText().toString();
                int satScore;
                try {
                    satScore = Integer.parseInt(((EditText) (rootV.findViewById(R.id.sat_score_editText))).getText().toString().trim());
                }catch(NumberFormatException e){
                    satScore = 0;
                }
                int actScore;
                try {
                    actScore = Integer.parseInt(((EditText) (rootV.findViewById(R.id.act_score_editText))).getText().toString().trim());
                }catch(NumberFormatException e){
                    actScore = 0;
                }
                if(email.equals("")){
                    mToast.mT(v, "Fill in your Email\n ... moron", false);
                }else if(password.equals("")){
                    mToast.mT(v, "Write your desired password\n ... moron", false);
                }else if(password2.equals("")){
                    mToast.mT(v, "Confirm the password\n ... moron", false);
                }else if(firstName.equals("")){
                    mToast.mT(v,"Write your first name", false);
                }else if(lastName.equals("")){
                    mToast.mT(v,"Write your last name", false);
                }
                if(password.equals(password2)) {
                    final Account myAccount = new Account(new Username(email), firstName, lastName);
                    if(satScore != 0) {
                        myAccount.setSatScore(satScore);
                    }else{
                        myAccount.setSatScore(1600);
                    }
                    if(actScore != 0){
                        myAccount.setActScore(actScore);
                    }else{
                        myAccount.setActScore(36);
                    }

                    mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            mToast.mT(rootV, "It worked", true);
                            uploadUser(myAccount);
                            mainActivity.setContentView(R.layout.activity_general);


                        } else {
                            // If sign in fails, display a message to the user
                            mToast.mT(rootV, task.getException().toString(), true);
                            listener = false;
                        }

                        // ...
                        }
                    });

                }
                Log.e("something happened", "HERE HERE AFTER");
            }
        });
    }
    private static void uploadUser(Account myAccount){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("users");
        String id = mAuth.getCurrentUser().getUid();
        myRef.child(id).setValue(myAccount);
    }

}

