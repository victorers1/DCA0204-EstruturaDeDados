#ifndef LISTACANDIDATOS_H
#define LISTACANDIDATOS_H
#include "candidato.h"
#include "nocandidatos.h"

class ListaCandidatos{
public:
    NoCandidatos *head;

    ListaCandidatos();
    ListaCandidatos(string nomeDoArquivo);
    bool estaVazia();
    void adicioneComoHead(Candidato *c);
    int tamanho();
    string toString();
    bool remover(string nome, string sobrenome);
    void filtrarCandidatos(int nota);
    void concatena(ListaCandidatos *l);
};

#endif // LISTACANDIDATOS_H
