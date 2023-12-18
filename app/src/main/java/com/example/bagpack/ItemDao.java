package com.example.bagpack;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import static androidx.room.OnConflictStrategy.REPLACE;

import java.util.List;

@Dao
public interface ItemDao {
    @Insert(onConflict = REPLACE)
    void saveItem(Items items);

    @Query("select * from items where category=:category order by id asc")
    List<Items> getAll(String category);

    @Delete
    void delete(Items items);

    @Query("update items set checked=:checked where Id=:id")
    void checkedOrUnchecked(int id , boolean checked);

    @Query("select count(*) from items")
    Integer getITemCount();

    @Query("delete from items where addby=:addby")
    Integer deleteAllSystemItem(String addby);

    @Query("delete from items where category=:category")
    Integer deleteAllCategory(String category);

    @Query("delete from items where category=:category and addby=:addby")
    Integer deleteAllCategoryandAddBy(String category, String addby);

    @Query("select * from items where checked=:checked order by  id asc")
    List<Items> getAllSelected(Boolean checked);


}
