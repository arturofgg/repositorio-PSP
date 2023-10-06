#include <stdio.h>
#include <unistd.h>
int main() 
{
fork(); //duplica el programa a partir de este punto
printf("Hola mundo!\n");
return 0; //Comprobar si hay error o no
}
