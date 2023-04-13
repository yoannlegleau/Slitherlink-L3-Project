package fr.slitherlink.app;

import com.google.api.services.oauth2.model.Userinfo;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.*;
import com.google.cloud.firestore.Query;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.*;
import com.google.firebase.cloud.FirestoreClient;
import com.google.firebase.database.*;
import fr.slitherlink.game.Score;


import java.io.FileInputStream;
import java.io.IOException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;


public class FireBase {

    private static FireBase instance;
    private FirebaseApp firebaseApp;
    private Firestore db;
    private FirebaseAuth auth;
    private DatabaseReference databaseReference;
    private String uid = " ";

    public FireBase() throws IOException {
        FileInputStream serviceAccount = new FileInputStream("src/main/resources/fr/slitherlink/database/db.json");
        FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl("https://slitherlink-7d382.firebaseio.com")
                .build();

        this.firebaseApp = FirebaseApp.initializeApp(options);
        this.auth = FirebaseAuth.getInstance(firebaseApp);
        this.databaseReference = FirebaseDatabase.getInstance(firebaseApp).getReference();
        this.db = FirestoreClient.getFirestore();
    }

    public static FireBase getInstance() throws IOException {
        if (instance == null) {
            instance = new FireBase();
        }
        return instance;
    }

    public void createUser(Userinfo userinfo) throws FirebaseAuthException {
        UserRecord userRecord;
        try {
            userRecord =  auth.getUserByEmail(userinfo.getEmail());
            System.out.println("Cet utilisateur existe déjà.");
        } catch (FirebaseAuthException e) {
            UserRecord.CreateRequest request = new UserRecord.CreateRequest()
                    .setEmail(userinfo.getEmail())
                    .setDisplayName(userinfo.getName());
            userRecord = auth.createUser(request);
            addNewUserToFirestore(userRecord.getUid(), userinfo);
        }
        uid = userRecord.getUid();
    }

    private void addNewUserToFirestore(String uid, Userinfo userinfo) {
        CollectionReference usersCollection = db.collection("users");
        Map<String, Object> userData = new HashMap<>();
        userData.put("name", userinfo.getName());
        userData.put("email", userinfo.getEmail());
        userData.put("picture", userinfo.getPicture());
        usersCollection.document(uid).set(userData);
    }

    public boolean isUserConnected() throws FirebaseAuthException {
        try {
            UserRecord user = auth.getUser(uid);
            return user != null;
        } catch (FirebaseAuthException e) {
            return false;
        }
    }

    //TODO: mettre un score au jour courant
    public void setScore(int nbPoints) throws FirebaseAuthException {
        CollectionReference scores = db.collection("scores");
        Map<String, Object> scoresData = new HashMap<>();
        scoresData.put("userId", getCurrentUser());
        scoresData.put("nbPoints", nbPoints);
        scores.document(uid).set(scoresData);
    }

    public List<Score> getAllScores() throws FirebaseAuthException {
        CollectionReference scores = db.collection("scores");
        List<Score> scoreList = new ArrayList<>();

        try {
            QuerySnapshot querySnapshot = scores.orderBy("nbPoints", Query.Direction.DESCENDING).get().get();
            for (QueryDocumentSnapshot document : querySnapshot) {
                String userId = document.getString("userId");
                int nbPoints = document.getLong("nbPoints").intValue();
                Score score = new Score(userId, nbPoints);
                scoreList.add(score);
            }
        } catch (Exception e) {
            System.err.println("Error getting scores: " + e.getMessage());
        }

        return scoreList;
    }

    public Userinfo getUserInfoByUid(String uid) throws ExecutionException, InterruptedException {
        DocumentReference userRef = db.collection("users").document(uid);
        DocumentSnapshot document = userRef.get().get();
        if (document.exists()) {
            Userinfo userinfo = document.toObject(Userinfo.class);
            return userinfo;
        } else {
            System.out.println("Utilisateur avec l'uid " + uid + " n'existe pas");
            return null;
        }
    }

    public String getCurrentUser() {
        return uid;
    }

    public void logOut() {
        uid = " ";
    }






}
