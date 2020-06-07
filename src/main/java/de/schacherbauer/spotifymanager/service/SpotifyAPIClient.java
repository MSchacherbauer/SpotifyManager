package de.schacherbauer.spotifymanager.service;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.SpotifyHttpManager;
import com.wrapper.spotify.requests.authorization.authorization_code.AuthorizationCodeRequest;
import org.springframework.stereotype.Service;

import java.net.URI;

@Service
public class SpotifyAPIClient {

    private static final String clientId = "418a923c30424f2cbf11d34d87afd101";
    private static final String clientSecret = "0ed40a2322b4442cac5dd87f69e7ec53";
    private static final URI redirectUri = SpotifyHttpManager.makeUri("http://localhost:8080/callback");
    private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
            .setClientId(clientId)
            .setClientSecret(clientSecret)
            .setRedirectUri(redirectUri)
            .build();

    public SpotifyApi api(){
        return spotifyApi;
    }

}
