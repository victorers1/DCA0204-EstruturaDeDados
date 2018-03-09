#include <iostream>
#include <math.h>
#include <cstdlib>
#include <ctime>

using namespace std;

void merge(int v[], int n, int inicio, int meio,int fim){
    int temp[n]={0};
    int tam = fim-inicio+1;
    int pEsq = inicio;
    int pDir = meio+1;

    for(int i=0; i<tam; i++){
        if((pEsq<=meio) && (pDir<=fim)){
            if(v[pEsq] < v[pDir]){
                temp[i] = v[pEsq];
                pEsq++;
            } else{
                temp[i] = v[pDir];
                pDir++;
            }
        }else{
            if(pEsq<=meio){
                temp[i] = v[pEsq];
                pEsq++;
            }else{
                temp[i] = v[pDir];
                pDir++;
            }
        }
    }
    for(int j=0, k=inicio; j<tam; j++,k++){
        //cout<<v[k]<<" <= "<< temp[j]<<", ";
        v[k] = temp[j];

    }
}

void mergeSort(int v[], int n, int inicio, int fim){
    if(inicio < fim){
        int meio = floor((inicio+fim)/2);
        mergeSort(v, n, inicio, meio);
        mergeSort(v, n, meio+1, fim);
        merge(v, n, inicio, meio, fim);
    }
}

void toString(int v[], int n){
    for(int i=0; i<n; i++){
        cout<<v[i]<<" ";
    }
    cout<<endl;
}

int main(){
    const int tam = 50;
    int v[tam];
    srand(time(NULL));

    for(int i=0; i<tam; i++){
        v[i] = rand()%100 +1;
    }
    cout<<"Desordenado:\n";
    toString(v, tam);

    mergeSort(v, tam, 0, tam-1);

    cout<<"Ordenado\n";
    toString(v, tam);


    return 0;
}
