// Classe ABB para demonstrar o balanceamento estático (veja código no final).
// Ledón, 2016/2023; Amilton S. Martha, 2015/2017.
package trabalhadoresempresa;

import java.util.LinkedList;

class ABB<E extends Comparable<E>> {  // Árvore Binária de Busca 

    private Node raiz;

    public ABB() {
        raiz = null;
    }

    public boolean isEmpty() {
        return (raiz == null);
    }

    //Configura a raiz da árvore
    public void setRaiz(Node araiz) {
        raiz = araiz;
    }

    public Node getRaiz() {
        return raiz;
    }

    public E inserir(E valor) {
        try {
            Node novo = new Node(valor);
            this.inserir(novo, raiz);
        } catch (Exception exMemoria) {
            return null;
        } // memória insuficiente
        return (valor);
    }

    private Node inserir(Node novo, Node anterior) {
        if (raiz == null) {    // ou if(isEmpty())
            raiz = novo;  // ou setRaiz(novo);
            return raiz;
        }
        if (anterior == null) {
            return novo; // chegou em uma folha
        } else {
            if (novo.getValue().compareTo(anterior.getValue()) < 0) {
                anterior.setFilhoEsquerdo(inserir(novo, anterior.getFilhoEsquerdo()));
            } else {
                anterior.setFilhoDireito(inserir(novo, anterior.getFilhoDireito()));
            }
            return anterior;
        }
    }

    public void emOrdem() {
        emOrdem(raiz);
    }

    public void emOrdem2() {
        emOrdem2(raiz);
    }

    public void preOrdem() {
        preOrdem(raiz);
    }

    public void posOrdem() {
        posOrdem(raiz);
    }

    public void emOrdem(Node no) {
        if (no != null) {
            emOrdem(no.getFilhoEsquerdo());
            System.out.print(no.getValue() + "   ");
            emOrdem(no.getFilhoDireito());
        }
    }

    public void emOrdem2(Node no) {
        if (no != null) {
            emOrdem2(no.getFilhoEsquerdo());
            System.out.print(no.getValue() + "\n");
            emOrdem2(no.getFilhoDireito());
        }
    }

    public void preOrdem(Node no) {
        if (no != null) {
            System.out.print(no.getValue() + "   ");
            preOrdem(no.getFilhoEsquerdo());
            preOrdem(no.getFilhoDireito());
        }
    }

    public void posOrdem(Node no) {
        if (no != null) {
            posOrdem(no.getFilhoEsquerdo());
            posOrdem(no.getFilhoDireito());
            System.out.print(no.getValue() + "   ");
        }
    }

    public void emNivel(String separacao) { // Método iterativo que utiliza uma fila auxiliar
        Node noAux;
        LinkedList fila = new LinkedList();
        fila.addLast(raiz);
        while (!fila.isEmpty()) {
            noAux = (Node) fila.removeFirst();
            if (noAux.getFilhoEsquerdo() != null) {
                fila.addLast(noAux.getFilhoEsquerdo());
            }
            if (noAux.getFilhoDireito() != null) {
                fila.addLast(noAux.getFilhoDireito());
            }
            System.out.print(noAux.getValue() + separacao);
        }
    }

    //Determina o menor elemento a partir de um nó
    public Node getMenor(Node node) {
        if (isEmpty()) {
            return null;
        }
        if (node.getFilhoEsquerdo() == null) {
            return node;
        } else {
            return getMenor(node.getFilhoEsquerdo());
        }
    }

    //Determina o maior elemento a partir de um nó
    public Node getMaior(Node node) {
        if (isEmpty()) {
            return null;
        }
        if (node.getFilhoDireito() == null) {
            return node;
        } else {
            return getMaior(node.getFilhoDireito());
        }
    }

    //Calcula o maior elemento a partir de um nó
    public Node getMax(Node raiz, Node paiRaiz) {
        if (isEmpty()) {
            return null;
        }
        Node aux;
        //Se não tiver mais filho direito
        if (raiz.getFilhoDireito() == null) {
            aux = raiz;
            //Se tiver um pai, ele assume o filho esquerdo
            if (paiRaiz != null) {
                if (paiRaiz.getFilhoEsquerdo() == raiz) // se é filho esquerdo
                {
                    paiRaiz.setFilhoEsquerdo(raiz.getFilhoEsquerdo());
                } else {
                    paiRaiz.setFilhoDireito(raiz.getFilhoEsquerdo());
                }
            }
            return aux;
        } else {
            return getMax(raiz.getFilhoDireito(), raiz);
        }
    }

