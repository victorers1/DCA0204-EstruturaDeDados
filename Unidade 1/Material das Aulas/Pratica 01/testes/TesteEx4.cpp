#include<iostream>
#include "ListaCandidatos.h"

using namespace std;

int main(){

  ListaCandidatos* lista = new ListaCandidatos("candidatsBourgogne.txt");
  cout << "lista de " << lista->tamanho() << " candidatos: " << lista->toString() << endl;


}
