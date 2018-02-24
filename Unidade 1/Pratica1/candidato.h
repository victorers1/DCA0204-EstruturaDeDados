#ifndef CANDIDATO_H
#define CANDIDATO_H
#include <cstring>
#include <sstream>

using namespace std;

class Candidato{

public:
    string nome, sobrenome;
    int nota;

    Candidato(string linha);

    string toString();
    bool igual(string n, string s);

};

#endif // CANDIDATO_H
