package dk.sdu.petni23.main;

import dk.sdu.petni23.common.data.GameData;
import dk.sdu.petni23.gameengine.Engine;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;

public class Main extends Application
{

    public static void main(String[] args)
    {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException
    {
        Pane gameWindow = GameData.gameWindow;
        Scene scene = GameData.scene;
        gameWindow.setPrefSize(800,800);
        GameData.displayWidthProperty().bind(gameWindow.widthProperty());
        GameData.displayHeightProperty().bind(gameWindow.heightProperty());

        // get spring context
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(ServiceConfiguration.class);
        System.out.println("Beans:");
        for (String beanName : ctx.getBeanDefinitionNames()) {
            System.out.println(beanName);
        }

        Engine.init(ctx);
        Engine.get().start();

        render();
        stage.setTitle("ASTEROIDS");
        stage.setScene(scene);
        stage.show();
    }

    private void render() {
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                Engine.get().update(1.0/60);
            }
        }.start();
    }
}