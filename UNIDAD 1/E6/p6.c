#include <sys/types.h>
#include <sys/wait.h>

#include <fcntl.h>
#include <unistd.h>
#include <stdio.h>
#include <string.h>
#include <stdlib.h>

#define SIZE 512


int main(void){
    pid_t pid;
    int p[2], p2[2], readbytes;
    char buffer[SIZE];
    pipe(p); //Se crea la tuberia
    pipe(p2); //2 tuberia

    if((pid=fork()) == 0 ){
        //hijo
        close (p[1]); // cerramos el lado de la escritura del pipe
        close (p2[0]); 

        while((readbytes=read(p[0], buffer, SIZE)) > 0)
        write(1, buffer, readbytes); //Pantalla -> HIJO LEE LA ESCRITURA DEL PADRE
        close (p[0]);

        strcpy(buffer, "Esto llega a traves de la tuberia (HIJO ESCRIBE) \n");
        write(p2[1], buffer, strlen(buffer));
        close (p2[1]);

        
    }else{
        //padre
        close (p[0]); //cerramos el lado de lectura del pipe
        close (p2[1]); 

        strcpy(buffer, "Esto llega a traves de la tuberia (PADRE ESCRIBE) \n");
        write(p[1], buffer, strlen(buffer));
        close(p[1]);

        while((readbytes=read(p2[0], buffer, SIZE)) > 0)
        write(1, buffer, readbytes); //Pantalla -> PADRE LEE LA ESCRITURA DEL HIJO
        close(p2[0]);
    }

waitpid(pid, NULL, 0);
exit(0);
}