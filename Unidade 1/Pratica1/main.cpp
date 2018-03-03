#include <iostream>
#include "nocandidatos.h"
#include "listacandidatos.h"
using namespace std;


int main(){ //copie e cole aqui os códigos de teste dados pelo professor

   ListaCandidatos* lista = new ListaCandidatos("C:/Users/Victor/OneDrive/UFRN/Estrutura de Dados/Unidade 1/Pratica1/candidatos/candidatsBourgogne.txt");
      if (lista->estaVazia()) cout << "Lista A vazia: " << lista->estaVazia() << endl;

      cout<<"tamanho = "<<lista->tamanho()<<endl;
      cout<<"Antes de remover:\n"<<lista->toString()<<endl;

      lista->filtrarCandidatos(50); //exclui todo os candidatos com nota menor que 50

      cout<<"Apos filtrar:"<<endl;
      cout<<lista->toString()<<endl;

    return 0;
}
