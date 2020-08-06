package com.sktelecom.t3k.dementia;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import org.json.JSONException;
import org.json.JSONObject;



public class MainActivity extends AppCompatActivity {

    private EditText mHostAddress, mHostPort, mPayloadData;
    private Button mStartGrpcBtn, mStopGrpcBtn;
    private static TextView mResultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mHostAddress = findViewById(R.id.host_edit_text);
        mHostPort = findViewById(R.id.port_edit_text);
        mHostAddress.setText("52.231.90.6");
        mHostPort.setText("60021");

        mStartGrpcBtn = findViewById(R.id.start_grpc_btn);
        mStopGrpcBtn = findViewById(R.id.stop_grpc_btn);
        mPayloadData = findViewById(R.id.payload_edit_text);

        mResultText = findViewById(R.id.result_text);
        mResultText.setMovementMethod(new ScrollingMovementMethod());


    }

    private static void setResultText(String text) {
        mResultText.setText(text);
    }

    public void startGrpc(View view) {
        setResultText("StartGrpC Button Clicked.");
    }

    public void stopGrpc(View view) {
        setResultText("StopGrpC Button Clicked.");
    }

    public void startInspection(View view) {
        try {
            startInspection(new JSONObject().put("patent_info","snow"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void startInspection(JSONObject payload) throws JSONException {
        setResultText("StartInspection Button Clicked. payload : " + payload.get("patent_info"));
    }

    public void stopInspection(View view) {
        try {
            stopInspection(new JSONObject().put("patent_info","snow"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void stopInspection(JSONObject payload) throws JSONException {
        setResultText("StopInspection Button Clicked. payload : " + payload.get("patent_info"));
    }


    public void startMic(View view) {
        try {
            startMic(new JSONObject().put("question","언제 국민학교 나오셨어요?"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void startMic(JSONObject payload) throws JSONException {
        setResultText("startMic Button Clicked. payload : " + payload.get("question"));
    }

    public void stopMic(View view) {
        try {
            stopMic(new JSONObject().put("question","언제 국민학교 나오셨어요?"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void stopMic(JSONObject payload) throws JSONException {
        setResultText("stopMic Button Clicked. payload : " + payload.get("question"));
    }


    public void doMlInference(View view) {
        setResultText("Do_ML_Inference Button Clicked.");
    }

    public void doAzureUpload(View view) {
        setResultText("Do_Azure_Upload Button Clicked.");
    }

    /*
    command 값이 inspection, action값이 start 일 때는 검사 시작
    command 값이 inspection, action값이 stop 일 때는 검사 종료
    command 값이 mic, action값이 start 일 때는 마이크 녹음 시작
    command 값이 mic, action값이 stop 일 때는 마이크 녹음 종료
     */
    public void apiViaUnity(String command, String action, JSONObject payload) throws JSONException {
        //command: inspection, mic
        switch( command )
        {
            case "inspection":
                // action : start, stop
                switch( action )
                {
                    case "start":
                        startInspection(payload);
                        break;
                    case "stop":
                        stopInspection(payload);
                        break;
                }
                break;
            case "mic":
                // action : start, stop
                switch( action )
                {
                    case "start":
                        startMic(payload);
                        break;
                    case "stop":
                        stopMic(payload);
                        break;
                }
                break;
        }
    }

}