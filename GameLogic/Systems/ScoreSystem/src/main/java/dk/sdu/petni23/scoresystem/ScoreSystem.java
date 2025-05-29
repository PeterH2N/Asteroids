package dk.sdu.petni23.scoresystem;

import dk.sdu.petni23.common.data.GameData;
import dk.sdu.petni23.gameengine.services.IPlugin;
import dk.sdu.petni23.gameengine.services.ISystem;
import javafx.geometry.VPos;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class ScoreSystem implements ISystem, IPlugin {
    private static final Font font = Font.loadFont(ScoreSystem.class.getResourceAsStream("/DS-DIGI.TTF"), 40);
    public static int score;
    private static Text scoreText;
    @Override
    public void update(double deltaTime) {
        scoreText.setText("SCORE: " + score);
    }

    @Override
    public int getPriority() {
        return Priority.PROCESSING.value;
    }

    @Override
    public void start() {
        score = 0;
        scoreText = new Text();
        scoreText.setFont(font);
        GameData.gameWindow.getChildren().add(scoreText);
        scoreText.setTextOrigin(VPos.TOP);
        scoreText.setX(25);
        scoreText.setY(25);
        scoreText.setFill(Color.WHITE);
    }

    @Override
    public void stop() {
        GameData.gameWindow.getChildren().remove(scoreText);
    }
}
