#ifndef NOCANDIDATOS_H
#define NOCANDIDATOS_H
#include "candidato.h"

class NoCandidatos{

public:
    Candidato *conteudo;
    NoCandidatos *next;

    NoCandidatos(Candidato *c, NoCandidatos *n);
    string toString();
};

#endif // NOCANDIDATOS_H
