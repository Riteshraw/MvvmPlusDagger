package com.rr.project.daggerplusarchitecture.room;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.rr.project.daggerplusarchitecture.room.User;

import java.util.List;

@Dao
public interface UserDao {
    @Insert
    public long insertAll(User users);

    @Update
    public int updateUsers(User users);

    @Delete
    public int delete(User user);

    @Query("SELECT * FROM user")
    LiveData<List<User>> getAll();

    @Query("SELECT * FROM user WHERE userID IN (:userIds)")
    List<User> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM user WHERE first_name LIKE :first AND last_name LIKE :last LIMIT 1")
    User findByName(String first, String last);


}
