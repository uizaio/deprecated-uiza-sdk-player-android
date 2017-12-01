package com.uiza.player.ui.data;

/**
 * Created by www.muathu@gmail.com on 11/24/2017.
 */

public interface UizaSubject {
    public void registerObserver(UizaRepositoryObserver uizaRepositoryObserver);

    public void removeObserver(UizaRepositoryObserver uizaRepositoryObserver);

    public void notifyObservers();
}
