#include <stdio.h>
#include <sys/sem.h>
#include <unistd.h>
#include <sys/ipc.h>

int main() {
  key_t key = ftok("/tps", 'c');

  if (key == 1){
    printf("error fork\n");
    return -1;

  }else{
    int semid = semget(key,3,ipc_create|ipc_excl|0666);

    if (semid == -1){
      printf("existe deja avec id %d\n", segmet(key,3,0));
    }else {
      printf("creer avec id %d\n", semid;
    }
  }  
  return 0;
}

