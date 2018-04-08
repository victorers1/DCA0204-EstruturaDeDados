#include<iostream>
#include"ListaCandidatos.h"

using namespace std;

int main(){

  ListaCandidatos* lista1 = new ListaCandidatos("candidatsCentre.txt");
  cout << "lista de " << lista1->tamanho() << " candidatos: " << lista1->toString() << endl;
  cout << "filtragem com nota = 13" << endl;
  lista1->filtrarCandidatos(13);
  cout << "lista de " << lista1->tamanho() << " candidatos: " << lista1->toString() << endl << endl;

   ListaCandidatos* lista2 = new ListaCandidatos("candidatsBourgogne.txt");
   cout << "lista de " << lista2->tamanho() << " candidatos: " << lista2->toString() << endl;
   cout << "filtragem com nota = 20" << endl;
  lista2->filtrarCandidatos(20);
  cout << "lista de " << lista2->tamanho() << " candidatos: " << lista2->toString() << endl << endl;

   ListaCandidatos* lista3 = new ListaCandidatos("candidatsLimousin.txt");
  cout << "lista de " << lista3->tamanho() << " candidatos: " << lista3->toString() << endl;
  cout << "filtragem com nota = 0" << endl;
  lista3->filtrarCandidatos(0);
  cout << "lista de " << lista3->tamanho() << " candidatos: " << lista3->toString() << endl << endl;




  
  
  
  
  
 

}
