package com.example.globalbank.database.local;

import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;


import java.util.List;

@Dao
public interface MainDao {

    @Insert(onConflict = REPLACE)
    void insert(BeneModel beneModel);

    @Query("SELECT * FROM table_Bene")
    List<BeneModel> getAll();

    /*
    @Query("UPDATE table_Bene SET name=:name WHERE id = (SELECT MAX(id) FROM table_QRCode)")
    void UpdateNewQRCodename(String name);


    @Query("UPDATE table_QRCode SET `check`=:check WHERE id = (SELECT MAX(id) FROM table_QRCode)")
    void UpdateNewQRcheck(boolean check);



    @Update
    void updateQRcodecheck(QRCodeModel qrCodeModel);

    @Update
    void updateQRfavoritebyid(QRCodeModel qrCodeModel);

    @Update
    void updateQRnamebyid(QRCodeModel qrCodeModel);


    @Query("SELECT `check` FROM table_QRCode WHERE id =:sid")
    boolean getcheck(int sid);

    @Query("SELECT name FROM table_QRCode WHERE id =:sid")
    String getname(int sid);

    @Query("SELECT result FROM table_QRCode WHERE id =:sid")
    String getresult(int sid);

    @Query("SELECT date FROM table_QRCode WHERE id =:sid")
    String gettime(int sid);






    @Query("SELECT * FROM table_QRCode WHERE `check` = 1")
    List<QRCodeModel> getFavAll();

    */

}
