#include <stdio.h>
#include <sys/ipc.h>
#include <sys/sem.h>

int create_sm(int nsems, key_t key) {
  int semid = semget(key, nsems, IPC_CREAT | IPC_EXCL | 0666);
  if (semid < 0) {
    semid = semget(key, nsems, 0);
    printf("Semaphor group already exists with id (%d)\n", semid);
  } else
    printf("New semaphore group created with id (%d)\n", semid);
  return semid;
}

void init_sm(int semid, int semnum, int val) {
    if ( semctl(semid, semnum, SETVAL, val)< 0) {
    perror("Init semctl failed\n");
  } else {
    printf("Init semctl successful\n");
  }
}

void p(int semid, int semnum) {
  struct sembuf sb;
  sb.sem_num = semnum;
  sb.sem_op = -1;
  sb.sem_flg = 0;
  if (semop(semid, &sb, 1) < 0) {
    perror("P semop failed\n");
  } else {
    printf("P semop executed successfully\n");
  }
}

void v(int semid, int semnum) {
  struct sembuf sb;
  sb.sem_num = semnum;
  sb.sem_op = 1;
  sb.sem_flg = 0;
  if (semop(semid, &sb, 1) < 0) {
    perror("V semop failed\n");
  } else {
    printf("V semop executed successfully\n");
  }
}

void z(int semid, int semnum) {
  struct sembuf sb;
  sb.sem_num = semnum;
  sb.sem_op = 0;
  sb.sem_flg = 0;
  if (semop(semid, &sb, 1) < 0) {
    perror("Z semop failed\n");
  } else {
    printf("Z semop executed successfully\n");
  }
}

void sem_destroy(int semid) {
  if (semctl(semid, 0, IPC_RMID) < 0) {
    perror("semctl IPC_RMID failed\n");
  } else {
    printf("Semaphore set removed successfully.\n");
  }
}
