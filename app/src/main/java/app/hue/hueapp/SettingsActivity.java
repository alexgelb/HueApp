package app.hue.hueapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import app.hue.hueapp.app.hue.hueapp.async.tasks.HueAsyncTask;
import app.hue.hueapp.app.hue.hueapp.enums.HueEnum;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

        Button sendButton = (Button)findViewById(R.id.sendHue);

        sendButton.setOnClickListener(
                new View.OnClickListener()
                {
                    public void onClick(View view)
                    {
                        CheckBox hueOn = (CheckBox)findViewById(R.id.hueOn);
                        EditText saturationText = (EditText)findViewById(R.id.saturationValue);
                        EditText brightnessText = (EditText)findViewById(R.id.brightnessValue);
                        EditText hueText = (EditText)findViewById(R.id.hueValue);

                        boolean on = hueOn.isChecked();

                        JSONObject jsonObject = new JSONObject();
                        try {
                            if (on) {
                                try {
                                    int saturation = Integer.parseInt(saturationText.getText().toString());
                                    if (saturation > -1 && saturation < 255) {
                                        jsonObject.put(HueEnum.SATURATION.getValue(), saturation);
                                    } else {
                                        saturationText.setText("254");
                                        jsonObject.put(HueEnum.SATURATION.getValue(), 254);
                                    }
                                } catch (Exception ex) {}

                                try {
                                    int brightness = Integer.parseInt(brightnessText.getText().toString());
                                    if (brightness > -1 && brightness < 255) {
                                        jsonObject.put(HueEnum.BRIGHTNESS.getValue(), brightness);
                                    } else {
                                        brightnessText.setText("254");
                                        jsonObject.put(HueEnum.BRIGHTNESS.getValue(), 254);
                                    }
                                } catch (Exception ex) {}

                                try {
                                int hue = Integer.parseInt(hueText.getText().toString());
                                    if (hue > -1 && hue < 65536) {
                                        jsonObject.put(HueEnum.HUE.getValue(), hue);
                                    } else {
                                        hueText.setText("65535");
                                        jsonObject.put(HueEnum.HUE.getValue(), 65535);
                                    }
                                } catch (Exception ex) {}
                            }

                            jsonObject.put(HueEnum.ON.getValue(), on);

                            new HueAsyncTask().execute(jsonObject.toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }
}
