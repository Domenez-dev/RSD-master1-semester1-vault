
#include "semaphore.h"
#include <stdio.h>
#include <sys/shm.h>
#include <unistd.h>

typedef struct data {
  int oxygen;
  int hydrogen;
  int cpt;
} sdata;

void bondHydrogen() { printf("H2O formed with Hydro PID: %d\n", getpid()); }

void barriere(int n, sdata *sd, int semid) {
  p(semid, 0);
  sd->cpt++;

  printf("valeur du compteur : %d\n", sd->cpt);
  if (sd->cpt < n) {
    v(semid, 0);
    printf("Blocked at barrier waiting for 1Hydrogen or 1Oxygen...\n");
    p(semid, 3);
  } else {
    sd->cpt = 0;
    v(semid, 0);

    for (int i = 0; i < n - 1; i++) {
      v(semid, 3);
    }
    v(semid, 2);
    v(semid, 1);
    v(semid, 1);
  }
}

int main() {
  key_t key = ftok(".", 'a');
  int shmid = shmget(key, sizeof(sdata), IPC_CREAT | IPC_EXCL | 0666);
  int semid = create_sm(3, key);

  if (shmid < 0) { // la zone existe deja
    shmid = shmget(key, sizeof(sdata), 0);
    printf("Memory segment already exists with ID (%d)\n", shmid);
  } else {
    printf("New memory segment created with ID (%d)\n", shmid);
  }
  sdata *sd = NULL;
  sd = shmat(shmid, sd, 0);

  p(semid, 0);
  sd->hydrogen = sd->hydrogen + 1;
  if ((sd->hydrogen >= 2) && (sd->oxygen >= 1)) {
    sd->hydrogen = sd->hydrogen - 2;
    sd->oxygen = sd->oxygen - 1;
  }

  v(semid, 0);
  p(semid, 1);

  barriere(3, sd, semid);
  bondHydrogen();
}
