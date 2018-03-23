#include "rushhour.h"


RushHour::RushHour(){
}

void RushHour::initFree(State *s){
    for (int i = 0; i < 6; i++)
        for (int j = 0; j < 6; j++)
            free[i][j] = true; // estacionamento vazio

    for(int carro=0; carro<nbcars; carro++){ //para cada carro
        if(orient[carro] == true){
            //cout<<"carro "<<carro <<" esta na horizontal e tem tamanho "<<len[carro]<<"\n";

            for(int bloco=0; bloco<len[carro]; bloco++){ //para cada bloco ocupado pelo carro
                //cout<<"free["<<moveon[carro]<<"]["<<s->pos[carro]+bloco<<"] = true\n";
                free[moveon[carro]][s->pos[carro] + bloco] = false; //percorre as colunas da linha 'moveon[carro]' da matriz 'free'
                // já que moveon[] indica em qual linha o carro horizontal se move
            }
        } else{
            //cout<<"carro "<<carro <<" esta na vertical e tem tamanho "<<len[carro]<<"\n";

            for(int bloco=0; bloco<len[carro]; bloco++){
                //cout<<"free["<<s->pos[carro]+bloco<<"]["<<moveon[carro]<<"] = true\n";
                free[s->pos[carro]+bloco][moveon[carro]] = false;
                // já que moveon[] indica em qual coluna o carro vertical se move
            }
        }

    }
}

list<State *> RushHour::moves(State *s){
    initFree(s);
    list<State*> l;

    for(int carro=0; carro<nbcars; carro++){
        if(orient[carro]==true){ //horizontal
            int posEsq = s->pos[carro]-1; //essas variáveis foram criadas pela legibilidade do código
            int posDir = s->pos[carro]+len[carro];

            if(posEsq>=0 && free[moveon[carro]][posEsq]==true) //esquerda livre
                l.push_back(new State(s, carro, -1));

            if(posDir<6 && free[moveon[carro]][posDir]==true) //direita livre
                l.push_back(new State(s, carro, 1));

        } else{ //vertical
            int posCima = s->pos[carro]-1;
            int posBaixo  = s->pos[carro]+len[carro];

            if(posCima>=0 && free[posCima][moveon[carro]]==true) //em cima livre
                l.push_back(new State(s, carro,-1));

            if(posBaixo<6 && free[posBaixo][moveon[carro]]==true) //embaixo livre
                l.push_back(new State(s, carro, +1));
        } //em cada IF há um teste verificando os índices estão dentro das bordas
    }
    return l;
}

State *RushHour::solve(State *s){
    // Objeto equivalente ao Conjunto, no Python. Guarda quais nós foram visitados
    hash_set<State*,hash_state,eq_state> visited;// L
    queue<State*> Q;
    State *u = new State();// estado vazio
    list<State*> V; // lista de movimentos possíveis a partir de s

    visited.insert(s); // recebe o estado inicial
    Q.push(s);
    while (!Q.empty()){
        u = Q.front(); Q.pop(); // Extrai primeiro da fila
        V = moves(u); // Todos os movimentos a partir de u

        list<State*>::iterator v; // v representa uma variável do tipo State*

        for(v = V.begin(); v == V.end(); v++){ //para cada vizinho de v ainda não listado, adicione-o na lista de listados e veja se é a solução
            if (visited.count(*v) == 0) {
                if((*v)->success()) {
                    return *v;
                }
                Q.push(*v);
                visited.insert(*v);
            }
        }
    }
    cerr << "sem solução" << endl; exit(1);
}

size_t hash_state::operator()(const State *t) const{
    int h = 0;

    for (int i = 0; i < t->pos.size(); i++)
        h = 37 * h + t->pos[i];

    return h;
}

bool eq_state::operator()(const State *t1, const State *t2) const {

    if(t1->pos.size() != t2->pos.size()) return false;
    for(int i=0; i < t1->pos.size(); i++){
        if(t1->pos[i] != t2->pos[i]) return false;

    }
    return true;
}

void RushHour::test2(){
    nbcars = 8;
    bool horiz1[] = {true, true, false, false, true, true, false, false};
    orient.assign(horiz1, horiz1+8);
    int len1[] = {2,2,3,2,3,2,3,3};
    len.assign(len1,len1+8);
    int moveon1[] = {2,0,0,0,5,4,5,3};
    moveon.assign(moveon1,moveon1+8);
    int start1[] = {1,0,1,4,2,4,0,1};
    vector<int> start(start1,start1+8);
    State* s = new State(start);
    initFree(s);
    for (int i = 0; i < 6; i++){
        for (int j = 0; j < 6; j++){
            if(free[i][j])
                cout<<"true ";
            else
                cout<<"false ";
        }
        cout << endl;
    }
}

void RushHour::test3(){
    nbcars = 12;
    bool horiz1[] = {true, false, true, false, false, true, false, true,
                     false, true, false, true};
    orient.assign(horiz1, horiz1+nbcars);
    int len1[] = {2,2,3,2,3,2,2,2,2,2,2,3};
    len.assign(len1,len1+12);
    int moveon1[] = {2,2,0,0,3,1,1,3,0,4,5,5};
    moveon.assign(moveon1,moveon1+nbcars);
    int start1[] = {1,0,3,1,1,4,3,4,4,2,4,1};
    vector<int> start(start1,start1+nbcars);
    State* s = new State(start);
    int start02[] = {1,0,3,1,1,4,3,4,4,2,4,2};
    vector<int> start2(start02,start02+nbcars);
    State* s2 = new State(start2);
    int n = 0;
    for (list<State*> L = moves(s); !L.empty(); n++) L.pop_front();
    cout << n << endl;
    n = 0;
    for (list<State*> L = moves(s2); !L.empty(); n++) L.pop_front();
    cout << n << endl;
}

void RushHour::test4() {
    nbcars = 12;
    string color1[] = {"vermelho","verde claro","amarelo","laranja",
                       "violeta claro","azul ceu","rosa","violeta","verde","preto","bege","azul"};
    color.assign(color1, color1+nbcars);
    bool horiz1[] = {true, false, true, false, false, true, false,
                     true, false, true, false, true};
    orient.assign(horiz1, horiz1+nbcars);
    int len1[] = {2,2,3,2,3,2,2,2,2,2,2,3};
    len.assign(len1,len1+nbcars);
    int moveon1[] = {2,2,0,0,3,1,1,3,0,4,5,5};
    moveon.assign(moveon1,moveon1+nbcars);
    int start1[] = {1,0,3,1,1,4,3,4,4,2,4,1};
    vector<int> start(start1,start1+nbcars);
    State* s = new State(start);
    int n = 0;
    for (s = solve(s); s.prev != null; s = s.prev) n++;
    cout << n << endl;
}
