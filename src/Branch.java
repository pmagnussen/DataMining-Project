
import java.util.ArrayList;

public class Branch {

    private Object condition;
    private Node ingoing;

    public Object getCondition() {
        return condition;
    }

    public void setCondition(Object condition) {
        this.condition = condition;
    }

    public Node getOutgoing() {
        return outgoing;
    }

    public void setOutgoing(Node outgoing) {
        this.outgoing = outgoing;
    }

    public ArrayList<Mushroom> getMushrooms() {
        return mushrooms;
    }

    public void setMushrooms(ArrayList<Mushroom> mushrooms) {
        this.mushrooms = mushrooms;
    }

    private Node outgoing;
    private ArrayList<Mushroom> mushrooms;

    public Branch(Object condition, Node ingoing, ArrayList<Mushroom> mushrooms) {
        this.condition = condition;
        this.ingoing = ingoing;
        this.mushrooms = mushrooms;
    }

    public Node getIngoing() {
        return ingoing;
    }

    public void setIngoing(Node ingoing) {
        this.ingoing = ingoing;
    }
}