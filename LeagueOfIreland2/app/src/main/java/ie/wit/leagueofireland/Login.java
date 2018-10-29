package ie.wit.leagueofireland;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {

    private EditText Password,Username;
    private Button btnLogin;
    private FirebaseAuth firebaseauth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Password = (EditText)findViewById(R.id.loginPassword);
        Username = (EditText)findViewById(R.id.loginUsername);
        btnLogin = (Button)findViewById(R.id.loginButton);

        firebaseauth = FirebaseAuth.getInstance();

        FirebaseUser user = firebaseauth.getCurrentUser();

        btnLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                validate(Username.getText().toString(), Password.getText().toString());
            }
        });

        if(user != null){
            finish();
            startActivity(new Intent(Login.this, LeagueofIreland.class));
        }
    }


    private void validate(String Username, String Password){
        firebaseauth.signInWithEmailAndPassword(Username, Password). addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(Login.this, "Login Successful", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Login.this, LeagueofIreland.class));
                }else{
                    Toast.makeText(Login.this, "Login Failed", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_leagueof_ireland, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {

            case R.id.menuHome:
                startActivity(new Intent(this, LeagueofIreland.class));
                Toast.makeText(this, "Stats Selected", Toast.LENGTH_SHORT).show();
                break;

            case R.id.menuTables:
                startActivity(new Intent(this, Tables.class));
                Toast.makeText(this, "Tables Selected", Toast.LENGTH_SHORT).show();
                break;


            case R.id.menuStats:
                startActivity(new Intent(this, Stats.class));
                Toast.makeText(this, "Stats Selected", Toast.LENGTH_SHORT).show();
                break;

            case R.id.menuDonate:
                startActivity(new Intent(this, Donate.class));
                Toast.makeText(this, "Donate Selected", Toast.LENGTH_SHORT).show();
                break;

            case R.id.menuReport:
                startActivity(new Intent(this, Report.class));
                Toast.makeText(this, "Report Selected", Toast.LENGTH_SHORT).show();
                break;

            case R.id.menuRegister:
                startActivity(new Intent(this, Registration.class));
                Toast.makeText(this, "Register Selected", Toast.LENGTH_SHORT).show();
                break;



        }

        return super.onOptionsItemSelected(item);
    }
}
