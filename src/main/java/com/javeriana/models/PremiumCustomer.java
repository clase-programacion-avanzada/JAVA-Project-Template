package com.javeriana.models;

import com.javeriana.models.Artist;
import com.javeriana.models.PlayList;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.Set;

public class PremiumCustomer extends Customer {
    private final List<PlayList> playLists;

    public PremiumCustomer(UUID id, String username, String password, String name, String lastName, int age, Set<Artist> followedArtists, List<PlayList> playLists) {
        super(id, username, password, name, lastName, age, followedArtists);
        this.playLists = playLists;
    }

    public PremiumCustomer(String username, String password, String name, String lastName, int age) {
        super(username, password, name, lastName, age);
        this.playLists = new ArrayList<>();
    }

    @Override
    public void addPlayList(PlayList playList) {
        this.playLists.add(playList);
    }

    @Override
    public List<PlayList> getPlayLists() {
        return this.playLists;
    }

    @Override
    protected List<String> getPlayListIds() {
        List<String> playListIds = new ArrayList<>();
        for (PlayList playList : this.playLists) {
            playListIds.add(playList.getId().toString());
        }
        return playListIds;
    }

    @Override
    public List<UUID> getPlayListsIds() {
        List<UUID> playListIds = new ArrayList<>();
        for (PlayList playList : this.playLists) {
            playListIds.add(playList.getId());
        }
        return playListIds;
    }

    @Override
    public String toCSV(String separator) {
        return "Premium" + separator + super.toCSV(separator);
    }

}