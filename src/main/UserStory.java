package main;

import java.util.Arrays;

public class UserStory extends Ticket {

    private final UserStory[] dependsOn;

    public UserStory(int id, String name, int estimate, UserStory... dependsOn) {
        super(id, name, estimate);
        this.dependsOn = dependsOn;
    }

    @Override
    public void complete() {
        isComp = true;

        for (UserStory dep:
                dependsOn) {
            if (!dep.isCompleted()) {
                isComp = false;
                break;
            }
        }
    }

    public UserStory[] getDependencies() {
        return Arrays.copyOf(dependsOn, dependsOn.length);
    }

    @Override
    public String toString() {
        return "[US " + getId() + "] " + getName();
    }
}
