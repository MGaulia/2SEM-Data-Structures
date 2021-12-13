import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * binary search tree (without balancing)
 */
public class BinarySearchTree {
    private final Comparator cmp;

    private Node root;
    
    public BinarySearchTree(Comparator comparator) {
        cmp = comparator;
    }

    public Node getRoot() {
        return root;
    }

    /**
    1. Start at the root node.
    2. If there is no current node, the search value was not found and you are done. Otherwise, proceed to step 3.
    3. Compare the search value with the key for the current node.
    4. If the keys are equal, then you have found the search key and are done. Otherwise, proceed to step 5.
    5. If the search key sorts lower than the key for the current node, then follow the left link and go to step 2.
    6. Otherwise, the search key must sort higher than the key for the current node, so follow the right link and go to step 2.
     */
    public Node search(Object value) {
        if(value == null) throw new NullPointerException("Search value can't be null");

        Node node = root;
        while (node != null) {
            int res = cmp.compare(value, node.getValue());

            if (res == 0) {
               break;
            } else if (res < 0) {
               node = node.getSmaller();
            } else {
               node = node.getLarger();
            }
        }
        return node;
    }

    /**
     * Insertion is nearly identical to searching except that 
     * when the value doesn’t exist, it is added to the tree as a leaf node.
     */
    public Node insert(Object value) {
        if(value == null) throw new NullPointerException("Insert value can't be null");
        Node parent = null;
        Node node = root;
        int res = 0;
        while (node != null) {
            parent = node;
            res = cmp.compare(value, node.getValue());
            if(res < 0) {
                node = node.getSmaller();
            } else if (res > 0) {
                node = node.getLarger();
            } else {
                System.out.println("ERROR: negalima iterpti pasikartojancia reiksme.");
                return null;
            }
        }
        // Surado kas bus iterpiamos reiksmes PARENT
        Node newNode = new Node(); //new Node(value); Sukuria nauja Node
        newNode.setValue(value); // Jos reiksme bus iterpiama reiskme
        newNode.setParent(parent);// Nustato kad jos tevas bus surastas tevas, kad susijungtu i medi
        //Jei reiksme mazesne uz parent, ja iterpia i kaire, jei didesne, i desine
        if (parent == null) {
            root = newNode;
        } else if (res < 0) {
            parent.setSmaller(newNode);
        } else if (res > 0){
            parent.setLarger(newNode);
        }
        return newNode;
    }
    
    public void printBinaryTree(Node node, int level){
        /** Medi spausdiname apversta 90 laipsniu, tad pradziai spausdiname desiniausia, o paskutini kairiausia elementa*/
            if(node==null)
            return;
        printBinaryTree(node.getLarger(), level+1); // Eis iki desiniausio elemento
        if(level!=0){
            for(int i=0;i<level-1;i++)
                System.out.print("|\t");
            System.out.println("|-------"+node.getValue()); // Spausdina strukturizavima ir vidurini/tevini elementa
        } else
            System.out.println(node.getValue());
        printBinaryTree(node.getSmaller(), level+1); // eis iki kairiausio elemento
    }
    
    // Duomenis is medzio perkopijuoti i ArrayList sortedList taip, kad jie butu surusiuoti nuo maziausio iki didziausio
    // tuo tikslu inOrder tvarka pereinam per medi
    public void treeToList(Node node, ArrayList<Preke> sortedList){
        if(node!=null) {
            /** IN order reiskia imam kaire, virsu, desine, tad pritaikom sita principa kiekvienam node*/
            treeToList(node.getSmaller(),sortedList);
            sortedList.add((Preke) node.getValue());
            treeToList(node.getLarger(),sortedList);
        }
    }


    //... rekursyviai suprogramuoti, kaip duomenys is surusiuoto masyvo perkeliami i subalansuota BinarySearchTree
    public Node sortedArrayToBST(List<Preke> arr, int start, int end) {
 
        if (start > end) {
            return null;
        }
 
        // sukuriame nauja tuscia medzio mazga 'node'
        Node rez = new Node();
        // viduryje stovinti masyvo 'arr' elementa irasome i to mazgo 'node' value kintamaji: node.setValue(...)
        int middle = start + (end - start)/2;//Taip geriau daryti vietoj (start + end)/2 kad isvengtume overflow (kai i skaiciu bandoma irasyti per didele reiksme)

        rez.setValue(arr.get(middle));

        // Recursively construct the left subtree and make it left child of root
        rez.setSmaller(sortedArrayToBST(arr, start, middle -1));
        // Recursively construct the right subtree and make it right child of root
        rez.setLarger(sortedArrayToBST(arr, middle + 1, end));

        return rez;
    }
}
