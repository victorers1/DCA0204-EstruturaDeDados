#include <iostream>
#include "nocandidatos.h"
#include "listacandidatos.h"
using namespace std;

void test(ListaCandidatos* l, string nome, string sobrenome){

    if(l->remover(nome,sobrenome)){
        cout << "remocao feita; nova lista: " << endl;
    } else{
        cout << "remocao nao realizada, " << nome << " " << sobrenome << " nao se encontra na lista" << endl;
    }

}

int main(){ //Vou juntar todos os testes aqui

    //Teste #1
    cout<<"TESTE #1"<<endl;
    NoCandidatos* m = NULL;
    cout << "No comeco m vale: ";
    cout << m << endl << endl;
    Candidato c("FONFEC Sophie 13");
    m = new NoCandidatos(&c,NULL);
    cout << "Depois m aponta para o no ";
    cout << m->conteudo->toString() << endl;
    cout << "e o seu no seguinte e ";
    cout << m->next << endl << endl;
    Candidato c1("HADY Jacques 7");
    m = new NoCandidatos(&c1,m);
    cout << "finalmente m referencia o no ";
    cout << m->conteudo->toString() << endl;
    cout << "e tambem via m->next, o no ";
    cout << m->next->conteudo->toString() << endl;
    cout << "que e o ultimo no, pois m->next->next vale ";
    cout << m->next->next << endl << endl;


    //Teste #2
    cout<<"\nTESTE #2"<<endl;
    ListaCandidatos* lista = new ListaCandidatos();
    if (lista->estaVazia())
        cout << "Lista A vazia: " << lista->estaVazia() << endl;
    lista->adicioneComoHead(new Candidato("FONFEC Sophie 13"));
    if(!lista->estaVazia())
        cout << "Lista B vazia: " << lista->estaVazia() << " " << lista->head->toString() << endl;
    lista->adicioneComoHead(new Candidato("HADY Jacques 7"));
    if(!lista->estaVazia())
        cout << "Lista C vazia: " << lista->estaVazia() << " " << lista->head->toString() << endl;


    //Teste #3a
    cout<<"\nTESTE #3a"<<endl;
    lista = new ListaCandidatos();
    lista->adicioneComoHead(new Candidato("FONFEC Sophie 13"));
    cout << "Numero de nos da lista: " << lista->tamanho() << endl;
    lista->adicioneComoHead(new Candidato("HADY Jacques 7"));
    lista->adicioneComoHead(new Candidato("ZAPPA Frank 2"));
    cout << "Numero de nos da lista: " << lista->tamanho() << endl;


    //Teste #3b
    cout<<"\nTESTE #3b"<<endl;
    lista = new ListaCandidatos();
    cout << "lista de " << lista->tamanho() << " candidatos: " << lista->toString()<< endl;
    lista->adicioneComoHead(new Candidato("FONFEC Sophie 13"));
    cout << "lista de " << lista->tamanho() << " candidatos: " << lista->toString()<< endl;
    lista->adicioneComoHead(new Candidato("HADY Jacques 7"));
    cout << "lista de " << lista->tamanho() << " candidatos: " << lista->toString()<< endl;
    delete(lista);

    //Teste #4
    cout<<"\nTESTE #4"<<endl;
    lista = new ListaCandidatos("candidatsBourgogne.txt");
    cout << "lista de " << lista->tamanho() << " candidatos: " << lista->toString() << endl;
    delete(lista);

    //Teste #5
    cout<<"\nTESTE #5"<<endl;
    lista = new ListaCandidatos("candidatsBourgogne.txt");
    cout << "lista de " << lista->tamanho() << " candidatos: " << lista->toString() << endl << endl;
    test(lista,  "KENZA","BEGIZ");
    cout << "lista de " << lista->tamanho() << " candidatos: " << lista->toString() << endl << endl;
    test(lista, "UGO", "CHESNEVARIN");
    cout << "lista de " << lista->tamanho() << " candidatos: " << lista->toString() << endl << endl;
    test(lista,  "SARAH", "THOULIER");
    cout << "lista de " << lista->tamanho() << " candidatos: " << lista->toString() << endl << endl;
    test(lista, "LUC","LEROI");
    cout << "lista de " << lista->tamanho() << " candidatos: " << lista->toString() << endl << endl;
    test(lista, "CLEMENT", "RABODOU");
    cout << "lista de " << lista->tamanho() << " candidatos: " << lista->toString() << endl << endl;
    test(lista, "GERALDINE", "ALLUIRE");
    cout << "lista de " << lista->tamanho() << " candidatos: " << lista->toString() << endl << endl;
    test(lista,  "CLEMENT","RABODOU");
    cout << "lista de " << lista->tamanho() << " candidatos: " << lista->toString() << endl << endl;
    delete(lista);


    //Teste #6
    cout<<"\nTESTE #6"<<endl;
    ListaCandidatos* lista1 = new ListaCandidatos("candidatsCentre.txt");
    cout << "lista de " << lista1->tamanho() << " candidatos: " << lista1->toString() << endl;
    cout << "filtragem com nota = 13" << endl;
    lista1->filtrarCandidatos(13);
    cout << "lista de " << lista1->tamanho() << " candidatos: " << lista1->toString() << endl << endl;

    ListaCandidatos* lista2 = new ListaCandidatos("candidatsBourgogne.txt");
    cout << "lista de " << lista2->tamanho() << " candidatos: " << lista2->toString() << endl;
    cout << "filtragem com nota = 20" << endl;
    lista2->filtrarCandidatos(20);
    cout << "lista de " << lista2->tamanho() << " candidatos: " << lista2->toString() << endl << endl;

    ListaCandidatos* lista3 = new ListaCandidatos("candidatsLimousin.txt");
    cout << "lista de " << lista3->tamanho() << " candidatos: " << lista3->toString() << endl;
    cout << "filtragem com nota = 0" << endl;
    lista3->filtrarCandidatos(0);
    cout << "lista de " << lista3->tamanho() << " candidatos: " << lista3->toString() << endl << endl;
    delete(lista1);
    delete(lista2);
    delete(lista3);

    //Teste #7
    cout<<"\nTESTE #7"<<endl;
    lista = new ListaCandidatos("candidatsBourgogne.txt");
    cout << "lista de " << lista->tamanho() << " candidatos: " << lista->toString() << endl << endl;

    lista1 = new ListaCandidatos("candidatsPicardie.txt");
    cout << "lista de " << lista1->tamanho() << " candidatos: " << lista1->toString() << endl << endl;
    cout << "concatenacao" << endl;
    lista->concatena(lista1);
    cout << "lista de " << lista->tamanho() << " candidatos: " << lista->toString() << endl << endl;

    return 0;
}
