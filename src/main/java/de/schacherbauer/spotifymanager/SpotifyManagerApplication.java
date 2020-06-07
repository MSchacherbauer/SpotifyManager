package de.schacherbauer.spotifymanager;

import com.neovisionaries.i18n.CountryCode;
import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.SpotifyHttpManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpotifyManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpotifyManagerApplication.class, args);

    }

}
