package trabalhadoresempresa;

class AVLTreeNode<T> {
    T trabalhador;
    AVLTreeNode<T> left, right;
    int height;

    public AVLTreeNode(T trabalhador) {
        this.trabalhador = trabalhador;
        this.height = 1; // Novo nó é inicialmente adicionado na folha
    }
}

public class AVLTree<T extends Comparable<T>> {
    private AVLTreeNode<T> root;

    // Função para obter a altura de um nó
    private int height(AVLTreeNode<T> N) {
        if (N == null)
            return 0;
        return N.height;
    }

    // Função para obter o fator de balanceamento de um nó
    private int getBalance(AVLTreeNode<T> N) {
        if (N == null)
            return 0;
        return height(N.left) - height(N.right);
    }

    // Rotação à direita
    private AVLTreeNode<T> rightRotate(AVLTreeNode<T> y) {
        AVLTreeNode<T> x = y.left;
        AVLTreeNode<T> T2 = x.right;

        // Realiza a rotação
        x.right = y;
        y.left = T2;

        // Atualiza as alturas
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        // Retorna o novo nó raiz
        return x;
    }

    // Rotação à esquerda
    private AVLTreeNode<T> leftRotate(AVLTreeNode<T> x) {
        AVLTreeNode<T> y = x.right;
        AVLTreeNode<T> T2 = y.left;

        // Realiza a rotação
        y.left = x;
        x.right = T2;

        // Atualiza as alturas
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        // Retorna o novo nó raiz
        return y;
    }

    // Função para inserir um trabalhador na árvore AVL
    public void insert(T trabalhador) {
        root = insertNode(root, trabalhador);
    }

    private AVLTreeNode<T> insertNode(AVLTreeNode<T> node, T trabalhador) {
        // 1. Realiza a inserção normal de árvore binária
        if (node == null) {
            return new AVLTreeNode<>(trabalhador);
        }

        // Comparação com base no nome do trabalhador
        if (trabalhador.compareTo(node.trabalhador) < 0) {
            node.left = insertNode(node.left, trabalhador);
        } else if (trabalhador.compareTo(node.trabalhador) > 0) {
            node.right = insertNode(node.right, trabalhador);
        } else {
            // Duplicatas não são permitidas
            return node;
        }

        // 2. Atualiza a altura deste ancestral nó
        node.height = 1 + Math.max(height(node.left), height(node.right));

        // 3. Obtém o fator de balanceamento deste ancestral nó
        int balance = getBalance(node);

        // Se este nó se tornar desbalanceado, então há 4 casos

        // Caso à esquerda à esquerda
        if (balance > 1 && trabalhador.compareTo(node.left.trabalhador) < 0) {
            return rightRotate(node);
        }

        // Caso à direita à direita
        if (balance < -1 && trabalhador.compareTo(node.right.trabalhador) > 0) {
            return leftRotate(node);
        }

        // Caso à esquerda à direita
        if (balance > 1 && trabalhador.compareTo(node.left.trabalhador) > 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // Caso à direita à esquerda
        if (balance < -1 && trabalhador.compareTo(node.right.trabalhador) < 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        // Retorna o nó (inalterado) ponteiro
        return node;
    }

    // Método para fazer a travessia em ordem da árvore (apenas para exibir os trabalhadores)
    public void inOrder() {
        inOrderRec(root);
    }

    private void inOrderRec(AVLTreeNode<T> node) {
        if (node != null) {
            inOrderRec(node.left);
            System.out.println(node.trabalhador); // Imprime o trabalhador
            inOrderRec(node.right);
        }
    }
}
