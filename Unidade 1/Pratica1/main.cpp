#include <iostream>
#include "nocandidatos.h"
#include "listacandidatos.h"

using namespace std;

int main(){ //copie e cole aqui os códigos de teste dados pelo professor

  ListaCandidatos* lista = new ListaCandidatos("candidatsBourgogne.txt");
  cout << "lista de " << lista->tamanho() << " candidatos: " << lista->toString() << endl << endl;

  ListaCandidatos* lista1 = new ListaCandidatos("candidatsPicardie.txt");
  cout << "lista de " << lista1->tamanho() << " candidatos: " << lista1->toString() << endl << endl;

  cout << "concatenacao" << endl;

  lista->concatena(lista1);

  cout << "lista de " << lista->tamanho() << " candidatos: " << lista->toString() << endl << endl;


    /*
    ListaCandidatos* lista = new ListaCandidatos();
      if (lista->estaVazia()) cout << "Lista A vazia: " << lista->estaVazia() << endl;

      lista->adicioneComoHead(new Candidato("DETAL Fulana 0"));
      cout<<"lista: "<<lista->toString()<<endl;

      lista->adicioneComoHead(new Candidato("FONFEC Sophie 0"));
      cout<<"lista: "<<lista->toString()<<endl;

      lista->adicioneComoHead(new Candidato("MUNDO ola 5"));
      cout<<"lista: "<<lista->toString()<<endl;

      lista->adicioneComoHead(new Candidato("CENOURA batata 10"));
      cout<<"lista: "<<lista->toString()<<endl;

      lista->adicioneComoHead(new Candidato("DOUG paolo 20"));
      cout<<"lista: "<<lista->toString()<<endl;

      lista->adicioneComoHead(new Candidato("CHRIS neon 50"));
      cout<<"lista: "<<lista->toString()<<endl;

      lista->adicioneComoHead(new Candidato("KELVIN ceusius 70"));
      cout<<"lista: "<<lista->toString()<<endl;

      lista->adicioneComoHead(new Candidato("EMANUEL Victor 100"));

      cout<<"tamanho = "<<lista->tamanho()<<endl;

      cout<<"Antes de remover:\n"<<lista->toString()<<endl;

      lista->filtrarCandidatos(50); //exclui todo os candidatos com nota menor que 50

      cout<<"Apos filtrar:"<<endl;
      cout<<lista->toString()<<endl;
      */
    return 0;
}
