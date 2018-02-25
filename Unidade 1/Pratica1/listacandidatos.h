#ifndef LISTACANDIDATOS_H
#define LISTACANDIDATOS_H
#include "candidato.h"
#include "nocandidatos.h"

class ListaCandidatos{
public:
    NoCandidatos *head;

    ListaCandidatos();
    bool estaVazia();
    void adicioneComoHead(Candidato *c);
    int tamanho();
    string toString();
    bool remover(string nome, string sobrenome);
};

#endif // LISTACANDIDATOS_H
