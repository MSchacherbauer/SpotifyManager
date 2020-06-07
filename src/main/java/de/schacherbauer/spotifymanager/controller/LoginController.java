package de.schacherbauer.spotifymanager.controller;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.requests.authorization.authorization_code.AuthorizationCodeUriRequest;
import de.schacherbauer.spotifymanager.service.SpotifyAPIClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.net.URI;

@Controller
public class LoginController {

    @Autowired
    SpotifyAPIClient apiClient;

    @GetMapping(value = "/login")
    public RedirectView login(){
        SpotifyApi spotifyApi = apiClient.api();
        AuthorizationCodeUriRequest authorizationCodeUriRequest = spotifyApi.authorizationCodeUri().build();

        final URI uri = authorizationCodeUriRequest.execute();

        System.out.println("URI: " + uri.toString());
        return new RedirectView(uri.toString());
    }
}
