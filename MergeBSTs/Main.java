
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        BinarySearchTree bst1 = new BinarySearchTree(new PrekesComparator());
        bst1.insert(new Preke(3, "P7"));
        bst1.insert(new Preke(1, "P7"));
        bst1.insert(new Preke(5, "P7"));

        BinarySearchTree bst2 = new BinarySearchTree(new PrekesComparator());
        bst2.insert(new Preke(4, "P8"));
        bst2.insert(new Preke(2, "P8"));
        bst2.insert(new Preke(6, "P8"));

        System.out.println("Pirmas medis: ");
        bst1.printBinaryTree(bst1.getRoot(),0);

        System.out.println("Antras medis: ");
        bst2.printBinaryTree(bst2.getRoot(),0);

        // Duomenis is medzio perkopijuoti i ArrayList 'l1' taip, kad jie butu surusiuoti nuo maziausio iki didziausio
        ArrayList<Preke> l1 = new ArrayList<Preke>();
        bst1.treeToList(bst1.getRoot(), l1);

        System.out.println("Pirmas Arraylist: ");
        printList(l1);
        // Duomenis is medzio perkopijuoti i ArrayList 'l2' taip, kad jie butu surusiuoti nuo maziausio iki didziausio
        ArrayList<Preke> l2 = new ArrayList<Preke>();
        bst2.treeToList(bst2.getRoot(), l2);

        System.out.println("Antras Arraylist: ");
        printList(l2);

        List<Preke> mergedList = merge(l1, l2, new PrekesComparator());
        System.out.println("Sujungtas Arraylist: ");
        printList(mergedList);
        
        BinarySearchTree bst3 = new BinarySearchTree(new PrekesComparator());
        Node root = bst3.sortedArrayToBST(mergedList, 0, mergedList.size() - 1);
        System.out.println("Medis padarytas is ArrayList'o");
        bst3.printBinaryTree(root, 0);
    }
    
    private static List<Preke> merge(List<Preke> l1, List<Preke> l2, Comparator cmp ){
        List<Preke> mergedList = new ArrayList<Preke>(l1.size() + l2.size());
	//... apjungti l1 ir l2 i viena surusiuota sarasa

        while (l1.size()!=0 && l2.size()!=0) {
            if (cmp.compare(l1.get(0),l2.get(0)) <0 ) {mergedList.add(l1.remove(0));}
            else { mergedList.add(l2.remove(0));}
        }

        while(l1.size()!=0) mergedList.add(l1.remove(0));
        while(l2.size()!=0) mergedList.add(l2.remove(0));

	return mergedList;
    }
    public static void printList(List l){
        for(Object o : l)
            System.out.print(o + " ");
        System.out.println();
    }
}
