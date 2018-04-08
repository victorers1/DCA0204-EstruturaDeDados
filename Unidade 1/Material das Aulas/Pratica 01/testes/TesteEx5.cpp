#include<iostream>
#include"ListaCandidatos.h"

using namespace std;

void test(ListaCandidatos* l, string nome, string sobrenome){

  if(l->remove(nome,sobrenome)){
    cout << "remocao feita; nova lista: " << endl;
  }
  else{
    cout << "remocao nao realizada, " << nome << " " << sobrenome << " nao se encontra na lista" << endl; 
  }

};

int main(){

  ListaCandidatos* lista = new ListaCandidatos("candidatsBourgogne.txt");
  cout << "lista de " << lista->tamanho() << " candidatos: " << lista->toString() << endl << endl;
  test(lista,  "KENZA","BEGIZ");
  cout << "lista de " << lista->tamanho() << " candidatos: " << lista->toString() << endl << endl;
  test(lista, "UGO", "CHESNEVARIN");
  cout << "lista de " << lista->tamanho() << " candidatos: " << lista->toString() << endl << endl;
  test(lista,  "SARAH", "THOULIER");
  cout << "lista de " << lista->tamanho() << " candidatos: " << lista->toString() << endl << endl;
  test(lista, "LUC","LEROI");
  cout << "lista de " << lista->tamanho() << " candidatos: " << lista->toString() << endl << endl;
  test(lista, "CLEMENT", "RABODOU");
  cout << "lista de " << lista->tamanho() << " candidatos: " << lista->toString() << endl << endl;
  test(lista, "GERALDINE", "ALLUIRE");
  cout << "lista de " << lista->tamanho() << " candidatos: " << lista->toString() << endl << endl;
  test(lista,  "CLEMENT","RABODOU");
  cout << "lista de " << lista->tamanho() << " candidatos: " << lista->toString() << endl << endl;

}
