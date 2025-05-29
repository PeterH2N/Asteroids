package dk.sdu.petni23.scoresystem;

import dk.sdu.petni23.common.components.ScoreComponent;
import dk.sdu.petni23.gameengine.node.Node;
import org.springframework.web.client.RestTemplate;

public class ScoreNode extends Node {
    public ScoreComponent scoreComponent;
    @Override
    public void onRemove() {
        String request = uri + scoreComponent.score;
        var response = restTemplate.getForEntity(request, String.class);
        ScoreSystem.score = Integer.parseInt(response.getBody());
    }

    @Override
    public void onAdd() {

    }

    static RestTemplate restTemplate = new RestTemplate();
    static String uri = "http://localhost:8080/score/add?score=";

}
