package com.example.globalbank.database.online;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.globalbank.Model.Transaction_H;
import com.example.globalbank.Model.User;
import com.example.globalbank.Model.UserManager;
import com.example.globalbank.RIBgenerator;
import com.example.globalbank.activity.Register_Activity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DbOnline {


    boolean checkB ;
    public Context context;


    private FirebaseDatabase db = FirebaseDatabase.getInstance("https://global-bank-35cd5-default-rtdb.europe-west1.firebasedatabase.app/");
    private DatabaseReference usersdb = db.getReference().child("Users");
    private DatabaseReference history = db.getReference().child("history");

    public DbOnline(){}
    public DbOnline(Context context) {
        this.context = context;

    }
    public interface OnTransactionDataListener {
        void onTransactionDataReceived(ArrayList<Transaction_H> transactionList);
    }

    private OnTransactionDataListener listener;

    public void setOnTransactionDataListener(OnTransactionDataListener listener) {
        this.listener = listener;
    }

    public void getTransactionInfo(String userRib) {
        history.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<Transaction_H> transactionList = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Transaction_H transaction = snapshot.getValue(Transaction_H.class);
                    if (transaction.getSender_rib().equals(userRib) || transaction.getReceiver_rib().equals(userRib)) {
                        transactionList.add(transaction);
                    }


                }
                if (listener != null) {
                    listener.onTransactionDataReceived(transactionList);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle onCancelled if needed
            }
        });
    }

    public void transaction_History(String fromRIB,String name_sender, String toRIB,String name_receiver, float amount , String reason , String currentDate){
        Transaction_H transaction = new Transaction_H(fromRIB,name_sender, toRIB,name_receiver, amount,  reason, currentDate);
        history.child(transaction.getId()).setValue(transaction)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            // Transaction saved successfully
                            // You can perform any additional actions or show a success message here

                        } else {
                            // Failed to save the transaction
                            Toast.makeText(context, "Failed to save the transaction", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }
    public void updateReceiverBalance(String toRIB, float amount ){
        DatabaseReference toUserRef = usersdb.child(toRIB);
        toUserRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    User toUser = dataSnapshot.getValue(User.class);
                    if (toUser != null) {
                        float toUserBalance = toUser.getBalance();
                        float newToUserBalance = toUserBalance + amount;
                        toUser.setBalance(newToUserBalance);
                        toUserRef.child("balance").setValue(newToUserBalance)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            // Receiver's balance update successful

                                        } else {
                                            // Receiver's balance update failed
                                            Toast.makeText(context, "Failed to update receiver's balance", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(context, "Failed to update receiver's balance", Toast.LENGTH_SHORT).show();
            }
        });

    }
    interface OnBalanceUpdateListener {
        void onBalanceUpdateSuccess();

        void onInsufficientBalance();
    }
    public void updateSenderBalance(String fromRIB, float amount, final OnBalanceUpdateListener listener) {
        DatabaseReference fromUserRef = usersdb.child(fromRIB);
        fromUserRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    User fromUser = dataSnapshot.getValue(User.class);
                    if (fromUser != null) {
                        float fromUserBalance = fromUser.getBalance();
                        if (fromUserBalance >= amount) {
                            // Deduct the amount from the sender's balance
                            float newFromUserBalance = fromUserBalance - amount;
                            fromUser.setBalance(newFromUserBalance);
                            fromUserRef.child("balance").setValue(newFromUserBalance)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                // Sender's balance update successful
                                                listener.onBalanceUpdateSuccess();
                                            } else {
                                                // Sender's balance update failed
                                                Toast.makeText(context, "Failed to update sender's balance", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                        } else {
                            // Insufficient balance for the transaction
                            listener.onInsufficientBalance();
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(context, "Failed to update sender's balance", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public interface OnTransactionListener{
        void onTransactionSuccess();
        void onTransactionCancelled();
    }
    public void updateBalances(String fromRIB,String name_sender, String toRIB,String name_receiver, float amount, String reason, String currentDate , final OnTransactionListener listener) {
        updateSenderBalance(fromRIB, amount, new OnBalanceUpdateListener() {
            @Override
            public void onBalanceUpdateSuccess() {
                updateReceiverBalance(toRIB, amount);
                transaction_History(fromRIB,name_sender, toRIB,name_receiver, amount, reason, currentDate);
                listener.onTransactionSuccess();
            }

            @Override
            public void onInsufficientBalance() {
                // Handle the case when the sender's balance is insufficient
                listener.onTransactionCancelled();
            }
        });
    }


    public void checkEmailExists(String email, final OnEmailCheckListener listener) {
                usersdb.orderByChild("email").equalTo(email)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        boolean emailExists = dataSnapshot.exists();
                        listener.onEmailCheckComplete(!emailExists);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        listener.onEmailCheckComplete(false);
                    }
                });
    }

    public void LoginUser(String email, String password, final OnLoginListener listener) {
        usersdb.orderByChild("email").equalTo(email).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    boolean passwordCorrect = false;
                    User loggedInUser = null;
                    String loggedInRib = null;

                    for (DataSnapshot childSnapshot : snapshot.getChildren()) {
                        User l_user = childSnapshot.getValue(User.class);
                        if (l_user != null && l_user.getPassword() != null && l_user.getPassword().equals(password)) {
                            passwordCorrect = true;
                            loggedInUser = l_user;
                            loggedInRib = childSnapshot.getKey(); // Get the RIB from the key

                            break; // Exit the loop after finding a correct password
                        }
                    }
                    if (passwordCorrect) {
                        if (loggedInUser != null && loggedInRib != null) {
                            loggedInUser.setRibNumber(loggedInRib); // Set the RIB in the User object
                            UserManager.getInstance().setUser(loggedInUser);// Set the logged in user in the UserManager
                            listener.onLoginSuccess(passwordCorrect);
                        }

                    } else {
                        Toast.makeText(context, "Incorrect password", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(context, "User not found", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(context, "Login error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public interface OnUserDataUpdateListener {
        void onUserDataUpdated(User user);

        void onUserDataUpdateFailed(String error);
    }

    public void listenForUserDataUpdates(final OnUserDataUpdateListener listener) {
        User user = UserManager.getInstance().getUser();
        if (user != null && user.getRibNumber() != null) {
            usersdb.child(user.getRibNumber())
                    .addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            String loggedInRib ;
                            loggedInRib = dataSnapshot.getKey(); // Get the RIB from the key

                            User updatedUser = dataSnapshot.getValue(User.class);
                            if (updatedUser != null) {
                                updatedUser.setRibNumber(loggedInRib); // Set the RIB in the User object
                                UserManager.getInstance().setUser(updatedUser); // Update user data in UserManager
                                listener.onUserDataUpdated(updatedUser);
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            listener.onUserDataUpdateFailed(databaseError.getMessage());
                        }
                    });
        } else {
            // Handle the case when the user object or ribNumber is null
        }
    }


    // Listener interface for email check completion


    public interface OnEmailCheckListener {
        void onEmailCheckComplete(boolean emailAvailable);
    }
    public interface OnLoginListener {
        void onLoginSuccess(Boolean passwordCorrect);
    }


    public void CreateUserById(String ribNumber, String email, String password, String name) {
        usersdb.child(ribNumber).addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // RIB already exists, generate a new one
                    String newRIB = RIBgenerator.generateRIB("150", "101");

                    CreateUserById(newRIB, email, password, name);
                } else {
                    // RIB does not exist, proceed with registration

                    User user = new User(email, password, 0, name);
                    usersdb.child(ribNumber).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {

                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }


        });
    }





}
