#include "candidato.h"

using namespace std;

Candidato::Candidato(string linha){
    stringstream stream(linha);
    stream >> sobrenome >> nome >> nota;
}

string Candidato::toString(){
    stringstream stream;
    stream << sobrenome << " " << nome << " " << nota;
    return stream.str();
}

bool Candidato::igual(string n, string s){
    if(sobrenome == s && nome == n) return true;
    else return false;
}
