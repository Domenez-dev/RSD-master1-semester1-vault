#include <stdio.h>
#include <pthread.h>
#include <semaphore.h>
#include <unistd.h>

// Shared variable
int shared_counter = 0;

// Declare the semaphore
sem_t semaphore;

// Function executed by the first thread
void* thread1_func(void* arg) {
    for (int i = 0; i < 3; i++) {
        sem_wait(&semaphore); // Lock the semaphore
        printf("Thread 1: Current value: %d\n", shared_counter);
        shared_counter++;
        printf("Thread 1: New value: %d\n", shared_counter);
        sem_post(&semaphore); // Unlock the semaphore
        sleep(1); // Simulate some work
    }
    return NULL;
}

// Function executed by the second thread
void* thread2_func(void* arg) {
    for (int i = 0; i < 3; i++) {
        sem_wait(&semaphore); // Lock the semaphore
        printf("Thread 2: Current value: %d\n", shared_counter);
        shared_counter--;
        printf("Thread 2: New value: %d\n", shared_counter);
        sem_post(&semaphore); // Unlock the semaphore
        sleep(1); // Simulate some work
    }
    return NULL;
}

int main() {
    pthread_t thread1, thread2;

    // Initialize the semaphore with an initial value of 1 (binary semaphore)
    sem_init(&semaphore, 0, 1);

    // Create two threads
    pthread_create(&thread1, NULL, thread1_func, NULL);
    pthread_create(&thread2, NULL, thread2_func, NULL);

    // Wait for both threads to finish
    pthread_join(thread1, NULL);
    pthread_join(thread2, NULL);

    // Destroy the semaphore
    sem_destroy(&semaphore);

    printf("Main thread: Final value of shared_counter: %d\n", shared_counter);
    printf("Main thread: Program finished.\n");
    return 0;
}
