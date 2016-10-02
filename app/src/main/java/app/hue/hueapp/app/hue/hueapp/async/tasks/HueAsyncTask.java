package app.hue.hueapp.app.hue.hueapp.async.tasks;

import android.os.AsyncTask;

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

public class HueAsyncTask extends AsyncTask<String, Void, Void> {

    @Override
    protected Void doInBackground(String... params) {
        if (params.length == 0) {
            return null;
        }

        Client client = ClientBuilder.newClient();
        WebTarget webTarget = client.target("http://10.0.0.13/").path("api/6dd2506f56ef41c9119969894be4468d/lights/1/state");
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);

        invocationBuilder.put(Entity.entity(params[0], MediaType.APPLICATION_JSON));

        return null;
    }

    @Override
    protected void onPostExecute(Void result) {

    }

    @Override
    protected void onPreExecute() {}

    @Override
    protected void onProgressUpdate(Void... values) {}

}
