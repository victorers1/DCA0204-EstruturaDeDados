#include <iostream>
#include "ListaCandidatos.h"


using namespace std;

int main(){

  ListaCandidatos* lista = new ListaCandidatos();
  lista->adicioneComoHead(new Candidato("FONFEC Sophie 13"));
  cout << "Numero de nos da lista: " << lista->tamanho() << endl;
  lista->adicioneComoHead(new Candidato("HADY Jacques 7"));
  lista->adicioneComoHead(new Candidato("ZAPPA Frank 2"));
  cout << "Numero de nos da lista: " << lista->tamanho() << endl;

}
