#include <iostream>
#include "ListaCandidatos.h"

using namespace std;

int main(){
  
  ListaCandidatos* lista = new ListaCandidatos();
  cout << "lista de " << lista->tamanho() << " candidatos: " << lista->toString()<< endl;
  lista->adicioneComoHead(new Candidato("FONFEC Sophie 13"));
  cout << "lista de " << lista->tamanho() << " candidatos: " << lista->toString()<< endl;
  lista->adicioneComoHead(new Candidato("HADY Jacques 7"));
  cout << "lista de " << lista->tamanho() << " candidatos: " << lista->toString()<< endl;
}
  
