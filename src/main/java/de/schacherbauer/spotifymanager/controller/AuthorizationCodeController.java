package de.schacherbauer.spotifymanager.controller;

import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.credentials.AuthorizationCodeCredentials;
import de.schacherbauer.spotifymanager.service.SpotifyAPIClient;
import org.apache.hc.core5.http.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;

@Controller
public class AuthorizationCodeController {

    @Autowired
    SpotifyAPIClient apiClient;

    @GetMapping(value = "/callback", produces = "application/json")
    @ResponseBody
    public RedirectView retrieveAuthorizationCode(@RequestParam String code){
        try {
            AuthorizationCodeCredentials credentials = apiClient.api().authorizationCode(code).build().execute();
            String accessToken = credentials.getAccessToken();
            apiClient.api().setAccessToken(accessToken);
            apiClient.api().setRefreshToken(credentials.getRefreshToken());
            System.out.printf("AccessToken received: "+accessToken);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SpotifyWebApiException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new RedirectView("http://localhost:8080/myPlaylists");
    }
}
