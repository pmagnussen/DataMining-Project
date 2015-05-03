
import data.Class_Label;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author pmagnussen
 */
public class Node {

    private ArrayList<Branch> branches;
    private final Object value;
    private final Branch ingoingBranch;

    public Node(Object value, Branch parent) {
        this.value = value;
        this.ingoingBranch = parent;
        this.branches = new ArrayList<Branch>();
    }

    public void addBranch(Branch branch) {
        this.branches.add(branch);
    }

    public ArrayList<Branch> getBranches() {
        return this.branches;
    }

    public Object getAttribute() {
        return this.value;
    }

    public Branch getIngoing() {
        return this.ingoingBranch;
    }

    public boolean isLeaf() {
        return this.branches.size() == 0;
    }
}
