package com.javeriana.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class RegularCustomer extends Customer {

    private PlayList playList;
    public static final String DEFAULT_PLAYLIST_NAME = "PlayListRegular";

    public RegularCustomer(UUID id, String username, String password, String name, String lastName, int age, Set<Artist> followedArtists, PlayList playList) {
        super(id, username, password, name, lastName, age, followedArtists);
        if (playList != null) {
            this.playList = playList;
        } else {
            throw new IllegalArgumentException("La lista de reproducción no puede ser nula");
        }
    }

    public RegularCustomer(String username, String password, String name, String lastName, int age) {
        super(username, password, name, lastName, age);
        this.playList = new PlayList(DEFAULT_PLAYLIST_NAME + (int) (Math.random() * 1000));
    }

    public RegularCustomer(UUID id, String username, String password, String name, String lastName, int age, Set<Artist> followedArtists) {
        super();
    }

    @Override
    public void addPlayList(PlayList playList) {
        throw new UnsupportedOperationException("Un usuario regular solo puede tener una playList");
    }

    @Override
    public List<PlayList> getPlayLists() {
        return new ArrayList<>(List.of(playList));
    }

    @Override
    protected List<String> getPlayListIds() {
        return new ArrayList<>(List.of(playList.getId().toString()));
    }

    @Override
    public List<UUID> getPlayListsIds() {
        return new ArrayList<>(List.of(playList.getId()));
    }

    @Override
    public String toCSV(String separator) {
        return "Regular" + separator + super.toCSV(separator);
    }
}
