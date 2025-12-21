import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Node<Integer> root = null;
        System.out.print("Deseja inserir? (1 - Sim / 2 - Não): ");
        int opc = scanner.nextInt();

        while (opc == 1){
            System.out.print("Informe o valor para ser inserido: ");
            int value = scanner.nextInt();
            root = insert(root, value);
            System.out.print("Deseja inserir? (1 - Sim / 2 - Não): ");
            opc = scanner.nextInt();
        }


        inOrder(root);

        scanner.close();
    }

    public static Node<Integer> insert(Node<Integer> node, int value){
        if (node == null){
            node = new Node<>();
            node.setValue(value);
            return node;
        }

        if (value <= node.getValue()){
            node.setLeft(insert(node.getLeft(), value));
        }
        else {
            node.setRight(insert(node.getRight(), value));
        }

        return node;
    }

    public static void inOrder(Node<Integer> node){
        if (node == null) return;
        inOrder(node.getLeft());
        System.out.println(node.getValue());
        inOrder(node.getRight());
    }
}