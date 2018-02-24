#include <cstring>
#include <iostream>
#include "Candidato.h"
#include "NoCandidato.h"


using namespace std;

int main(){
  
  NoCandidato* m = NULL;
  cout << "No comeco m vale: ";
  cout << m << endl << endl;

  Candidato c("FONFEC Sophie 13");
  m = new NoCandidato(&c,NULL);

  cout << "Depois m aponta para o no ";
  cout << m->conteudo->toString() << endl;
  cout << "e o seu no seguinte e ";
  cout << m->next << endl << endl;

  
  Candidato c1("HADY Jacques 7");
  m = new NoCandidato(&c1,m);
  
  cout << "finalmente m referencia o no ";
  cout << m->conteudo->toString() << endl;
  cout << "e tambem via m->next, o no ";
  cout << m->next->conteudo->toString() << endl;
  cout << "que e o ultimo no, pois m->next->next vale ";
  cout << m->next->next << endl << endl;

  //  cout << m->toString() << endl;

}
