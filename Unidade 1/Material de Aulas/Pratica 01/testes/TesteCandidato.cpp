#include <cstring>
#include <fstream>
#include <iostream>
#include "Candidato.h"

using namespace std;

int main(){

 
  ifstream fcin("candidatsPicardie.txt");
  
  string dados;
  getline(fcin,dados);
  cout << "nome da regiao: " << dados << endl;

  while(getline(fcin,dados)){
    
    Candidato c(dados);
    cout << "criacao do candidato: " << c.toString() << endl;

  }
  



}
