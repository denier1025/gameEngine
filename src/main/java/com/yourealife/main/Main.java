package com.yourealife.main;

import com.yourealife.renderEngine.Loader;
import com.yourealife.renderEngine.RawModel;
import com.yourealife.renderEngine.Renderer;
import com.yourealife.renderEngine.Window;

public class Main {
    private static final org.slf4j.Logger log =
            org.slf4j.LoggerFactory.getLogger(Window.class);

    public static void main(String[] args) {
        Window window = new Window(1280, 720, "LWJGL Game");
        window.create();

        Loader loader = new Loader();
        Renderer renderer = new Renderer();

        // OpenGL expects vertices to be defined counter clockwise by default
        float[] vertices = {
                // Left bottom triangle
                -0.5f, 0.5f, 0f,
                -0.5f, -0.5f, 0f,
                0.5f, -0.5f, 0f,
                // Right top triangle
                0.5f, -0.5f, 0f,
                0.5f, 0.5f, 0f,
                -0.5f, 0.5f, 0f
        };

        RawModel model = loader.loadToVAO(vertices);

        long count = 0;
        long from = System.nanoTime();
        while(!window.isClosed()) {
            renderer.prepare();
            renderer.render(model);

            ++count;
            log.info("Loop is started!");

            window.update();
        }
        long to = System.nanoTime();
        log.info("{} FPS", count/((to - from)/1000_000_000));

        loader.cleanUp();
        window.closeWindow();
    }
}
