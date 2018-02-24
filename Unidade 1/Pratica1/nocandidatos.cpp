#include "nocandidatos.h"

NoCandidatos::NoCandidatos(Candidato *c, NoCandidatos *n){
    this->conteudo = c;
    this->next = n;
}

string NoCandidatos::toString(){
    return conteudo->toString(); //Aproveita a função da classe Candidato
}
