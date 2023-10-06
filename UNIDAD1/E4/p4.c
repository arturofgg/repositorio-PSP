#include <stdio.h>
#include <sys/types.h>
#include <unistd.h>

int main(void){
    printf("SOY EL PADRE Y MI PID ES: %d\n", getpid()); //PROCESO BASE

    pid_t pid = fork();
//instrucciones que tanto el apdre como el hijo harán.
//EN EL PROCESO ORIGINAL (EL PADRE) SU VALOR ES DISTINTO DE 0 Y EL PROCESO HIJO EL VALOR ES 0

    if (pid > 0) //PROCESO PADRE
    {
        //instrucciones que solo el padre hará
        printf("PADRE! Soy el proceso padre y mi pid sigue siendo %d\n", getpid());
        printf("PADRE! Mi hijo tiene pid: %d\n", pid);
    }else //PROCESO HIJO
    {
        //intrucciones que solo el hijo hará, -1 es error
        printf("HIJO! Soy el proceso hijo y mi pid sigue siendo %d\n", getpid());
        printf("HIJO! Mi padre tiene pid: %d\n", getppid());
    }
}