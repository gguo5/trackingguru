/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gguo.util;

import com.gguo.trackingguru.ComponentControls;
import com.gguo.trackingguru.Tracking;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

/**
 *
 * @author Grant
 */
public class HttpUtil {

    final static Logger logger = Logger.getLogger(HttpUtil.class.getName());

    public static String[] getParams(String currentTabName) {
        String[] params = new String[4];

        Tracking.Logistics logi = Tracking.Logistics.valueOf(currentTabName);
        String url = "";
        String w = "";
        String cmodel = "";
        int ntype = 0;
        switch (logi) {
            case BlueSky:
                url = "http://track.blueskyexpress.com.au/cgi-bin/GInfo.dll?EmmisTrack";
                w = "blueskyexpress";
                break;

            case EFS:
                url = "http://nz.efspost.net/cgi-bin/GInfo.dll?EmmisTrack";
                w = "nzefs";
                break;
            default:
                url = "";
                w = "";
                break;
        }
        params[0] = url;
        params[1] = w;
        params[2] = cmodel;
        params[3] = String.valueOf(ntype);
        return params;

    }

    public static String POSTRequest(String[] params, String cno, final Tracking mainTracking) {
        //process http post
        String responseBody = "";
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            HttpPost httpPost = new HttpPost(params[0]);

            List<NameValuePair> nvps = new ArrayList<NameValuePair>();
            nvps.add(new BasicNameValuePair("w", params[1]));
            nvps.add(new BasicNameValuePair("cmodel", params[2]));
            nvps.add(new BasicNameValuePair("cno", cno));
            nvps.add(new BasicNameValuePair("ntype", params[3]));

            httpPost.setEntity(new UrlEncodedFormEntity(nvps));

            // Create a custom response handler
            ResponseHandler<String> responseHandler = new ResponseHandler<String>() {
                public String handleResponse(
                        final HttpResponse response) throws ClientProtocolException, IOException {
                    int status = response.getStatusLine().getStatusCode();
                    mainTracking.setStatusLabelText(response.getStatusLine().toString());
                    if (status >= 200 && status < 300) {
                        HttpEntity entity = response.getEntity();
                        return entity != null ? EntityUtils.toString(entity) : null;
                    } else {
                        throw new ClientProtocolException("Unexpected response status: " + status);
                    }
                }
            };
            responseBody = httpclient.execute(httpPost, responseHandler);

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Can not connect to host, try again later.");
            logger.error("IOException", ex);
        } finally {
            try {
                httpclient.close();
            } catch (IOException ex) {
                logger.error("IOException", ex);
            }
        }
        return responseBody;
    }
}
