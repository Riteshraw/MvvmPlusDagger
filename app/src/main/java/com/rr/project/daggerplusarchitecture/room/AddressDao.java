package com.rr.project.daggerplusarchitecture.room;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface AddressDao {
    @Insert
    public long insertAllAddress(UserAddress address);

    @Update
    public int updateUsersAddress(UserAddress address);

    @Delete
    public int deleteAddress(UserAddress address);

    @Query("SELECT * FROM userAddress")
    LiveData<List<UserAddress>> getAll();

    @Query("SELECT * FROM userAddress WHERE addressID IN (:addressID)")
    List<UserAddress> getAddressById(int[] addressID);

//    @Query("SELECT * FROM user WHERE first_name LIKE :first AND last_name LIKE :last LIMIT 1")
//    User findByName(String first, String last);


}
