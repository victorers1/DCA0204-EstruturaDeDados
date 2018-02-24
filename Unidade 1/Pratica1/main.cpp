#include <iostream>
#include "nocandidatos.h"
#include "listacandidatos.h"

using namespace std;

int main(){ //copie e cole aqui os códigos de teste dados pelo professor
    ListaCandidatos* lista = new ListaCandidatos();

    lista->adicioneComoHead(new Candidato("FONFEC Sophie 13"));
    lista->adicioneComoHead(new Candidato("EMANUEL Victor 100"));
    //cout << "Numero de nos da lista: " << lista->tamanho() << endl;
    lista->adicioneComoHead(new Candidato("HADY Jacques 7"));
    lista->adicioneComoHead(new Candidato("ZAPPA Frank 2"));
    cout << "Numero de nos da lista: " << lista->tamanho() << endl;

    cout<<lista->toString()<<endl;
    return 0;
}

/*
 * TODO:
 * Quando a lista contém exatamente um nó, o
 * próximo a ser adicionado sobrescreve o primeiro.
 */
