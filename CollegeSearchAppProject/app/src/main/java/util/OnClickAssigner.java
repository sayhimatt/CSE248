package util;

import android.content.DialogInterface;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.guidi.collegesearch.main.R;

import static android.widget.Toast.makeText;

public final class OnClickAssigner {
    public static void loginHandler(View v){
        ImageButton loginB = v.findViewById(R.id.login_image_button);
        loginB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "This", Toast.LENGTH_LONG).show();

            }
        });

        v.findViewById(R.id.email_edit_text);
        v.findViewById(R.id.password_edit_text);
    }
}