    private int compara(Object ob1, Object ob2) {
        return ((Comparable) ob1).compareTo(((Comparable) ob2));
    }

    public boolean eliminar(Object e) {
        return eliminar(raiz, null, e);
    }

    //Remove um elemento da árvore e retorna true ou false
    private boolean eliminar(Node node, Node paiRaiz, Object e) {
        Node aux;
        if (node == null) {  // não achou o elemento, não existe (chegou na folha)
            return false;
        } else { // a árvore ou sub-árvore não está vazia
            if (compara(e, node.getValue()) == 0) {  // o nó a eliminar está na raiz
                aux = node;
                //Se o nó não possui filhos, basta sumir com o nó
                if (node.getFilhoEsquerdo() == null && node.getFilhoDireito() == null) {
                    //Se não tiver pai, é a raiz da árvore
                    if (paiRaiz == null) {
                        setRaiz(null);
                    } //Senão, o pai deve "deserdar" o filho
                    else {
                        //Verifica se o nó que será eliminado é o filho esquerdo ou direito  do pai:
                        if (paiRaiz.getFilhoEsquerdo() != null && compara(paiRaiz.getFilhoEsquerdo().getValue(), e) == 0) {
                            paiRaiz.setFilhoEsquerdo(null);
                        } else if (paiRaiz.getFilhoDireito() != null && compara(paiRaiz.getFilhoDireito().getValue(), e) == 0) {
                            paiRaiz.setFilhoDireito(null);
                        }
                    }
                } else if (node.getFilhoDireito() == null) {   // se só tiver o filho esquerdo
                    //Se tiver um pai, ele assume o filho esquerdo
                    if (paiRaiz != null) {
                        //Verifica se a raiz é filho esquerdo ou direito para assumir o neto
                        if (paiRaiz.getFilhoEsquerdo() != null && compara(paiRaiz.getFilhoEsquerdo().getValue(), e) == 0) {
                            paiRaiz.setFilhoEsquerdo(node.getFilhoEsquerdo());
                        } else {
                            paiRaiz.setFilhoDireito(node.getFilhoEsquerdo());
                        }
                    } //Se não tiver pai (caso da raiz), adotar seu filho esquerdo
                    else {
                        node.setValue(node.getFilhoEsquerdo().getValue());
                        node.setFilhoEsquerdo(node.getFilhoEsquerdo().getFilhoEsquerdo());
                        node.setFilhoDireito(node.getFilhoEsquerdo().getFilhoDireito());
                    }
                } else if (node.getFilhoEsquerdo() == null) {   // se só tiver o filho direito
                    //Se tiver um pai, ele assume o filho esquerdo
                    if (paiRaiz != null) {
                        //Verifica se a raiz é filho esquerdo ou direito para assumir o neto
                        if (paiRaiz.getFilhoEsquerdo() != null && compara(paiRaiz.getFilhoEsquerdo().getValue(), e) == 0) {
                            paiRaiz.setFilhoEsquerdo(node.getFilhoDireito());
                        } else {
                            paiRaiz.setFilhoDireito(node.getFilhoDireito());
                        }
                    } //Se não tiver pai (caso da raiz), adotar seu filho esquerdo
                    else {
                        node.setValue(node.getFilhoDireito().getValue());
                        node.setFilhoEsquerdo(node.getFilhoDireito().getFilhoEsquerdo());
                        node.setFilhoDireito(node.getFilhoDireito().getFilhoDireito());
                    }
                } else {   //Raiz possui os 2 filhos
                    aux = getMax(node.getFilhoEsquerdo(), node);
                    node.setValue(aux.getValue());
                }
                aux = null;
                return true;
            } else { //Se não achou o nó a eliminar na raiz, continue procurando recursivamente:
                //Se for menor que a raiz, continuar procurando à esquerda
                if (compara(e, node.getValue()) < 0) {
                    return eliminar(node.getFilhoEsquerdo(), node, e);
                } else { // ou à direita
                    return eliminar(node.getFilhoDireito(), node, e);
                }
            }
        }
    }

    public Node searchABB(Object obj) {
        return searchABB(raiz, obj);
    }

