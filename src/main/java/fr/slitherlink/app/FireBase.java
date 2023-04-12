package fr.slitherlink.app;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FireBase {

    private static FirebaseApp firebaseApp = FirebaseApp.initializeApp();
    private static FirebaseAuth auth = FirebaseAuth.getInstance(firebaseApp);
    private static DatabaseReference databaseReference = FirebaseDatabase.getInstance(firebaseApp).getReference();

    public static void addNewUser(String email, String picture) {
        auth = FirebaseAuth.getInstance();

    }
}
