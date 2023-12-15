package javaFx.controller;

import javaFx.Main;
import model.GameFacade;

public class Application {

    private GameFacade gameFacade;
    private Main view;

    public Application() {
        this.gameFacade = new GameFacade();
        this.view = new Main();
    }



}
