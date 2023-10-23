#include <sys/types.h>
#include <sys/wait.h>
#include <fcntl.h>
#include <unistd.h>
#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <time.h>
#include <signal.h>

//Constantes
#define SIZE 512

//Funciones prototipo
void codigo_hijo(int);
void codigo_padre(pid_t);
void manejador(int signum);

//Variables globales
int t[2], t2[2];
char mensaje[SIZE];

int main(void){
    pid_t pid;
    int readbytes, random;
    char buffer[SIZE];

    pipe(t); 
    pipe(t2); //2 tuberia

    signal(SIGUSR1, manejador);
    signal(SIGUSR2, manejador);


    srand(time(NULL)); //Altero la semilla de numeros aleatorios en base al tiempo
    random=rand() % 101; //numero entre 0 y 100

    pid = fork();
    switch(pid){
        case -1: 
            printf("No existo");
            exit(-1);
            break;

        case 0:
            close (t2[1]);//Lee hijo
            close (t[0]); //escribe hijo
            codigo_hijo(random);
            break;

        default:
            close (t2[0]);//EScribe padre
            close (t[1]); //lee padre
            sleep(1);
            codigo_padre(pid);
            break;
    }
    return 0;
}

void codigo_hijo(int random){
    char textoHijo[200];
    int bytesleidos;

    printf("hola soy el hijo: %d\n", random);

    while(bytesleidos = read(t2[0], textoHijo, 200) > 0)
    write(1, textoHijo, bytesleidos);

     close(t2[0]);
}

void codigo_padre(pid_t pid){

    strcpy(mensaje, "HOLA HIJO");
    write(t2[1], mensaje, strlen(mensaje));

    //ENVIA SEÑAL AL HIJO POR EL MANEJADOR
    kill(pid,SIGUSR1);

    //ESPERA A QUE EL HIJO TERMINE
    waitpid(pid, NULL, 0);

    close(t2[1]);
}

void manejador (int signum){
    int bytesrecibidos;
    if(signum==SIGUSR1) //Señal que envia el padre al hijo
    {
        printf("Soy el hijo, He recibido esto\n");
        while(bytesrecibidos = read(t2[0], mensaje, SIZE)>0)
            write(1, mensaje, bytesrecibidos);

    }else if(signum==SIGUSR2) //Señal que envia el hijo al padre
    {
    }else{
        printf("Recibi una seña desconocida\n");
    }

}