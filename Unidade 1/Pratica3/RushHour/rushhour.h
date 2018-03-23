#ifndef RUSHHOUR_H
#define RUSHHOUR_H

#include <iostream>
#include <set>
#include <queue>
#include <vector>
#include <hash_set>
#include "state.h"
#include <list>
#include <iterator>

using namespace std;
using __gnu_cxx::hash_set;
//necessário para inicialização de vectors

//função hash
struct hash_state{
   size_t operator()(const State* t) const;
};

//função igualdade para hash_set
struct eq_state{
   bool operator()(const State* t1, const State* t2) const;
};

class RushHour {

      public:

    /*
     * a representação do problema é :
     * a grade tem 6 colunas, numeradas 0 a 5 de esquerda para direita
     * e 6 linhas, numeradas de 0 a  5 de cima para baixo
     *
     * existem nbcars carros, numerados de 0 a  nbcars-1
     * para cada veículo i :
     * - color[i] fornece sua cor
     * - horiz[i] indica se temos um carro na horizontal
     * - len[i] fornece o seu comprimento (2 ou 3)
     * - moveon[i] indica em qual linha o carro se desloca para um carro horizontal
     *   e em qual coluna para um carro vertical
     *
     * o veiculo de indice 0 é o que tem que sair, temos então
     * horiz[0]==true, len[0]==2, moveon[0]==2
     */

    int nbcars; //número de carros no estacionamento
    vector<string> color; //cor dos carros
    vector<bool> orient; //orientação dos carros
    vector<int> len; //tamanho de cada carro
    vector<int> moveon; //indica em que linha se desloca um carro horizontal, ou que coluna se desloca um carro na vertical

    int nbMoves;

    bool free[6][6]; //a matriz free é utilizada em moves para determinar rapidamente se a casa (i,j) está livre

    RushHour();

    void initFree(State* s);

    list<State*> moves(State* s); //retorna a lista de deslocamentos a partir de s

    State* solve(State* s); //procura uma solução a partir de s

    void printSolution(State* s); //imprime uma solução

    void test2();
    void test3();
    void test4();
    void solve22();
};



#endif // RUSHHOUR_H
