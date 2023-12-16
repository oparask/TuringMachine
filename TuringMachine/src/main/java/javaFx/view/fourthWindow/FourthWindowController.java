package javaFx.view.fourthWindow;

import javaFx.view.PopOut;
import javaFx.view.StyledButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import model.GameFacade;

import static console.View.*;

public class FourthWindowController {
    private FourthWindowView view;
    private GameFacade gameFacade;

    public FourthWindowController(FourthWindowView view, GameFacade gameFacade) {
        this.view = view;
        this.gameFacade = gameFacade;
        attachEventHandlers();
    }

    private void attachEventHandlers() {
        // Retrieve the Enter Code button from the view
        StyledButton enterCodeButton = view.getEnterCodeButton();
        enterCodeButton.addEventHandler(ActionEvent.ACTION, new EnterCodeButtonClickHandler());

        // Retrieve the Test Validator button from the view
        StyledButton testValidatorButton = view.getTestValidatorButton();
        testValidatorButton.addEventHandler(ActionEvent.ACTION, new TestValidatorButtonClickHandler());

        /*// Retrieve the Next Round button from the view
        StyledButton nextRoundButton = view.getNextRoundButton();
        nextRoundButton.addEventHandler(ActionEvent.ACTION, new NextRoundButtonClickHandler());
*/
        // Retrieve the Guess Code button from the view
        StyledButton guessCodeButton = view.getGuessCodeButton();
        guessCodeButton.addEventHandler(ActionEvent.ACTION, new GuessCodeButtonClickHandler());

        // Add event handler for the "Undo" button
        view.getUndoButton().addEventHandler(ActionEvent.ACTION, new UndoButtonClickHandler());

        // Add event handler for the "Redo" button
        view.getRedoButton().addEventHandler(ActionEvent.ACTION, new RedoButtonClickHandler());
    }

    private class EnterCodeButtonClickHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            try {
                if (gameFacade.getCurRoundTestedValidators().isEmpty()) {
                    CodeVbox codeVbox = view.getCodeVbox();
                    Button firstDigit = codeVbox.getFirstDigit();
                    Button secondDigit = codeVbox.getSecondDigit();
                    Button thirdDigit = codeVbox.getThirdDigit();

                    if (firstDigit == null || secondDigit == null || thirdDigit == null) {
                        PopOut popOut = new PopOut("ERROR", "Invalid user code. Your code must have 3 digits.", true);
                        popOut.show();
                    } else {
                        String code = firstDigit.getText() + secondDigit.getText() + thirdDigit.getText();
                        gameFacade.enterCode(Integer.parseInt(code));
                        displayMessage(gameFacade.getUserCode().toString());
                    }
                } else {
                    PopOut popOut = new PopOut("ERROR", "Go to the next round if you want to test another code.", true);
                    popOut.show();
                    displayInvalidInput("Go to the next round if you want to test another code.");
                }
            } catch (Exception e) {
                e.printStackTrace();
                PopOut popOut = new PopOut("ERROR", e.getMessage(), true);
                popOut.show();
                displayInvalidInput(e.getMessage());
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
                    displayInvalidInput("You must choose first your user code!");
                } else {
                    Integer validatorIndex = view.getIndexOfClickedButton();
                    if( validatorIndex == null){
                        PopOut popOut = new PopOut("ERROR", "You must choose a validator!", true);
                        popOut.show();
                        displayInvalidInput("You must choose a validator!");
                    } else {
                        displayValidators(gameFacade.getProblemValidators());
                        displayUserCode(gameFacade.getUserCode());
                        gameFacade.testValidator(validatorIndex);
                        System.out.println();
                        displayTestResult(gameFacade.getLastValidatorTestResult());
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                PopOut popOut = new PopOut("ERROR", e.getMessage(), true);
                popOut.show();
                displayInvalidInput(e.getMessage());
            }
        }
    }

   /* private class NextRoundButtonClickHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            try {
                if (gameFacade.getCurRoundTestedValidators().isEmpty()) {
                    PopOut popOut = new PopOut("ERROR", "You haven't tested any validators for the round "
                            + gameFacade.getRounds().size() + "!", true);
                    popOut.show();
                    displayInvalidInput("You haven't yet chosen a validator for the round " + gameFacade.getRounds().size() + ".");
                } else {
                    displayScore(gameFacade.getScore(), gameFacade.getRounds().size());
                }
            } catch (Exception e) {
                e.printStackTrace();
                PopOut popOut = new PopOut("ERROR", e.getMessage(), true);
                popOut.show();
                displayInvalidInput(e.getMessage());
            }
        }
    }*/

    private class GuessCodeButtonClickHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            try {
                if (gameFacade.getUserCode() == null) {
                    PopOut popOut = new PopOut("ERROR", "You must choose first your user code!", true);
                    popOut.show();
                    displayInvalidInput("You must choose first your user code!");
                } else {
                    displayScore(gameFacade.getScore(), gameFacade.getRounds().size());
                    displayGuessedCode(gameFacade.guessCode());
                }
            } catch (Exception e) {
                e.printStackTrace();
                PopOut popOut = new PopOut("ERROR", e.getMessage(), true);
                popOut.show();
                displayInvalidInput(e.getMessage());
            }
        }
    }

    private class UndoButtonClickHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            gameFacade.undo();
            displayMessage("UNDO");
            displayScore(gameFacade.getScore(), gameFacade.getRounds().size());
        }
    }

    private class RedoButtonClickHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            gameFacade.redo();
            displayMessage("REDO");
            displayScore(gameFacade.getScore(), gameFacade.getRounds().size());
        }
    }

    public GameFacade getGameFacade() {
        return gameFacade;
    }
}

