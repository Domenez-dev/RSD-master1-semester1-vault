#include <stdio.h>
#include <pthread.h>
#include <semaphore.h>
#include <unistd.h>

// Semaphores and shared variables
sem_t resource_access;  // Controls access to the shared resource
sem_t read_count_access;  // Synchronizes updates to the reader count
int read_count = 0;  // Number of active readers

// File path
const char* file_path = "shared_file.txt";

// Reader thread function
void* reader_func(void* arg) {
    int reader_id = *((int*)arg);
    for (int i = 0; i < 2; i++) {  // Each reader reads twice
        sem_wait(&read_count_access);  // Lock access to read_count
        read_count++;
        if (read_count == 1) {
            sem_wait(&resource_access);  // First reader locks resource
        }
        sem_post(&read_count_access);  // Unlock read_count_access

        // Read from the file
        printf("Reader %d: Reading from file...\n", reader_id);
        FILE* file = fopen(file_path, "r");
        if (file) {
            char buffer[256];
            while (fgets(buffer, sizeof(buffer), file)) {
                printf("Reader %d read: %s", reader_id, buffer);
            }
            fclose(file);
        } else {
            printf("Reader %d: Failed to open file.\n", reader_id);
        }
        sleep(1);  // Simulate reading time

        sem_wait(&read_count_access);  // Lock access to read_count
        read_count--;
        if (read_count == 0) {
            sem_post(&resource_access);  // Last reader unlocks resource
        }
        sem_post(&read_count_access);  // Unlock read_count_access
        sleep(1);  // Simulate time between reads
    }
    return NULL;
}

// Writer thread function
void* writer_func(void* arg) {
    int writer_id = *((int*)arg);
    for (int i = 0; i < 2; i++) {  // Each writer writes twice
        sem_wait(&resource_access);  // Lock resource for writing

        // Write to the file
        printf("Writer %d: Writing to file...\n", writer_id);
        FILE* file = fopen(file_path, "a");
        if (file) {
            fprintf(file, "Writer %d was here.\n", writer_id);
            fclose(file);
        } else {
            printf("Writer %d: Failed to open file.\n", writer_id);
        }
        sleep(2);  // Simulate writing time

        sem_post(&resource_access);  // Unlock resource
        sleep(2);  // Simulate time between writes
    }
    return NULL;
}

int main() {
    pthread_t readers[3], writers[2];
    int reader_ids[3] = {1, 2, 3};
    int writer_ids[2] = {1, 2};

    // Initialize semaphores
    sem_init(&resource_access, 0, 1);
    sem_init(&read_count_access, 0, 1);

    // Create reader threads
    for (int i = 0; i < 3; i++) {
        pthread_create(&readers[i], NULL, reader_func, &reader_ids[i]);
    }

    // Create writer threads
    for (int i = 0; i < 2; i++) {
        pthread_create(&writers[i], NULL, writer_func, &writer_ids[i]);
    }

    // Wait for all threads to finish
    for (int i = 0; i < 3; i++) {
        pthread_join(readers[i], NULL);
    }
    for (int i = 0; i < 2; i++) {
        pthread_join(writers[i], NULL);
    }

    // Destroy semaphores
    sem_destroy(&resource_access);
    sem_destroy(&read_count_access);

    printf("Main thread: Program finished.\n");
    return 0;
}
