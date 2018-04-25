//#include <boost/assign/std/vector.hpp>
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

//using namespace boost::assign;
using namespace std;
class State {

   public:
    /*
     * o estado fornece a posição de cada veículo, utilizando a seguinte convenção :
     * para um veículo horizontal é a coluna da casa mais à esquerda
     * para um veículo vertical é a coluna da casa mais acima
     * (lembrete: a coluna mais à esquerda é 0, e a linha mais alta é 0)
     */
    vector<int> pos;

    /*
     * nós guardamos qual o deslocamento levou a este estado
     */
    int c;
    int d;
    State* prev;

    /*
     * constrói um estado inicial (c e d recebem qualquer valor = lixo)
     */
    State(vector<int>& p) {
        //pos = new int[p.size()];
        int tam = p.size();
        for (int i = 0; i < tam; i++)
            pos.push_back(p[i]);
        prev = NULL;
    }

    /*
     * constrói um estado obtido a partir de s deslocando-se o veículo c de d (-1 ou +1)
     */
    State(State* s, int c, int d) {
        prev = s;

        int tam = s->pos.size();
        for (int i = 0; i < tam; i++)
            pos.push_back(s->pos[i]);


        pos[c] = pos[c] + d;
        // A SER COMPLETADO
    }

    // nós ganhamos?
    bool success() {
        if(c ==  0 && pos[0] == 4){
            return true;
        }else {
            return false;
        }

    }

    bool equals(State* s) {
        if (s->pos.size() != pos.size()){
            cerr << "Estados de comprimento diferentes" << endl;
            exit(1);
        }
        int tamanho = pos.size();

        for (int i = 0; i < tamanho; i++)
            if (pos[i] != s->pos[i]) return false;
        return true;
    }


};





//necessário para uso de hash_set



//função hash
struct hash_state
{
   size_t operator()(const State* t) const
   {
     int h = 0;

     for (int i = 0; i < t->pos.size(); i++)
            h = 37 * h + t->pos[i];

     return h;
   }
};

//função igualdade para hash_set
struct eq_state
{
   bool operator()(const State* t1, const State* t2) const {

       if(t1->pos.size() != t2->pos.size()) return false;
       for(int i=0; i < t1->pos.size(); i++){
               if(t1->pos[i] != t2->pos[i]) return false;

       }
      return true;
   }
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

    int nbcars;
    vector<string> color;
    vector<bool> horiz;
    vector<int> len;
    vector<int> moveon;

    int nbMoves;
    /*
     * a matriz free é utilizada em moves para determinar rapidamente se a casa (i,j) está livre
     */
    bool free[6][6];

    void initFree(State* s) {
        for(int i=0; i<6; i++){
            for(int j=0; j<6; j++){
                free[i][j] = true;
            }
        }
        for(int i=0; i<nbcars; i++){
            if(horiz[i]){
                int linha = moveon[i];
                int tamanho = len[i];
                int pos = s->pos[i];
                for (int j=0; j<tamanho; j++){
                    free[linha][pos+j] = false;
                }
            }else{
                int coluna = moveon[i];
                int tamanho = len[i];
                int pos = s->pos[i];
                for (int j=0; j<tamanho; j++){
                    free[pos+j][coluna] = false;
                }
            }
        }
        // A SER COMPLETADA
    }

    /*
     * retorna a lista de deslocamentos a partir de s
     */

    list<State*> moves(State* s) {
        initFree(s);
        list<State*> l;

        for(int i = 0; i<nbcars;i++){
            if(horiz[i]){
                int linha = moveon[i];
                int tamanho = len[i];
                int pos = s->pos[i];
                if(free[linha][pos + tamanho] == true){
                    State *temp = new State(s, i, 1);
                    l.push_back(temp);

                }else if(free[linha][pos - 1] == true){
                    State *temp = new State(s, i, -1);
                    l.push_back(temp);
                }
            }else{
                int coluna = moveon[i];
                int tamanho = len[i];
                int pos = s->pos[i];
                if(free[pos + tamanho][coluna] == true){
                    State *temp = new State(s, i, 1);
                    l.push_back(temp);
                }else if(free[pos - 1][coluna] == true){
                    State *temp = new State(s, i, -1);
                    l.push_back(temp);
                }
            }
        }
        // A SER COMPLETADA
        return l;
    }

