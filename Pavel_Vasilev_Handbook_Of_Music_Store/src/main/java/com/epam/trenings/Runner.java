package com.epam.trenings;

import com.epam.trenings.io.JDBCTypeLoader;
import com.epam.trenings.io.XMLTypeLoader;
import com.epam.trenings.model.Handbook;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;


/**
 * Created by pava0715 on 01.06.2016.
 */
public class Runner {


    public Runner() {

    }

    public static void run() {
        Handbook handbookOfMusicStore = Utils.getFilledHandbook();
        Utils.printHandbook("Handbook of music stroe:", handbookOfMusicStore);

        XMLTypeLoader xmlLoader = new XMLTypeLoader("Handbook.xml");
        Utils.presentLoadedHandbook(handbookOfMusicStore, xmlLoader);

        JDBCTypeLoader jdbcTypeLoader = new JDBCTypeLoader();
        Utils.presentLoadedHandbook(handbookOfMusicStore, jdbcTypeLoader);
    }
}