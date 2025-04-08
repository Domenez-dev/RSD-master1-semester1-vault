#include <stdio.h>
#include <sys/ipc.h>
#include <sys/sem.h>
#include <unistd.h>

void seminit (int idsem, int numsem, int initval) {
  struct sembuf sb = {numsem, 0, 0};
  semctl(idsem, numsem, SETVAL, initval);
}

void P (int semid, int numsem){
  struct sembuf sb = {numsem, -1, 0};
  semop(semid, &sb, 1);
}

void V (int semid, int numsem){
  struct sembuf sb = {numsem, 1, 0};
  semop(semid, &sb, 1);
}

void main() {
  key_t key = ftok("/tps", 'a');
  if (key == 1){
    printf("error fork\n");

  }else{
    int semid = semget(key,4,IPC_CREAT|IPC_EXCL|0666);
    if (semid == -1){
      perror("semget: ");
      semid = semget(key, 4, 0);
      printf("existe deja avec id %d\n", semget(key,3,0));
    }else {
      printf("creer avec id %d\n", semid);
    }
    // for (int i = 0; i<4; i++){
    //   seminit(semid, i, 1);
    // }
    int array[5] = {1,1,1,1};
    semctl(semid, 0, SETALL, array);
    printf("\npid is: %d", semctl(semid, 0, SETALL, array));
    
    int s = semctl(semid, 0, GETALL);
    
    for(int loop = 0; loop < 4; loop++){
        printf("\n%d", s[loop]);
    }
  }
}

