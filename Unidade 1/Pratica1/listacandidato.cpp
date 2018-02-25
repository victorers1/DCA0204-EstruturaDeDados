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
    NoCandidatos *no = new NoCandidatos(c, NULL);
    if(head==NULL){
        head=no;
    }else{
        no->next = head;
        head = no;
    }
}

int ListaCandidatos::tamanho(){
    if(estaVazia()) return 0;

    NoCandidatos *noAtual = head;
    int conta=0;
    do{
        noAtual = noAtual->next;
        conta++;
    }while(noAtual); //para quando noAtual==NULL
    return conta-1;
}

string ListaCandidatos::toString(){
    string saida = "";
    NoCandidatos *noAtual = head;
    while(noAtual->next != NULL){
        saida.append(noAtual->toString());
        saida.append(" -> ");
        //cout<<"--"<<saida<<"--"<<endl;
        noAtual = noAtual->next;
    }

    saida.append("0");
    return saida;
}

bool ListaCandidatos::remover(string nome, string sobrenome){
    if(estaVazia()) return false;

    //checa primeiro
    NoCandidatos *noAnt = head;
    if(noAnt->conteudo->igual(nome, sobrenome)){ //checa primeiro
        tamanho()==1 ? head=NULL : head = noAnt->next;
        delete(noAnt);
        return true;
    }

    if(tamanho()>=2){
        NoCandidatos *noAtual = head;
        NoCandidatos *lixo;
        //para cada nó, checa se o próximo é o procurado, por isso não verifica o primeiro
        while(noAtual->next!=NULL){  //para no último nó sem fazer verificação
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
