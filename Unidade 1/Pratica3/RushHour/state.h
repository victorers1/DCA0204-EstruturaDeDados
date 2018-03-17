#ifndef STATE_H
#define STATE_H

#include <iostream>
#include <set>
#include <queue>
#include <vector>
#include <hash_set>
#include <list>
#include <vector>

using namespace std;
using __gnu_cxx::hash_set;
//necessário para inicialização de vectors

class State {
   public:
    /*
     * o estado fornece a posição de cada veículo, utilizando a seguinte convenção :
     * para um veículo horizontal é a coluna da casa mais à esquerda
     * para um veículo vertical é a coluna da casa mais acima
     * (lembrete: a coluna mais à esquerda é 0, e a linha mais alta é 0)
     */
    vector<int> pos;

    //nós guardamos qual o deslocamento levou a este estado
    int c; //carro movido no estado anterior
    int d; //deslocamento dado no estado anterior para se chegar no atual (+1/ -1)
    State* prev; //estado anterior

    State();//construtor não usado
    
    //constrói um estado inicial (c e d recebem qualquer valor = lixo)
    State(vector<int>& p);

    State(State* s, int c, int d);//constrói um estado obtido a partir de s deslocando-se o veículo c de d (-1 ou +1)

    bool success(); //indica quando o jogo acabou

    bool equals(State* s);
};


#endif // STATE_H
