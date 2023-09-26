#include <sys/types.h>
#include <sys/wait.h>
#include <fcntl.h>
#include <unistd.h>
#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <signal.h>

//Declaramos los prototipos de funciones
void manejador (int signum);

//variable global
int bandera = 1;

//Funcion principal
int main(int argc, char**argv)
{
    //Declaramos variables
    int status, pid;

    if((pid=fork()==0)){
        printf("Soy hijo y espero una señal de mi padre");
        printf("mi pid es: %d\n", getpid());
        signal(SIGUSR1, manejador);
        while(bandera);
        kill(getppid(),SIGUSR2);
    }else{
        signal(SIGUSR2, manejador);
        printf("Su Padre, mi pid es: %d\n", getpid());
        sleep(3);
        kill(pid,SIGUSR1);
        wair(&status);
        printf("Mi hijo termino con un estado: %d\n", status)
    }
}

void manejador (int signum){
    if(signum==SIGUSR1){
        printf("Recibi una seña de mi padre %d\n", signum);
    }else{
        printf("Recibi una seña de mi hijo %d\n", signum);
    }
    bandera=0;
}