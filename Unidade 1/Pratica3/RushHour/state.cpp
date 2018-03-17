#include "state.h"

State::State(){
}

State::State(vector<int> &p) {
    //pos = new int[p.size()];
    int tam = p.size();
    for (int i = 0; i < tam; i++)
        pos.push_back(p[i]);
    prev = NULL;
}

State::State(State *s, int c, int d) {
    //guardando valores
    prev = s;
    this->c = c;
    this->d = d;

    //criar vetor de posição atual
    this->pos = s->pos; //posição atual é a posição anterior ...
    this->pos[c] += d; //... adicionado a um deslocamento
}

bool State::success() {
    return pos[0] == 4; //significa que o carro vermelho está imediatamente a frente da saída
}

bool State::equals(State *s) {
    if (s->pos.size() != pos.size()){
        cerr << "Estados de comprimento diferentes" << endl;
        exit(1);
    }
    int tamanho = pos.size();

    for (int i = 0; i < tamanho; i++)
        if (pos[i] != s->pos[i]) return false;
    return true;
}