    //Busca um elemento na árvore e retorna o nó onde encontrou ou null se não encontrou o nodo
    private Node searchABB(Node node, Object obj) { // procura obj a partir do nó node
        //Se a raiz for nula (árvore vazia) ou chegou em uma folha => não achou o objeto procurado:
        if (node == null) { // ou   if (isEmpty()) 
            return null;
        } else {
            //Se achou o elemento, retornar o nó:
            if (((Comparable) obj).compareTo(node.getValue()) == 0) {
                return node;
            } else {
                //Se não achou, buscar recursivamente para a esquerda ou direita:
                if (((Comparable) obj).compareTo(node.getValue()) < 0) {
                    return searchABB(node.getFilhoEsquerdo(), obj);
                } else {
                    return searchABB(node.getFilhoDireito(), obj);
                }
            }
        }
    }

    // Algumas implementações de operações com ABBs em forma iterativa:
    public Node find(Object obj) {  // método (iterativo) para buscar um obj na ABB                          
        if (isEmpty()) {
            return null;
        }
        Node atual = raiz; // começamos na raiz
        while (compara(obj, atual.getValue()) != 0) { // enquanto não encontremos o item procurado
            if (compara(obj, atual.getValue()) < 0) {
                atual = atual.getFilhoEsquerdo();
            } else {
                atual = atual.getFilhoDireito();
            }
            if (atual == null) {
                return null;  // retornamos null se não encontramos o item procurado               
            }
        }
        return atual; // retornamos o nodo encontrado
    }

    public E insert(E valor) { // implementação iterativa da inserção na ABB
        Node novoNodo;
        try {
            novoNodo = new Node(valor);
        } catch (Exception ex) {
            return null;
        } // memória insuficiente
        if (isEmpty()) {
            raiz = novoNodo; // se a ABB estiver vazia, inserimos na raiz 
        } else {
            Node atual = raiz;  // começamos procurar pela raiz      
            Node pai;
            while (true) {  // ciclo que só será interrompido ao acontecer a inserção (break interno)
                pai = atual;
                if (compara(valor, atual.getValue()) < 0) { // verificamos se devemos ir para a esquerda
                    atual = atual.getFilhoEsquerdo();
                    if (atual == null) {  // inserir à esquerda
                        pai.setFilhoEsquerdo(novoNodo);
                        break;
                    }
                } else { // ou ir para direita
                    atual = atual.getFilhoDireito();
                    if (atual == null) { // inserir a direita
                        pai.setFilhoDireito(novoNodo);
                        break;
                    }
                }
            }
        }
        return valor;
    }

    public void destroy() {   // Destruir a ABB
        destroy(raiz);
    }

    //Atravessamento em pós-ordem para destruir a ABB
    private void destroy(Node raiz) {
        if (raiz != null) {
            destroy(raiz.getFilhoEsquerdo());
            destroy(raiz.getFilhoDireito());
            this.eliminar(raiz.getValue());
        }
    }

    //---------------------------------------------
    // Implementações para balanceamento estático:
    //---------------------------------------------
    // Determinar a quantidade de nodos da ABB
    public int count() {
        return count(raiz);
    }

    // Atravessamento em pós-ordem para contar (qtde de nodos da ABB)
    public int count(Node raiz) {
        int total = 0;
        if (raiz != null) {
            total += count(raiz.getFilhoEsquerdo());
            total += count(raiz.getFilhoDireito());
            total++; // total = total + 1;
        }
        return total;
    }

    // Atributos necessários para efetuar o balanceamento estático
    private int i = 0;
    private Object vetor[];

    //Atravessamento em ordem para criar o vetor ordenado de menor a maior
    private void carregaVetor(Node meio) {
        if (meio != null) {
            carregaVetor(meio.getFilhoEsquerdo());
            vetor[i++] = meio.getValue();
            carregaVetor(meio.getFilhoDireito());
        }
    }

    private void balanceamentoEstatico(int inicio, int fim) {
        if (inicio <= fim) {
            //Calcula a posição central do sub-vetor
            int posmeio = (inicio + fim) / 2;
            //Insere o valor na árvore
            inserir((E) vetor[posmeio]);
            balanceamentoEstatico(inicio, posmeio - 1);
            balanceamentoEstatico(posmeio + 1, fim);
        }
    }

    public void balancear() {
        i = 0; // zeramos o contador auxiliar       
        int tam = count(); // calculamos o tamanho do vetor necessário        
        vetor = new Object[tam]; // alocamos memória para o vetor auxiliar      
        carregaVetor(getRaiz()); // percorremos a ABB em-ordem, para criar o vetor ordenado
        destroy(); // destruímos a ABB, que será criada novamente
        balanceamentoEstatico(0, tam - 1); // balanceamento estático: criamos a ABB de novo
    }

}
