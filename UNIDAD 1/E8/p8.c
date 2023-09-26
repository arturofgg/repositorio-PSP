#include <sys/types.h>
#include <sys/wait.h>
#include <fcntl.h>
#include <unistd.h>
#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <time.h>

#define SIZE 512
//Funciones prototipo
void codigo_hijo(int, int);
void codigo_padre(pid_t, int);

int main(void){
    pid_t pid;
    int t[2], t2[2], readbytes, random;
    char buffer[SIZE];

    pipe(t); //Se crea la tuberia
    pipe(t2); //2 tuberia

    srand(time(NULL)); //Altero la semilla de numeros aleatorios en base al tiempo
    random=rand() % 101; //numero entre 0 y 100

    pid = fork();
    switch(pid){
        case -1: 
            printf("No existo");
            exit(-1);
            break;

        case 0:
        close (t[1]); //Escribe hijo
        close (t2[0]);//Lee hijo
            codigo_hijo(random, t2[0]);
            break;

        default:
        close (t[0]); //Lee padre
        close (t2[1]);//EScribe padre
            codigo_padre(pid, t2[1]);
            break;
    }
    return 0;
}

void codigo_hijo(int ns, int tuberiaLectura){
    char textoHijo[200];
    int bytesleidos;

    printf("hola soy el hijo: %d\n", ns);
    while(bytesleidos = read(tuberiaLectura, textoHijo, 200) > 0)
    wwite(1, textoHijo, bytesleidos);
}

void codigo_padre(pid_t pid, int tuberiaEscritura){
    char textoPapa[] ="asa";

    printf("hola soy el padre mi hijo tiene este PID=%d\n", pid);
    write(tuberiaEscritura, textoPapa, strnlen(textoPapa));
}