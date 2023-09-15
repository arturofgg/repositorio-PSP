#include <stdio.h>
#include <unistd.h>
void main(void) {
printf("Hola mundo. Esta carpeta contiene:\n");
execl ("/bin/ls","ls","-l",(char*) NULL);
printf("Esto nunca llega a ejecutarse");
}
