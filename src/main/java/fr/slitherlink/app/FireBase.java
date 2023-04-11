package fr.slitherlink.app;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserRecord;
import com.google.firebase.database.FirebaseDatabase;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class FireBase {

    private static final String DATABASE_URL = "https://slitherlink-7d382.firebaseio.com";
    private static final String SERVICE_ACCOUNT_FILE = "/db.json";

    private static FireBase instance;
    private FirebaseDatabase database;
    private FirebaseAuth auth;

    private FireBase() {
        try {
            InputStream serviceAccount = new FileInputStream(SERVICE_ACCOUNT_FILE);

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl(DATABASE_URL)
                    .build();

            FirebaseApp.initializeApp(options);

            database = FirebaseDatabase.getInstance();
            auth = FirebaseAuth.getInstance();

        } catch (IOException e) {
            System.err.println("Firebase initialization error: " + e.getMessage());
        }
    }

    public FirebaseAuth getAuth() {
        return auth;
    }

    public static FireBase getInstance() {
        if (instance == null) {
            instance = new FireBase();
        }
        return instance;
    }

    public UserRecord createUserFromGoogleSignIn(String email, String password, String displayName, String photoUrl) throws Exception {
        UserRecord.CreateRequest request = new UserRecord.CreateRequest()
                .setEmail(email)
                .setPassword(password)
                .setDisplayName(displayName)
                .setPhotoUrl(photoUrl);

        UserRecord userRecord = auth.createUser(request);

        return userRecord;
    }

}