    /*
     * procura uma solução a partir de s
     */
    State* solve(State* s) {
        hash_set<State*,hash_state,eq_state> visited;
        visited.insert(s);
        queue<State*> Q;

        Q.push(s);

        while (!Q.empty()) {
            State* atual = Q.front();
            Q.pop();
            if(atual->success()){
                return atual;
            }
            else {
                list<State*> newStates = moves(atual);
                while(!newStates.empty()){
                    int qt = visited.count(newStates.front());
                    if(qt==0){
                        Q.push(newStates.front());
                        visited.insert(newStates.front());
                        newStates.pop_front();
                    }else{
                        newStates.pop_front();
                    }
                }
            }

        }
        cerr << "sem solução" << endl; exit(1);
    }

    /*
     * imprime uma solução
     */

    void printSolution(State* s) {
        for (s = solve(s); s->prev != NULL; s = s->prev){
            if(horiz[s->c]){
                if(s->d==1){
                    cout << "Veiculo " << color[s->c] << " para direita \n";
                }else {
                    cout << "Veiculo " << color[s->c] << " para esquerda \n";
                }
            } else {
                if(s->d==1){
                    cout << "Veiculo " << color[s->c] << " para cima \n";
                }else {
                    cout << "Veiculo " << color[s->c] << " para baixo \n";
                }
            }
        }
    }

    void test2() {
        nbcars = 8;
        bool horiz1[] = {true, true, false, false, true, true, false, false};
        horiz.assign(horiz1, horiz1+8);
        int len1[] = {2,2,3,2,3,2,3,3};
        len.assign(len1,len1+8);
        int moveon1[] = {2,0,0,0,5,4,5,3};
        moveon.assign(moveon1,moveon1+8);
        int start1[] = {1,0,1,4,2,4,0,1};
        vector<int> start(start1,start1+8);
        State* s = new State(start);
        initFree(s);
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++){
                cout << free[i][j] << "\t";
            }
            cout << endl;
        }
    }

    void test3(){
        nbcars = 12;
        bool horiz1[] = {true, false, true, false, false, true, false, true,
        false, true, false, true};
        horiz.assign(horiz1, horiz1+nbcars);
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

    void test4() {
        nbcars = 12;
        string color1[] = {"vermelho","verde claro","amarelo","laranja", "violeta claro","azul ceu","rosa","violeta","verde","preto","bege","azul"};
        color.assign(color1, color1+nbcars);
        bool horiz1[] = {true, false, true, false, false, true, false, true, false, true, false, true};
        horiz.assign(horiz1, horiz1+nbcars); int len1[] = {2,2,3,2,3,2,2,2,2,2,2,3};
        len.assign(len1,len1+nbcars);
        int moveon1[] = {2,2,0,0,3,1,1,3,0,4,5,5};
        moveon.assign(moveon1,moveon1+nbcars);
        int start1[] = {1,0,3,1,1,4,3,4,4,2,4,1};
        vector<int> start(start1,start1+nbcars);
        State* s = new State(start);
        int n = 0;
        for (s = solve(s); s->prev != NULL; s = s->prev) n++;
        cout << n << endl;
    }

    void solve22() {
    nbcars = 12;
    string color1[] = {"vermelho","verde claro","amarelo","laranja",
    "violeta claro","azul ceu","rosa","violeta","verde","preto","bege","azul"};
    color.assign(color1, color1+nbcars);
    bool horiz1[] = {true, false, true, false, false, true, false,
    true, false, true, false, true};
    horiz.assign(horiz1, horiz1+nbcars);
    int len1[] = {2,2,3,2,3,2,2,2,2,2,2,3};
    len.assign(len1,len1+nbcars);
    int moveon1[] = {2,2,0,0,3,1,1,3,0,4,5,5};
    moveon.assign(moveon1,moveon1+nbcars);
    int start1[] = {1,0,3,1,1,4,3,4,4,2,4,1};
    vector<int> start(start1,start1+nbcars);
    State* s = new State(start);
    s = solve(s);
    printSolution(s);
    }


    void solve1() {
    nbcars = 8;
    string color1[] = {"vermelho","verde claro","violeta",
    "laranja","verde","azul ceu","amarelo","azul"};
    color.assign(color1, color1+nbcars);
    bool horiz1[] = {true, true, false, false, true,
    true, false, false};
    horiz.assign(horiz1, horiz1+nbcars);
    int len1[] = {2,2,3,2,3,2,3,3};
    len.assign(len1,len1+nbcars);
    int moveon1[] = {2,0,0,0,5,4,5,3};
    moveon.assign(moveon1,moveon1+nbcars);
    int start1[] = {1,0,1,4,2,4,0,1};
    vector<int> start(start1,start1+nbcars);
    State* s = new State(start);
    s = solve(s);
    printSolution(s);
    }

//devemos obter o resultado a seguir:


};








int main(){
    RushHour tes;
    tes.test4();

    return 0;
}
