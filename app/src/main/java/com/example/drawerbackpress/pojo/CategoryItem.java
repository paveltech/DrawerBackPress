package com.example.drawerbackpress.pojo;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;

import com.example.drawerbackpress.R;
import com.example.drawerbackpress.fragment.AlbumArtistListFragment;
import com.example.drawerbackpress.fragment.AlbumListFragment;
import com.example.drawerbackpress.fragment.FolderFragment;
import com.example.drawerbackpress.fragment.GenreListFragment;
import com.example.drawerbackpress.fragment.PlaylistListFragment;
import com.example.drawerbackpress.fragment.SongListFragment;
import com.example.drawerbackpress.fragment.SuggestedFragment;
import com.example.drawerbackpress.utils.ComparisonUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CategoryItem {

    public @interface Type{
        int GENRES = 0;
        int SUGGESTED = 1;
        int ARTISTS = 2;
        int ALBUMS = 3;
        int SONGS = 4;
        int PLAYLISTS = 5;
        int FOLDERS = 6;
    }

    @Type
    public int type;

    public int sortOrder;
    public  boolean isChecked;


    private CategoryItem(@Type int type , SharedPreferences sharedPreferences){
        this.type = type;
        isChecked = sharedPreferences.getBoolean(getEnabledKey(), isEnabledByDefault());
        sortOrder = sharedPreferences.getInt(getSortKey(), 0);
    }


    public boolean isEnabledByDefault() {
        switch (type) {
            case Type.GENRES:
                return true;
            case Type.SUGGESTED:
                return true;
            case Type.ARTISTS:
                return true;
            case Type.ALBUMS:
                return true;
            case Type.SONGS:
                return true;
            case Type.FOLDERS:
                return false;
            case Type.PLAYLISTS:
                return false;
        }
        return true;
    }

    public String getSortKey() {
        return getKey() + "_sort";
    }

    public String getEnabledKey() {
        return getKey() + "_enabled";
    }

    @StringRes
    public int getTitleResId() {
        switch (type) {
            case Type.GENRES:
                return R.string.genres_title;
            case Type.SUGGESTED:
                return R.string.suggested_title;
            case Type.ARTISTS:
                return R.string.artists_title;
            case Type.ALBUMS:
                return R.string.albums_title;
            case Type.SONGS:
                return R.string.tracks_title;
            case Type.FOLDERS:
                return R.string.folders_title;
            case Type.PLAYLISTS:
                return R.string.playlists_title;
        }
        return -1;
    }

    public String getKey() {
        switch (type) {
            case Type.GENRES:
                return "genres";
            case Type.SUGGESTED:
                return "suggested";
            case Type.ARTISTS:
                return "artists";
            case Type.ALBUMS:
                return "albums";
            case Type.SONGS:
                return "songs";
            case Type.FOLDERS:
                return "folders";
            case Type.PLAYLISTS:
                return "playlists";
        }
        return null;
    }


    public Fragment getFragment(Context context) {
        switch (type) {
            case Type.GENRES:
                return GenreListFragment.newInstance(context.getString(getTitleResId()));
            case Type.SUGGESTED:
                return SuggestedFragment.newInstance(context.getString(getTitleResId()));
            case Type.ARTISTS:
                return AlbumArtistListFragment.newInstance(context.getString(getTitleResId()));
            case Type.ALBUMS:
                return AlbumListFragment.newInstance(context.getString(getTitleResId()));
            case Type.SONGS:
                return SongListFragment.newInstance(context.getString(getTitleResId()));
            case Type.FOLDERS:
                return FolderFragment.newInstance(context.getString(getTitleResId()), true);
            case Type.PLAYLISTS:
                return PlaylistListFragment.newInstance(context.getString(getTitleResId()));
        }
        return null;
    }


    public static List<CategoryItem> getCategoryItems(SharedPreferences sharedPreferences) {
        List<CategoryItem> items = new ArrayList<>();
        items.add(new CategoryItem(Type.GENRES, sharedPreferences));
        items.add(new CategoryItem(Type.SUGGESTED, sharedPreferences));
        items.add(new CategoryItem(Type.ARTISTS, sharedPreferences));
        items.add(new CategoryItem(Type.ALBUMS, sharedPreferences));
        items.add(new CategoryItem(Type.SONGS, sharedPreferences));
        items.add(new CategoryItem(Type.FOLDERS, sharedPreferences));
        items.add(new CategoryItem(Type.PLAYLISTS, sharedPreferences));
        Collections.sort(items, (a, b) -> ComparisonUtils.compareInt(a.sortOrder, b.sortOrder));
        return items;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategoryItem that = (CategoryItem) o;
        return type == that.type;
    }

    @Override
    public int hashCode() {
        return type;
    }
}
