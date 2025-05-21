package dk.sdu.petni23.common.data;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.Pane;

public class GameData
{
    private static final IntegerProperty displayWidth = new SimpleIntegerProperty(0);
    private static final IntegerProperty displayHeight = new SimpleIntegerProperty(0);
    public static final double viewPortWidth = 30;

    public static final GameKeys gameKeys = new GameKeys();
    private static final DoubleProperty ppmProperty = new SimpleDoubleProperty();
    public static final Pane gameWindow = new Pane();
    public static final Scene scene = new Scene(gameWindow);

    static {
        ppmProperty.bind(displayWidth.divide(viewPortWidth));
    }
    public static IntegerProperty displayWidthProperty() {
        return displayWidth;
    }
    public static IntegerProperty displayHeightProperty() {
        return displayHeight;
    }
    public static int getDisplayWidth()
    {
        return displayWidth.get();
    }

    public static int getDisplayHeight()
    {
        return displayHeight.get();
    }
    public static double getPPM() {
        return ppmProperty.get();
    }
}
