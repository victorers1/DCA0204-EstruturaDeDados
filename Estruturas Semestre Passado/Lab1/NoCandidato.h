#ifndef NOCANDIDATO_H
#define NOCANDIDATO_H
#include <cstring>
#include <sstream>
#include "Candidato.h"

using namespace std;


class NoCandidato{
    public:
        Candidato *conteudo;
        NoCandidato *next;
        NoCandidato(Candidato *cand, NoCandidato *prox){
            conteudo = cand;
            next = prox;
        }

        string toString(){
            stringstream stream;
            if(conteudo != NULL){
                stream << conteudo->sobrenome << " " << conteudo->nome << " " << conteudo->nota << " -> ";
            }
            if(next==NULL){
                stream << "0";
            }
            else{
                stream << next->toString();
            }
            return stream.str();
        /** \brief
         *
         * \param
         * \param
         * \return
         *
         */
        }
};

#endif // NOCANDIDATO_H
