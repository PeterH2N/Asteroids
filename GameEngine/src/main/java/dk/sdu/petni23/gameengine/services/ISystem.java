package dk.sdu.petni23.gameengine.services;

public interface ISystem
{
    void update(double deltaTime);

    int getPriority();

    enum Priority {
        PREPROCESSING(0),
        PROCESSING(1),
        POSTPROCESSING(2),
        RENDERING(3);

        public final int value;

        Priority(int v) {
            value = v;
        }
    }
}
