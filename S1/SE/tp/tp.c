#include <stdio.h>
#include <sys/sem.h>
#include <unistd.h>
#include <sys/ipc.h>

int main() {
  key_t key = ftok("/tps", 'a');

  if (key == 1){
    printf("error fork\n");
    return -1;

  }else{
    int semid = semget(key,4,IPC_CREAT|IPC_EXCL|0666);

    if (semid == -1){
      printf("existe deja avec id %d\n", semget(key,3,0));
    }else {
      printf("creer avec id %d\n", semid);
    }
  }
  return 0;
}

