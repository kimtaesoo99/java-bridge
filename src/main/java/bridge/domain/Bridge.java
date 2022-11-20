package bridge.domain;


import java.util.Collections;
import java.util.List;

public class Bridge {
    private static final int DEFAULT = 0;
    private static final int MOVE_ON_NEXT = 1;
    private final List<String> bridge;
    private int location;

    public Bridge(List<String> bridge) {
        this.bridge = bridge;
        this.location = DEFAULT;
    }

    public List<String> getBridge() {
        return Collections.unmodifiableList(bridge);
    }

    public int getLocation() {
        return location;
    }

    public void increaseLocation() {
        setLocation(this.location + MOVE_ON_NEXT);
    }

    private void setLocation(int location) {
        this.location = location;
    }

    public void initLocation() {
        setLocation(DEFAULT);
    }

    public boolean isPass(String moveUpOrDown) {
        return bridge.get(location).equals(moveUpOrDown);
    }
}
