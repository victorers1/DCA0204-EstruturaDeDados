#include <iostream>
#include "nocandidatos.h"
#include "listacandidatos.h"

using namespace std;

int main(){ //copie e cole aqui os códigos de teste dados pelo professor
    ListaCandidatos* lista = new ListaCandidatos();
      if (lista->estaVazia()) cout << "Lista A vazia: " << lista->estaVazia() << endl;

      //lista->adicioneComoHead(new Candidato("DETAL Fulana 13"));
      //cout<<"lista: "<<lista->toString()<<endl;

      lista->adicioneComoHead(new Candidato("FONFEC Sophie 13"));
      cout<<"lista: "<<lista->toString()<<endl;

      lista->adicioneComoHead(new Candidato("AMARAL Guillherme 13"));
      cout<<"lista: "<<lista->toString()<<endl;

      lista->adicioneComoHead(new Candidato("MOURA batata 13"));
      cout<<"lista: "<<lista->toString()<<endl;

      lista->adicioneComoHead(new Candidato("DOUGLAS paolo 13"));
      cout<<"lista: "<<lista->toString()<<endl;

      lista->adicioneComoHead(new Candidato("CRISTO deon 13"));
      cout<<"lista: "<<lista->toString()<<endl;

      lista->adicioneComoHead(new Candidato("KELVIN lucas 13"));
      cout<<"lista: "<<lista->toString()<<endl;

      lista->adicioneComoHead(new Candidato("BENGALA Victor 13"));
      cout<<"lista: "<<lista->toString()<<endl;
      cout<<"tamanho = "<<lista->tamanho()<<endl;

      cout<<"Antes de remover:\n"<<lista->toString()<<endl;
      lista->remover("batata", "MOURA"); //remove um do meio
      lista->remover("Victor", "BENGALA"); //remove do início
      lista->remover("Guillherme", "AMARAL"); //remove do fim
      lista->remover("ola", "MUNDO"); //inexistente

      cout<<"Apos remocao:"<<endl;
      cout<<lista->toString()<<endl;
    return 0;
}

/*
 * TODO:
 * Quando a lista contém exatamente um nó, o
 * próximo a ser adicionado sobrescreve o primeiro.
 */
