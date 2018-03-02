#include "listacandidatos.h"
#include <iostream>

using namespace std;

ListaCandidatos::ListaCandidatos(){
    head = NULL;
}

bool ListaCandidatos::estaVazia(){
    /*
     * Retorna True somente se a lista encadeada está vazia
     */
    return head == NULL;
}

void ListaCandidatos::adicioneComoHead(Candidato *c){
    /*
     * Adiciona um Nó à frente do primeiro nó da lista
     */

    NoCandidatos *no = new NoCandidatos(c, head);
    head = no;
}

int ListaCandidatos::tamanho(){
    if(estaVazia()) return 0;

    NoCandidatos *noAtual = head;
    int conta=0;
    do{
        conta++;
        noAtual = noAtual->next;
    }while(noAtual); //para quando noAtual==NULL
    return conta;
}

string ListaCandidatos::toString(){
    if(head==NULL) return "0";


    string saida = "";
    NoCandidatos *noAtual = head;
    do{
        saida.append(noAtual->toString());
        saida.append(" -> ");
        noAtual = noAtual->next;
    }while(noAtual != NULL);

    saida.append("0");

    return saida;
}

bool ListaCandidatos::remover(string nome, string sobrenome){
    if(estaVazia()) return false;

    //Checa primeiro
    NoCandidatos *noAnt = head;
    if(noAnt->conteudo->igual(nome, sobrenome)){ //checa primeiro
        tamanho()==1 ? head = NULL : head = noAnt->next;
        delete(noAnt);
        return true;
    }

    if(tamanho()>=2){
        NoCandidatos *noAtual = head;
        NoCandidatos *lixo;
        //Para cada nó, checa se o próximo é o procurado, por isso não verifica o primeiro
        while(noAtual != NULL){  //Para após último nó
            if(noAtual->next->conteudo->igual(nome, sobrenome)){
                lixo = noAtual->next; //salva endereço do nó para deletar depois
                noAtual->next = noAtual->next->next;
                delete(lixo);
                return true;
            }
            noAtual = noAtual->next;
        }
    }
    return false;
}

void ListaCandidatos::filtrarCandidatos(int nota){
    /*
     * Recebe um inteiro que representa a nota de corte pra continuar no concurso da petrobras
     * O todos os candidado com this->nota menor que a variável passada por valor 'nota' será removido da lista
     */
    NoCandidatos *noAtual = head; //Nó usado para percorrer a lista
    NoCandidatos *noRemov; //Nó que será removido

    do{
        if(noAtual->conteudo->nota < nota){
            noRemov = noAtual;
            noAtual = noAtual->next;
            remover(noRemov->conteudo->nome, noRemov->conteudo->sobrenome);
        } else {
            noAtual = noAtual->next;
        }
    } while(noAtual!= NULL);
}


void concatena(ListaCandidatos *l){
    NoCandidatos *no = head;
    while(no->next != NULL) no = no->next;
    no->next = l->head;
}
