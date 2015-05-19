package sample.com.buddyclouddroidtest.retrofit;

import com.fatboyindustrial.gsonjodatime.Converters;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.OkHttpClient;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import retrofit.RestAdapter;
import retrofit.client.OkClient;

/**
 * Created by Destri on 5/18/15.
 */
public class BuddyCloudAdapter {
    static final int CONNECT_TIMEOUT_MILLIS = 15 * 1000; // 15s
    static final int READ_TIMEOUT_MILLIS = 20 * 1000; // 20s

    public static BuddyCloudRestApi build() {

        return new RestAdapter.Builder()
                .setClient(new OkClient(buildTrustAllCertClient()))
                .setConverter(new GsonWithTypeFailToStringConverter(Converters.registerDateTime(new GsonBuilder()).create()))
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint("https://demo.buddycloud.org:443/api")
                .build()
                .create(BuddyCloudRestApi.class);


    }

    private static OkHttpClient buildTrustAllCertClient() {
        OkHttpClient okHttpClient = new OkHttpClient();

        okHttpClient.setConnectTimeout(CONNECT_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS);
        okHttpClient.setReadTimeout(READ_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS);


        try {
            okHttpClient.setSslSocketFactory(createSslSocketFactory());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Failed ", e);
        } catch (KeyManagementException e) {
            throw new RuntimeException("Failed ", e);
        }

        return okHttpClient;
    }

    private static SSLSocketFactory createSslSocketFactory() throws NoSuchAlgorithmException, KeyManagementException {
            // Create a trust manager that does not validate certificate chains
            final TrustManager[] trustAllCerts = new TrustManager[] {
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return null;
                        }
                    }
            };

            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            // Create an ssl socket factory with our all-trusting manager
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
            return sslSocketFactory;
    }
}
