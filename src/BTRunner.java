/**
 * Created with IntelliJ IDEA.
 * User: jkates
 * Date: 11/18/15
 * Time: 1:08 PM
 * To change this template use File | Settings | File Templates.
 */
public class BTRunner {

    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree(4, 2, 5, 1, 3);
        tree.mirror();
        tree.printPaths();
    }
}
