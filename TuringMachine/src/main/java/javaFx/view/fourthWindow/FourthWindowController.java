package javaFx.view.fourthWindow;

import javaFx.view.StyledButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import model.GameFacade;

public class FourthWindowController {
    private FourthWindowView view;
    private GameFacade gameFacade;

    public FourthWindowController(FourthWindowView view, GameFacade gameFacade) {
        this.view = view;
        this.gameFacade = gameFacade;
        attachEventHandlers();
    }

    private void attachEventHandlers() {
        // Récupérer le bouton Enter Code depuis la vue
        StyledButton enterCodeButton = view.getEnterCodeButton();

        enterCodeButton.addEventHandler(ActionEvent.ACTION, new EnterCodeButtonClickHandler());

    }

    private class EnterCodeButtonClickHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            CodeVbox codeVbox = view.getCodeVbox();
            // Accéder aux champs de la classe CodeVbox
            Button firstDigit = codeVbox.getFirstDigit();
            Button secondDigit = codeVbox.getSecondDigit();
            Button thirdDigit = codeVbox.getThirdDigit();

            // Utiliser les boutons comme nécessaire
            if (firstDigit != null && secondDigit != null && thirdDigit != null) {
                String code = firstDigit.getText() + secondDigit.getText() + thirdDigit.getText();
                gameFacade.enterCode(Integer.parseInt(code));
                System.out.println(gameFacade.getUserCode());
            }
        }
    }
}