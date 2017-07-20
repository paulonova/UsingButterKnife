package com.example.android.coderunner;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Layout;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    public static final String LOG_TEXT_KEY = "log_text_key";
    private TextView mLog;

    @BindView(R.id.run_button)    Button runButton;

    /**
     * butterKnife OnClickEvent annotation */
    @OnClick(R.id.clear_button)
    public void onClearButtonClick(){
        mLog.setText("");
        mLog.scrollTo(0, 0);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        runButton.setOnClickListener(v -> runCode());  //Lambda


//        findViewById(R.id.clear_button)
//                .setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        clearLog();
//                    }
//                });

        mLog = (TextView) findViewById(R.id.log);
        mLog.setMovementMethod(new ScrollingMovementMethod());

        //ConfigChanges
        if(savedInstanceState != null && savedInstanceState.containsKey(LOG_TEXT_KEY)){
            mLog.setText(savedInstanceState.getString(LOG_TEXT_KEY));
        }else{
            mLog.setText("");
        }

        logMessage("onCreate");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        String logText = mLog.getText().toString();
        outState.putString(LOG_TEXT_KEY, logText);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onPause() {
        super.onPause();
        logMessage("onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        logMessage("onResume");
    }

    @Override
    protected void onStop() {
        super.onStop();
        logMessage("onStop");
    }

    @Override
    protected void onStart() {
        super.onStart();
        logMessage("onStart");
    }

    private void logMessage(String message) {
//      Output message to logcat console
        Log.i(TAG, message);

//      Output message to TextView
        mLog.append(message + "\n");

//      Adjust scroll position to make last line visible
        Layout layout = mLog.getLayout();
        if (layout != null) {
            int scrollAmount = layout.getLineTop(mLog.getLineCount()) - mLog.getHeight();
            mLog.scrollTo(0, scrollAmount > 0 ? scrollAmount : 0);
        }
    }

    public void runCode() {
        logMessage("runCode");
    }

//    public void clearLog() {
//        mLog.setText("");
//        mLog.scrollTo(0, 0);
//    }
}
