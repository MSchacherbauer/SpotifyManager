package de.schacherbauer.spotifymanager.controller;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.PlaylistSimplified;
import de.schacherbauer.spotifymanager.service.SpotifyAPIClient;
import org.apache.hc.core5.http.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Collectors;

@Controller
public class FindDuplicatesController {

    @Autowired
    SpotifyAPIClient apiClient;

    @GetMapping(value="/myPlaylists")
    public String myPlaylists(){
        SpotifyApi spotifyApi = apiClient.api();
        Paging<PlaylistSimplified> playlistSimplifiedPaging = null;
        try {
            playlistSimplifiedPaging = spotifyApi.getListOfCurrentUsersPlaylists().build().execute();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SpotifyWebApiException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return Arrays.stream(playlistSimplifiedPaging.getItems()).map(p->p.getName()).collect(Collectors.joining("\n"));
    }
}
