package fr.slitherlink.app;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.oauth2.Oauth2;
import com.google.api.services.oauth2.model.Userinfo;


import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class OAuthAuthenticator {

    private static final String CLIENT_ID = "308181366295-4hn7msjk5rjlvm773to7qre4h5tustoj.apps.googleusercontent.com";
    private static final String CLIENT_SECRET = "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";
    private static final List<String> SCOPES = Arrays.asList("email", "profile");

    public  OAuthAuthenticator() {}

    public void authentifier() {
        try {
            GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                    new NetHttpTransport(),
                    new JacksonFactory(),
                    CLIENT_ID,
                    CLIENT_SECRET,
                    SCOPES)
                    .build();
            Credential credential = new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize("user");
            Oauth2 oauth2 = new Oauth2.Builder(new NetHttpTransport(), new JacksonFactory(), credential)
                    .setApplicationName("SlitherLink")
                    .build();
            Userinfo userinfo = oauth2.userinfo().get().execute();

            System.out.println("Utilisateur authentifié : " + userinfo.getEmail());
            System.out.println("Utilisateur authentifié : " + userinfo.getPicture());
        } catch (IOException e) {
            System.err.println("Erreur d'authentification Google : " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
