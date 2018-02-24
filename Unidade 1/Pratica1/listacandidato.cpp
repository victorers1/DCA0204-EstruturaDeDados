#include "listacandidatos.h"
#include <iostream>

using namespace std;

ListaCandidatos::ListaCandidatos(){
    this->head = NULL;
}

bool ListaCandidatos::estaVazia(){
    /*
     * Retorna True somente se a lista encadeada está vazia
     */
    return this->head == NULL;
}

void ListaCandidatos::adicioneComoHead(Candidato *c){
    /*
     * Adiciona um Nó à frente do primeiro nó da lista
     */
    NoCandidatos *no = new NoCandidatos(c, this->head);
    this->head = no;
}

int ListaCandidatos::tamanho(){
    int conta=0;
    NoCandidatos *noAtual = head;

    if(head->next==NULL) conta++; //conta o primeiro elemento

    while(noAtual->next != 0){
        conta++;  //incrementa se houver um próximo nó
        noAtual = noAtual->next;
    } //quando nó atual é o útilmo (noAtual->next==0), sai do while

    return conta;
}

string ListaCandidatos::toString(){
    string saida = "";
    NoCandidatos *noAtual = head;
    while(noAtual->next != 0){
        saida.append(noAtual->toString());
        saida.append(" -> ");
        //cout<<"--"<<saida<<"--"<<endl;
        noAtual = noAtual->next;
    }

    saida.append("0");
    return saida;
}
