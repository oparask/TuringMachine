package javaFx.view.fourthWindow;

import javaFx.Main;
import javaFx.view.PopOut;
import javaFx.view.StyledButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import model.GameFacade;

import java.util.Map;

import static console.View.displayGuessedCode;
import static console.View.displayInvalidInput;

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

        // Récupérer le bouton Test Validator depuis la vue
        StyledButton testValidatorButton = view.getTestValidatorButton();
        testValidatorButton.addEventHandler(ActionEvent.ACTION, new TestValidatorButtonClickHandler());

        //Récupérer le bouton Test Validator depuis la vue
        StyledButton nextRoundButton = view.getNextRoundButton();
        nextRoundButton.addEventHandler(ActionEvent.ACTION, new NextRoundButtonClickHandler());


        //Récupérer le bouton Test Validator depuis la vue
        StyledButton guessCodeButton = view.getGuessCodeButton();
        guessCodeButton.addEventHandler(ActionEvent.ACTION, new GuessCodeButtonClickHandler());
    }

    private class EnterCodeButtonClickHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            try {

                if (gameFacade.getCurRoundTestedValidators().isEmpty()) {
                    CodeVbox codeVbox = view.getCodeVbox();
                    // Accéder aux champs de la classe CodeVbox
                    Button firstDigit = codeVbox.getFirstDigit();
                    Button secondDigit = codeVbox.getSecondDigit();
                    Button thirdDigit = codeVbox.getThirdDigit();

                    // Utiliser les boutons comme nécessaire
                    if (firstDigit == null || secondDigit == null || thirdDigit == null) {
                        PopOut popOut = new PopOut("ERROR", "Invalid user code. Your code must have 3 digits.", true);
                        popOut.show();
                    } else {
                        String code = firstDigit.getText() + secondDigit.getText() + thirdDigit.getText();
                        gameFacade.enterCode(Integer.parseInt(code));
                        System.out.println(gameFacade.getUserCode());
                    }
                } else {
                    PopOut popOut = new PopOut("ERROR", "Go to the next round if you want to test another code.", true);
                    popOut.show();
                }


            } catch (Exception e) {
                // Gérer l'exception ici (affichage ou traitement supplémentaire si nécessaire)
                e.printStackTrace();
                PopOut popOut = new PopOut("ERROR", e.getMessage(), true);
                popOut.show();
            }
        }
    }

    private class TestValidatorButtonClickHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            try {
                if (gameFacade.getUserCode() == null) {
                    PopOut popOut = new PopOut("ERROR", "You must choose first your user code!", true);
                    popOut.show();
                } else {

                    Map<Button, Boolean> buttonClickedMap = view.getButtonClickedMap();

                    // Vérifier si aucun bouton n'est cliqué
                    boolean aucunBoutonClique = true;

                    for (Map.Entry<Button, Boolean> entry : buttonClickedMap.entrySet()) {
                        if (entry.getValue()) {
                            // Un bouton a été cliqué, récupérez le bouton et effectuez le traitement nécessaire
                            Button boutonClique = entry.getKey();
                            Integer validatorIndex = (Integer) boutonClique.getUserData();
                            gameFacade.testValidator(validatorIndex);
                            System.out.println(gameFacade.getLastValidatorTestResult() + ""
                                    + gameFacade.getCurRoundTestedValidators().get(gameFacade.getCurRoundTestedValidators().size() - 1).getValidatorNumber());
                            aucunBoutonClique = false;
                        }
                    }

                    // Si aucun bouton n'a été cliqué, imprimez un message
                    if (aucunBoutonClique) {
                        PopOut popOut = new PopOut("ERROR", "You must choose a validator!", true);
                        popOut.show();
                    }

                }
            } catch (Exception e) {
                // Gérer l'exception ici (affichage ou traitement supplémentaire si nécessaire)
                e.printStackTrace();
                PopOut popOut = new PopOut("ERROR", e.getMessage(), true);
                popOut.show();
            }
        }
    }

    private class NextRoundButtonClickHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            try {
                if (!gameFacade.getCurRoundTestedValidators().isEmpty()) {
                    gameFacade.nextRound();

                    //mettre a jour les boutton des validateurs
                    view.getValidatorsLayout().resetView();

                    //mettre a jour les boutons pour le code
                    view.getCodeVbox().resetView();

                } else {
                    PopOut popOut = new PopOut("ERROR", "You haven't tested any validators for the round "
                            + gameFacade.getRounds().size() + ".", true);
                    popOut.show();
                }
            } catch (Exception e) {
                // Gérer l'exception ici (affichage ou traitement supplémentaire si nécessaire)
                e.printStackTrace();
                PopOut popOut = new PopOut("ERROR", e.getMessage(), true);
                popOut.show();
            }
        }
    }

    private class GuessCodeButtonClickHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            try {
                if (gameFacade.getUserCode() != null) {
                   displayGuessedCode(gameFacade.guessCode());
                } else {
                    PopOut popOut = new PopOut("ERROR", "You must choose first your user code!", true);
                    popOut.show();
                }
            } catch (Exception e) {
                // Gérer l'exception ici (affichage ou traitement supplémentaire si nécessaire)
                e.printStackTrace();
                PopOut popOut = new PopOut("ERROR", e.getMessage(), true);
                popOut.show();
            }
        }
    }
}

