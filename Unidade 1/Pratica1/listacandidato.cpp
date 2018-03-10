#include "listacandidatos.h"
#include <iostream>
#include <string>
#include <fstream>

using namespace std;

ListaCandidatos::ListaCandidatos(){
    head = NULL;
}

ListaCandidatos::ListaCandidatos(string nomeDoArquivo){
    head = NULL;
    string dados;
    ifstream cand(nomeDoArquivo.c_str());

    if(!cand.is_open()){
        cout<<"Nao abriu o arquivo!\n";
        exit(0);
    }

    getline(cand, dados);

    cout << "criacao da lista de candidatos de: " << dados << endl;

    while(getline(cand, dados)){
        getline(cand,dados); //parace que o getline acima não está removendo o ENTER, é necessário outro getline()
        //cout<<"Dados do arquivo: "<<dados<<endl;
        adicioneComoHead(new Candidato(dados));
    }
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

    //cout<<"Adicionado a lista: "<<c->toString()<<endl;
    NoCandidatos *no = new NoCandidatos(c, head);
    head = no;
}

int ListaCandidatos::tamanho(){
    if(head==NULL) return 0;

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
    else return head->toString();
}

bool ListaCandidatos::remover(string nome, string sobrenome){
    if(head==NULL) return false;

    //Checa primeiro
    NoCandidatos *noAtual = head;
    if(noAtual->conteudo->igual(nome, sobrenome)){ //checa primeiro
        tamanho()==1 ? head = NULL : head = noAtual->next;
        delete(noAtual);
        return true;
    }

    //noAtual = head;
    if(tamanho()>=2){
        //Para cada nó, checa se o próximo é o procurado. Por isso não verifica o primeiro.
        while(noAtual->next != NULL){  //Para após último nó
            if(noAtual->next->conteudo->igual(nome, sobrenome)){
                NoCandidatos *lixo = noAtual->next; //salva endereço do nó para deletar depois
                noAtual->next = noAtual->next->next; //só dá atribuir diratemente porque os atributor são todos públicos
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
     * Recebe um inteiro que representa a nota de corte pra continuar
     * O todos os candidado com this->nota menor que a variável passada por valor 'nota' será removido da lista
     * */
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

void ListaCandidatos::concatena(ListaCandidatos *l){
    NoCandidatos *no = head;
    while(no->next != NULL) no = no->next;

    no->next = l->head;
}
