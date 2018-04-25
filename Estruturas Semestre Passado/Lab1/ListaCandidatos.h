#ifndef LISTACANDIDATOS_H
#define LISTACANDIDATOS_H
#include <cstring>
#include <fstream>
#include <iostream>
#include "Candidato.h"
#include "NoCandidato.h"

using namespace std;


class ListaCandidatos{
    public:
        NoCandidato* head;
        ListaCandidatos(){
            head = NULL;
        }

        ListaCandidatos(string arquivo){
            ifstream fcin(arquivo.c_str());
            string dados;
            getline(fcin,dados);
            head = NULL;
            while(getline(fcin,dados)){
                Candidato *c = new Candidato(dados);
                adicioneComoHead(c);
            }

        }

        void adicioneComoHead(Candidato* c){
            NoCandidato* aux;
            aux = head;
            head = new NoCandidato(c,aux);
        }

        bool estaVazia(){
            if(head == NULL)
                return true;
            else
                return false;
        }

       int tamanho(){
            int contador = 0;
            NoCandidato* controle;
            controle = head;
            if(estaVazia()){
                return contador;
            }
            while(controle != NULL){
                contador++;
                if(controle->next == NULL){
                    delete(controle);
                    return contador;
                }
                else{
                    controle = controle->next;
                }
            }
       }


        string toString(){
            stringstream stream;
            if(estaVazia())
                stream << "0";
            else
                stream << head->toString();
            return stream.str();

        }

        NoCandidato* buscaCandidato(string nome, string sobrenome){
          NoCandidato* auxiliar;
          auxiliar = head;
          if (head == NULL) {
            return NULL;
          }

          while(auxiliar->next != NULL){
            //caso retorna o head
            if(auxiliar->conteudo->nome == nome && auxiliar->conteudo->sobrenome == sobrenome){
              return auxiliar;
            }
            //caso retorna o candidato anterior ao que quer ser removido (head nao entra aqui)
            else if(auxiliar->next->conteudo->nome == nome && auxiliar->next->conteudo->sobrenome == sobrenome){
              return auxiliar;
            //pula pro prox no
            }else{
                if(auxiliar->next != NULL){
                    auxiliar = auxiliar->next;
                }
                else{
                    return NULL;
                }
                //caso o candidato nao esta presente na lista
            }
          }
        }



        bool remove(string nome, string sobrenome){
          NoCandidato* aux;
          NoCandidato* candidato_anterior = buscaCandidato(nome, sobrenome);
          if (candidato_anterior == NULL) {
            return false;
          }
          if(estaVazia())
            return false;
          else if(candidato_anterior == head){
            aux = head;
            if(aux->next == NULL){
              delete(aux);
              head = NULL;
              return true;
            }else{
              head = aux->next;
              delete(aux);
              return true;
            }
          }else if(candidato_anterior->next != NULL){
            candidato_anterior = buscaCandidato(nome, sobrenome);
            aux = candidato_anterior->next;
            candidato_anterior->next = aux->next;
            delete(aux);
            return true;
          }else{
            return false;
          }
        }

        void filtrarCandidatos(int nota){
          NoCandidato* aux;
          aux = head;
          while(aux != NULL){
            if(aux->conteudo->nota < nota){
              remove(aux->conteudo->nome, aux->conteudo->sobrenome);
            }else{
              aux = aux->next;
            }
          }
        }

        void concatena(ListaCandidatos* l){
          NoCandidato* aux;
          aux = head;
          while(aux != NULL){
            if(aux->next == NULL){
              aux->next = l->head;
              break;
            }else{
              aux = aux->next;
            }
          }
        }
};


#endif // LISTACANDIDATOS_H
