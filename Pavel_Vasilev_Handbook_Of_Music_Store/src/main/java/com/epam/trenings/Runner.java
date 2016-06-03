package com.epam.trenings;

import com.epam.trenings.io.TextTypeLoader;
import com.epam.trenings.model.Handbook;

/**
 * Created by pava0715 on 01.06.2016.
 */
public class Runner {
    public static void run() {
        Handbook handbookOfMusicStore = new Handbook();
        TextTypeLoader<Handbook> loaderForHandbook = new TextTypeLoader<>();

        System.out.println("Start loading handbook");
        loaderForHandbook.load(handbookOfMusicStore);


    }
}
