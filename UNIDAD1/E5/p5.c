#include <stdio.h>
#include <unistd.h>

int main(void){
    int x = 1;
    
    if (fork()==0)
    {
        printf("HIJO! %d\n",++x);
    }else 
    {
        printf("PADRE! %d\n",--x);
    }
}