package bridge.controller;


import bridge.BridgeMaker;
import bridge.BridgeNumberGenerator;
import bridge.BridgeRandomNumberGenerator;
import bridge.domain.Bridge;
import bridge.domain.User;
import bridge.service.BridgeGame;
import bridge.service.InputViewService;
import bridge.view.OutputView;
import java.util.List;

public class BridgeGameController {
    private final BridgeNumberGenerator bridgeNumberGenerator = new BridgeRandomNumberGenerator();
    private final BridgeMaker bridgeMaker = new BridgeMaker(bridgeNumberGenerator);
    private final OutputView outputView = new OutputView();
    private final InputViewService inputViewService = new InputViewService();
    private final BridgeGame bridgeGame = new BridgeGame();

    public void start() {
        printStartGame();
        Bridge bridge = new Bridge(createBridge());
        User user = new User();
        moveBridge(bridge, user);
    }

    public void printStartGame() {
        outputView.printStartGame();
    }

    public List<String> createBridge() {
        return bridgeMaker.makeBridge(inputViewService.inputBridgeSize());
    }

    public void moveBridge(Bridge bridge, User user) {
        while (!user.isGameOver()) {
            String moveUpOrDown = inputViewService.inputMoving();
            boolean pass = bridge.isPass(moveUpOrDown);
            outputView.printMap(bridge.getLocation(), pass, moveUpOrDown);
            runPassOrFailCase(pass, bridge, user);
        }
        printResult(user);
    }

    public void runPassOrFailCase(boolean pass, Bridge bridge, User user) {
        if (pass) {
            bridgeGame.move(bridge, user);
        }
        if (!pass) {
            String gameCommand = inputViewService.inputGameCommand();
            runFailCase(bridge, user,gameCommand);
        }
    }

    public void runFailCase(Bridge bridge, User user,String gameCommand) {
        if (gameCommand.equals("R")) {
            outputView.clearMap();
            bridgeGame.retry(bridge, user);
        }
        if (gameCommand.equals("Q")) {
            user.finishGame();
        }
    }

    public void printResult(User user) {
        outputView.printResult(user);
    }
}