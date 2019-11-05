package edu.calvin.cs262.HW3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NewPlayerActivity extends AppCompatActivity {

    public static final String EXTRA_REPLY =
            "com.example.android.roomplayerssample.REPLY";

    private EditText editPlayerName;
    private EditText editPlayerEmail;
    private EditText editPlayerId;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_player);
        editPlayerName = findViewById(R.id.edit_player_name);
        editPlayerEmail = findViewById(R.id.edit_email);
        editPlayerId = findViewById(R.id.edit_id);

        final Button button = findViewById(R.id.button_save);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent replyIntent = new Intent();
                if (TextUtils.isEmpty(editPlayerName.getText())) {
                    setResult(RESULT_CANCELED, replyIntent);
                } else {
                    String playerName = editPlayerName.getText().toString();
                    String playerEmail = editPlayerEmail.getText().toString();
                    String playerId = editPlayerId.getText().toString();

                    replyIntent.putExtra("PLAYER_NAME", playerName);
                    replyIntent.putExtra("EMAIL", playerEmail);
                    replyIntent.putExtra("ID", playerId);
                    setResult(RESULT_OK, replyIntent);
                }
                finish();
            }
        });
    }

}
