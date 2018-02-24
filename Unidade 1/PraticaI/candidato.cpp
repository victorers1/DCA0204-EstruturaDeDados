#include <cstring>
#include <sstream>
#include "candidato.h"

using namespace std;

Candidato::Candidato(string linha){
    stringstream stream(linha);
    stream >> sobrenome >> nome >> nota;
}

void Candidato::setNome(string nome){
    this->nome = nome;
}
void Candidato::setSobrenome(string sobrenome){
    this->sobrenome = sobrenome;
}
void Candidato::setNota(float nota){
    this->nota = nota;
}

string Candidato::getNome(){
    return nome;
}
string Candidato::getSobrenome(){
    return sobrenome;
}
float Candidato::getNota(){
    return nota;
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
