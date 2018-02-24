#include <iostream>
#include "ListaCandidatos.h"

using namespace std;

int main(){
  
  ListaCandidatos* lista = new ListaCandidatos();
  if (lista->estaVazia()) 
    cout << "Lista A vazia: " << lista->estaVazia() << endl;
  lista->adicioneComoHead(new Candidato("FONFEC Sophie 13"));
  if(!lista->estaVazia())
    cout << "Lista B vazia: " << lista->estaVazia() << " " << lista->head->toString() << endl;
  lista->adicioneComoHead(new Candidato("HADY Jacques 7"));  
  if(!lista->estaVazia())
    cout << "Lista C vazia: " << lista->estaVazia() << " " << lista->head->toString() << endl;
}
