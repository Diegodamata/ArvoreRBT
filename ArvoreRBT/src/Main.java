import enums.Colors;

import java.util.Scanner;

public class Main {
    static Node<Integer> root;
    public static void main(String[] args) {

        //Regras da RBT
        //1 - todo node pode ser RED ou BLACK
        //2 - a raiz sempre é BLACK
        //3 - um node RED não pode ter um pai RED
        //4 - valores nulos é considerados BLACK
        //5 - da raiz ate as ultimas folhas tem a mesma quantidade de nodes BLACK

        Scanner scanner = new Scanner(System.in);

        System.out.print("Deseja inserir? (1 - Sim / 2 - Não): ");
        int opc = scanner.nextInt();

        while (opc == 1){
            System.out.print("Informe o valor: ");
            int value = scanner.nextInt();
            insert(value);
            System.out.print("Deseja inserir? (1 - Sim / 2 - Não): ");
            opc = scanner.nextInt();
        }

        inOrder(root);

        scanner.close();
    }

    public static void insert(int value){
        Node<Integer> novoNode = new Node<>(value);

        root = bstInsert(root, novoNode);

        verificarRotacao(novoNode);

        root.setColor(Colors.BLACK);
    }

    private static Node<Integer> bstInsert(Node<Integer> root, Node<Integer> novoNode) {
        if (root == null){
            return novoNode;
        }

        if (novoNode.getValue() < root.getValue()){
            novoNode = bstInsert(root.getLeft(), novoNode);
            root.setLeft(novoNode);
            novoNode.setFather(root);
        }else {
            novoNode = bstInsert(root.getRight(), novoNode);
            root.setRight(novoNode);
            novoNode.setFather(root);
        }

        return root;
    }

    public static void verificarRotacao(Node<Integer> node){

        while (node.getFather() != null && node.getFather().getColor() == Colors.RED){

            Node<Integer> pai = node.getFather();
            Node<Integer> avo = pai.getFather();

            if (pai == avo.getLeft()){
                Node<Integer> tio = avo.getRight();

                if (tio != null && tio.getColor() == Colors.RED){
                    pai.setColor(Colors.BLACK);
                    tio.setColor(Colors.BLACK);
                    avo.setColor(Colors.RED);
                    node = avo;
                }
            }
        }
    }

    public static void inOrder(Node<Integer> root){
        if (root == null) return;
        inOrder(root.getLeft());
        System.out.print(root.getValue() + " - ");
        System.out.println(root.getColor());
        inOrder(root.getRight());
    }
}