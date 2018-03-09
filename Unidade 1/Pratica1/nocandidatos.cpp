#include "nocandidatos.h"

NoCandidatos::NoCandidatos(Candidato *c, NoCandidatos *n){
    this->conteudo = c;
    this->next = n;
}

string NoCandidatos::toString(){
    NoCandidatos *no = this;
    string texto = "";
    while(true){
        if(no->next != NULL){
            texto = texto + no->conteudo->toString() + " -> ";
            no = no->next;
        }else{
            texto = texto + no->conteudo->toString() + " -> 0";
            return texto;
        }
    }
}
